package com.cesgroup.prison.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

/**
 * 系统参数信息缓存
 * @author cheng.jie
 */
@Component
public class TXtcsCache extends AbsCacheTable {
	/** 表名 **/
	public static final String tableName = "t_xtcs";	// 表名
	/** 字段名 **/
	public static final String ID = "ID";				// 主键ID
	public static final String CSBM = "CSBM";			// 参数编码
	public static final String CSMC = "CSMC";			// 参数名称
	public static final String CSZ = "CSZ";				// 参数值
	public static final String SJZT = "SJZT";			// 数据状态

	private TXtcsCache() {

	}

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("query_t_xtcs", null, tableName, CSBM);
			log.info("==>缓存[t_xtcs][系统参数表]数据... SUCCESS!");
			return true;
		} catch (Exception ex) {
			log.error("==>缓存[t_xtcs][系统参数表]数据... ERROR：", ex);
			return false;
		}
	}

	@Override
	public Boolean refresh() throws Exception {
		return this.queryData();
	}

	
	public List<Map<String, Object>> getMapList(Map<String, Object> where, List<String> fields) {
		return RedisCache.getMapList(tableName, where, fields);
	}

	
	public Map<String, Object> getMap(Map<String, Object> where, List<String> fields) {
		return RedisCache.getMap(tableName, where, fields);
	}

	
	public Map<String, Object> getMap(Map<String, Object> where, String... fields) {
		return RedisCache.getMap(tableName, where, fields);
	}

	
	public Object getObject(Map<String, Object> where, String field) {
		return RedisCache.getObject(tableName, where, field);
	}

	
	public Object getObject(Object key) {
		return RedisCache.getObject(tableName, key);
	}

	
	public Object getObject(Object[] keys) {
		return RedisCache.getObject(tableName, keys);
	}

	
	public Object getObject(Object key, String filedName) {
		return RedisCache.getObject(tableName, key, filedName);
	}

	
	public Object getObject(Object[] keys, String filedName) {
		return RedisCache.getObject(tableName, keys, filedName);
	}

	
	public Map<String, Object> getHashMap(Object key) {
		return RedisCache.getHashMap(tableName, key);
	}

	
	public Map<String, Object> getHashMap(Object[] keys) {
		return RedisCache.getHashMap(tableName, keys);
	}

	
	public Map<String, Object> getHashMap(Object key, String[] filedNames) {
		return RedisCache.getHashMap(tableName, key, filedNames);
	}

	
	public Map<String, Object> getHashMap(Object[] keys, String[] filedNames) {
		return RedisCache.getHashMap(tableName, keys, filedNames);
	}
}
