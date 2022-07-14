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
import com.cesgroup.prison.httpclient.zfxx.ZszhHttpClient;
import com.cesgroup.prison.szbb.dao.ZszhDao;
import com.cesgroup.prison.szbb.entity.Zszh;
import com.cesgroup.prison.szbb.service.ZszhService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 数字冰雹数据对接 ServiceImpl
 * 2.战时指挥
 * @author zhou.jian
 * @date 2019-03-01
 */
@Service("zszhService")
@Transactional
public class ZszhServiceImpl extends BaseDaoService<Zszh,String,ZszhDao> implements ZszhService{

	@Resource
	private ZszhDao zszhDao;
	
	/**
	 * 1.战时指挥 - 现场警员
	 * @return
	 */
	public void getXcjyList(String jyCode){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> SpotPoliceList = zszhDao.getXcjyList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("SpotPoliceList", SpotPoliceList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entityXcjy(param, newParams);
			System.out.println(" 【2.1.战时指挥 - 现场警员】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 2.战时指挥 - 待援警员
	 * @return
	 */
	public void getDyjyList(String jyCode){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> SupportPoliceList = zszhDao.getDyjyList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("SupportPoliceList", SupportPoliceList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entityDyjy(param, newParams);
			System.out.println(" 【2.2.战时指挥 - 待援警员】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3.战时指挥 - 待命车辆
	 * @return
	 */
	public void getDmclList(String jyCode){ 
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> SupportVehicleList = zszhDao.getDmclList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("SupportVehicleList", SupportVehicleList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entityDmcl(param, newParams);
			System.out.println(" 【2.3.战时指挥 - 待命车辆】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 4.战时指挥 - 警务装备
	 * @return
	 */
	public void getJwzbList(String jyCode){ 
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> PoliceEquipmentList = zszhDao.getJwzbList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PoliceEquipmentList", PoliceEquipmentList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entityJwzb(param, newParams);
			System.out.println(" 【2.4.战时指挥 - 警务装备】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 5.战时指挥 - 事件信息
	 * @return
	 */
	public void getSjxx(String jyCode){ 
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		Map<String, Object> maps = zszhDao.getSjxx(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("PoliceEquipmentList", PoliceEquipmentList);
//		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(maps);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entitySjxx(param, newParams);
			System.out.println(" 【2.5.战时指挥 - 事件信息】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 6.战时指挥 - 事件流程
	 * @return
	 */
	public void getSjlcList(String jyCode){ 
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> ProcessList = zszhDao.getSjlcList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ProcessList", ProcessList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entitySjlc(param, newParams);
			System.out.println(" 【2.6.战时指挥 - 事件流程】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 7.战时指挥 - 当前流程
	 * @return
	 */
	public void getDqlc(String jyCode){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		Map<String, Object> maps = zszhDao.getDqlc(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("PoliceEquipmentList", PoliceEquipmentList);
//		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(maps);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entityDqlc(param, newParams);
			System.out.println(" 【2.7.战时指挥 - 当前流程】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 8.战时指挥 - 涉事部位
	 * @return
	 */
	public void getSsbm(String jyCode){ 
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		Map<String, Object> maps = zszhDao.getSsbm(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("PoliceEquipmentList", PoliceEquipmentList);
//		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(maps);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = ZszhHttpClient.entitySsbm(param, newParams);
			System.out.println(" 【2.8.战时指挥 - 涉事部位】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
	
}
