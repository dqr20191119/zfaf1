package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.ScreenPlanWs;

public interface ScreenPlanWsMapper extends BaseDao<ScreenPlanWs, String> {

	public List<Map<String, Object>> getScreenPlanAreaListByPalnId(Map<String, Object> paramMap);

	public List<Map<String, Object>> getScreenPlanWindowListByAreaId(Map<String, Object> paramMap);

	public List<Map<String, Object>> getScreenPlanCameraListByAreaId(Map<String, Object> paramMap);

}