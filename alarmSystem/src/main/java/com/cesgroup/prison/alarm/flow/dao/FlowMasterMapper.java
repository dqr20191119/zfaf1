package com.cesgroup.prison.alarm.flow.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.flow.entity.FlowMasterEntity;

public interface FlowMasterMapper extends BaseDao<FlowMasterEntity, String> {

	int deleteByIds(List<String> ids);

	Page<Map<String, String>> findByPage(Map map, PageRequest pageRequest);

	public List<Map<String, Object>> findMaster(Map<String, Object> map);
	
	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	List<Map<String, Object>> searchCombData(Map<String, Object> map);
}
