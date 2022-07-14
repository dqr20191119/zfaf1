package com.cesgroup.prison.screen.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.entity.ScreenAreaCameraEntity;

public interface ScreenAreaCameraMapper extends BaseDao<ScreenAreaCameraEntity, String> {
	public List<Map<String, Object>> listAll(Map<String, Object> map);

	void updateInfo(Map<String, Object> map);

	public List<Map<String, Object>> listAllForSx(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	List<Map<String, Object>> findCameraList(List<String> ids);
}
