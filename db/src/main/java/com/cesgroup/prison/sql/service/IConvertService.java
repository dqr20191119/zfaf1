package com.cesgroup.prison.sql.service;

/**
 * 字段转换服务接口（提供给外部实现）
 *
 */
public interface IConvertService {

	/**
	 * 转换值
	 * @param columnKey	表字段名称
	 * @param columnVal	表字段值
	 * @param constKey	转换字段名称
	 * @return
	 */
	public String convert(String columnKey, String columnVal, String constKey);
}
