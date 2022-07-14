package com.cesgroup.scrap.db.bean;

import java.util.List;
/**
 * cesgroup
 * 更新Sql参数bean
 *
 */
public class SqlParamsBean {
    private String sqlId;
    private List<Object> parasList;
    public SqlParamsBean(){}
    public SqlParamsBean(String sqlId,List<Object> parasList){
    	this.sqlId = sqlId;
    	this.parasList = parasList;
    }
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public List<Object> getParasList() {
		return parasList;
	}
	public void setParasList(List<Object> parasList) {
		this.parasList = parasList;
	}
    
}
