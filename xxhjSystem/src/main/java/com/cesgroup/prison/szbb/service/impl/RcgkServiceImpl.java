package com.cesgroup.prison.szbb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.zfxx.SzbbHttpClient;
import com.cesgroup.prison.szbb.dao.RcgkDao;
import com.cesgroup.prison.szbb.entity.Rcgk;
import com.cesgroup.prison.szbb.service.RcgkService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 数字冰雹数据对接ServiceImpl
 * 1.日常管控 
 * @author zhou.jian
 * @date 2019-2-28
 */
@Service("rcgkService")
@Transactional
public class RcgkServiceImpl extends BaseDaoService<Rcgk,String,RcgkDao> implements RcgkService{

	
	@Resource
	private RcgkDao rcgkDao;
	
    private int getValue(Object tmp) {
        int total = 0;
        if (tmp instanceof BigDecimal) {
            total = ((BigDecimal) tmp).intValue();
        } else if (tmp instanceof Long) {
            total = ((Long) tmp).intValue();
        } else if (tmp instanceof Integer) {
            total = (Integer) tmp;
        }
        return total;
    }
    
	/**
	 * 1.今日值班信息接口
	 * @return 结果集
	 */
	@Override
	public void getJrzbList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		// 1.1.值班长集合
		List<Map<String, Object>> DutyLeaderList = rcgkDao.getJrzbzList(param);
		// 1.2.当日值班主任信息 
		List<Map<String, Object>> DutyDirectorList = rcgkDao.getJrzbzrList(param);
		// 1.3.当日指挥中心值班员信息
		List<Map<String, Object>> DutyStaffList = rcgkDao.getJrzhzxzbyList(param);
		// 1.4.当日值班特警信息 无数据源
		List<Map<String, Object>> DutySwatList = new ArrayList<Map<String, Object>>();//rcgkDao.getJrzbtjList();
		// 1.5.当日其他值班人员信息 无数据源
		List<Map<String, Object>> DutyOthersList = new ArrayList<Map<String, Object>>();//rcgkDao.getJrqtzbryList();
		// 1.6.当日监区执勤警力信息
		List<Map<String, Object>> DutyPoliceList = rcgkDao.getJrjqzqjlList(param);
		
		//推送数据的当前时间
		String Time = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("DutyLeaderList", DutyLeaderList);
		map.put("DutyDirectorList", DutyDirectorList);
		map.put("DutyStaffList", DutyStaffList);
		map.put("DutySwatList", DutySwatList);
		map.put("DutyOthersList", DutyOthersList);
		map.put("DutyPoliceList", DutyPoliceList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entityJrzb(param, newParams);
			System.out.println(" 【1.1.今日值班】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 2.今日概况信息接口
	 * @return 结果集
	 */
	@Override
	public void getJrgk(String jyCode){
		try {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("jyCode", jyCode);
			//今日防控等级  PACLevel
			String PACLevel =  rcgkDao.getJrfkdj(param);
			//执勤警力人数
			Integer DutyPoliceCount = rcgkDao.getJrjyzqjlrs(param);
			//在册罪犯人数
			List<Map<String,Object>> registeredCriminalCountList = rcgkDao.getJrzczfrs(param);
			//今日罪犯人数
			Integer RegisteredCriminalCount = getValue(registeredCriminalCountList.get(0).get("ITEMS_VALUE"));
			List<Map<String,Object>> timeLine = rcgkDao.getJrgkzfsjzList(param);
			//罪犯时间轴
			List<Map<String,Object>> CriminalTimeAxisList = new ArrayList<Map<String,Object>>();
			Map<String, Object> time = new HashMap<String,Object>();
			String date = (String)registeredCriminalCountList.get(0).get("D_URL_TIME");
			for (int i = 0, j = 0; i < timeLine.size();) {
				if (!date.equals((String)timeLine.get(i).get("D_URL_TIME"))) {
					time.put("TIME", date);
					CriminalTimeAxisList.add(this.convertTimeLine(time));
					j++;
					date = (String)registeredCriminalCountList.get(j).get("D_URL_TIME");
					time = new HashMap<String,Object>();
				} else {
					time.put((String)timeLine.get(i).get("ITEMS_ID"), timeLine.get(i).get("ITEMS_VALUE"));
					i++;
				}
			}
			
			//推送数据的当前时间
			String curDate = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("PACLevel", PACLevel);
			map.put("DutyPoliceCount", DutyPoliceCount);
			map.put("RegisteredCriminalCount", RegisteredCriminalCount);
			map.put("CriminalTimeAxisList", CriminalTimeAxisList);
			map.put("Time", curDate);
		 
			Gson gson = new Gson();
			//最终数据结果转换成json格式
			JsonObject newParams = new JsonObject();
			String jsonMap = gson.toJson(map);
			newParams.addProperty("json", jsonMap);
			String obj = SzbbHttpClient.entityJrgk(param, newParams);
			System.out.println(" 【1.2.今日概况】 --> " + obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String,Object> convertTimeLine(Map<String,Object> time) {
		Map<String, Object> map = new HashMap<String,Object>();
		//日期	Date
		map.put("Date", time.get("TIME"));
		//在册	RegisteredCriminalCount
		map.put("RegisteredCriminalCount", time.get("ZFXX_ZCRS"));
		//实押	ActualCriminalCount
		//实押=在册-解回再审-暂予监外执行-离监探亲-社会医院-脱逃-寄押+暂押
		int sy = getValue(time.get("ZFXX_ZCRS")) - getValue(time.get("ZFXX_THDJ")) - getValue(time.get("ZFXX_JWZX"))
				 - getValue(time.get("ZFXX_LJTQ")) - getValue(time.get("ZFXX_JZZY")) - getValue(time.get("ZFXX_JYZF")) + getValue(time.get("ZFDD_ZYZF"));
		map.put("ActualCriminalCount", sy);
		//暂予狱外执行	ExecutionOutsidePrisonCount
		map.put("ExecutionOutsidePrisonCount", time.get("ZFXX_JWZX"));
		//外出就医	OutsideToDoctorCount
		map.put("OutsideToDoctorCount", 0);
		//暂押	TempDetainCount
		map.put("TempDetainCount", time.get("ZFDD_ZYZF"));
		//离监探亲	VisitFamilyCount
		map.put("VisitFamilyCount", time.get("ZFXX_LJTQ"));
		//保外就医	MedicalParoleCount
		map.put("MedicalParoleCount", time.get("ZFXX_JZZY"));
		//解回在押	ReDetainCount
		map.put("ReDetainCount", time.get("ZFXX_THDJ"));
		//当日收押	DayDetainCount
		map.put("DayDetainCount", time.get("ZFDD_SYZF"));
		//当日调入	DayTransferInCount
		map.put("DayTransferInCount", time.get("ZFDD_DRZF"));
		//当日释放	DayReleaseCount
		map.put("DayReleaseCount", time.get("ZFDD_SFZF"));
		//当日死亡	DayDeadCount
		map.put("DayDeadCount", time.get("ZFDD_SWZF"));
		//当日遣送	DayRepatriateCount
		map.put("DayRepatriateCount", time.get("ZFDD_QSZF"));
		//当日调出	DayTransferOutCount
		map.put("DayTransferOutCount", time.get("ZFDD_DCZF"));
		//其他	DayOtherCount
		map.put("DayOtherCount", time.get("ZFDD_QTZF"));
		return map;
	}

	/**
	 * 3.区域管控信息接口
	 * @return 结果集
	 * @throws RESTHttpClientException 
	 */
	@Override
	public void getQygkList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> regionalControlList = rcgkDao.getQygkList(param);
		
		String Time = Util.getCurrentDate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("RegionalControlList", regionalControlList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entityQygk(param, newParams);
			System.out.println(" 【1.3.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 4.重点罪犯、重控犯信息接口
	 * @return 结果集
	 */
	@Override
	public void getZdzfList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> ImportantCriminalList = rcgkDao.getZdzfxxList(param);
		List<Map<String, Object>> CriminalDetailList = rcgkDao.getZkfxxList(param);
		
		String Time = Util.getCurrentDate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ImportantCriminalList", ImportantCriminalList);
		map.put("CriminalDetailList", CriminalDetailList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entityZdzf(param, newParams);
			System.out.println(" 【1.4.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 5.大门出入管控信息接口
	 * @return 结果集
	 */
	@Override
	public void getDmcrList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		//5.1.大门出入管控信息接口 - 车辆进入数量
		int VehicleEnterCount = getValue(rcgkDao.getCljrList(param));
		//5.2.大门出入管控信息接口 - 车辆驶出数量
		int VehicleLeaveCount = getValue(rcgkDao.getClscList(param));
		//5.3.大门出入管控信息接口 - 人员进入数量
		int PeopleEnterCount = getValue(rcgkDao.getRyjrList(param));
		//5.4.大门出入管控信息接口 - 人员离开数量
		int PeopleLeaveCount = getValue(rcgkDao.getRylkList(param));
		//5.5.大门出入管控信息接口 - 设备检测集合
		List<Map<String, Object>> DeviceDetectList = rcgkDao.getSbjcList(param);
		
		String Time = Util.getCurrentDate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("VehicleEnterCount", VehicleEnterCount);
		map.put("VehicleLeaveCount", VehicleLeaveCount);
		map.put("PeopleEnterCount", PeopleEnterCount);
		map.put("PeopleLeaveCount", PeopleLeaveCount);
		map.put("DeviceDetectList", DeviceDetectList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		String obj = null;
		try {
			obj = SzbbHttpClient.entityDmcr(param, newParams);
			System.out.println(" 【1.5.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 6.人脸识别信息接口
	 * @return 结果集
	 */
	@Override
	public void getRlsbList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		Map<String, Object> maps = rcgkDao.getRlsb(param);
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(maps);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entityRlsb(param, newParams);
			System.out.println(" 【1.6.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 7.周界安防信息接口
	 * @return 结果集
	 */
	@Override
	public void getZjafList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> BorderStateList = rcgkDao.getZjafList(param);
		
		String Time = Util.getCurrentDate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("BorderStateList", BorderStateList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entityZjaf(param, newParams);
			System.out.println(" 【1.7.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 8.手机管控信息接口
	 * @return 结果集
	 */
	@Override
	public void getSjgkList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> MobileControlList = rcgkDao.getSjgkList(param);
		
		String Time = Util.getCurrentDate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("MobileControlList", MobileControlList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entitySjgk(param, newParams);
			System.out.println(" 【1.8.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 9.狱外押解任务信息接口
	 * @return 结果集
	 */
	@Override
	public void getYwyjrwList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		String Criminal = rcgkDao.getZfxm(param);
		String Destination = rcgkDao.getMdd(param);
		List<Map<String, Object>> PoliceList = rcgkDao.getMjxxList(param);
		List<Map<String, Object>> PlanRoute = rcgkDao.getGhlxList(param);
		
		String Time = Util.getCurrentDate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("Criminal", Criminal);
		map.put("Destination", Destination);
		map.put("PoliceList", PoliceList);
		map.put("PlanRoute", PlanRoute);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entityYwyjrw(param, newParams);
			System.out.println(" 【1.9.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		
		
	}


	/**
	 * 10.狱外押解车辆实时位置信息接口
	 * @return 结果集
	 */
	@Override
	public void getYwyjclwzList(String jyCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("jyCode", jyCode);
		List<Map<String, Object>> PositionList = rcgkDao.getYwyjclwzList(param);
		
		String Time = Util.getCurrentDate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PositionList", PositionList);
		map.put("Time", Time);
		
		Gson gson = new Gson();
		//最终数据结果转换成json格式
		JsonObject newParams = new JsonObject();
		String jsonMap = gson.toJson(map);
		newParams.addProperty("json", jsonMap);
		
		String obj = null;
		try {
			obj = SzbbHttpClient.entityYwyjclwz(param, newParams);
			System.out.println(" 【1.10.区域管控信息接口】 --> " + obj);
		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
	}
}
