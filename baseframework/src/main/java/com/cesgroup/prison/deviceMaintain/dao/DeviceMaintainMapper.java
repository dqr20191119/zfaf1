package com.cesgroup.prison.deviceMaintain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainEntity;

public interface DeviceMaintainMapper extends BaseDao<DeviceMaintainEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);
	
	Map<String, Object> findById(Map<String, Object> map);
}
