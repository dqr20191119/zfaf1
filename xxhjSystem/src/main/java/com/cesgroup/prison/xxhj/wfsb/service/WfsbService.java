package com.cesgroup.prison.xxhj.wfsb.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.wfsb.entity.Wfsb;

public interface WfsbService extends IBaseCRUDService<Wfsb, String> {
	 
	public List<Map<String, String>> listPhysicalDeviceCount(String pdeCusNumber);
 	
 	public List<Map<String, String>> listPhysicalDeviceCountPrisonList(String pdeDeviceName, String obdParentId) throws Exception;
}

