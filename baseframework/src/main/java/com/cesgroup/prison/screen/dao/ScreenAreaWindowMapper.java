package com.cesgroup.prison.screen.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.entity.ScreenAreaWindowEntity;

public interface ScreenAreaWindowMapper extends BaseDao<ScreenAreaWindowEntity, String> {
	void updateInfo(Map<String, Object> map);

	public List<Map<String, Object>> listAll(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	List<Map<String, Object>> getScreenPlanWindowListByAreaId(Map<String, Object> paramMap);
}
