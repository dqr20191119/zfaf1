package com.cesgroup.prison.xxhj.jndt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.jndt.entity.JndtEntity;

public interface JndtMapper extends BaseDao<JndtEntity, String> {

	public JndtEntity getById(String id);

	public Page<JndtEntity> findList(JndtEntity jndtEntity, PageRequest pageRequest);
	
	public List<JndtEntity> findAllList(JndtEntity jndtEntity); 

	public void deleteByIds(List<String> idList);

	public void insertHistory(JndtEntity jndtEntity);

	public void deleteByRelationIds(List<String> asList);

	public Page<JndtEntity> findHistoryList(JndtEntity jndtEntity, PageRequest pageRequest);

	public JndtEntity getHistoryById(String id);

	public List<Map<String, String>> countPeople(@Param("parCusNumber")String parCusNumber,@Param("parOutCategory")String parOutCategory);

	public void completeJndtByIds(List<String> idList);

	public void completeJndtByRelationIds(List<String> idList);

}