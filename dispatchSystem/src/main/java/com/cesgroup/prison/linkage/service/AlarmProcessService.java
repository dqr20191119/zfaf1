package com.cesgroup.prison.linkage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.alarm.messager.bean.AlarmMessageBean;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.service.AlarmService;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.constants.alarm.AlarmConstants;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.fm.service.AMessageProcess;
import com.cesgroup.prison.jfsb.dao.AlertorMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.sql.service.IQueryService;
import com.cesgroup.prison.xxhj.jndt.dao.JndtMapper;
import com.cesgroup.prison.xxhj.jndt.entity.JndtEntity;

/**
 * cesgroup
 * 报警处理类
 * @author lihh
 *
 */
@Service
public class AlarmProcessService  extends AMessageProcess<AlarmMessageBean> {
	private static final Logger logger = LoggerFactory.getLogger(AlarmProcessService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//报警
	private static final String ALARM="1";
	public final static String systemUser = "-1";
		
	@Resource
	private IQueryService queryService;
	
	//新框架中的alarmService 处理类
	@Resource
	private AlarmService alarmService;
	
	@Resource
	private JndtMapper jndtMapper;
	
	@Resource
	private MqMessageSender mqMessageSender;
	
	/**
	 * 报警设备数据库访问类
	 */
	@Resource
	private AlertorMapper alertorMapper;
	
	protected void process(MsgHeader msgHead, AlarmMessageBean msgBody) {
		switch (msgHead.getMsgType()) {
			case AlarmConstants.ALARM_MSG_001://// 报警消息
				processAlarm(msgHead.getCusNumber(), msgBody);
				break;
			case AlarmConstants.ALARM_MSG_003:// 报警器状态
				//updateAlertorStatus(msgBody);
	        	//updateDeviceCount(msgHead.getCusNumber());
				break;
			case AlarmConstants.ALARM_MSG_002:// 布防、撤防消息
				//infraredWork(msgHead.getCusNumber(), msgBody);
				break;
		}
	}
	
	
	/**
	 * 报警消息处理
	 * @param cusNumber
	 * @param
	 */
	@Transactional(rollbackFor = Exception.class)
	public void processAlarm(String cusNumber, AlarmMessageBean alarmBean){
		//获取报警记录编号
		List<Object> parasList = new ArrayList<Object>();
		parasList.add(cusNumber);
		parasList.add(alarmBean.getAlarmDeviceType());
		//获取报警等级
		String level = queryService.queryValue(AlarmConstants.ALARM_LEVEL_QUERY, parasList);
		
    	//根据消息队列传来的报警器ID，到报警器信息表中，查询该报警器在本系统中的主键ID
		String alertorId = this.queryAlertorIdByAlarmBean(alarmBean,cusNumber);
		
		//根据alertorId是否为空做不同的处理
		if(Util.isNull(alertorId)) {
			logger.error("根据消息队列传来的报警器ID，到报警器信息表中，查询该报警器在本系统中的主键ID，得到的结果为空,即该报警设备在本系统中未初始化====监狱编号为:"+cusNumber
                                  + "报警信息为====="+alarmBean.toString());
			return;
		} else {
			//将报警设备在本系统中的主键赋予AlarmMessageBean，替换掉报警设备在第三方系统中的ID
			alarmBean.setAlarmID(alertorId);
		}

        //目前只有报警才进行处理和信息推送到前台、取消报警仅改数据库的报警状态
        if (ALARM.equals(alarmBean.getAlarmAction())) {
    		boolean flage = checkAlarmTime(cusNumber,level,alarmBean);
    		if(flage){
    			logger.info("两次报警时间间隔小于120秒报警记录舍弃");
    		}else{   
    			
    			//插入报警记录
    			//String x =  new java.util.Random().netInt(max - min) + min;//随机 min  与max  之间的数
    			String recordId =  String.valueOf(new java.util.Random().nextInt(10000));//设置随机数
    			AlarmRecordEntity alarmRecordEntity =  insertAlarm(cusNumber,recordId,level,alarmBean);
    			
    			// 插入监内动态
    			insertJndt(alarmRecordEntity);
    			
    			if(alarmRecordEntity != null && alarmRecordEntity.getId() != null) {
    				try {
            			
    					/*//String alarmMsg = alarmService2.handle(cusNumber,alarmBean.getAlarmID(), alarmRecordEntity.getId(), null,alarmBean.getAlarmDeviceType());
    					//String alarmMsg_new = JSONObject.toJSONString(alarmRecordEntity);
    					WebSocketMessage webSocketMessage = new WebSocketMessage();
    					webSocketMessage.setSendType("1");
    					webSocketMessage.setSendTo(cusNumber);
            			webSocketMessage.setMsgType(AlarmConstants.ALARM_MSG_3001);
            			webSocketMessage.setContent(JSONObject.toJSONString(alarmRecordEntity));
            			// 向前台发送消息
            			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), cusNumber);
            			// 为了省局演示的临时代码
            			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), "6500");
            			//RedisCache.putHash(alarmMsaMap, recordId, alarmMsg);*/  
    					
            			// 向前台发送消息
            			Map<String, String> msgMap = new HashMap<String, String>();
            			msgMap.put("msgType", IMsgTypeConst.CURRENT_ALERT);
            			msgMap.put("sendType", "1");
            			msgMap.put("sendTo", cusNumber + ","+AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG);           			
            			msgMap.put("content", JSONObject.toJSONString(alarmRecordEntity));
             			msgMap.put("isSendToGzt", "0");
             			msgMap.put("url", null);
             			msgMap.put("ywId", null);
            			msgMap.put("cusNumber", cusNumber);
            			MessageSendFacade.send(msgMap);
            		} catch (Exception e) {
            			logger.error("", e);
            		}
    			}
        		
        		logger.info("报警消息处理完成：");
    		}
    		
        }else{
        	//更新报警记录状态
        	updateAlarmStatus(alarmBean);
        }
	}
	/**
	 *  过滤同一个报警器120秒内报警记录
	 * @param
	 * @param alarmBean
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean checkAlarmTime(String cusNumber, String level, AlarmMessageBean alarmBean) {
		List<Object> params = new ArrayList<Object>();
		params.add(cusNumber);	
		params.add(alarmBean.getAlarmID());				// 报警设备编号	
		params.add(alarmBean.getAlarmTime());			// 报警时间
		List<Map<String, Object>> data = queryService.query("query_cds_alert_record_dtls_max120munite","0","0",params);
	    if(data.isEmpty()){
	    	return false;	
	    }else{
	    	return true;
	    }		
	}
	/**
	 *  插入报警记录表
	 * @param recordId
	 * @param alarmBean
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public AlarmRecordEntity insertAlarm(String cusNumber, String recordId, String alarmLevel, AlarmMessageBean alarmBean) {
		AlarmRecordEntity alarmRecordEntity =  null;
		try {
			alarmRecordEntity =  new AlarmRecordEntity();
			alarmRecordEntity.setArdCusNumber(cusNumber);// 监狱机构号
			alarmRecordEntity.setArdBsnsDate(new Date());// 报警记录日期
			alarmRecordEntity.setArdTypeIndc(alarmBean.getAlarmDeviceType());// 报警设备类型
			alarmRecordEntity.setArdAlarmTypeIndc(alarmBean.getAlarmType());// 报警类型
			alarmRecordEntity.setArdAlertorIdnty(alarmBean.getAlarmID());//设备编号
			alarmRecordEntity.setArdAlertLevelIndc(alarmLevel);// 报警等级
			alarmRecordEntity.setArdAlertTime(new Date());// 报警时间
			alarmRecordEntity.setArdAlertSttsIndc(alarmBean.getAlarmAction());// 报警状态
			alarmRecordEntity.setArdOprtnSttsIndc(AlarmConstants.ARD_OPRTN_STTS_1);// 报警处置状态
			alarmRecordEntity.setArdRemark(alarmBean.getRemark());// 报警备注
			alarmRecordEntity.setArdCrteUserId(systemUser);
			alarmRecordEntity.setArdUpdtUserId(systemUser);
			alarmRecordEntity.setArdCrteTime(new Date());
			alarmRecordEntity.setArdUpdtTime(new Date());
			
			alarmService.insert(alarmRecordEntity);
			logger.info("添加报警记录状态成功!");
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("添加报警记录状态失败!");
		}
		return alarmRecordEntity;

		
	}
	
	
	
	/**
	 * 更新报警记录状态
	 * @param
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateAlarmStatus (AlarmMessageBean alarmBean) {
		List<Object> parasList=new ArrayList<Object>();
		parasList.add(alarmBean.getAlarmAction());
		parasList.add(systemUser);
		parasList.add(alarmBean.getAlarmID());
		//int i = updateService.update(AlarmConstants.ALARM_RECORD_STATUS_UPDATE, parasList);
		//if (i > 0) {
		//	logger.info("更新报警记录状态");
		//	return true;
		//} else {
		//	return false;
		//}
		return false;
	}

	public void insertJndt(AlarmRecordEntity alarmRecordEntity) {
		
		JndtEntity jndtEntity = new JndtEntity();
		jndtEntity.setParCusNumber(alarmRecordEntity.getArdCusNumber());
		jndtEntity.setParOutCategory("BJ");
		jndtEntity.setParOutReason(dateFormat.format(alarmRecordEntity.getArdBsnsDate()) + "发生报警！");
		jndtEntity.setParStartTime(alarmRecordEntity.getArdBsnsDate());
		jndtEntity.setParRemark(alarmRecordEntity.getId());
		jndtEntity.setParStartCrteTime(new Date());
		jndtMapper.insert(jndtEntity);
	}
	
	@Override
	public Class<AlarmMessageBean> getBodyClass() {
		return AlarmMessageBean.class;
	}

	/**
	 * 根据AlarmMessageBean到数据库中查询报警设备的主键ID
	 * 
	 * @param alarmBean
	 * @return
	 */
	private String queryAlertorIdByAlarmBean(AlarmMessageBean alarmBean,String cusNumber) {
		String alertorId = "";
		if(alarmBean != null && Util.notNull(alarmBean.getAlarmID())) {
    		List<AlertorEntity> alertorEntityList = this.alertorMapper.findByAbdIdnty(alarmBean.getAlarmID(),cusNumber);
    		if(alertorEntityList != null && alertorEntityList.size() > 0) {
    			alertorId = alertorEntityList.get(0).getId();
    		}
    	}
		return alertorId;
	}
}
