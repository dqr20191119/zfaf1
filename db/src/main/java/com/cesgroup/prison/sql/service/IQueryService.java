package com.cesgroup.prison.sql.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface IQueryService {

	public List<Map<String,Object>> query (String args);
	public List<Map<String,Object>> query (JSONObject argsJSON);
	public List<Map<String,Object>> query (String sqlId, List<Object> params);
	public List<Map<String,Object>> query (String sqlId, String whereId, List<Object> params);
	public List<Map<String,Object>> query (String sqlId, String whereId, String orderId, List<Object> params);
	public List<Map<String,Object>> queryForOrder (String sqlId, String orderId, List<Object> params);

	/**
	 * 查询一个值，相当于  select  into
	 * @param sqlid
	 * @param parasList
	 * @return
	 */
	public String queryValue(String sqlid, List<Object> parasList);
}
