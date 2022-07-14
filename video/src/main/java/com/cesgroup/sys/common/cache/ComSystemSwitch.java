package com.cesgroup.sys.common.cache;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;


@Component
public class ComSystemSwitch extends AbsCacheTable{
	// 表名
	public static final String tableName = "com_system_switch";
	// 表字段
	public static final String CSS_MODULE_NAME = "CSS_MODULE_NAME";// 模块名称
	public static final String CSS_SWITCH_NAME = "CSS_SWITCH_NAME";// 开关名称
	public static final String CSS_SWITCH_STTS = "CSS_SWITCH_STTS";// 开关状态
	
	private ComSystemSwitch(){
		//super.loadData();
	}

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("query_com_system_switch", null, tableName, CSS_SWITCH_NAME);
			log.info("==>缓存[com_system_switch][系统开关设置表]数据... SUCCESS!");
			return true;
		} catch (Exception ex){
			log.error("==>缓存[com_system_switch][系统开关设置表]数据... ERROR：", ex);
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
