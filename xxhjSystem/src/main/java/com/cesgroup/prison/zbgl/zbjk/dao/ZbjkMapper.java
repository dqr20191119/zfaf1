package com.cesgroup.prison.zbgl.zbjk.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.zbjk.entity.ZbjkEntity;

public interface ZbjkMapper extends BaseDao<ZbjkEntity, String> {
	
	public Page<Map<String, Object>> findList(Map<String, Object> map, Pageable pageable);
	
	public List<Map<String, Object>> findAllList(Map<String, Object> map);
	
	public List<Map<String, Object>> findInoutRecord(Map<String, Object> map);

}
