package com.cesgroup.fm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cesgroup
 * 消息服务映射类
 * @author lihh
 */
public class MsgServiceMap {
	// 私有化
	private MsgServiceMap() {

	};

	/** key msgType value:serviceName */
	private static Map<String, String> serviceNameMap = new ConcurrentHashMap<String, String>();
	private static MsgServiceMap serviceMapingUtil = null;

	/**
	 * 获取对象实例
	 */
	public synchronized static MsgServiceMap getInstance() {
		if (serviceMapingUtil == null) {
			serviceMapingUtil = new MsgServiceMap();
		}
		return serviceMapingUtil;
	}

	/**
	 * 添加服务映射
	 * @param key	自定义名称
	 * @param value	服务名称
	 */
	public static void put(String key, String value) throws Exception {
		if (!serviceNameMap.containsKey(key)) {
			serviceNameMap.put(key, value);
		} else {
			throw new Exception("key " + key + " 已存在");
		}
	}

	/**
	 * 获取消息服务名称
	 */
	public synchronized String getServiceName(String msgType) {
		return serviceNameMap == null ? null : serviceNameMap.get(msgType);
	}
}
