package com.cesgroup.scrap.db.bean;

import java.util.HashMap;
import java.util.Map;
/**
 * cesgroup
 * sql配置的实体bean
 */
public class SqlBean {
    private String sql=null;
    private Map<String,String> whereMap=new HashMap<String,String>();
    private Map<String,String> orderMap=new HashMap<String,String>();
    private Map<String,String> constMap=new HashMap<String,String>();
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Map<String, String> getConstMap() {
		return constMap;
	}
	public void setConstMap(Map<String, String> constMap) {
		this.constMap = constMap;
	}
	public Map<String, String> getWhereMap() {
		return whereMap;
	}
	public void setWhereMap(Map<String, String> whereMap) {
		this.whereMap = whereMap;
	}
	public Map<String, String> getOrderMap() {
		return orderMap;
	}
	public void setOrderMap(Map<String, String> orderMap) {
		this.orderMap = orderMap;
	}
	
	
}
