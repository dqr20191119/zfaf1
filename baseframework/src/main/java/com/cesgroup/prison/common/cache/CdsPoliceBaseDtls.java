package com.cesgroup.prison.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;
/**
 * cesgroup
 * 民警信息表（也是用户信息表）
 * @author lihh
 */
@Component
public class CdsPoliceBaseDtls extends AbsCacheTable{

	//*********************************↓↓↓表字段定义↓↓↓*************************************
	public static final String tableName = "CDS_POLICE_BASE_DTLS";
	public static final String PBD_CUS_NUMBER = "PBD_CUS_NUMBER";// 机构号
	public static final String PBD_POLICE_IDNTY = "PBD_POLICE_IDNTY";// 民警编号
	public static final String PBD_POLICE_NAME = "PBD_POLICE_NAME";// 民警姓名
	public static final String PBD_DOOR_CARD_IDNTY = "PBD_DOOR_CARD_IDNTY";// 门禁卡号
	public static final String PBD_LOGIN_NAME = "PBD_LOGIN_NAME";// 登录名
	public static final String PBD_LOGIN_PWD = "PBD_LOGIN_PWD";// 登录密码
	public static final String PBD_USER_ID = "PBD_USER_ID";// 用户ID
	public static final String PBD_USER_STTS_INDC = "PBD_USER_STTS_INDC";// 用户状态
	//*********************************↑↑↑表字段定义↑↑↑*************************************

	private CdsPoliceBaseDtls () {

	}

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("sys_query_police_info_push_redis", null, tableName, PBD_USER_ID);
			queryAndPutHash("sys_query_police_info_push_redis", null, tableName, PBD_POLICE_IDNTY);
			log.info("==>缓存[cds_police_base_dtls][民警信息表(即用户信息表)]数据... SUCCESS!");

			return true;
		} catch (Exception ex){
			log.error("==>缓存[cds_police_base_dtls][民警信息表(即用户信息表)]数据... ERROR：", ex);
			return false;
		}
	}

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
