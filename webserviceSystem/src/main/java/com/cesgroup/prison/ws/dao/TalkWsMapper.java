package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.TalkWs;

public interface TalkWsMapper extends BaseDao<TalkWs, String> {

	public List<Map<String, Object>> getTalkList(Map<String, Object> paramMap);
}