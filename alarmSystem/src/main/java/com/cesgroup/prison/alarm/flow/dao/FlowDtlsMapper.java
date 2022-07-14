package com.cesgroup.prison.alarm.flow.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.flow.entity.FlowDtlsEntity;

public interface FlowDtlsMapper extends BaseDao<FlowDtlsEntity, String> {

	List<Map> findByMasterId(Map<String, Object> map);

	/**
	 * 刪除流程时删除全部
	 * @param ids
	 * @return
	 */
	int deleteByMasterIds(List<String> ids);

	/**
	 * 修改流程时删除部分
	 * @param list
	 * @param masterId
	 */
	void deleteByMasterId(Map<String, Object> map);

	public List<Map<String, Object>> findFlowDtls(Map<String, Object> map);
}
