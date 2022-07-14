package com.cesgroup.prison.xxhj.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.jfsb.entity.Jfsb;

public interface JfsbMapper extends BaseDao<Jfsb, String> {

 	public List<Map<String, String>> listDeviceMasterInfo(Map<String,Object> map);
 	
 	public List<Map<String, Object>> listTechnicalCameraInfo(Map<String,Object> map);
 	
	public List<Map<String, Object>> listTechnicalAlertorInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> listTechnicalInfraredInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> listTechnicalPowerNetworkInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> listTechnicalDoorInfo(Map<String,Object> map);
	
	public List<Map<String,Object>> listTechnicalSnakeInfo(Map<String,Object> map);

	public Page<Map<String, Object>> listOnePrisonCameraInfo(Map<String,Object> map,Pageable pageable);
	
	public List<Map<String, Object>> listOnePrisonCameraPart(Map<String,Object> map);
	
	public Page<Map<String, Object>> listOnePrisonAlertorInfo(Map<String,Object> map,Pageable pageable);
	
	public List<Map<String, Object>> listOnePrisonAlertorPart(Map<String,Object> map);
	
	public Page<Map<String, Object>> listOnePrisonInfraredInfo(Map<String,Object> map,Pageable pageable);
	
	public List<Map<String, Object>> listOnePrisonInfraredPart(Map<String,Object> map);
	
	public Page<Map<String, Object>> listOnePrisonPowerNetworkInfo(Map<String,Object> map,Pageable pageable);
	
	public List<Map<String, Object>> listOnePrisonPowerNetworkPart(Map<String,Object> map);

	public Page<Map<String, Object>> listOnePrisonDoorInfo(Map<String,Object> map,Pageable pageable);
	
	public List<Map<String, Object>> listOnePrisonDoorPart(Map<String,Object> map);

	public Page<Map<String, Object>> listOnePrisonSnakeInfo(Map<String,Object> map,Pageable pageable);
	
	public List<Map<String,Object>> listOnePrisonSnakePart(Map<String,Object> map);
	
	public List<Map<String,Object>> listSysCameraInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> listSysAlertorBaseInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> listSysInfraredBaseInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> listSysPowerNetworkInfo(Map<String,Object> map);
	
	public List<Map<String, Object>> listSysDoorInfo(Map<String,Object> map);
	
	public List<Map<String,Object>> listSnakeWireInfo(Map<String,Object> map);
	
	public Page<Map<String, Object>> listdeviceMaintainRecord(Map<String,Object> map, Pageable page);
}