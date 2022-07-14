package com.cesgroup.prison.screen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.entity.ScreenPlanEntity;

public interface ScreenPlanMapper extends BaseDao<ScreenPlanEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteById(Map<String, Object> map);
	
	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	List<Map<String, Object>> searchCombData(Map<String, Object> map);
}
