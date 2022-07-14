package com.cesgroup.prison.common.cache;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;

/**
 * 扩展配置的缓存类
 * @author hz208
 */
@Component
public class ConfigExtend extends AbsCacheTable {
	private static final Logger log = LoggerFactory.getLogger(ConfigExtend.class);
	private static final String CACHE_KEY = "CONFIG.EXTEND";

	@Override
	public Boolean queryData() {
		return load();
	}

	@Override
	public Boolean refresh() throws Exception {
		return queryData();
	}

	/**
	 * 加载扩展配置数据到缓存
	 */
	private boolean load () {
		try {
			RedisCache.deleteHash(CACHE_KEY);
			ResourceBundle.clearCache();
			// 加载配置文件
			ResourceBundle bundle = ResourceBundle.getBundle("extend-config");

			// 存放缓存
			for(String key : bundle.keySet()) {
				log.info(key+":"+bundle.getString(key));
				RedisCache.putHash(CACHE_KEY, key, bundle.getString(key));
			}
			log.info("加载扩展配置数据... OK!");
		} catch (Exception e) {
			log.error("加载扩展配置数据异常：" + e);
			return false;
		}
		return true;
	}


	/**
	 * 获取配置
	 * @param key
	 * @return
	 */
	public static Object getValue (String key) {
		return RedisCache.getObject(CACHE_KEY, key);
	}
}
