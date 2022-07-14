package com.cesgroup.prison.common.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

@Component
public class CallNamesPrisonerDtls extends AbsCacheTable {
	public static final String tableName = "cds_call_names_register_prisoner"; // 表名
	public static final String sqlId = "query_cds_call_names_register_prisoner_for_redis";
	public static final String whereId = "0";

	public static final String ID = "ID"; // 新主键
	public static final String JYH = "ZFBH"; // 监狱号
	public static final String LCH = "LCH";// 楼层号
	public static final String LC = "LC";// 楼层
	public static final String JSH = "JSH"; // 监舍号
	public static final String DEMPT_ID = "DEMPT_ID";// 部门编号

	public static final String ZFBH = "ZFBH"; // 罪犯编号
	public static final String ZF = "ZF";// 罪犯

	public static final String IMG_ID = "IMG_ID"; // 照片id
	public static final String IMG_URL = "IMG_URL";// 照片url
	public static final String IMG_SIZE = "IMG_SIZE";// 照片size
	public static final String IMG_NAME = "IMG_NAME";// 照片文件名

	private CallNamesPrisonerDtls() {
	}

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash(sqlId, null, tableName, ZFBH);

			log.info("==>缓存[cds_call_names_register_prisoner][点名注册人员信息]数据... SUCCESS!");
			return true;
		} catch (Exception ex) {
			log.error("==>缓存[cds_call_names_register_prisoner][点名注册人员信息]数据... ERROR：", ex);
			return false;
		}
	}

	@Override
	public Boolean refresh() throws Exception {
		return this.queryData();
	}

	public Boolean queryData(String cusNumber) {
		try {
			JSONObject queryParams = new JSONObject();
			queryParams.put("sqlId", "query_cds_call_names_register_prisoner_for_redis");
			queryParams.put("whereId", "0");
			List<String> params = new ArrayList<>();
			params.add(cusNumber);

			queryParams.put("params", params);

			queryAndPutHash(queryParams, tableName, new String[] { ZFBH });
			log.info("==>缓存[cds_call_names_register_prisoner][点名注册人员信息]数据... SUCCESS!");
			return true;
		} catch (Exception ex) {
			log.error("==>缓存[cds_call_names_register_prisoner][点名注册人员信息]数据... ERROR：", ex);
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
