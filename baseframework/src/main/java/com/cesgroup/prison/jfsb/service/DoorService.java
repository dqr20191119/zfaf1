package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.jfsb.entity.DoorEntity;

public interface DoorService extends IBaseCRUDService<DoorEntity, String> {

	Page<Map<String, Object>> listAll(DoorEntity entity, Pageable pageable);

	AjaxMessage addInfo(DoorEntity entity);

	void updateInfo(DoorEntity entity) throws Exception;

	void deleteByIds(List<String> list);

	DoorEntity findById(String id);

	List<DoorEntity> findByIds(List<String> ids);

	List<Map<String, Object>> findForJqTree(Map<String, Object> paramMap);

	List<Map<String, Object>> findForTree(Map<String, Object> paramMap);

	Map<String, Object> findDoorSum(DoorEntity entity);

}
