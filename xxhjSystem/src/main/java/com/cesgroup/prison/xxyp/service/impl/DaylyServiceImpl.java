package com.cesgroup.prison.xxyp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.prison.xxhj.jryf.dao.JryfMapper;
import com.cesgroup.prison.xxyp.dao.DaylyMapper;
import com.cesgroup.prison.xxyp.entity.Dayly;
import com.cesgroup.prison.xxyp.service.DaylyService;

@Service
public class DaylyServiceImpl extends BaseDaoService<Dayly,String,DaylyMapper> implements DaylyService	{
	//今日押犯
	@Autowired
	private JryfMapper  jryfMapper;
	
	@Override
	@Transactional
	public void initDaylyData(String cusNumber){
		deleteSameDay(cusNumber);
		String nowYMD = DateUtil.getDateString(new Date(), "yyyy-MM-dd");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cusNumber", cusNumber);
		logger.debug("查询参数 == " + params);
		String hour = "";
		//1、民警值班信息
		//queryPoliceBase(nowYMD,hour,1,params);
		//2、押犯基本情况
		queryPrisonerBase(cusNumber,nowYMD,hour,"2",params);
		//3、罪犯违规情况
		/*queryMisdeedsBase(nowYMD,hour,3,params); 
		//4、罪犯重点人头情况
		queryKeynoteBase(nowYMD,hour,4,params);
		//5、罪犯惩处、高危管控情况
		queryPunishmentBase(nowYMD,hour,5,params);
		//6、罪犯监内就医情况
		queryInprisHospitalBase(nowYMD,hour,6,params);
		//7、罪犯外出情况
		queryPrisonoutBase(nowYMD,hour,7,params);
		//10、外来车辆进出情况
		queryCarInoutBase(nowYMD,hour,10,params);
		//11、外来人员进出情况
		queryPeopleInoutBase(nowYMD,hour,11,params);
		//12、网络技防运行情况
		queryTechnicalBase(nowYMD,hour,12,params);
		//13、报警情况
		queryAlarmBase(nowYMD,hour,13,params);
		//14、安全生产情况
		queryProductionBase(nowYMD,hour,14,params);
		//15、舆情情况
		queryPublicSetntBase(nowYMD,hour,15,params);
		//16、上访信息
		queryPetitionBase(nowYMD,hour,16,params);
		//17、监内施工
		queryConstructionBase(nowYMD,hour,17,params);
		//18、监狱相关要求
		queryPrisonRequireBase(nowYMD,hour,18,params);
		//19、省局通报、要求
		queryProvCircularBase(nowYMD,hour,19,params);*/
	}
	@Override
	public List<Map<String,Object>> getDaylyData(Dayly dayly){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("pidDate", dayly.getPidDate());
		map.put("pidCusNumber", dayly.getPidCusNumber());
		return getDao().getDaylyData(map);
	}

	//删除当天的日报
	@Transactional
	public int deleteSameDay(String cusNumber) {
		return getDao().deleteSameDay(cusNumber);
	}
	
	/**
	 * 2、押犯基本情况
	 * @param nowYMD
	 * @param hour
	 * @param recordId
	 * @param params
	 */
	@Transactional
	public void queryPrisonerBase(String cusNumber,String nowYMD,String hour,String recordId,Map<String,Object> params){
		
		List<Map<String, Object>> list= jryfMapper.queryXJPrisonerCount(params);
		for (Map<String, Object> map : list) {
			BigDecimal key=(BigDecimal)map.get("LB");
			BigDecimal count =(BigDecimal)map.get("RS"); 
			String s_count=count.stripTrailingZeros().toPlainString();
			System.out.println(key+":"+s_count);
			//实际在押
			if (key.compareTo(new BigDecimal("1"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_1",s_count);	
			}
			//实押危安犯总数
			else if(key.compareTo(new BigDecimal("2"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_2",s_count);	
			}
			//加戴戒具罪犯数
			else if(key.compareTo(new BigDecimal("4"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_3",s_count);	
			}
			//关押禁闭罪犯数
			else if(key.compareTo(new BigDecimal("5"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_4",s_count);	
			}
			//隔离审查罪犯数
			else if(key.compareTo(new BigDecimal("6"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_5",s_count);	
			}
			//立案侦查罪犯数
			else if(key.compareTo(new BigDecimal("7"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_6",s_count);	
			}
			//解回重审罪犯数
			else if(key.compareTo(new BigDecimal("8"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_7",s_count);	
			}
			//暂予监外执行罪犯数
			else if(key.compareTo(new BigDecimal("9"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_8",s_count);	
			}
			//老病残罪犯数
			else if(key.compareTo(new BigDecimal("10"))==0) {                                             //实押罪犯总数
				addPrisonData(cusNumber,nowYMD,hour,recordId,"prisoner_9",s_count);	
			}
		}
		//在册罪犯
		/*List<Map<String,Object>> prisoner_list1 = queryService.query("cds_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_1",prisoner_list1);
		addProvData(nowYMD,hour,recordId,"prisoner_1",prisoner_list1);*/
		//实际在押
		/*HttpServletRequest request;
		prisonerTodayService.queryXJPrisonerCount(request);
		List<Map<String,Object>> prisoner_list2 = queryService.query("cds_inside_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_2",prisoner_list2);
		addProvData(nowYMD,hour,recordId,"prisoner_2",prisoner_list2);
		//今日解回再审
		List<Map<String,Object>> prisoner_list3 = queryService.query("cds_toDayRepeatcheck_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_9",prisoner_list3);
		addProvData(nowYMD,hour,recordId,"prisoner_9",prisoner_list3);
		//当前解回再审
		List<Map<String,Object>> prisoner_list4 = queryService.query("cds_repeatcheck_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_4",prisoner_list4);
		addProvData(nowYMD,hour,recordId,"prisoner_4",prisoner_list4);
		//当日释放罪犯人数
		List<Map<String,Object>> prisoner_list5 = queryService.query("cds_free_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_7",prisoner_list5);
		addProvData(nowYMD,hour,recordId,"prisoner_7",prisoner_list5);
		//当日收押罪犯人数
		List<Map<String,Object>> prisoner_list6 = queryService.query("cds_todayin_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_6",prisoner_list6);
		addProvData(nowYMD,hour,recordId,"prisoner_6",prisoner_list6);*/
		//男犯人数
		/*List<Map<String,Object>> prisoner_list7 = queryService.query("cds_man_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_2_man",prisoner_list7);
		addProvData(nowYMD,hour,recordId,"prisoner_2_man",prisoner_list7);*/
		//女犯人数
		/*List<Map<String,Object>> prisoner_list8 = queryService.query("cds_woman_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_2_woman",prisoner_list8);
		addProvData(nowYMD,hour,recordId,"prisoner_2_woman",prisoner_list8);*/
		//未成年犯人数
		/*List<Map<String,Object>> prisoner_list9 = queryService.query("cds_nonage_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_2_nonage",prisoner_list9);
		addProvData(nowYMD,hour,recordId,"prisoner_2_nonage",prisoner_list9);*/
		//监外执行罪犯人数
		/*List<Map<String,Object>> prisoner_list10 = queryService.query("cds_prisonOut_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_5_1",prisoner_list10);
		addProvData(nowYMD,hour,recordId,"prisoner_5_1",prisoner_list10);*/
		//在逃罪犯人数
		/*List<Map<String,Object>> prisoner_list11 = queryService.query("cds_runAway_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_3",prisoner_list11);
		addProvData(nowYMD,hour,recordId,"prisoner_3",prisoner_list11);*/
		//罪犯死亡人数
		/*List<Map<String,Object>> prisoner_list12 = queryService.query("cds_dead_prisoner_count_date", params);
		addPrisonData(nowYMD,hour,recordId,"prisoner_8",prisoner_list12);
		addProvData(nowYMD,hour,recordId,"prisoner_8",prisoner_list12);*/
	}
	@Transactional
	public void addPrisonData(String pidCusNumber,String nowYMD,String pidHour,String pidRecordId,String pidRecordItem,String pidRecordValue) {
		Dayly dayly=new Dayly();
		dayly.setPidCusNumber(pidCusNumber);
		dayly.setPidDate(DateUtil.getDate(nowYMD));
		dayly.setPidHour(pidHour);
		dayly.setPidRecordId(pidRecordId);
		dayly.setPidRecordItem(pidRecordItem);
		dayly.setPidRecordValue(pidRecordValue);
		getDao().insert(dayly);
	}
}
