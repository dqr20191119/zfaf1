package com.cesgroup.prison.sql.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

/**
 * cesgroup
 * 常量信息表
 */
@Component
public class ComConstantDtlsCache extends AbsCacheTable {

	//*********************************↓↓↓表字段定义↓↓↓*************************************
	public static final String tableName = "COM_CONSTANT_DTLS";
	public static final String CDT_CNSTNT_IDNTY = "CDT_CNSTNT_IDNTY";	// 常量代码
	public static final String CDT_CNSTNT_NAME = "CDT_CNSTNT_NAME";		// 常量代码描述
	public static final String CDT_KEY = "CDT_KEY";						// 常量值
	public static final String CDT_VALUE = "CDT_VALUE";					// 常量值描述
	//*********************************↑↑↑表字段定义↑↑↑*************************************

	private ComConstantDtlsCache () {

	}

	@Override
	public Boolean refresh() throws Exception {
		return this.queryData();
	}

	@Override
	public Boolean queryData() {
		/*try {
			clearData(tableName);
			queryAndPutList("com_constant_dtls", null, tableName);
			queryAndPutHash("com_constant_dtls", null, tableName, new String[]{CDT_CNSTNT_IDNTY,CDT_KEY}, CDT_VALUE);
			log.info("==>缓存[com_constant_dtls][常量信息表]数据... SUCCESS!");
		} catch (Exception e) {
			log.error("==>缓存[com_constant_dtls][常量信息表]数据... ERROR：", e);
			return false;
		}*/
		return true;
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
