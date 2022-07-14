package com.cesgroup.prison.xxhj.jndt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.jndt.entity.JndtEntity;

public interface JndtService extends IBaseCRUDService<JndtEntity, String> {
	
	public void saveOrUpdate(JndtEntity jndtEntity) throws Exception;
	
	public JndtEntity getById(String id);
	
	public Page<JndtEntity> findList(JndtEntity jndtEntity, PageRequest pageRequest);
	
	public List<JndtEntity> findAllList(JndtEntity jndtEntity);
	
	public void deleteByIds(String ids);

	public Page<JndtEntity> findHistoryList(JndtEntity jndtEntity, PageRequest pageRequest);

	public JndtEntity getHistoryById(String id);

	public List<Map<String, String>> countPeople(String cusNumber,String parOutCategory);

	public void completeJndtByIds(String ids);

}