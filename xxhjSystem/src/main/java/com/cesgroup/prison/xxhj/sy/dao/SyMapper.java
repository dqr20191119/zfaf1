package com.cesgroup.prison.xxhj.sy.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.sy.entity.Sy;

public interface SyMapper extends BaseDao<Sy, String> {

	public List<Map<String, Object>> queryForeignCarCount(Map<String, Object> map);
	
	public List<Map<String, Object>> queryForeignPeopleCount(Map<String, Object> map);
	
	public List<Map<String, Object>> queryKeynotePrisoner1(Map<String, Object> map);
	
	public List<Map<String, Object>> queryKeynotePrisoner2(Map<String, Object> map);
	
	public List<Map<String, Object>> queryKeynotePrisoner3(Map<String, Object> map);
}