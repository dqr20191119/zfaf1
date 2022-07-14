package com.cesgroup.prison.broadcastPlan.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.broadcastPlan.entity.BroadcastPlan;
import com.cesgroup.prison.broadcastPlan.service.BroadcastPlanService;
import com.cesgroup.prison.broadcastPlan.service.impl.BroadcastPlanServiceImpl;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;
import com.cesgroup.prison.jfsb.service.BroadcastFileService;
import com.cesgroup.prison.jfsb.service.BroadcastService;
import com.cesgroup.prison.jfsb.service.impl.BroadcastFileServiceImpl;
import com.cesgroup.prison.jfsb.service.impl.BroadcastServiceImpl;

public class BroadcastPlanCommom {
	private static BroadcastFileService broadcastFileService = (BroadcastFileService) SpringContextUtils.getBean(BroadcastFileServiceImpl.class);	
	
	private static BroadcastService broadcastService = (BroadcastService) SpringContextUtils.getBean(BroadcastServiceImpl.class);	
	
	private static BroadcastPlanService broadcastPlanService = (BroadcastPlanService) SpringContextUtils.getBean(BroadcastPlanServiceImpl.class);
	
	public static String broadcastFileNameCommbox() {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		List<BroadcastFile> bfList =null;
		List<Map> resList = new ArrayList<Map>();
		try {
			bfList = broadcastFileService.queryByBfdCusNumber(user.getCusNumber());
		} catch (BusinessLayerException e) {
			e.printStackTrace();
		}
		if(bfList != null && bfList.size() > 0) {
			for(BroadcastFile bf : bfList) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("value", bf.getId());
    			map.put("text", bf.getBfdName());
    			resList.add(map);
    		}
		}
		String jsonString = JSON.toJSONString(resList);
		return jsonString;
	}
	
	
	
	public static String broadcastNameCommbox() {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		List<BroadcastEntity> bfList =null;
		List<Map> resList = new ArrayList<Map>();
		try {
			bfList = broadcastService.queryByBfdCusNumber(user.getCusNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(bfList != null && bfList.size() > 0) {
			for(BroadcastEntity bf : bfList) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("value", bf.getId());
    			map.put("text", bf.getBbdName());
    			resList.add(map);
    		}
		}
		String jsonString = JSON.toJSONString(resList);
		return jsonString;
	}
	
	public static String broadcastPlanNameCommbox() {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		List<BroadcastPlan> bfList =null;
		List<Map> resList = new ArrayList<Map>();
		try {
			bfList = broadcastPlanService.queryByBfdCusNumber(user.getCusNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(bfList != null && bfList.size() > 0) {
			for(BroadcastPlan bf : bfList) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("value", bf.getId());
    			map.put("text", bf.getDbpBroadcastPlanName());
    			resList.add(map);
    		}
		}
		String jsonString = JSON.toJSONString(resList);
		return jsonString;
	}
	
}
