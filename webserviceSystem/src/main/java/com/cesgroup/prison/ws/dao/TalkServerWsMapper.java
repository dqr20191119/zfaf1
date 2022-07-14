package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.TalkServerWs;

public interface TalkServerWsMapper extends BaseDao<TalkServerWs, String> {

	public List<Map<String, Object>> getTalkServerList(Map<String, Object> paramMap);
}