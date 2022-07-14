package com.cesgroup.prison.prisonPath.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.prisonPath.entity.PrisonPathEntity;

public interface PrisonPathMapper extends BaseDao<PrisonPathEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	public List<Map<String, Object>> findPathByAreaIdForCombobox(Map<String, Object> paramMap);

	void updateInfo(Map<String, Object> map);
	
	void deleteByIds(List<String> ids);



}
