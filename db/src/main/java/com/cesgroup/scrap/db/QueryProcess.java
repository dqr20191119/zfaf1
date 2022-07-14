package com.cesgroup.scrap.db;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.cesgroup.framework.util.ReflectUtil;
import com.cesgroup.scrap.db.bean.CustomWheres;

@Repository
public class QueryProcess{

	private final int maxRows = 100000;
	@Resource
	private JdbcDAO jdbcDAO;
	@Resource
	private JdbcProductionDAO jdbcProductionDAO;
	@Resource 
	private SqlProcess sqlProcess;

	public List<Map<String, Object>> query(String sql, List<Object> args) {
		if(args==null){
			args=new ArrayList<Object>() ;
		}
		List<Map<String, Object>> queryList = null;
		sql=sqlProcess.convertSql(sql);
		try {
			queryList = jdbcDAO.query(maxRows, sql, args.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryList;
	}
	
	public List<Map<String, Object>> queryForFe(String sql, List<Object> args) {
		if(args==null){
			args=new ArrayList<Object>() ;
		}
		List<Map<String, Object>> queryList = null;
		sql=sqlProcess.convertSql(sql);
		try {
			queryList = jdbcProductionDAO.query(maxRows, sql, args.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryList;
	}
	
	
	/**
	 * 执行SQL查询
	 * @param sqlId sqlID
	 * @param args 参数集合
	 * @return
	 */
	public List<Map<String, Object>> process(String sqlId, List<Object> args) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(sqlId);//getSql(sqlId);
		if(sql!=null&&!sql.isEmpty()){
			rtnList= query(sql,args);
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	/**
	 * 执行sql查询
	 * @param sqlId
	 * @param whereId
	 * @param ordreId
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> process(String sqlId,String whereId,String ordreId, List<Object> args) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(sqlId,whereId,ordreId);//getSql(sqlId);
		if(sql!=null&&!sql.isEmpty()){
			rtnList= query(sql,args);
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	/**
	 * 执行sql查询
	 * @param sqlId
	 * @param whereId
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> process(String sqlId,String whereId, List<Object> args) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(sqlId,whereId);//getSql(sqlId);
		if(sql!=null&&!sql.isEmpty()){
			rtnList= query(sql,args);
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	
	public List<Map<String, Object>> processForFe(String sqlId,String whereId, List<Object> args) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(sqlId,whereId);//getSql(sqlId);
		if(sql!=null&&!sql.isEmpty()){
			rtnList= queryForFe(sql,args);
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	
	/**
	 * 执行sql查询
	 * @param sqlId
	 * @param orderId
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> processForOrderId(String sqlId,String orderId, List<Object> args) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySqlForOrderId(sqlId,orderId);//getSql(sqlId);
		if(sql!=null&&!sql.isEmpty()){
			rtnList= query(sql,args);
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	
	/**
	 * 执行SQL查询
	 * @param sqlId sqlID
	 * @param args 参数集合
	 * @param orderBy 排序语句
	 * @return
	 */
	public List<Map<String, Object>> process(String sqlId, List<Object> args,String orderBy) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(sqlId);//getSql(sqlId);
		if(sql!=null&&!sql.isEmpty()){
			rtnList= query(sql,args);
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	
	/**
	 * 执行SQL查询
	 * <br/>事例:
	 * <p><blockquote><pre>
	 * String <b>key</b> = "queryDual"; // SQL : "select * from dual @where@ @and@"
	 * // 参数设置
	 * CustomWheres <b>cWheres</b> = new CustomWheres();
	 * cWheres.put("where", "where 1=1");
	 * cWheres.put("and","and 1 > 0");
	 * 
	 * sql = <b>getInsertSql</b>(key, cWheres);
	 * //输出 ： "select * from dual where 1=1 and 1 > 0"
	 * </pre></blockquote></p>
	 *  
	 * @param sqlId sql编号
	 * @param args 参数集合
	 * @param whereMap 自定义SQL条件语句
	 * @return
	 */
	public List<Map<String, Object>> process(String sqlId, List<Object> args, CustomWheres cWheres) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(sqlId, cWheres);
		if (sql != null && !sql.isEmpty()){
			rtnList= query(sql,args);
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	
	
	
	
	public int count(String sqlId,String sqlCondition){
		int count=0;
		String sql = sqlProcess.getQuerySql(sqlId);
		sql=sqlProcess.convertSql(sql);
		if(sql!=null&&!sql.isEmpty()){
			if(sqlCondition!=null && !sqlCondition.isEmpty()){
				sql=sql+sqlCondition;
			}
			sql="select count(0) ct from ("+sql+") temp";
			count=Integer.parseInt(jdbcDAO.query(maxRows, sql).get(0).get("ct").toString());
		}else{
			new Exception("Not Found sql !");
		}
		return count;
	}
	
	public int count(String sql,List<Object> args){
		int count=0;
		sql=sqlProcess.convertSql(sql);
		if(sql!=null&&!sql.isEmpty()){
			sql="select count(0) ct from ("+sql+") temp";
			count=Integer.parseInt(jdbcDAO.query(maxRows, sql,args.toArray()).get(0).get("ct").toString());
		}else{
			new Exception("Not Found sql !");
		}
		return count;
	}
	
	public List<Map<String,Object>>  pagination(int curPage,int pageSize,int maxRowCount,String sqlId,String sqlCondition,String sqlOrderBy) {
		List<Map<String,Object>> rtnList=new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(sqlId);//getSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		if(sql!=null&&!sql.isEmpty()){
			//拼装SQL
			StringBuffer sqlSb=new StringBuffer();
			sqlSb.append(" select * from (select row_number() over(order by 0) as rowno,newtab.* from  ( ");
			sqlSb.append(sql);
			if (sqlCondition!=null && !"".equals(sqlCondition.trim())) {  //页面设置含查询条件
				sqlSb.append(sqlCondition);
			}
			
			//删除原SQL的order by待完成
			
			if (sqlOrderBy!=null && !"".equals(sqlOrderBy.trim())) {  //页面设置含排序顺序
				sqlSb.append(" order by ").append(sqlOrderBy);
			}
			int start=(curPage-1)*pageSize+1;
			int end=curPage*pageSize;
			if(maxRowCount<end){
				end=maxRowCount;
			}
			sqlSb.append(" ) newtab) alltab where alltab.rowno between "+start+" and "+end );
			
			rtnList = jdbcDAO.query(maxRows, sqlSb.toString());
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}
	
/*	public List<Map<String,Object>>  pagination(QueryBean qb,String sqlCondition,String sqlOrderBy) {
		List<Map<String,Object>> rtnList=new ArrayList<Map<String,Object>>();
		String sql = sqlProcess.getQuerySql(qb.getSqlId());//getSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		if(sql!=null&&!sql.isEmpty()){
			//拼装SQL
			StringBuffer sqlSb=new StringBuffer();
			sqlSb.append(" select * from (select row_number() over(order by 0) as rowno,newtab.* from  ( ");
			sqlSb.append(sql);
			if (sqlCondition!=null && !"".equals(sqlCondition.trim())) {  //页面设置含查询条件
				sqlSb.append(sqlCondition);
			}
			
			//删除原SQL的order by待完成
			
			if (sqlOrderBy!=null && !"".equals(sqlOrderBy.trim())) {  //页面设置含排序顺序
				sqlSb.append(" order by ").append(sqlOrderBy);
			}
			int start=(qb.getCurPage()-1)*qb.getPageSize()+1;
			int end=qb.getCurPage()*qb.getPageSize();
			if(qb.getMaxRowCount()<end){
				end=qb.getMaxRowCount();
			}
			sqlSb.append(" ) newtab) alltab where alltab.rowno between "+start+" and "+end );
			
			rtnList = jdbcDAO.query(maxRows, sqlSb.toString());
		}else{
			new Exception("Not Found sql !");
		}
		return rtnList;
	}*/
	
	public <T> List<T> getList(String sqlId, List<Object> args, Class<T> clazz) throws Exception{
		return getList(sqlId, args, clazz, null);
	}
	
	public <T> List<T> getList(String sqlId, List<Object> args, Class<T> clazz, CustomWheres cWheres) throws Exception{
		List<T> tlist=new ArrayList<T>();
		
		T t=null;
		Method method=null;
		String methodName=null;
		List<Map<String,Object>> list= process(sqlId, args, cWheres);
		
		if (list == null) 
			return null;
		
		for(Map<String,Object> hm : list){
			t=clazz.newInstance();
			for(String key : hm.keySet()){
				methodName=getSetMethodName(key);
				//通过反射找到set方法,只有一个参数
				@SuppressWarnings("rawtypes")
				Class paramType=ReflectUtil.getMethodParamTypes(t,methodName)[0];
				method = clazz.getMethod(methodName,paramType);
				if(int.class.equals(paramType)){
					method.invoke(t,Integer.parseInt(hm.get(key).toString()));
				}else{
					method.invoke(t,hm.get(key)==null?"":hm.get(key).toString());
				}
			}
			tlist.add(t);
		}
		
		return tlist;
	}
	
	
	public <T> T get(String sqlId, List<Object> args,Class<T> clazz) throws Exception{
		List<T> tlist=getList( sqlId,  args, clazz);
		if(tlist.size()==1){
			return tlist.get(0);
		}
		return null;
	}

	/**
	 * 将Map转换为嵌套List 
	 * @param result
	 * @return
	 */
	public List<List<Object>> convertMapToLists(List<Map<String,Object>> result){
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> subList=null;
		for(Map<String,Object> map : result){
			subList=new ArrayList<Object>();
			for(String key : map.keySet()){
				subList.add(map.get(key));
			}
			list.add(subList);
		}
		return list;
	}

	/**
	 * 获取实体Bean 字段对于的set方法
	 * @param column
	 * @return
	 */
	public static String getSetMethodName(String column){
		column=column.toLowerCase();
		String[] columns=column.split("_");
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("set");
		for(String col:columns){
			strbuf.append(col.substring(0,1).toUpperCase());
			strbuf.append(col.substring(1).toLowerCase());
		}
		return strbuf.toString();
	}


	public int getMaxRows() {
		return maxRows;
	}

}
