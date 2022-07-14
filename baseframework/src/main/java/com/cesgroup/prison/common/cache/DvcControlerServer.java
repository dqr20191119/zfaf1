package com.cesgroup.prison.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;


/**
 * cesgroup
 * 设备云台控制服务
 * lihh
 */
@Component
public class DvcControlerServer extends AbsCacheTable{
	// 表名
	public static final String tableName = "DVC_CONTROLER_SERVER";

	// 表字段
	public static final String DCS_CUS_NUMBER = "DCS_CUS_NUMBER";
	public static final String DCS_TYPE_INDC = "DCS_TYPE_INDC";
	public static final String DCS_SERVER_ADDRS = "DCS_SERVER_ADDRS";

	private DvcControlerServer(){
		//super.loadData();
	}
	

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("query_dvc_controler_server", null, tableName, new String[]{DCS_CUS_NUMBER, DCS_TYPE_INDC}, DCS_SERVER_ADDRS);
			log.info("==>缓存[dvc_controler_server][设备控制服务表]数据... SUCCESS!");
			return true;
		} catch (Exception ex){
			log.error("==>缓存[dvc_controler_server][设备控制服务表]数据... ERROR：", ex);
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
