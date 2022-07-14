package com.cesgroup.prison.szbb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.zfxx.FxpgHttpClient;
import com.cesgroup.prison.szbb.dao.FxpgDao;
import com.cesgroup.prison.szbb.entity.Fxpg;
import com.cesgroup.prison.szbb.service.FxpgService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * 数字冰雹数据对接 ServiceImpl
 * 3.安全风险评估 
 * @author zhou.jian
 * @date 2019-03-01
 */
@Service("fxpgService")
@Transactional
public class FxpgServiceImpl extends BaseDaoService<Fxpg,String,FxpgDao> implements FxpgService{

	
	@Resource
	private FxpgDao fxpgDao;
	
	/**
	 * 1.监狱排名
	 */
	@Override
	public void getJypm(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> RankingList = fxpgDao.getJypmList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("RankingList", RankingList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityJypm(param, newParams);
			System.out.println(" 【3.1.监狱排名】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 2.风险清单
	 */
	@Override
	public void getFxqd(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		//已发风险集合
		List<Map<String, Object>> OccurredRiskList = fxpgDao.getJffxList(param);
		//近期风险集合
		List<Map<String, Object>> RecentRiskList = fxpgDao.getJqfxList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("OccurredRiskList", OccurredRiskList);
		map.put("RecentRiskList", RecentRiskList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityFxqd(param, newParams);
			System.out.println(" 【3.2.风险清单】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 3.频发风险
	 */
	@Override
	public void getPffx(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		//已发风险集合
		List<Map<String, Object>> FrequentlyRiskList = fxpgDao.getPffxList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("FrequentlyRiskList", FrequentlyRiskList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityPffx(param, newParams);
			System.out.println(" 【3.3.频发风险】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 4.风险等级
	 */
	@Override
	public void getFxdj(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> EvaluateList = fxpgDao.getFxdjList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("EvaluateList", EvaluateList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityFxdj(param, newParams);
			System.out.println(" 【3.4.风险等级】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 5.全部风险点
	 */
	@Override
	public void getQbfxd(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> AllRiskPointList = fxpgDao.getAllfxdList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("AllRiskPointList", AllRiskPointList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityQbfxd(param, newParams);
			System.out.println(" 【3.5.全部风险点】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 6.当前发生风险点
	 */
	@Override
	public void getDqfxd(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> CurrentRiskList = fxpgDao.getDqfxdList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("CurrentRiskPointList", CurrentRiskList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityDqfxd(param, newParams);
			System.out.println(" 【3.6.当前发生风险点】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 7.当前发生风险详情
	 */
	@Override
	public void getDqfxxq(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> CurrentRiskDetailList = fxpgDao.getDqfsfxList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("CurrentRiskDetailList", CurrentRiskDetailList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityDqfxxq(param, newParams);
			System.out.println(" 【3.7.当前发生风险详情】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 8.网格风险
	 */
	@Override
	public void getWgfx(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> GridRiskDetailList = fxpgDao.getWgfxList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("GridRiskDetailList", GridRiskDetailList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityWgfx(param, newParams);
			System.out.println(" 【3.8.网格风险】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 9.网格风险评估
	 */
	@Override
	public void getWgfxpg(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> GridEvaluateList = fxpgDao.getWgpgList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("GridEvaluateList", GridEvaluateList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityWgfxpg(param, newParams);
			System.out.println(" 【3.9.网格风险评估】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 10.网格扣分
	 */
	@Override
	public void getWgkf(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> GridDeductionList = fxpgDao.getWgkfList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("GridDeductionList", GridDeductionList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = FxpgHttpClient.entityWgkf(param, newParams);
			System.out.println(" 【3.10.网格扣分】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}

}
