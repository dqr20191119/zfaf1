package com.cesgroup.prison.xxhj.jfsb.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.jfsb.entity.Jfsb;

public interface JfsbService extends IBaseCRUDService<Jfsb, String> {

 	public List<Map<String, String>> listDeviceMasterInfo(String cusNumber, String typeIndc);
 	
 	public List<Map<String, Object>>listTechnicalDevicePrisonList(HttpServletRequest request) throws Exception;
 	
 	public Page<Map<String, Object>> listOnePrisonCameraInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception;
 	
 	public Page<Map<String, Object>>listOnePrisonAlertorInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception;
 	
 	public Page<Map<String, Object>>listOnePrisonInfraredInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception;
 	
 	public Page<Map<String, Object>>listOnePrisonPowerNetworkInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception;
 	
 	public Page<Map<String, Object>>listOnePrisonDoorInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception;
 	
 	public Page<Map<String, Object>>listOnePrisonSnakeInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception;
 	
 	public List<Map<String, Object>>listSysDeviceInfo(HttpServletRequest request);
 	
 	//显示20条记录
 	public List<Map<String, Object>>listOnePrisonDevicePart(HttpServletRequest request);
 	
	public Page<Map<String, Object>>listdeviceMaintainRecord(String dmrCusNumber,String dmrDeviceType,String dmrDeviceIdnty,Pageable page);
}