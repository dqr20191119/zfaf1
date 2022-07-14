package com.cesgroup.prison.regionDepart.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.regionDepart.dao.RegionDepartMapper;
import com.cesgroup.prison.regionDepart.entity.RegionDepart;
import com.cesgroup.prison.regionDepart.service.RegionDepartService;
import com.cesgroup.prison.utils.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * 
 * @author linhe
 * @date 2018-3-9
 */
@Service
public class RegionDepartServiceImpl extends BaseService<RegionDepart,String> implements RegionDepartService {
	@Autowired
	private RegionDepartMapper mapper;
	/*
	 * 根据监狱编号和区域编号查询
	 * @see com.cesgroup.prison.regionDepart.service.RegionDepartService#findByCusNumberAndAreaId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<RegionDepart> findByCusNumberAndAreaId(String cusNumber, String areaId) {
		return this.mapper.findByCusNumberAndAreaId(cusNumber, areaId);
	}
	/*
	 * 批量添加
	 * @see com.cesgroup.prison.regionDepart.service.RegionDepartService#insertByBatch(java.util.List)
	 */
	@Override
	@Transactional
	public int insertByBatch(String objs) throws Exception {
		List<RegionDepart> list = new ArrayList<>();
		//当前登陆用户
		UserBean loginUser = AuthSystemFacade.getLoginUserInfo();
		//当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONArray array = JSONArray.fromObject(objs);
		List<?> list2 = JSONArray.toList(array, new RegionDepart(), new JsonConfig());
		for(int i=0; i<list2.size();i++) {
			RegionDepart regionDepart = (RegionDepart)list2.get(i);
			regionDepart.setId(CommonUtil.createUUID().replace("-", ""));
			regionDepart.setAdrCrteUserId(loginUser.getUserId());
			regionDepart.setAdrUpdtUserId(loginUser.getUserId());
			regionDepart.setAdrCrteTime(df.parse(df.format(new Date())));
			regionDepart.setAdrUpdtTime(df.parse(df.format(new Date())));
			if(regionDepart.getAdrDprtmntId()!=null && !"".equals(regionDepart.getAdrDprtmntId())){
				list.add(regionDepart);
			}
		}
		//批量删除
		RegionDepart rd = (RegionDepart)list2.get(0);
		this.mapper.deleteByCusNumberAndAreaId(rd.getAdrCusNumber(), rd.getAdrAreaId());
		//批量添加
		int result = 0;
		if(list.size()>0){
        	result = this.mapper.insertByBatch(list);
		}
        return result;
	}
	
}
