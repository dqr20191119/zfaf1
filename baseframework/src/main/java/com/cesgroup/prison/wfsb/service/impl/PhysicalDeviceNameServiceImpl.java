package com.cesgroup.prison.wfsb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.wfsb.dao.PhysicalDeviceNameMapper;
import com.cesgroup.prison.wfsb.entity.PhysicalDeviceName;
import com.cesgroup.prison.wfsb.service.IPhysicalDeviceNameService;
@Service
public class PhysicalDeviceNameServiceImpl 
extends BaseDaoService<PhysicalDeviceName,String,PhysicalDeviceNameMapper> 
implements IPhysicalDeviceNameService{
	public Page<Map<String, String>> searchPhysicalDeviceName(PhysicalDeviceName physicalDeviceName_param,
			PageRequest pageRequest) {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String cusNumber = userBean.getCusNumber();
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("cusNumber", cusNumber);
			map.put("physicalDeviceName", physicalDeviceName_param);
			return getDao().searchPhysicalDeviceName(map,pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,Object>> simplePhysicalDeviceName(String pdnCusNumber){
		return getDao().simplePhysicalDeviceName(pdnCusNumber);
	}

	@Transactional
	public void updatePart(PhysicalDeviceName physicalDeviceName_param) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("physicalDeviceName", physicalDeviceName_param);
		getDao().updatePart(map);
	}
	
}
