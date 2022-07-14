package com.cesgroup.framework.cache;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.cron.CronUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.scrap.db.QueryProcess;
/**
 * cesgroup
 * 统一缓存抽象方法
 * @author lihh
 *
 */
public abstract class AbsCacheTable implements IRefreshCache, ApplicationListener<ApplicationEvent>{

	protected static final Logger log = LoggerFactory.getLogger(AbsCacheTable.class);

	@Autowired
	protected QueryProcess queryProcess;

	private boolean initFlag = true;

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		if (initFlag) {
			initFlag = false;
			loadData();
		}
	}

	/**
	 * 加载缓存数据
	 */
	protected void loadData(){
		Thread tt = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					queryData();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		tt.setPriority(Thread.NORM_PRIORITY);
		tt.start();
	}

	@Override
	public Boolean refresh(String fileName) throws Exception {
		return refresh();
	}

	/**
	 * 删除缓存数据
	 * @param cacheKey
	 */
	protected void clearData (String cacheKey) {
		RedisCache.deleteHash(cacheKey);
	}

	/**
	 * 清除缓存数据
	 * @param cacheKey
	 * @param keys
	 */
	protected void clearData (String cacheKey, Object... keys) {
		RedisCache.deleteHash(cacheKey, keys);
	}
	
	/**
	 * 查询数据并存入Hash缓存
	 * @param sqlId 查询语句编号
	 * @param params 参数集合
	 * @param tableName 表明
	 * @param key KEY标识
	 * @param filedNames 字段名称组
	 * @throws Exception
	 */
	protected Boolean queryAndPutHash(String sqlId, List<Object> params, String tableName, String key, String... filedNames) throws Exception{
		return this.queryAndPutHash(sqlId, params, tableName, new String[]{key}, filedNames);
	}

	/**
	 * 查询数据并存入Hash缓存
	 * @param sqlId 查询语句编号
	 * @param params 参数集合
	 * @param tableName 表明
	 * @param key KEY标识 组
	 * @param filedNames 字段名称组
	 * @throws Exception
	 */
	protected Boolean queryAndPutHash(String sqlId, List<Object> params, String tableName, String[] keys, String... filedNames) throws Exception{
		JSONObject queryParams = new JSONObject();
		queryParams.put("sqlId", sqlId);
		queryParams.put("params", params);

		return queryAndPutHash(queryParams, tableName, keys, filedNames);
	}

	/**
	 * 查询数据并存入Hash缓存
	 * @param queryParams	查询条件{"sqlId":"","whereId":"","orderId":"","params":[]}
	 * @param tableName
	 * @param keys
	 * @param filedNames
	 * @return
	 * @throws Exception
	 */
	protected Boolean queryAndPutHash(JSONObject queryParams, String tableName, String[] keys, String... filedNames) throws Exception{
		String sqlId = Tools.toStr(queryParams.get("sqlId"), "");
		String whereId = Tools.toStr(queryParams.get("whereId"), "");
		String orderId = Tools.toStr(queryParams.get("orderId"), "");
		List<Object> params = queryParams.getJSONArray("params");

		return RedisCache.putHash(tableName, keys, filedNames, queryProcess.process(sqlId, whereId, orderId, params));
		//return false;
	}
	
	/**
	 * 查询数据并存入MapList缓存中(存入前会先清除原缓存)
	 * @param sqlId 查询语句编号
	 * @param params 参数集合
	 * @param tableName 表明
	 * @return
	 * @throws Exception
	 */
	protected Boolean queryAndPutList(String sqlId, List<Object> params, String tableName) throws Exception{
		return queryAndPutList(sqlId, params, tableName, new String[0]);
	}

	/**
	 * 查询数据并存入MapList缓存中(存入前会先清除原缓存)
	 * @param sqlId 查询语句编号
	 * @param params 参数集合
	 * @param tableName 表明
	 * @param fields 需要保存的字段
	 * @return
	 * @throws Exception
	 */
	protected Boolean queryAndPutList(String sqlId, List<Object> params, String tableName, String... fields) throws Exception{
		JSONObject queryParams = new JSONObject();
		queryParams.put("sqlId", sqlId);
		queryParams.put("params", params);
		
		return queryAndPutList(queryParams, tableName, fields);
	}

	/**
	 * 查询数据并存入MapList缓存中(存入前会先清除原缓存)
	 * @param queryParams	查询条件{"sqlId":"","whereId":"","orderId":"","params":[]}
	 * @param tableName
	 * @param fields
	 * @return
	 * @throws Exception
	 */
	protected Boolean queryAndPutList(JSONObject queryParams, String tableName, String... fields) throws Exception{
		String sqlId = Tools.toStr(queryParams.get("sqlId"), "");
		String whereId = Tools.toStr(queryParams.get("whereId"), "");
		String orderId = Tools.toStr(queryParams.get("orderId"), "");
		List<Object> params = queryParams.getJSONArray("params");

		//List<Map<String, Object>> retMapList = queryProcess.process(sqlId, whereId, orderId, params);
		List<Map<String, Object>> retMapList = null;//queryProcess.process(sqlId, whereId, orderId, params);
		List<Map<String, Object>> pushList = null;
		// 检查查询结果
		if (retMapList == null) {
			throw new Exception("数据查询失败，查询结果null");
		}
		if (retMapList.size() == 0) {
			throw new Exception("数据查询失败，查询结果0");
		}

		if (fields != null && fields.length > 0){
			Map<String, Object> newMap = null;
			pushList = new ArrayList<Map<String,Object>>();
			for(Map<String, Object> tempMap : retMapList){
				newMap = new LinkedHashMap<String, Object>();
				for(String field : fields){
					newMap.put(field, tempMap.get(field));
				}
				pushList.add(newMap);
			}
		} else {
			pushList = retMapList;
		}

		RedisCache.deleteList(tableName);
		long pushNumber = RedisCache.putList(tableName, pushList);
		if (pushNumber != retMapList.size()) {
			throw new Exception("数据查询成功，查询" + retMapList.size() + "条数据, 存入" + pushNumber + "条数据");
		}
		return true;
	}


	/**
	 * 查询数据
	 */
	public abstract Boolean queryData();

	/**
	 * 获取MapList集合指定条件的Map集合
	 * @param where 条件, Map格式: String 是集合中字段名，Object是字段值
	 * @param fields 查询的字段集合
	 * @return
	 */
//	public abstract List<Map<String, Object>> getMapList(Map<String, Object> where, List<String> fields);

	/**
	 * 获取MapList缓存集合中指定条件的的Map对象
	 * @param where 条件, Map格式: String 是集合中字段名，Object是字段值
	 * @param fields 查询的字段集合
	 * @return 
	 */
//	public abstract Map<String, Object> getMap(Map<String, Object> where, List<String> fields);

	/**
	 * 获取MapList缓存集合中指定条件的的Map对象
	 * @param where 条件, Map格式: String 是集合中字段名，Object是字段值
	 * @param fields 查询的字段集合
	 * @return
	 */
//	public abstract Map<String, Object> getMap(Map<String, Object> where, String... fields);

	/**
	 * 获取List缓存集合中获取指定条件的指定字段值
	 * @param where 条件, Map格式: String 是集合中字段名，Object是字段值
	 * @param field 查询的字段名
	 * @return 
	 */
//	public abstract Object getObject(Map<String, Object> where, String field);

	/**
	 * 获取Hash缓存数据
	 * @param key 查询的KEY值
	 * @return Object
	 */
//	public abstract Object getObject(Object key);

	/**
	 * 获取Hash缓存数据
	 * @param keys 查询的KEY值组
	 * @return Object
	 */
//	public abstract Object getObject(Object[] keys);

	/**
	 * 获取Hash缓存数据
	 * @param key 查询的KEY值
	 * @param filedNames 查询的字段名称组
	 * @return 直接返回该字段值(Object)
	 */
//	public abstract Object getObject(Object key, String filedName);

	/**
	 * 获取Hash缓存数据
	 * @param keys 查询的KEY值组
	 * @param filedName 查询的字段名称
	 * @return 直接返回该字段值(Object)
	 */
//	public abstract Object getObject(Object[] keys, String filedName);

	/**
	 * 获取Hash缓存数据
	 * @param key 查询的KEY值
	 * @param filedNames 查询的字段名称组
	 * @return 
	 */
//	public abstract Map<String, Object> getHashMap(Object key);

	/**
	 * 获取Hash缓存数据
	 * @param key 查询的KEY值
	 * @param filedNames 查询的字段名称组
	 * @return 
	 */
//	public abstract Map<String, Object> getHashMap(Object[] keys);

	/**
	 * 获取Hash缓存数据
	 * @param key 查询的KEY值
	 * @param filedNames 查询的字段名称组
	 * @return 
	 */
//	public abstract Map<String, Object> getHashMap(Object key, String[] filedNames);

	/**
	 * 获取Hash缓存数据
	 * @param keys 查询的KEY值组
	 * @param filedNames 查询的字段名称组
	 * @return 
	 */
//	public abstract Map<String, Object> getHashMap(Object[] keys, String[] filedNames);
}
