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
import com.cesgroup.prison.xxyp.dao.ProvDaylyMapper;
import com.cesgroup.prison.xxyp.entity.ProvDayly;
import com.cesgroup.prison.xxyp.service.ProvDaylyService;

@Service
public class ProvDaylyServiceImpl extends BaseDaoService<ProvDayly,String,ProvDaylyMapper> implements ProvDaylyService	{
	//今日押犯
	@Autowired
	private JryfMapper  jryfMapper;
	
	@Override
	@Transactional
	public void initProvDaylyData() {
		deleteSameDay();
		String nowYMD = DateUtil.getDateString(new Date(), "yyyy-MM-dd");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cusNumber", "0");//0拿全省的数据，不知道为什么这么定义，null不是更好吗
		logger.debug("查询参数 == " + params);
		String hour = "";
		//2、押犯基本情况
		queryPrisonerBase(nowYMD,hour,"2",params);
	}

	/**
	 * 2、押犯基本情况
	 * @param nowYMD
	 * @param hour
	 * @param recordId
	 * @param params
	 */
	@Transactional
	public void queryPrisonerBase(String nowYMD,String hour,String recordId,Map<String,Object> params){
		
		List<Map<String, Object>> list= jryfMapper.queryXJPrisonerCount(params);
		for (Map<String, Object> map : list) {
			BigDecimal key=(BigDecimal)map.get("LB");
			BigDecimal count =(BigDecimal)map.get("RS"); 
			String s_count=count.stripTrailingZeros().toPlainString();
			System.out.println(key+":"+s_count);
			//实际在押
			if (key.compareTo(new BigDecimal("1"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_1",s_count);	
			}
			//实押危安犯总数
			else if(key.compareTo(new BigDecimal("2"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_2",s_count);	
			}
			//加戴戒具罪犯数
			else if(key.compareTo(new BigDecimal("4"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_3",s_count);	
			}
			//关押禁闭罪犯数
			else if(key.compareTo(new BigDecimal("5"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_4",s_count);	
			}
			//隔离审查罪犯数
			else if(key.compareTo(new BigDecimal("6"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_5",s_count);	
			}
			//立案侦查罪犯数
			else if(key.compareTo(new BigDecimal("7"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_6",s_count);	
			}
			//解回重审罪犯数
			else if(key.compareTo(new BigDecimal("8"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_7",s_count);	
			}
			//暂予监外执行罪犯数
			else if(key.compareTo(new BigDecimal("9"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_8",s_count);	
			}
			//老病残罪犯数
			else if(key.compareTo(new BigDecimal("10"))==0) {                                             //实押罪犯总数
				addProvData(nowYMD,hour,recordId,"prisoner_9",s_count);	
			}
		}
	}
	@Override
	public List<Map<String,Object>> getProvDaylyData(ProvDayly provDayly){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("pidDate", provDayly.getPidDate());
		return getDao().getProvDaylyData(map);
	}
	//删除当天的日报
	@Transactional
	public int deleteSameDay() {
		return getDao().deleteSameDay();
	}
	@Transactional
	public void addProvData(String nowYMD,String pidHour,String pidRecordId,String pidRecordItem,String pidRecordValue) {
		ProvDayly provDayly=new ProvDayly();
		provDayly.setPidDate(DateUtil.getDate(nowYMD));
		provDayly.setPidHour(pidHour);
		provDayly.setPidRecordId(pidRecordId);
		provDayly.setPidRecordItem(pidRecordItem);
		provDayly.setPidRecordValue(pidRecordValue);
		getDao().insert(provDayly);
	}
}
