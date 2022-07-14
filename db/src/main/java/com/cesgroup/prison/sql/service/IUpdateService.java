package com.cesgroup.prison.sql.service;

import java.util.List;

import com.cesgroup.scrap.db.bean.SqlParamsBean;


public interface IUpdateService {
	public int update(String sqlid,List<Object> parasList) ;
	
	public int updateBlob(String sqlId, final List<Object> params);
	
	public void batchUpdate(List<SqlParamsBean> sqlParams);
	
/*	public AjaxMessage batchUpdate(String args);*/
	
	/**
	 * 更新含Clob类型的数据
	 * @param sqlId
	 * @param params
	 */
	public int updateClob(String sqlId, final List<Object> params);
}
