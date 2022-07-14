package com.cesgroup.prison.wfsb.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.wfsb.entity.PoliceDevice;

public interface IPoliceDeviceService {
	public Page<Map<String,String>> searchPoliceDevice(PoliceDevice policeDevice_param,PageRequest pageRequest);
	
	//局部修改
	public void updatePart(PoliceDevice policeDevice_param);
}
