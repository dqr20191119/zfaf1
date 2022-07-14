package com.cesgroup.sys.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

@Component
public class ComFunctionMenuConfig extends AbsCacheTable{
	// 表名
	public static final String tableName = "com_function_menu_config";
	// 表字段
	public static final String id = "ID";
	public static final String parentId = "PARENT_ID";
	public static final String name = "NAME";
	public static final String url = "URL";
	public static final String openType = "OPEN_TYPE";
	public static final String width = "WIDTH";
	public static final String height = "HEIGHT";
	public static final String style = "STYLE";
	public static final String showType = "SHOW_TYPE";
	public static final String showTime = "SHOW_TIME";
	public static final String viewId = "VIEW_ID";
	public static final String seq = "SEQ";

	private ComFunctionMenuConfig(){
		//super.loadData();
	}

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("sys_query_menu_config", null, tableName, id);
			log.info("==>缓存[com_function_menu_config][功能菜单配置表]数据... SUCCESS!");
			return true;
		} catch (Exception ex){
			log.error("==>缓存[com_function_menu_config][功能菜单配置表]数据... ERROR：", ex);
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
