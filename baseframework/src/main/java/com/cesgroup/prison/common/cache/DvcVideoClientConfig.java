package com.cesgroup.prison.common.cache;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;
@Component
public class DvcVideoClientConfig extends AbsCacheTable{
	// 表名
	public static final String tableName = "dvc_video_client_config";
	// 表字段
	public static final String VCC_CUS_NUMBER = "VCC_CUS_NUMBER";
	public static final String VCC_APP_IP = "VCC_APP_IP";
	public static final String VCC_CLIENT_IP = "VCC_CLIENT_IP";
	public static final String VCC_WIDTH = "VCC_WIDTH";
	public static final String VCC_HEIGHT = "VCC_HEIGHT";
	public static final String VCC_X_CRDNT = "VCC_X_CRDNT";
	public static final String VCC_Y_CRDNT = "VCC_Y_CRDNT";
	public static final String VCC_IMG_PATH = "VCC_IMG_PATH";
	public static final String VCC_VIDEO_PATH = "VCC_VIDEO_PATH";
	
	private DvcVideoClientConfig(){
		//super.loadData();
	}


	@Override
	public Boolean queryData() {
		try{
			clearData(tableName);
			queryAndPutHash("query_dvc_video_client_config", null, tableName, VCC_CLIENT_IP);
			log.info("==>缓存[dvc_video_client_config][视频客户端配置信息表]数据... SUCCESS!");
			return true;
		} catch (Exception ex){
			log.error("==>缓存[dvc_video_client_config][视频客户端配置信息表]数据... ERROR：", ex);
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


    @Override
    protected void clearData(String cacheKey, Object... keys) {
        super.clearData(cacheKey, keys);
    }

    @Override
    protected void clearData(String cacheKey) {
        super.clearData(cacheKey);
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
