package com.cesgroup.prison.doorPlan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.doorPlan.entity.DoorPlanRltnEntity;

public interface DoorPlanRltnMapper extends BaseDao<DoorPlanRltnEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	List<Map<String, Object>> listAllForMj(Map<String, Object> map);

}
