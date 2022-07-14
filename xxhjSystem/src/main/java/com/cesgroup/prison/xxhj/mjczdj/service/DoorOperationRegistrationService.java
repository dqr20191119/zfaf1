package com.cesgroup.prison.xxhj.mjczdj.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.mjczdj.entity.DoorOperationRegistrationEntity;

public interface DoorOperationRegistrationService extends IBaseCRUDService<DoorOperationRegistrationEntity, String> {

	public Page<Map<String, Object>> listAll(DoorOperationRegistrationEntity entity, Pageable pageable);

	public void addInfo(DoorOperationRegistrationEntity entity) throws Exception;

	public void updateInfo(DoorOperationRegistrationEntity entity) throws Exception;

	public void deleteByIds(List<String> list);

	public DoorOperationRegistrationEntity findById(String id);

	/**
	* @methodName: findTodayRegistrationByDprtmntAndCusNumber
	* @Description: 查询当日记录
	* @param cusNumber 监狱code
	* @param dprtmntId 部门code
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findTodayRegistrationByDprtmntAndCusNumber(String cusNumber, String dprtmntId);

}
