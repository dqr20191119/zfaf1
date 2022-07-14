package com.cesgroup.prison.screen.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.entity.ScreenPlanAreaRltnEntity;

public interface ScreenPlanAreaRltnMapper extends BaseDao<ScreenPlanAreaRltnEntity, String> {
	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	List<Map<String, Object>> searchCombData(Map<String, Object> map);

	void updateInfo(Map<String, Object> map);

	void deleteById(Map<String, Object> map);

	List<Map<String, Object>> getScreenPlanAreaListByPalnId(Map<String, Object> paramMap);

}
