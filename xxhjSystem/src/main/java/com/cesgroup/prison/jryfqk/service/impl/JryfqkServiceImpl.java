package com.cesgroup.prison.jryfqk.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jryfqk.dao.JryfqkDao;
import com.cesgroup.prison.jryfqk.entity.JryfqkEntity;
import com.cesgroup.prison.jryfqk.service.JryfqkService;

@Service("jryfqkService")
public class JryfqkServiceImpl  extends BaseDaoService<JryfqkEntity, String,JryfqkDao> implements JryfqkService {

	@Autowired
	private JryfqkDao jryfqkDao;
	
	@Override
	public List getJryafk() {
		// TODO Auto-generated method stub
		List listA = new ArrayList();
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String jyId = user.getOrgCode();
		//暂予监外执行、社会医院、寄押、离监探亲、脱逃捕回、解回再审
		Map mappra = new HashMap();
		
		
		int level = AuthSystemFacade.whatLevelForLoginUser();
		String jqName="";
		if(level == 1) {
			mappra.put("jyId", AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG);
		}else if(level == 2) {
			mappra.put("jyId", jyId);
		}else {
			mappra.put("jyId", jyId);
			jqName = user.getDprtmntName();
		} 
		mappra.put("jqName", jqName);
		
		System.out.println("********************************"+jyId+":"+jqName);
		List<Map <String, Object>> list = jryfqkDao.getJryafq(mappra);
		
		
		List listcf = new ArrayList();
		for(int i = 0; i < list.size(); i++){
			Map<String, Object> mapList = list.get(i);
			String key = (String) mapList.get("ITEMS_ID");
			BigDecimal b =  (BigDecimal) mapList.get("ITEMS_VALUE");
			Date time = (Date) mapList.get("D_URL_TIME");
			String value = b.toString();
			Map<String, Object> map = new HashMap();
			if(listcf.contains(key)){
				continue;
			}
			map.put("key", key);
			map.put("value", value);
			map.put("time", sdf.format(time));
			listcf.add(key);
			listA.add(map);
		}
		List<Map <String, Object>> listzfdd = jryfqkDao.getZfddry(mappra);
		for(int i = 0; i < listzfdd.size(); i++){
			Map<String, Object> mapList = listzfdd.get(i);
			String key = (String) mapList.get("ITEMS_ID");
			BigDecimal b =  (BigDecimal) mapList.get("ITEMS_VALUE");
			Date time = (Date) mapList.get("D_URL_TIME");
			String value = b.toString();
			Map<String, Object> map = new HashMap();
			if(listcf.contains(key)){
				continue;
			}
			map.put("key", key);
			map.put("value", value);
			map.put("time", sdf.format(time));
			listcf.add(key);
			listA.add(map);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return listA;
	}

	@Override
	public Page<Map<String, Object>> findList(HttpServletRequest request,Pageable pageable) {
		// TODO Auto-generated method stub
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String jyId = user.getOrgCode();
		Map<String ,Object> map = new HashMap<String ,Object>();
		//int level = AuthSystemFacade.whatLevelForLoginUser();
		int level = Integer.parseInt(user.getUserLevel().toString());
		
		String jqName="";
		if(level == 1) {
			
		}else if(level == 2) {
			map.put("jyId", jyId);
		}else {
			map.put("jyId", jyId);
			jqName = user.getDprtmntName();
			map.put("jqName", jqName);
			map.put("jqId", user.getDprtmntCode());
		} 
		
		/**
		 * 统计获取的数据为对应当天的数据，业务数据为前一天的，所以此处往前推一天
		 */
		//map.put("urlTime", DateUtil.getDateString(DateUtil.getDateAfterOrBefore(DateUtil.getDate(request.getParameter("time")),-1),DateUtil.dashFormat));
		//DateUtil.getDateString(DateUtil.getDateAfterOrBefore(DateUtil.getDate(request.getParameter("time")),-1),DateUtil.dashFormat)
		Page<Map<String, Object>> page = null;
		String type = request.getParameter("type");
		map.put("myType", type);
		//<h4>在册罪犯:<span class="num" id="today1_modify"></span></h4>
		//<h4><span>在押:</span><span class="num" id="today9_modify"></span></h4>
		
		//<p class="custodyTitle alertType">监外就诊</p>
		// <p class="custodyNum alertNum" style="cursor: pointer;" id="today10_modify">0</p> 
		
		//<p class="custodyTitle alertType">监外住院</p>
		// <p class="custodyNum alertNum" style="cursor: pointer;" id="today37_modify">0</p>  
		page = jryfqkDao.findListAllCommon(map, pageable);
		
		/*if("zfWcjy".equals(type)){
			//外出就医
			page = jryfqkDao.findListWcjy(map, pageable);
		}else if("zfYwjy".equals(type)){
			//狱外寄押
			page = jryfqkDao.findListYwjy(map, pageable);
		}else if("zfTtrs".equals(type)){
			//脱逃
		}else if("zfTxlj".equals(type)){
			// 离监探亲
			page = jryfqkDao.findListLjtq(map, pageable);
		}else if("zfJwzx".equals(type)){
			//暂于监外执行
			page = jryfqkDao.findListZyjwzx(map, pageable);
		}else if("zfJhzs".equals(type)){
			//解回再审
			page = jryfqkDao.findListJhzs(map, pageable);
		}else if("zfSfrs".equals(type)){
			//释放
			page = jryfqkDao.findListSf(map, pageable);
		}else if("zfSyrs".equals(type)){
			//收押
			page = jryfqkDao.findListSy(map, pageable);
		}else if("zfDcrs".equals(type)){
			//调出
			page = jryfqkDao.findListDc(map, pageable);
		}else if("zfSwrs".equals(type)){
			//死亡
			page = jryfqkDao.findListSw(map, pageable);
		}else if("ZfQsrs".equals(type)){
			//遣送
		}else if("zfDrrs".equals(type)){
			//调入
			page = jryfqkDao.findListDr(map, pageable);
			
		}*/
		return page;
	}

	@Override
	public Page<Map<String, Object>> findListZaiCe(HttpServletRequest request, Pageable pageable) {
				UserBean user = AuthSystemFacade.getLoginUserInfo();
				String jyId = user.getOrgCode();
				Map<String ,Object> map = new HashMap<String ,Object>();
				//int level = AuthSystemFacade.whatLevelForLoginUser();
				int level = Integer.parseInt(user.getUserLevel().toString());
				Page<Map<String, Object>> page = null;
				String sj="1";
				if(level == 1) {
					map.put("sj", sj);
				}else if(level == 2) {
					map.put("jyId", jyId);
				}else {
					map.put("jyId", jyId);
					map.put("jqId", user.getDprtmntCode());
				} 
				page = jryfqkDao.findListZaiCe(map, pageable);
		return page;
	}
	@Override
	public Page<Map<String, Object>> findListZaiYa(HttpServletRequest request, Pageable pageable) {
				UserBean user = AuthSystemFacade.getLoginUserInfo();
				String jyId = user.getOrgCode();
				Map<String ,Object> map = new HashMap<String ,Object>();
				int level = Integer.parseInt(user.getUserLevel().toString());
				Date mytime = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				map.put("mytime", sdf.format(mytime));
				Page<Map<String, Object>> page = null;
				if(level == 1) {
					
				}else if(level == 2) {
					map.put("jyId", jyId);
				}else {
					map.put("jyId", jyId);
					map.put("jqId", user.getDprtmntCode());
				} 
				page = jryfqkDao.findListZaiYa(map, pageable);
		return page;
	}
}
