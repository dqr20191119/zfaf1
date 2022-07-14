package com.cesgroup.prison.sql.service;

import java.util.List;

public interface IAddService {
	public int insert(String sqlid,List<Object> parasList);
	
	public int insertBlob(String sqlId, final List<Object> params);
	/**
	 * 插入包含Clob类型的数据
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public int insertClob(String sqlId, final List<Object> params);
}
