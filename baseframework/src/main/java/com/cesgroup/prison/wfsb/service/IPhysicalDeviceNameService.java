package com.cesgroup.prison.wfsb.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.wfsb.entity.PhysicalDevice;
import com.cesgroup.prison.wfsb.entity.PhysicalDeviceName;

public interface IPhysicalDeviceNameService {
	public Page<Map<String,String>> searchPhysicalDeviceName(PhysicalDeviceName physicalDeviceName_param,PageRequest pageRequest);
	
	public List<Map<String,Object>> simplePhysicalDeviceName(String pdnCusNumber);
	//局部修改
	public void updatePart(PhysicalDeviceName physicalDeviceName_param);
}
