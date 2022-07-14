package com.cesgroup.prison.sppz.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.sppz.entity.VideoDevice;

public interface IVideoDeviceService {
	public Page<Map<String,String>> searchVideoDevice(VideoDevice videoDevice_param,PageRequest pageRequest);
	
	/**
	 * 供前置机统一拉取单位下的所有设备信息，业务平台慎用
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> searchVideoDevice(VideoDevice videoDevice_param);	
	
	public List<Map<String,Object>> simpleVideoDeviceList(String vdiCusNumber);
}
