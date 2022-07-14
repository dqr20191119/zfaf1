package com.cesgroup.prison.deviceMaintain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainEntity;

public interface DeviceMaintainService extends IBaseCRUDService<DeviceMaintainEntity, String> {

	Page<Map<String, Object>> listAll(DeviceMaintainEntity entity, Pageable pageable);

	void addInfo(DeviceMaintainEntity entity,HttpServletRequest request) throws Exception;

	void updateInfo(DeviceMaintainEntity entity) throws Exception;

	void deleteByIds(List<String> ids);

	Map<String, Object> findById(String id);

	// 签收
	void signIn(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception;

	// 处理
	void affairsDone(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception;

	// 反馈
	void feedBack(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception;

	// 提醒
	void remind(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception;

}
