package com.cesgroup.prison.sql.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cesgroup.scrap.common.ALoadXml;
import com.cesgroup.scrap.db.SqlProcessAbstract;
import com.cesgroup.scrap.db.bean.SqlBean;

/**
 * cesgroup
 * 功能描述： 加载SQL执行语句
 */
@Component
public class LoadSqlConfigs extends ALoadXml {
	private static final Logger log = LoggerFactory.getLogger(LoadSqlConfigs.class);
	private SqlMap sqlMap = new SqlMap();
	private static LinkedHashMap<String, Integer> fileTypeMap = new LinkedHashMap<String, Integer>();
	private static final LoadSqlConfigs loadSqlConfig = new LoadSqlConfigs();
	private LoadSqlConfigs() {}

	static {
		fileTypeMap.put("insert", 0);
		fileTypeMap.put("delete", 1);
		fileTypeMap.put("update", 2);
		fileTypeMap.put("query", 3);
		fileTypeMap.put("proc", 4);
		fileTypeMap.put("function", 5);
		log.info("开始加载SQL执行语句配置文件...");
		loadSqlConfig.load("");
	}

	/**
	 * 获取对象实例
	 * @return
	 */
	public static synchronized LoadSqlConfigs getInstance() {
		return loadSqlConfig;
	}

	/**
	 * 加载文件
	 */
	private synchronized void load(String fileName) {
		this.loadXml("sql", fileName, "item");
		log.info("SQL执行语句配置文件加载完成!!!");
	}

	/**
	 * 重新加载
	 * @return 
	 */
	public synchronized Boolean refresh(String fileName) {
		try {
			log.info("重新加载Sql执行语句...");
			sqlMap.clear();
			this.load(fileName);
			return true;
		} catch(Exception ex){
			log.error("重新加载Sql执行语句失败，异常：", ex);
			return false;
		}
	}

	@Override
	protected void parseXml(String fileName, NodeList nodeList) {
		// 获取文件分类类型，去掉后缀名再分解文件名称以获取文件分类类型
		String typeName = fileName.replace(".xml", "").split("-")[0];
		// 获取文件类型对应ID
		int typeId = fileTypeMap.get(typeName);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element item = (Element) nodeList.item(i);
			// 获取SQL执行语句对应ID
			String id = item.getAttribute("id");
			// 重复ID检测
			if (!sqlMap.containsKey(typeId, id)) {
				SqlBean sqlBean = new SqlBean();
				// SQL语句节点
				Node sql = item.getElementsByTagName("sql").item(0);
				// 获取节点值
				String sqlValue = sql.getFirstChild().getNodeValue();
				sqlBean.setSql(sqlValue);
				// 获取where条件
				NodeList constNodeList = item.getElementsByTagName("Const");
				if (constNodeList.getLength() > 0) {
					Map<String, String> constMap = new HashMap<String, String>();
					for (int j = 0; j < constNodeList.getLength(); j++) {
						Element constItem = (Element) constNodeList.item(j);
						String constKey = constItem.getAttribute("key");
						String constValue = constItem.getFirstChild().getNodeValue();
						// 转换where 条件里面的大于和小于号
						constMap.put(constKey, constValue);
					}
					sqlBean.setConstMap(constMap);
				}
				// 获取where条件
				NodeList whereNodeList = item.getElementsByTagName("where");
				// 存在where条件
				if (whereNodeList.getLength() > 0) {
					Map<String, String> whereMap = new HashMap<String, String>();
					for (int j = 0; j < whereNodeList.getLength(); j++) {
						Element whereItem = (Element) whereNodeList.item(j);
						String wId = whereItem.getAttribute("wid");
						String whereValue = whereItem.getFirstChild().getNodeValue();
						// 转换where 条件里面的大于和小于号
						whereValue = whereValue.replace("#", "<").replace("$", ">");
						whereMap.put(wId, whereValue);
					}
					sqlBean.setWhereMap(whereMap);
				}
				// 获取order条件
				NodeList orderNodeList = item.getElementsByTagName("order");
				// 存在where条件
				if (orderNodeList.getLength() > 0) {
					Map<String, String> orderMap = new HashMap<String, String>();
					for (int j = 0; j < orderNodeList.getLength(); j++) {
						Element orderItem = (Element) orderNodeList.item(j);
						String oId = orderItem.getAttribute("oid");
						String orderValue = orderItem.getFirstChild().getNodeValue();
						// 转换where 条件里面的大于和小于号
						orderMap.put(oId, orderValue);
					}
					sqlBean.setOrderMap(orderMap);
				}
				sqlMap.put(typeId, id, sqlBean);
			} else {
				throw new RuntimeException("在" + typeName + "类的配置文件中ID[" + id + "]重复!!!");
			}
		}
	}

	class SqlMap extends SqlProcessAbstract {
		/**
		 * ID重复检测
		 * @param fileType  文件分类ID
		 * @param key  SQL编号
		 * @return true重复/false未重复
		 */
		protected boolean containsKey(int fileType, String key) {
			switch (fileType) {
			case 0:
				return insertMap.containsKey(key);
			case 1:
				return deleteMap.containsKey(key);
			case 2:
				return updateMap.containsKey(key);
			case 3:
				return queryMap.containsKey(key);
			case 4:
				return procMap.containsKey(key);
			case 5:
				return functionMap.containsKey(key);
			default:
				return otherMap.containsKey(key);
			}
		}

		/**
		 * 分类存放数据
		 * @param fileType XML文件分类ID
		 * @param keySQL编号
		 * @param value SQL语句
		 */
		protected void put(int fileType, String key, SqlBean sqlBean) {
			switch (fileType) {
			case 0:
				insertMap.put(key, sqlBean);
				break;
			case 1:
				deleteMap.put(key, sqlBean);
				break;
			case 2:
				updateMap.put(key, sqlBean);
				break;
			case 3:
				queryMap.put(key, sqlBean);
				break;
			case 4:
				procMap.put(key, sqlBean);
				break;
			case 5:
				functionMap.put(key, sqlBean);
				break;
			default:
				otherMap.put(key, sqlBean);
				break;
			}
		}

		/**
		 * 清除所有缓存数据
		 */
		protected void clear() {
			log.info("清除SQL缓存数据...");
			otherMap.clear();
			insertMap.clear();
			deleteMap.clear();
			queryMap.clear();
			updateMap.clear();
			procMap.clear();
			functionMap.clear();
		}
	}
}
