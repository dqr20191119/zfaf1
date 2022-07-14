package com.cesgroup.prison.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cesgroup.prison.ws.dao.TalkWsMapper;

@WebService(endpointInterface="com.cesgroup.prison.ws.service.TalkWsService")
public class TalkWsServiceImpl implements TalkWsService {
	
	private SerializerFeature[] SerializerFeatures = new SerializerFeature[]{SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty};
	
	@Resource
	private TalkWsMapper talkWsMapper;
	
	@Override
	public String getTalkList(String cusNumber) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("type", "0");
		
		List<Map<String, Object>> alertorList = talkWsMapper.getTalkList(paramMap);
		return JSON.toJSONString(alertorList, SerializerFeatures);
	}

	@Override
	public String getTalkListForTime(String cusNumber, String updateDate) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("updateDate", updateDate);
		paramMap.put("type", "1");
		
		List<Map<String, Object>> alertorList = talkWsMapper.getTalkList(paramMap);		
		return JSON.toJSONString(alertorList, SerializerFeatures);
	}
}