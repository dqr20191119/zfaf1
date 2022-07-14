package com.cesgroup.prison.wfsb.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.wfsb.entity.PhysicalDevice;
import com.cesgroup.prison.wfsb.entity.PoliceDevice;

public interface IPhysicalDeviceService {

	public Page<Map<String,String>> searchPhysicalDevice(PhysicalDevice physicalDevice_param,PageRequest pageRequest);
	//局部修改
	public void updatePart(PhysicalDevice physicalDevice_param);
}
