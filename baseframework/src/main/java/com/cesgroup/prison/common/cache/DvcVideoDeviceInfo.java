package com.cesgroup.prison.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;
/**
 * cesgroup
 * 录像机
 * 查询摄像机设备基础信息表数据
 * @author lihh
 *
 */

@Component
public class DvcVideoDeviceInfo extends AbsCacheTable{
	// 表名
	public static final String tableName = "dvc_video_device_info";
	// 表字段
	public static final String VDI_CUS_NUMBER = "VDI_CUS_NUMBER"; 
	public static final String VDI_DEVICE_NAME = "VDI_DEVICE_NAME";
	public static final String VDI_TYPE_INDC = "VDI_TYPE_INDC";
	public static final String VDI_BRAND = "VDI_BRAND";
	public static final String VDI_MODE = "VDI_MODE";
	public static final String VDI_IP_ADDRS = "VDI_IP_ADDRS";
	public static final String VDI_IP2_ADDRS = "VDI_IP2_ADDRS";
	public static final String VDI_PORT = "VDI_PORT";
	public static final String VDI_USER_NAME = "VDI_USER_NAME";
	public static final String VDI_USER_PASSWORD = "VDI_USER_PASSWORD";
	
	public static final String ID = "ID"; 									//新主键
	
	private DvcVideoDeviceInfo(){
		//super.loadData();
	}


	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("query_dvc_video_device_info", null, tableName, ID);
			log.info("==>缓存[dvc_video_device_info][摄像机设备信息表]数据... SUCCESS!");
			return true;
		} catch (Exception ex){
			log.error("==>缓存[dvc_video_device_info][摄像机设备信息表]数据... ERROR：", ex);
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
