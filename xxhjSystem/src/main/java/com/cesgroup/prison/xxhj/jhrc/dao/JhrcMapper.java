package com.cesgroup.prison.xxhj.jhrc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.jhrc.entity.JhrcEntity;

public interface JhrcMapper extends BaseDao<JhrcEntity, String> {
	
	public JhrcEntity getById(String id);
	
	public Page<JhrcEntity> findList(JhrcEntity jhrcEntity, PageRequest pageRequest);
	
	public void deleteByIds(List<String> idList);

	public List<Map<String, Object>> searchRcByDay(Map<String,Object> map);

	public List<Map<String, Object>> searchJndtByDay(Map<String,Object> map);
	
	public Page<JhrcEntity> searchJhrc(String departId);
	
	public Map<String, Object> getZbr(Map<String,Object> map);
	
	public void deleteDsq();

}
