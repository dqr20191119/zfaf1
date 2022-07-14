package com.cesgroup.prison.sql.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.scrap.db.SqlProcess;
import com.cesgroup.scrap.db.bean.SqlBean;


@Service
public class ConstConvertImpl implements IConstConvert {
	private static final Logger log = LoggerFactory.getLogger(ConstConvertImpl.class);

	// 转换服务映射
	private static Map<String, String> serviceMap = new LinkedHashMap<String, String>();

	// SQL处理对象
	@Resource
	private SqlProcess sqlProcess;
	
	/**
	 * 存放转换服务映射
	 * @param key
	 * @param value
	 */
	public static boolean putService(String key, String value) {
		try {
			if (!serviceMap.containsKey(key)) {
				serviceMap.put(key, value);
				return true;
			} else {
				log.warn("转换服务已存在!");
				return false;
			}
		} catch (Exception ex) {
			log.error(" 存放转换服务映射错误：", ex);
			return false;
		}
	}

	/**
	 * 数据转换
	 * @param sqlId		查询语句ID
	 * @param dataList	查询结果集
	 * @return
	 */
	@Override
	public List<Map<String, Object>> convert(String sqlId, List<Map<String, Object>> dataList) {
		return convertIndc(dataList, sqlProcess.getQuerySqlBean(sqlId));
	}
	
	/**
	 * 转换数据的枚举值
	 * @param dataList 转换前的数据列表
	 * @param sqlBean
	 * @return 转换后的数据列表
	 */
	@Override
	public List<Map<String,Object>> convertIndc(List<Map<String,Object>> dataList, SqlBean sqlBean){
		IConvertService convertService = null;	// 转换服务
		String columnKey = null;		// 表字段
		String columnVal = null;		// 值
		String constKey = null;			// 要转换的常量名称
		String constVal = null;			// 要转换的常量名称
		String serviceName = null;		// 服务名称

		if (dataList != null && dataList.size() > 0) {
			for (Entry<String,String> entry : sqlBean.getConstMap().entrySet()) {
				columnKey = entry.getKey().toUpperCase();					// 转换的表字段
				constKey = entry.getValue();								// 转换的对应的字段名称
				serviceName = Tools.toStr(serviceMap.get(constKey), "");	// 获取转换服务名称
				convertService = null;

				// 服务名称存在则获取服务实例
				if (Tools.notEmpty(serviceName)) {
					convertService = (IConvertService) SpringContextUtils.getBean(serviceName);
				}

				// 轮循查询结果集
				for (Map<String,Object> rowMap : dataList) {
					if (rowMap.containsKey(columnKey)) {
						columnVal = Tools.toStr(rowMap.get(columnKey), "").trim();

						if (Tools.notEmpty(columnVal)) {
							// 判断是采用转换服务还是内部方法
							if (convertService == null) {
								constVal = convert(columnKey, columnVal, constKey);
							} else {
								constVal = convertService.convert(columnKey, columnVal, constKey);
							}

							// 转换完之后重新赋值并退出本次循环
							rowMap.put(columnKey, constVal);
						}
					}
				}
			}
		}
		return dataList;
	}


	/**
	 * 转换值
	 * @param columnKey	表字段名称
	 * @param columnVal	表字段值
	 * @param constKey	转换字段名称
	 * @return
	 */
	public String convert(String columnKey, String columnVal, String constKey) {
		String[] columnVals = columnVal.split(",");
		String[] keys = new String[2];
		String constVal = "";

		if (columnVals != null && columnVals.length > 0) {
			for (int i = 0; i < columnVals.length; i++) {
				keys[0] = constKey;
				keys[1] = columnVals[i].trim();
				constVal += Tools.toStr(RedisCache.getObject(ComConstantDtlsCache.tableName, keys), "");
			}
		} else {
			constVal = columnVal;
		}
//		log.debug("常量转换: " + columnKey + " | " + columnVal + " | " + constKey + " | " + constVal);
		return constVal;
	}
}
