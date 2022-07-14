package com.cesgroup.prison.common.cache;

import org.springframework.stereotype.Component;
import com.cesgroup.framework.cache.AbsCacheTable;
import com.cesgroup.prison.db.service.RedisCache;
/**
 * 报警等级映射类
 */
@Component
public class AlarmLevelMap extends AbsCacheTable{
	// 报警等级表缓存字段信息
	public static final String cacheKey 		= "CDS_ALARM_TYPE";		// 缓存标识：表名
	public static final String ALT_CUS_NUMBER 	= "alt_cus_number";		// 机构号
	public static final String ALT_TYPE_ID 		= "alt_type_id";		// 报警设备类型ID
	public static final String ALT_LEVEL 		= "alt_level";			// 报警等级
	@Override
	public Boolean queryData() {
		try {
			// 清除缓存
			clearData(cacheKey);
			// 缓存规则：key = 机构号_类型ID，value=报警等级
			queryAndPutHash("cds_query_alarm_type_level", null, cacheKey, new String[]{ALT_CUS_NUMBER, ALT_TYPE_ID}, ALT_LEVEL);
			log.info("==>缓存[CDS_ALARM_TYPE][报警类型等级表]数据... SUCCESS!");
			return true;
		} catch (Exception e) {
			log.error("==>缓存[CDS_ALARM_TYPE][报警类型等级表]数据... ERROR：", e);
		}
		return false;
	}

	@Override
	public Boolean refresh() throws Exception {
		return this.queryData();
	}

	/*
	 * 获取报警等级（根据机构号+报警设备类型）
	 */
	public static Object getAlarmLevel (Object... keys) {
		Object alarmLevel = RedisCache.getObject(cacheKey, keys);
		if (alarmLevel == null) {
			alarmLevel = 0;
			log.warn("未获取配置项中的报警等级，默认等级0");
		}
		return alarmLevel;
	}
}
