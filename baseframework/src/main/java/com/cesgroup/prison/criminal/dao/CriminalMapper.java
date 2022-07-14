package com.cesgroup.prison.criminal.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.criminal.entity.CriminalEntity;

public interface CriminalMapper extends BaseDao<CriminalEntity, String> {
	
	public Integer getCriminalCount(Map<String, Object> map);
	
	public Integer getImportCount(Map<String, Object> map);
	
	public Page<Map<String, Object>> searchListPage(Map<String, Object> map, Pageable pageable);
	
	public Map<String, Object> getQy(Map<String, Object> map);
	
}
