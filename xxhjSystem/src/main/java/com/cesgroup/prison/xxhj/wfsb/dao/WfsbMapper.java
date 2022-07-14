package com.cesgroup.prison.xxhj.wfsb.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.wfsb.entity.Wfsb;

public interface WfsbMapper extends BaseDao<Wfsb, String> {

	public List<Map<String, String>> listPhysicalDeviceCount(String _parameter);
	
	public List<Map<String, String>> listPhysicalDeviceCountPris(Map<String, Object> map);
}