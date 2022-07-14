package com.cesgroup.prison.wfsb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wfsb.dao.PoliceDeviceMapper;
import com.cesgroup.prison.wfsb.entity.PoliceDevice;
import com.cesgroup.prison.wfsb.service.IPoliceDeviceService;
@Service
public class PoliceDeviceServiceImpl 
extends BaseDaoService<PoliceDevice,String,PoliceDeviceMapper> 
implements IPoliceDeviceService{
	public Page<Map<String, String>> searchPoliceDevice(PoliceDevice policeDevice_param,
			PageRequest pageRequest) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("policeDevice", policeDevice_param);
		return getDao().searchPoliceDevice(map,pageRequest);
	}
	
	@Transactional
	public void updatePart(PoliceDevice policeDevice_param) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("policeDevice", policeDevice_param);
		getDao().updatePart(map);
	}
}
