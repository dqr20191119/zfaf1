package com.cesgroup.prison.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

/**
 * cesgroup
 * 流媒体服务
 * @author lihh
 *
 */
@Component
public class DvcStreamServerInfo extends AbsCacheTable{
	// 表名
	public static final String tableName = "dvc_stream_server_info";
	// 表字段
	public static final String SSI_CUS_NUMBER = "SSI_CUS_NUMBER";
	public static final String SSI_SERVER_NAME = "SSI_SERVER_NAME";
	public static final String SSI_IP_ADDRS = "SSI_IP_ADDRS";
	public static final String SSI_PORT = "SSI_PORT";
	public static final String SSI_IP2_ADDRS = "SSI_IP2_ADDRS";
	public static final String SSI_PORT2 = "SSI_PORT2";
	public static final String SSI_AREA_ID = "SSI_AREA_ID";
	
	public static final String ID = "ID";						//新主键
	
	private DvcStreamServerInfo(){
		//super.loadData();
	}

	@Override
	public Boolean queryData() {
		try{
			clearData(tableName);
			queryAndPutHash("query_dvc_stream_server_info", null, tableName, ID);
			log.info("==>缓存[dvc_stream_server_info][流媒体服务表]数据... SUCCESS!");
			return true;
		} catch(Exception ex){
			log.error("==>缓存[dvc_stream_server_info][流媒体服务表]数据... ERROR：", ex);
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
