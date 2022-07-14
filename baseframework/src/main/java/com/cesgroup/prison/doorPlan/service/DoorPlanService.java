package com.cesgroup.prison.doorPlan.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.doorPlan.entity.DoorPlanEntity;
import com.cesgroup.prison.doorPlan.entity.DoorPlanRltnEntity;

public interface DoorPlanService extends IBaseCRUDService<DoorPlanEntity, String> {

	Page<Map<String, Object>> listAll(DoorPlanEntity entity, Pageable pageable);

	void addInfo(DoorPlanEntity entity) throws Exception;

	void updateInfo(DoorPlanEntity entity) throws Exception;

	void deleteByIds(List<String> list);

	void updateSttsByIds(List<String> list, String stts);

	DoorPlanEntity findById(String id);

	List<Map<String, Object>> listAllForMj(String cusNumber, String areaId, String planId, String doorName);

	Page<Map<String, Object>> listAllForDoor(DoorPlanRltnEntity doorPlanRltnEntity, Pageable pageable);

	void deleteByIdsForDoor(List<String> list);

	AjaxMessage saveDoorRltn(Object doorDate);

	List<Map<String, Object>> findForCombotree(Map<String, Object> paramMap);

}
