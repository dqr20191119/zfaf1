package com.cesgroup.prison.common.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

/**
 * cesgroup
 * 摄像头基本信息
 * @author lihh
 *
 */
@Component
public class DvcCameraBaseDtls extends AbsCacheTable {
	public static final String tableName = "dvc_camera_base_dtls";						// 表名
	public static final String CBD_CUS_NUMBER = "CBD_CUS_NUMBER";						// 机构号
	public static final String CBD_NAME = "CBD_NAME";									// 摄像机名称
	public static final String CBD_TYPE_INDC = "CBD_TYPE_INDC"; 						// 摄像机类型 1球机、2枪机
	public static final String CBD_DVR_IDNTY = "CBD_DVR_IDNTY";							// 摄像机DVR的ID对应表dvc_video_device_info的新主键
	public static final String CBD_IP_ADDRS = "CBD_IP_ADDRS";							// 摄像机的IP地址
	public static final String CBD_DVR_CHNNL_IDNTY = "CBD_DVR_CHNNL_IDNTY";				// DVR的通道号
	public static final String CBD_STREAM_MEDIA_IDNTY = "CBD_STREAM_MEDIA_IDNTY";		// 流媒体服务ID
	public static final String CBD_STREAM_TYPE = "CBD_STREAM_TYPE";						// 码流类型：0主码流、1辅码流

	public static final String CBD_PLATFORM = "CBD_PLATFORM";							// 所在平台ID，对应dvc_video_device_info的新主键
	public static final String CBD_PLATFORM_IDNTY = "CBD_PLATFORM_IDNTY";				// 设备所在平台的唯一编号
	public static final String CBD_VIDEO_PLAY_INDC = "CBD_VIDEO_PLAY_INDC";				// 设备播放方式
	public static final String CBD_VIDEO_PLAYBACK_INDC = "CBD_VIDEO_PLAYBACK_INDC";		// 设备回放方式
	
	//add by zk
	public static final String CBD_BRAND_NAME = "CBD_BRAND_NAME";						// 摄像机品牌
	public static final String CBD_PORT = "CBD_PORT";									// 摄像机端口
	public static final String CBD_USER_NAME = "CBD_USER_NAME";							// 摄像机用户名
	public static final String CBD_USER_PASSWORD = "CBD_USER_PASSWORD";					// 摄像机密码
	public static final String CBD_CHNNL_IDNTY = "CBD_CHNNL_IDNTY";						// 摄像机通道号
	public static final String ID = "ID";												//新主键
	
	private DvcCameraBaseDtls() {

	}

	@Override
	public Boolean queryData() {
		try {
			clearData(tableName);
			queryAndPutHash("query_dvc_camera_base_dtls", null, tableName, ID);
			queryAndPutHash("query_dvc_camera_base_dtls", null, tableName, CBD_IP_ADDRS, ID);
			log.info("==>缓存[dvc_camera_base_dtls][摄像机基础信息表]数据... SUCCESS!");
			return true;
		} catch (Exception ex) {
			log.error("==>缓存[dvc_camera_base_dtls][摄像机基础信息表]数据... ERROR：", ex);
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
