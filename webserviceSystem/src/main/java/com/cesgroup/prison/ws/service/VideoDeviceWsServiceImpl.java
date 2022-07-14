package com.cesgroup.prison.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cesgroup.prison.ws.dao.VideoDeviceWsMapper;

@WebService(endpointInterface="com.cesgroup.prison.ws.service.VideoDeviceWsService")
public class VideoDeviceWsServiceImpl implements VideoDeviceWsService {
	
	private SerializerFeature[] SerializerFeatures = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty};
	
	@Resource
	private VideoDeviceWsMapper videoDeviceWsMapper;
		
	@Override
	public String getVideoDeviceList(String cusNumber) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("type", "0");
		
		List<Map<String, Object>> videoDeviceList = videoDeviceWsMapper.getVideoDeviceList(paramMap);
		return JSON.toJSONString(videoDeviceList, SerializerFeatures);
	}

	@Override
	public String getVideoDeviceListForTime(String cusNumber, String updateDate) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("updateDate", updateDate);
		paramMap.put("type", "1");
		
		List<Map<String, Object>> videoDeviceList = videoDeviceWsMapper.getVideoDeviceList(paramMap);
		return JSON.toJSONString(videoDeviceList, SerializerFeatures);
	}
}