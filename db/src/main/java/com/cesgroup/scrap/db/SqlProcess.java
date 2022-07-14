package com.cesgroup.scrap.db;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cesgroup.prison.sql.service.LoadSqlConfigs;
import com.cesgroup.scrap.db.bean.CustomWheres;
import com.cesgroup.scrap.db.bean.SqlBean;

@Repository
public class SqlProcess extends SqlProcessAbstract{
	
	@Resource
	private LoadSqlConfigs loadSqlConfigs;
	
	private static final Logger log = LoggerFactory.getLogger(SqlProcess.class);
	// sql替换的通配符集
	private static final String replaces = "where, and, or, group by, having, between, left join, inner join, right join, on, group by";
	/**
	 * 通过sqlid获取sql，但只获取sql不包括where条件和order by 条件
	 * @param sqlId
	 * @return
	 */
	public String getSql(Map<String,SqlBean> sqlMap,String sqlId) {
		SqlBean sqlBean = sqlMap.get(sqlId);
		if (sqlBean != null) {
			return checkSql(sqlBean.getSql(), sqlId);
		}else{
			return null;
		}
	}

	/**
	 * 通过sqlid、whereid、orderid 获取sql
	 * @param sqlId
	 * @param whereId
	 * @param orderId
	 * @return
	 */
    public String getSql(Map<String,SqlBean> sqlMap,String sqlId,String whereId,String orderId){
    	SqlBean sqlBean = sqlMap.get(sqlId);
    	if (sqlBean !=null) {
    		Map<String,String> whereMap = sqlBean.getWhereMap();
    		Map<String,String> orderMap = sqlBean.getOrderMap();
    		String sql = sqlBean.getSql();

    		if (whereMap.containsKey(whereId)) {
    			sql += " " + whereMap.get(whereId);
    		}
    		if (orderMap.containsKey(orderId)) {
    			sql += " " + orderMap.get(orderId);
    		}
    		return sql;
    	} else {
			return null;
    	}
    }

	/**
	 * 通过sqlid、whereid 获取sql
	 * @param sqlId
	 * @param whereId
	 * @return
	 */
    public String getSql(Map<String,SqlBean> sqlMap,String sqlId,String whereId){
    	SqlBean sqlBean = sqlMap.get(sqlId);
    	if (sqlBean !=null) {
    		Map<String,String> whereMap = sqlBean.getWhereMap();
    		String sql = sqlBean.getSql();

    		if (whereMap.containsKey(whereId)) {
    			sql += " " + whereMap.get(whereId);
    		}
    		return sql;
    	} else {
			return null;
    	}
    }

	/**
	 * 通过sqlid、whereid 获取sql
	 * @param sqlId
	 * @param orderId
	 * @return
	 */
    public String getSqlForOrderId(Map<String,SqlBean> sqlMap,String sqlId,String orderId){
    	SqlBean sqlBean = sqlMap.get(sqlId);
    	if (sqlBean != null) {
    		Map<String,String> orderMap = sqlBean.getOrderMap();
    		String sql = sqlBean.getSql();

    		if (orderMap.containsKey(orderId)) {
    			sql += " " + orderMap.get(orderId);
    		}
    		return sql;
    	} else {
			return null;
    	}
    }
	/**
	 * 获取插入语句
	 * @param sqlId SQL编号
	 * @return SQL语句 or null对象
	 */
	public  String getInsertSql(String sqlId) {
		String sql=getSql(insertMap,sqlId);
		return checkSql(sql, sqlId);
	}

	/**
	 * 获取删除语句
	 * @param sqlId SQL编号
	 * @return SQL语句  or null对象
	 */
	public String getDeleteSql(String sqlId) {
		String sql=getSql(deleteMap,sqlId);
		return checkSql(sql, sqlId);
	}
	/**
	 * 获取删除语句
	 * @param sqlId SQL编号
	 * @return SQL语句  or null对象
	 */
	public String getDeleteSql(String sqlId,String whereId) {
		String sql=getSql(deleteMap,sqlId,whereId);
		return checkSql(sql, sqlId);
	}
	
	/**
	 * 获取删除语句(可自定义SQL的条件语句)
	 * 
	 * <br/>事例:
	 * <p><blockquote><pre>
	 * String <b>key</b> = "queryDual"; // SQL : "select * from dual @where@ @and@"
	 * // 参数设置
	 * CustomWheres <b>cWheres</b> = new CustomWheres();
	 * cWheres.put("where", "1=1");
	 * cWheres.put("and","1 > 0");
	 * 
	 * sql = <b>getInsertSql</b>(key, cWheres);
	 * //输出 ： "select * from dual where 1=1 and 1 > 0"
	 * </pre></blockquote></p>
	 * 
	 * @param key SQL编号
	 * @param wheresMap where条件语句集
	 * @return SQL语句  or null对象
	 */
	public String getDeleteSql(String key, CustomWheres cWheres) {
		return replaceSql(getDeleteSql(key), cWheres);
	}

	/**
	 * 获取更新语句
	 * @param sqlId SQL编号
	 * @return SQL语句 or null对象
	 */
	public String getUpdateSql(String sqlId) {
		String sql = getSql(updateMap,sqlId);
		if (sql == null) sql = getSql(insertMap,sqlId);
		if (sql == null) sql = getSql(deleteMap,sqlId);
		return checkSql(sql, sqlId);
	}


	/**
	 * 获取更新语句
	 * @param sqlId
	 * @param whereId
	 * @return
	 */
	public String getUpdateSql(String sqlId,String whereId) {
		String sql = getSql(updateMap, sqlId, whereId);
		if (sql == null) sql = getSql(insertMap, sqlId, whereId);
		if (sql == null) sql = getSql(deleteMap, sqlId, whereId);
		return checkSql(sql, sqlId);
	}
	
	/**
	 * 获取更新语句(可自定义SQL的条件语句)
	 *
	 * <br/>事例:
	 * <p><blockquote><pre>
	 * String <b>key</b> = "queryDual"; // SQL : "select * from dual @where@ @and@"
	 * // 参数设置
	 * CustomWheres <b>cWheres</b> = new CustomWheres();
	 * cWheres.put("where", "1=1");
	 * cWheres.put("and","1 > 0");
	 * 
	 * sql = <b>getInsertSql</b>(key, cWheres);
	 * //输出 ： "select * from dual where 1=1 and 1 > 0"
	 * </pre></blockquote></p>
	 * 
	 * @param key SQL编号
	 * @param wheresMap where条件语句集
	 * @return SQL语句  or null对象
	 */
	public String getUpdateSql(String key, CustomWheres cWheres) {
		return replaceSql(getUpdateSql(key), cWheres);
	}
	
    public SqlBean getQuerySqlBean(String sqlId){
		SqlBean sqlBean=queryMap.get(sqlId);
		return sqlBean;
    }
	/**
	 * 通过sqlid获取sql，但只获取sql不包括where条件和order by 条件
	 * @param sqlId
	 * @return
	 */
	public String getQuerySql(String sqlId) {
		String sql=getSql(queryMap,sqlId);
		return checkSql(sql, sqlId);
	}
	/**
	 * 通过
	 * @param sqlId
	 * @param whereId
	 * @param orderId
	 * @return
	 */
    public String getQuerySql(String sqlId,String whereId,String orderId){
		String sql=getSql(queryMap,sqlId,whereId,orderId);
		return sql;
    }
    public String getQuerySql(String sqlId,String whereId){
    	String sql=getSql(queryMap,sqlId,whereId);
		return sql;
    }
    public String getQuerySqlForOrderId(String sqlId,String orderId){
    	String sql=getSqlForOrderId(queryMap,sqlId,orderId);
		return sql;
    }

	/**
	 * 获取查询语句(可自定义SQL的条件语句)
	 * 
	 * <br/>事例:
	 * <p><blockquote><pre>
	 * String <b>key</b> = "queryDual"; // SQL : "select * from dual @where@ @and@"
	 * // 参数设置
	 * CustomWheres <b>cWheres</b> = new CustomWheres();
	 * cWheres.put("where", "1=1");
	 * cWheres.put("and","1 > 0");
	 * 
	 * sql = <b>getInsertSql</b>(key, cWheres);
	 * //输出 ： "select * from dual where 1=1 and 1 > 0"
	 * </pre></blockquote></p>
	 * 
	 * @param key SQL编号
	 * @param wheresMap where条件语句集
	 * @return SQL语句  or null对象
	 */
	public String getQuerySql(String key, CustomWheres cWheres) {
		return replaceSql(getQuerySql(key), cWheres);
	}
	
	
	
	
	/**
	 * 获取存储过程
	 * @param key SQL编号
	 * @return SQL语句 or null对象
	 */
	public String getProc(String sqlId) {
		String sql=getSql(procMap,sqlId);
		return checkSql(sql, sqlId);
	}
	public String getProc(String sqlId,String whereId) {
		String sql=getSql(procMap,sqlId,whereId);
		return checkSql(sql, sqlId);
	}

	
	
	
	/**
	 * 获取函数
	 * @param sqlId 函数编号
	 * @return 函数 or null对象
	 */
	public String getFunction(String sqlId) {
		String sql=getSql(functionMap,sqlId);
		return checkSql(sql, sqlId);
	}
	public String getFunction(String sqlId,String whereId) {
		String sql=getSql(functionMap,sqlId);
		return checkSql(sql, sqlId);
	}

	
	
	
	/**
	 * 使 自定义的SQL条件语句 替换 自定义通配符<br/>
	 *
	 * <br/>事例:
	 * <p><blockquote><pre>
	 * String <b>key</b> = "queryDual"; // SQL : "select * from dual @where@ @and@"
	 * // 参数设置
	 * CustomWheres <b>cWheres</b> = new CustomWheres();
	 * cWheres.put("where", "1=1");
	 * cWheres.put("and","1 > 0");
	 * 
	 * sql = <b>getInsertSql</b>(key, cWheres);
	 * //输出 ： "select * from dual where 1=1 and 1 > 0"
	 * </pre></blockquote></p>
	 * 
	 * @param sql 配置的SQL
	 * @param wheresMap 替换语句集合	 
	 * 		格式：{"自定义通配符" : "自定义的sql[where]部分语句"}<br/>
	 * 		自定义通配符包括：所有的SQL语句语法字符【where|and|or|group by|... 】<br/>
	 * 		自定义where部分语句：通配符对应的sql语句
	 * @return
	 */
	private String replaceSql(String sql, CustomWheres cWheres){
		
		if (sql == null) return null;
		if (cWheres == null) return sql;
		
		List<Map<String, String>> whereList = cWheres.get();
		for(Map<String, String> whereMap : whereList){
		
			Iterator<Entry<String, String>> i = whereMap.entrySet().iterator();
		
			while(i.hasNext()){
				Entry<String, String> e = i.next();
				String key = e.getKey();
				String val = e.getValue();

				if (val.indexOf(key) < 0){
					val = key + " " + val;
				}
				
				key = "@" + key.toUpperCase() + "@";
				val = " " + val + " ";
				
				if (sql.indexOf(key)> 0)
					sql = sql.replaceFirst(key, val);
				else if (sql.indexOf(key.toLowerCase()) > 0)
					sql = sql.replaceFirst(key.toLowerCase(), val);
			}
		}
		
		// 去掉未替换的统配符
		String[] strs = replaces.split(",");
		for(String str : strs){
			str = str.trim();
			sql = sql.replace("@"  + str.toLowerCase() + "@", " ");
			sql = sql.replace("@"  + str.toUpperCase() + "@", " ");
		}
		log.debug("自定义执行语句:[" + sql + "]");
		
		return sql;
	}
	
	/**
	 * 检测SQL，当sql不等于null时做SQL语句格式化处理并返回，当sql等于null时去
	 * 未分类(未归档)的SQL集合里面查询是否存在指定key的语句，如果存在则做SQL
	 * 语句格式化处理并返回，不存在直接返回null
	 * @param sql 
	 * 			已分类的SQL
	 * @param key
	 * 			SQL编号
	 * @return
	 * 			SQL语句 or null
	 */
	private String checkSql(String sql, String key){
		if (sql == null) {
			return formatSql(getSql(otherMap,key), null);
		} else {
			return formatSql(sql);
		}
	}
	
	/**
	 * 获取SQL执行语句<br/>
	 * 			此方法在获取到SQL执行语句后去掉了XML里面的显示格式，将原本多行的
	 * 			SQL语句格式化成了一行，去掉里面的制表符及以空格替换掉了里面的换行符
	 * @param sql 
	 * 			配置文件类带格式的SQL
	 * @return 
	 * 			如果key存在则返回对应执行语句，如果不存在则返回null
	 */
	private String formatSql(String sql){
		return sql.replaceAll("	", "").replace("\n", " ").trim();
	}
	
	/**
	 * 获取SQL执行语句<br/>
	 * 			此方法在获取到SQL执行语句后去掉了XML里面的显示格式，将原本多行的
	 * 			SQL语句格式化成了一行，去掉里面的制表符及以空格替换掉了里面的换行符
	 * @param sql 
	 * 			配置文件类带格式的SQL
	 * @param defaultSql
	 * 			默认使用的SQL
	 * @return 
	 * 			如果key存在则返回对应执行语句，如果不存在则返回null
	 */
	private String formatSql(String sql, String defaultSql){
		return sql != null ? sql.replaceAll("	", "").replace("\n", " ").trim() : defaultSql;
	}
	
	
	/**
	 * 替换配置文件中的特殊符号
	 * 如：#代表小于号$代表大于号
	 * @param sql
	 * @return
	 */
	public String convertSql(String sql){
		//#代表小于号$代表大于号
		return sql.replace("#", "<").replace("$",">");
	}
}
