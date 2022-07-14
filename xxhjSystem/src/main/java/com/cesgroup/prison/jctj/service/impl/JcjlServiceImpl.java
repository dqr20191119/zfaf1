package com.cesgroup.prison.jctj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jctj.dao.JcjlDao;
import com.cesgroup.prison.jctj.entity.JcjlEntity;
import com.cesgroup.prison.jctj.service.JcjlService;
import com.cesgroup.prison.utils.PageUtil;
import com.cesgroup.prison.utils.PagerModel;
import com.cesgroup.prison.zfxx.zfdm.dao.ZfZwdmDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfZwdm;

@Service("jcjlService")
public class JcjlServiceImpl  extends BaseDaoService<JcjlEntity, String,JcjlDao> implements JcjlService {

	@Autowired
	private JcjlDao jcjlDao;
	@Autowired
	private ZfZwdmDao zfZwdmDao;

	public static String SZ_ST = "食堂"; 
	
	public static String SZ_CJ = "车间";
	
	public static String SZ_LWL = "劳务楼";
	
	public static String SZ_JS = "监舍";
	
	public static String SZ_GGQY = "公共区域";
	
	public static String SZ_JQ = "监区";
	
	public static String SZ_HDDT = "活动大厅";
	
	public static String SZ_JXL = "教学楼";
	
	@Override
	public PagerModel getJctj(Map<String, Object> param, int page, int rows) {
		String today = (String)param.get("time");
		List<Map<String, Object>> ltstResult = new ArrayList<Map<String, Object>>();
		int total = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat sdfHm = new SimpleDateFormat("HH:mm");
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String userLevel = user.getUserLevel().toString();
			if("1".equals(userLevel)||"2".equals(userLevel)){//省局、监狱用户
				param.put("cusNumber", user.getOrgCode());
			}else if("3".equals(userLevel)){//监区
				param.put("cusNumber", user.getOrgCode());
				param.put("deptCode", user.getDprtmntCode());
			}
			param.put("begin", PageUtil.getBegin(page, rows));
			param.put("end", PageUtil.getEnd(page, rows));
			total = PageUtil.getTotal(jcjlDao.getQbjcjlTotal(param));
			List<Map<String, Object>> list = jcjlDao.getQbjcjl(param);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				String zfXm = (String) map.get("XM");
				String cs  = (String) map.get("CHANNEL_NAME");
				String time = (String) map.get("ST");
				if (cs.indexOf(SZ_ST)!=-1) {
					cs = SZ_ST;
				} else if (cs.indexOf(SZ_CJ)!=-1 || cs.indexOf(SZ_LWL)!=-1) {
					cs = SZ_CJ;
				} else if (cs.indexOf(SZ_JS)!=-1) {
					cs = SZ_JS;
				} else if (cs.indexOf(SZ_HDDT)!=-1) {
					cs = SZ_HDDT;
				} else if (cs.indexOf(SZ_JQ)!=-1) {
					cs = SZ_JQ;
				} else if (cs.indexOf(SZ_JXL)!=-1) {
					cs = SZ_JXL;
				} else {
					cs = SZ_GGQY;
				}
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("zfXm", zfXm);
				temp.put("jqName", user.getDprtmntName());
				temp.put("cs", cs);
				if (today == null) {
					temp.put("time", time);
				} else {
					temp.put("time", sdfHm.format(sdf.parse(time)));
				}
				ltstResult.add(temp);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		PagerModel pm = new PagerModel(total, ltstResult, page, rows);
		return pm;
	}

	@Override
	public Map<String, Object> getZfszqytj() {
		List<ZfZwdm> zfZwdmList = new ArrayList<ZfZwdm>();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		zfZwdmList = zfZwdmDao.findByDmlxAndCorpnameAndDeptnameOrderByDtdmsjDesc(null, user.getCusNumber(), user.getDprtmntCode());
		Map<String, Object> map = null;
		Map<String, Object>  mapReturn = new HashMap<String, Object>();
		List<Map<String, Object>>  listReturn = new ArrayList<Map<String, Object>>();
		int mod = Math.abs(user.getDprtmntCode().hashCode()) % 5;
		int i = 1;
		if (zfZwdmList != null && zfZwdmList.size() > 0) {
			for (ZfZwdm temp : zfZwdmList) {
				map = new HashMap<String, Object>();
				map.put("jz", temp.getDeptname());//监组
				map.put("jzzrs", temp.getZrs());//监组总人数
				if (i % mod == 0 && temp.getZrs() > 0 ) {
					map.put("jsrs", 1);//监舍人数
					map.put("ldcsrs",  temp.getZrs() - 1);//劳动场所人数
				} else {
					map.put("jsrs", 0);//监舍人数
					map.put("ldcsrs",  temp.getZrs());//劳动场所人数
				}
				listReturn.add(map);
				i++;
			}
		}
		mapReturn.put("mx", listReturn);
		return mapReturn;
	}

}
