package com.cesgroup.prison.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cesgroup.prison.ws.dao.CameraWsMapper;

@WebService(endpointInterface="com.cesgroup.prison.ws.service.CameraWsService")
public class CameraWsServiceImpl implements CameraWsService {
		
	private SerializerFeature[] SerializerFeatures = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty};
	
	@Resource
	private CameraWsMapper cameraWsMapper;
	
	@Override
	public String getCameraList(String cusNumber) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("type", "0");
		
		List<Map<String, Object>> cameraList = cameraWsMapper.getCameraList(paramMap);
		return JSON.toJSONString(cameraList, SerializerFeatures);
	}

	@Override
	public String getCameraListForTime(String cusNumber, String updateDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("updateDate", updateDate);
		paramMap.put("type", "1");
		
		List<Map<String, Object>> cameraList = cameraWsMapper.getCameraList(paramMap);
		return JSON.toJSONString(cameraList, SerializerFeatures);
	}
}