package com.cesgroup.prison.doorPlan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.doorPlan.entity.DoorPlanEntity;

public interface DoorPlanMapper extends BaseDao<DoorPlanEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	void updateSttsByIds(Map<String, Object> paramMap);

	List<Map<String, Object>> findAllPlan(Map<String, Object> paramMap);

	List<Map<String, Object>> findAllDoorRltn(Map<String, Object> paramMap);
}
