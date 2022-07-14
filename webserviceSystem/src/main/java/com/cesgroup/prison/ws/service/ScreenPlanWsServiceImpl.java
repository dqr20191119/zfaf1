package com.cesgroup.prison.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cesgroup.prison.ws.dao.ScreenPlanWsMapper;

@WebService(endpointInterface="com.cesgroup.prison.ws.service.ScreenPlanWsService")
public class ScreenPlanWsServiceImpl implements ScreenPlanWsService {

	private SerializerFeature[] SerializerFeatures = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty};
	
	@Resource
	private ScreenPlanWsMapper screenPlanWsMapper;

	@Override
	public String getScreenPlanList(String cusNumber, String screenPlanId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("screenPlanId", screenPlanId);
		
		List<Map<String, Object>> screenPlanAreaList = screenPlanWsMapper.getScreenPlanAreaListByPalnId(paramMap);
		
		if(screenPlanAreaList != null && !screenPlanAreaList.isEmpty()) {
			for (Map<String, Object> map : screenPlanAreaList) {
				String cusNumber_ = (String) map.get("cusNumber");
				String screenPlanId_ = (String) map.get("screenPlanId");
				String screenPlanAreaId = (String) map.get("screenPlanAreaId");
				
				Map<String, Object> paramMap_ = new HashMap<String, Object>();
				paramMap_.put("cusNumber", cusNumber_);
				paramMap_.put("screenPlanId", screenPlanId_);
				paramMap_.put("screenPlanAreaId", screenPlanAreaId);
				
				List<Map<String, Object>> screenPlanWindowList = screenPlanWsMapper.getScreenPlanWindowListByAreaId(paramMap_);
				map.put("screenPlanWindowList", screenPlanWindowList);

				List<Map<String, Object>> screenPlanCameraList = screenPlanWsMapper.getScreenPlanCameraListByAreaId(paramMap_);
				map.put("screenPlanCameraList", screenPlanCameraList);
			}
		}
		
		return JSON.toJSONString(screenPlanAreaList, SerializerFeatures);
	}
}