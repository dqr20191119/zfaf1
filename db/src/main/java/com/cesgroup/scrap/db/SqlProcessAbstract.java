package com.cesgroup.scrap.db;

import java.util.LinkedHashMap;

import com.cesgroup.scrap.db.bean.SqlBean;

public abstract class SqlProcessAbstract {
	// 存放未归档的SQL
	protected static LinkedHashMap<String, SqlBean> otherMap = new LinkedHashMap<String, SqlBean>();
	// 存放插入类SQL
	protected static LinkedHashMap<String, SqlBean> insertMap = new LinkedHashMap<String, SqlBean>();
	// 存放删除类SQL
	protected static LinkedHashMap<String, SqlBean> deleteMap = new LinkedHashMap<String, SqlBean>();
	// 存放更新类SQL
	protected static LinkedHashMap<String, SqlBean> updateMap = new LinkedHashMap<String, SqlBean>();
	// 存放查询类SQL
	protected static LinkedHashMap<String, SqlBean> queryMap = new LinkedHashMap<String, SqlBean>();
	// 存放存储过程
	protected static LinkedHashMap<String, SqlBean> procMap = new LinkedHashMap<String, SqlBean>();
	// 存放函数
	protected static LinkedHashMap<String, SqlBean> functionMap = new LinkedHashMap<String, SqlBean>();
}
