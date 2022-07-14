package com.cesgroup.prison.szbb.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.cesgroup.prison.httpclient.zfxx.LdglHttpClient;
import com.cesgroup.prison.szbb.dao.LdglDao;
import com.cesgroup.prison.szbb.entity.Ldgl;
import com.cesgroup.prison.szbb.service.LdglService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * 数字冰雹数据对接 ServiceImpl
 * 4.领导管理驾驶舱
 * @author zhou.jian
 * @date 2019-03-01
 */
@Service("ldglService")
@Transactional
public class LdglServiceImpl extends BaseDaoService<Ldgl,String,LdglDao> implements LdglService{


	@Resource
	private LdglDao LdglDao;
	
	private int getValue(Object value) {
		int total = 0;
		if (value instanceof BigDecimal) {
			total = ((BigDecimal)value).intValue();
		} else if (value instanceof Long) {
			total = ((Long)value).intValue();
		} else if (value instanceof Integer) {
			total = (Integer)value;
		}
		return total;
	}
	
	
	/**
	 * 1.领导管理驾驶舱 - 罪犯变化
	 */
	public void getZfbh(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> CriminalDistributeList = LdglDao.getZffbList(param);
		List<Map<String, Object>> CriminalTrendList = LdglDao.getZfbhList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("CriminalDistributeList", CriminalDistributeList);
		map.put("CriminalTrendList", CriminalTrendList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityZfbh(param, newParams);
			System.out.println(" 【4.1.领导管理驾驶舱 - 罪犯变化】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 2.领导管理驾驶舱 - 罪犯年龄对比
	 */
	@Override
	public void getZfnldb(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> CriminalAgeList = LdglDao.getFznldbList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("CriminalAgeList", CriminalAgeList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityZfnldb(param, newParams);
			System.out.println(" 【4.2.领导管理驾驶舱 - 罪犯年龄对比】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 3.领导管理驾驶舱 - 罪犯类型
	 */
	public void getZflx(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> CriminalTypeList = LdglDao.getFzlxList(param);
		List<Map<String, Object>> SentenceTypeList = LdglDao.getXqfbList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("CriminalTypeList", CriminalTypeList);
		map.put("SentenceTypeList", SentenceTypeList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityZflx(param, newParams);
			System.out.println(" 【4.3.领导管理驾驶舱 - 罪犯类型】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 4.领导管理驾驶舱 - 队伍建设
	 */
	public void getDwjs(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> PoliceRateList = LdglDao.getJlbhlList(param);
		List<Map<String, Object>> PoliceTrendList = LdglDao.getJlbhqsList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PoliceRateList", PoliceRateList);
		map.put("PoliceTrendList", PoliceTrendList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityDwjs(param, newParams);
			System.out.println(" 【4.4.领导管理驾驶舱 - 队伍建设】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 5.领导管理驾驶舱 - 个别谈话
	 */
	public void getGbth(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		param.put("corp", SzbbUtil.getCorpByJyCode(jyCode));
		List<Map<String, Object>> MonthTalkList = LdglDao.getYptList(param);
		List<Map<String, Object>> TenTalkList = LdglDao.getSbtList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("MonthTalkList", MonthTalkList);
		map.put("TenTalkList", TenTalkList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityGbth(param, newParams);
			System.out.println(" 【4.5.领导管理驾驶舱 - 个别谈话】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 6.领导管理驾驶舱 - 亲情电话
	 */
	public void getQqdh(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> MeetAndCallList = LdglDao.getQqdhList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("MeetAndCallList", MeetAndCallList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityQqdh(param, newParams);
			System.out.println(" 【4.6.领导管理驾驶舱 - 亲情电话】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 7.领导管理驾驶舱 - 刑罚执行
	 */
	public void getXfzx(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> PenaltyList = LdglDao.getJxjsList(param);
		List<Map<String, Object>> ProsecutionList = LdglDao.getSkjList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PenaltyList", PenaltyList);
		map.put("ProsecutionList", ProsecutionList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityXfzx(param, newParams);
			System.out.println(" 【4.7.领导管理驾驶舱 - 刑罚执行】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 8.领导管理驾驶舱 - 劳动工具
	 */
	public void getLdgj(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> LaborToolList = LdglDao.getLdgjList(param);
		List<Map<String, Object>> LaborToolUsedList = LdglDao.getSyqkList(param);
		int LaborToolCount = 0;
		for (Map<String, Object> map : LaborToolList) {
			LaborToolCount = LaborToolCount + getValue(map.get("Count"));
		}
		DecimalFormat df = new DecimalFormat("0.00");
		for (Map<String, Object> map : LaborToolList) {
			map.put("Proportion", df.format((double)getValue(map.get("Count")) / (double)LaborToolCount));
		}
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("LaborToolCount", LaborToolCount);
		map.put("LaborToolList", LaborToolList);
		map.put("LaborToolUsedList", LaborToolUsedList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);

		String obj = null;
		try {
			obj = LdglHttpClient.entityLdgj(param, newParams);
			System.out.println(" 【4.8.领导管理驾驶舱 - 劳动工具】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 9.领导管理驾驶舱 - 大宗物品
	 */
	public void getDzwp(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> ExpensesList = LdglDao.getJfzcList(param);
		List<Map<String, Object>> DietList = LdglDao.getYsjgkList(param);
		List<Map<String, Object>> PurchaseList = LdglDao.getCmList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ExpensesList", ExpensesList);
		map.put("DietList", DietList);
		map.put("PurchaseList", PurchaseList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityDzwp(param, newParams);
			System.out.println(" 【4.9.领导管理驾驶舱 - 大宗物品】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 10.领导管理驾驶舱 - 就医统计
	 */
	public void getJytj(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> MedicalList = LdglDao.getJqjyList(param);
		List<Map<String, Object>> MedicalTrendList = LdglDao.getJyqsList(param);
		List<Map<String, Object>> ChronicList = LdglDao.getMbfxList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("MedicalList", MedicalList);
		map.put("MedicalTrendList", MedicalTrendList);
		map.put("ChronicList", ChronicList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityJytj(param, newParams);
			System.out.println(" 【4.10.领导管理驾驶舱 - 就医统计】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 11.领导管理驾驶舱 - 组织结构
	 */
	public void getZzjg(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> OrganizationList = LdglDao.getZzjgList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("OrganizationList", OrganizationList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = LdglHttpClient.entityZzjg(param, newParams);
			System.out.println(" 【4.11.领导管理驾驶舱 - 组织结构】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}

	
}
