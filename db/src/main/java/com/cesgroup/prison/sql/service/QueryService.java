package com.cesgroup.prison.sql.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.frm.db.util.IConst;
import com.cesgroup.scrap.db.QueryProcess;
import com.cesgroup.scrap.db.SqlProcess;

/**
 * cesgroup
 * 通用查询处理服务类
 */
@Service
public class QueryService implements IQueryService{
	// 日志记录
//	private static final Logger log = LoggerFactory.getLogger(QueryService.class);
	
	@Resource
	private QueryProcess queryProcess;

	@Resource 
	private SqlProcess sqlProcess;

	@Autowired
	private IConstConvert constConvertUtil;

	@Override
	public List<Map<String, Object>> query (String args) {
		return query(JSON.parseObject(args));
	}

	@Override
	public List<Map<String,Object>> query (JSONObject argsJSON) {
		//将前台参数转换为json对象
		String sqlId = argsJSON.getString(IConst.SQLID);
		String whereId = argsJSON.getString(IConst.WHERID);
		String orderId = argsJSON.getString(IConst.ORDERID);
		List<Object> params = argsJSON.getJSONArray(IConst.PARAS);

		boolean hasWhereId = Tools.notEmpty(whereId);
		boolean hasOrderId = Tools.notEmpty(orderId);

		if (hasWhereId) {
			return hasOrderId ? query(sqlId, whereId, orderId, params) : query(sqlId, whereId, params);
		} else {
			return hasOrderId ? queryForOrder(sqlId, orderId, params) : query(sqlId, params);
		}
	}


	/**
	 * 通用查询service方法
	 * @param sqlId sql配置文件中sqlid
	 * @param params 参数列表
	 * @return 查询结果
	 */
	public List<Map<String,Object>> query (String sqlId, List<Object> params) {
//		long bTime = new Date().getTime();
		List<Map<String,Object>> rtnList = null;

		rtnList = queryProcess.process(sqlId, params);
		rtnList = constConvertUtil.convert(sqlId, rtnList);
//		log.debug("--> 查询数据耗时：" + (new Date().getTime() - bTime) + "毫秒，sqlId=["+sqlId+"]");

		return rtnList;
	}

	public List<Map<String,Object>> query (String sqlId, String whereId, List<Object> params) {
//		long bTime = new Date().getTime();
		List<Map<String,Object>> rtnList = null;

		rtnList = queryProcess.process(sqlId, whereId, params);
		rtnList = constConvertUtil.convert(sqlId, rtnList);
//		log.debug("--> 查询数据耗时：" + (new Date().getTime() - bTime) + "毫秒，sqlId=["+sqlId+"]");

		return rtnList;
	}

	@Override
	public List<Map<String,Object>> query (String sqlId,String whereId, String orderId, List<Object> params) {
//		long bTime = new Date().getTime();
		List<Map<String,Object>> rtnList = null;

		rtnList = queryProcess.process(sqlId, whereId, orderId, params);
		rtnList = constConvertUtil.convert(sqlId, rtnList);
//		log.debug("--> 查询数据耗时：" + (new Date().getTime() - bTime) + "毫秒，sqlId=["+sqlId+"]");

		return rtnList;
	}

	@Override
	public List<Map<String, Object>> queryForOrder (String sqlId, String orderId, List<Object> params) {
//		long bTime = new Date().getTime();
		List<Map<String,Object>> rtnList = null;

		rtnList = queryProcess.processForOrderId(sqlId, orderId, params);
		rtnList = constConvertUtil.convert(sqlId, rtnList);
//		log.debug("--> 查询数据耗时：" + (new Date().getTime() - bTime) + "毫秒，sqlId=["+sqlId+"]");

		return rtnList;
	}

	@Override
	public String queryValue (String sqlId, List<Object> params) {
//		long bTime = new Date().getTime();
		String rtnStr = null;
		List<Map<String,Object>> rtnList = query(sqlId,params);

		if (rtnList != null && rtnList.size() > 0) {
			Map<String,Object> map = rtnList.get(0);
			Object[] values = map.values().toArray();
			if (values[0] != null) {
				rtnStr = Tools.toStr(values[0]);
			}
		}
//		log.debug("--> 查询数据耗时：" + (new Date().getTime() - bTime) + "毫秒，sqlId=["+sqlId+"]");
		return rtnStr;
	}
}
