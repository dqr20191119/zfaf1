package com.cesgroup.prison.prisonPath.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.prisonPath.entity.PrisonPathCameraRltnEntity;

public interface PrisonPathCameraRltnMapper extends BaseDao<PrisonPathCameraRltnEntity, String> {

	List<Map<String, Object>> listAll(Map<String, Object> map);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	public List<Map<String, Object>> listAllForSx(Map<String, Object> map);
}
