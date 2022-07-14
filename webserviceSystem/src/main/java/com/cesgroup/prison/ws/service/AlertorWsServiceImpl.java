package com.cesgroup.prison.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cesgroup.prison.ws.dao.AlertorWsMapper;

@WebService(endpointInterface="com.cesgroup.prison.ws.service.AlertorWsService")
public class AlertorWsServiceImpl implements AlertorWsService {
	
	private SerializerFeature[] SerializerFeatures = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty};
	
	@Resource
	private AlertorWsMapper alertorWsMapper;
	
	@Override
	public String getAlertorList(String cusNumber) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("type", "0");
		
		List<Map<String, Object>> alertorList = alertorWsMapper.getAlertorList(paramMap);
		return JSON.toJSONString(alertorList, SerializerFeatures);
	}

	@Override
	public String getAlertorListForTime(String cusNumber, String updateDate) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("updateDate", updateDate);
		paramMap.put("type", "1");
		
		List<Map<String, Object>> alertorList = alertorWsMapper.getAlertorList(paramMap);		
		return JSON.toJSONString(alertorList, SerializerFeatures);
	}

	@Override
	public String getAlertorListByType(String cusNumber, String type) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("type", type);
		
		List<Map<String, Object>> alertorList = alertorWsMapper.getAlertorListByType(paramMap);
		return JSON.toJSONString(alertorList, SerializerFeatures);
	}
}