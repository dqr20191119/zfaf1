package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.DoorWs;

public interface DoorWsMapper extends BaseDao<DoorWs, String> {

	public List<Map<String, Object>> getDoorList(Map<String, Object> paramMap);
}