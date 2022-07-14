package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.BroadcastWs;

public interface BroadcastWsMapper extends BaseDao<BroadcastWs, String> {

	public List<Map<String, Object>> getBroadcastList(Map<String, Object> paramMap);
}