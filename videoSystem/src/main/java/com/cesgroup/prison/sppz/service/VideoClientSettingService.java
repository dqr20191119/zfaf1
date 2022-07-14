package com.cesgroup.prison.sppz.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.sppz.entity.VideoClient;

public interface VideoClientSettingService {

	public Page<Map<String,String>> searchVideoClient(VideoClient videoClient_param,PageRequest pageRequest);
	//局部修改
	public void updatePart(VideoClient videoClient_param)  throws Exception;
}
