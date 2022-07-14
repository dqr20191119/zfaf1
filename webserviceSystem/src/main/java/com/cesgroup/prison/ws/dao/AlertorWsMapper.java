package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.AlertorWs;

public interface AlertorWsMapper extends BaseDao<AlertorWs, String> {

	public List<Map<String, Object>> getAlertorList(Map<String, Object> paramMap);

	public List<Map<String, Object>> getAlertorListByType(Map<String, Object> paramMap);
}