package com.cesgroup.prison.common.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.AreadeviceEntity;

public interface AreadeviceMapper extends BaseDao<AreadeviceEntity, String> {

	public List<Map<String, Object>> findAllArea(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllCamera(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllTalk(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllAlertor(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllDoor(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllBroadcast(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllTalkServer(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findAllLabel(Map<String, Object> paramMap);

	public List<Map<String, Object>> findParenAreaIdAndName(Map<String, Object> paramMap);

	public List<Map<String, Object>> findChildAreaIdAndName(Map<String, Object> paramMap);

	/**
	 * 根据监狱编号与部门编码查询可见区域
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> findAreaListByQueryMap(Map<String, Object> paramMap);

	/**
	 * 根据查询条件查询符合条件的广播设备数据
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> findBroadcastListByQueryMap(Map<String, Object> paramMap);
}
