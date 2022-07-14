package com.cesgroup.prison.wfsb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wfsb.dao.PhysicalDeviceMapper;
import com.cesgroup.prison.wfsb.entity.PhysicalDevice;
import com.cesgroup.prison.wfsb.service.IPhysicalDeviceService;
@Service
public class PhysicalDeviceServiceImpl 
extends BaseDaoService<PhysicalDevice,String,PhysicalDeviceMapper> 
implements IPhysicalDeviceService{

	public Page<Map<String, String>> searchPhysicalDevice(PhysicalDevice physicalDevice_param,
			PageRequest pageRequest) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("physicalDevice", physicalDevice_param);
		return getDao().searchPhysicalDevice(map,pageRequest);
	}

	@Transactional
	public void updatePart(PhysicalDevice physicalDevice_param) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("physicalDevice", physicalDevice_param);
		getDao().updatePart(map);
	}
}
