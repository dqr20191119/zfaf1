package com.cesgroup.prison.xxhj.mjczdj.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.mjczdj.entity.DoorOperationRegistrationEntity;

public interface DoorOperationRegistrationMapper extends BaseDao<DoorOperationRegistrationEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	List<Map<String, Object>> findTodayRegistrationByDprtmntAndCusNumber(Map<String, Object> map);
}
