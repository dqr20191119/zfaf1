package com.cesgroup.prison.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

@Component
public class CdsFtpServerConfig extends AbsCacheTable {
	// 表名
	public static final String tableName = "cds_ftp_server_config";
	public static final String FSC_CUS_NUMBER = "FSC_CUS_NUMBER";
	public static final String FSC_SERVER_IP = "FSC_SERVER_IP";
	public static final String FSC_USER_NAME = "FSC_USER_NAME";
	public static final String FSC_PASSWORD = "FSC_PASSWORD";
	public static final String FSC_DOWNLOAD_ADDRS = "FSC_DOWNLOAD_ADDRS";
	public static final String FSC_FTP_STTS = "FSC_FTP_STTS";

	private CdsFtpServerConfig(){

	}
	
	@Override
	public Boolean refresh() throws Exception {
		return this.queryData();
	}

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("cds_query_ftp_server_config", null, tableName, FSC_CUS_NUMBER);
			log.info("==>缓存[cds_ftp_server_config][FTP服务器配置表]数据... SUCCESS!");
			return true;
		} catch (Exception ex){
			log.error("==>缓存[cds_ftp_server_config][FTP服务器配置表]数据... ERROR：", ex);
			return false;
		}
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
