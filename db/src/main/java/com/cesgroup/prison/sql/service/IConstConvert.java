package com.cesgroup.prison.sql.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.scrap.db.bean.SqlBean;


/**
 * 常量转换接口
 */
public interface IConstConvert {
	/**
	 * 转换数据的枚举值
	 * @param dataList 转换前的数据列表
	 * @param sqlBean
	 * @return 转换后的数据列表
	 */
	public List<Map<String,Object>> convertIndc(List<Map<String,Object>> dataList, SqlBean sqlBean);
	
	
	/**
	 * 数据转换
	 * @param sqlId		查询语句ID
	 * @param dataList	查询结果集
	 * @return
	 */
	public List<Map<String,Object>> convert (String sqlId, List<Map<String,Object>> dataList);
}
