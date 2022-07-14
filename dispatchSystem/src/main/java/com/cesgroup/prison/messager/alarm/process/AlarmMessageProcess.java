package com.cesgroup.prison.messager.alarm.process;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.alarm.messager.bean.AlarmMessageBean;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.bean.WebSocketMessage;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.service.AlarmService;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.constants.alarm.AlarmConstants;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.fm.service.AMessageProcess;
import com.cesgroup.prison.linkage.service.IAlarmService;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.sql.service.IAddService;
import com.cesgroup.prison.sql.service.IQueryService;
import com.cesgroup.prison.sql.service.IUpdateService;
import com.cesgroup.scrap.db.SequenceUtil;
/**
 * cesgroup
 * 报警消息的处理类
 * @author lihh
 * @date 2017-12-30
 */
@Service
@Transactional
public class AlarmMessageProcess extends AMessageProcess<AlarmMessageBean> {
	final Logger logger = LoggerFactory.getLogger(AlarmMessageProcess.class);
	@Resource
	private IAddService addService;
	@Resource 
	private IUpdateService updateService;
	@Resource
	private IQueryService queryService;
	@Resource
	private SequenceUtil sequenceUtil;
	@Resource
	private IAlarmService alarmService2;
	@Resource
	private MqMessageSender mqMessageSender;
	
	//新框架中的alarmService 处理类
	@Resource
	private AlarmService alarmService;
	
	//报警
	private static final String ALARM="1";

	//存放未处理完的报警消息
	public final static String alarmMsaMap="alarm_msg_map";
	public final static String systemUser = "-1";

	@Override
	public Class<AlarmMessageBean> getBodyClass() {
		return AlarmMessageBean.class;
	}

	@Override
	protected void process(MsgHeader msgHead, AlarmMessageBean msgBody) {
		switch (msgHead.getMsgType()) {
			case AlarmConstants.ALARM_MSG_001://// 报警消息
				processAlarm(msgHead.getCusNumber(), msgBody);
				break;
			case AlarmConstants.ALARM_MSG_003:// 报警器状态
				updateAlertorStatus(msgBody);
	        	updateDeviceCount(msgHead.getCusNumber());
				break;
			case AlarmConstants.ALARM_MSG_002:// 布防、撤防消息
				infraredWork(msgHead.getCusNumber(), msgBody);
				break;
		}
	}
	/**
	 * 红外对射布防撤防
	 * @param cusNumber
	 * @param jSONObject
	 * @return 
	 */
	@Transactional(rollbackFor = Exception.class)
	private boolean infraredWork(String cusNumber, AlarmMessageBean alarmBean) {
		List<Object> parasList = new ArrayList<Object>();		
		parasList.add(alarmBean.getAction());
		parasList.add(cusNumber);
		parasList.add(alarmBean.getAlarmID());
		int i = updateService.update(AlarmConstants.INFRARED_WORK_UPDATE, parasList);
		if (i > 0) {
			logger.info("布防撤防成功");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 报警消息处理
	 * @param cusNumber
	 * @param jSONObject
	 */
	@Transactional(rollbackFor = Exception.class)
	public void processAlarm_test(String cusNumber, AlarmMessageBean alarmBean){
		
		try {
			//String alarmMsg = alarmService.handle(cusNumber,alarmBean.getAlarmID(), recordId, null,alarmBean.getAlarmType());
			WebSocketMessage webSocketMessage = new WebSocketMessage();
			webSocketMessage.setMsgType(AlarmConstants.ALARM_MSG_3001);
			webSocketMessage.setContent(JSONObject.toJSONString("{\"title\":\"test\"}"));
			// 向前台发送消息
			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), cusNumber);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		logger.info("报警消息处理完成：");
		
	}
	
	
	

	/**
	 * 报警消息处理
	 * @param cusNumber
	 * @param jSONObject
	 */
	@Transactional(rollbackFor = Exception.class)
	public void processAlarm(String cusNumber, AlarmMessageBean alarmBean){
		//获取报警记录编号
		//String recordId = sequenceUtil.querySequence("ARD_ALERT_RECORD_ID");
		
		//String x =  new java.util.Random().netInt(max - min) + min;//随机 min  与max  之间的数
		String recordId =  String.valueOf(new java.util.Random().nextInt(10000));//设置随机数
		
		List<Object> parasList = new ArrayList<Object>();
		//parasList.add(alarmBean.getAlarmType());
		parasList.add(alarmBean.getAlarmDeviceType());
		//获取报警等级
		String level = queryService.queryValue(AlarmConstants.ALARM_LEVEL_QUERY, parasList);

        //目前只有报警才进行处理和信息推送到前台、取消报警仅改数据库的报警状态
        if (ALARM.equals(alarmBean.getAlarmAction())) {        
    		boolean flage = checkAlarmTime(cusNumber,recordId,level,alarmBean);
    		if(flage){
    			logger.info("两次报警时间间隔小于120秒报警记录舍弃");
    		}else{    			
    			//插入报警记录
    			AlarmRecordEntity alarmRecordEntity =  insertAlarm(cusNumber,recordId,level,alarmBean);
    			if(alarmRecordEntity != null && alarmRecordEntity.getId() != null) {
    				try {
            			//String alarmMsg = alarmService2.handle(cusNumber,alarmBean.getAlarmID(), recordId, null,alarmBean.getAlarmType());
            			//String alarmMsg = alarmService2.handle(cusNumber,alarmBean.getAlarmID(), alarmRecordEntity.getId(), null,alarmBean.getAlarmDeviceType());
            			
    					JSONObject jsonObj = new JSONObject();
    					jsonObj.put("alarmRecordId", alarmRecordEntity.getId());
    					
    					WebSocketMessage webSocketMessage = new WebSocketMessage();
            			webSocketMessage.setMsgType(AlarmConstants.ALARM_MSG_3001);
            			webSocketMessage.setContent(JSONObject.toJSONString(jsonObj));
            			
            			// 向前台发送消息
            			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), cusNumber);
            			// 为了省局演示的临时代码
            			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG);
            			
            			// RedisCache.putHash(alarmMsaMap, recordId, alarmMsg);
            			RedisCache.putHash(alarmMsaMap, recordId, JSONObject.toJSONString(jsonObj));
            			
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
	 * @param recordId
	 * @param alarmBean
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	private boolean checkAlarmTime(String cusNumber, String recordId, String level, AlarmMessageBean alarmBean) {
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
	 * 废弃
	 *  插入报警记录表
	 * @param recordId
	 * @param alarmBean
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean insertAlarm_bak(String cusNumber, String recordId, String alarmLevel, AlarmMessageBean alarmBean) {
		String sqlId = AlarmConstants.CDS_ADD_ALARM_RECORD;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Object> params = null;

		params = new ArrayList<Object>();
		params.add(cusNumber);							// 监狱机构号
		params.add(recordId);							// 报警记录编号
		params.add(dateFormat.format(new Date()));		// 报警记录日期
		params.add(alarmBean.getAlarmDeviceType());		// 报警设备类型
		params.add(alarmBean.getAlarmType());			// 报警类型
		params.add("");									// 报警人
		params.add(alarmBean.getAlarmID());				// 报警设备编号
		params.add(alarmLevel);							// 报警等级
		params.add(alarmBean.getAlarmTime());			// 报警时间
		params.add(alarmBean.getAlarmAction());			// 报警状态
		params.add(AlarmConstants.ARD_OPRTN_STTS_1);	// 报警处置状态
		params.add("");									// 现场情况
		params.add(alarmBean.getRemark());				// 报警备注
		params.add(systemUser);							// 报警记录创建人
		params.add(systemUser);							// 报警记录更新人
		
		String id = UUID.randomUUID().toString().replaceAll("-", "");//系统主键
		params.add(id);//新主键
		
		
		

		int result = addService.insert(sqlId, params);
		if (result > 0) {
			logger.info("添加报警记录状态成功!");
			return true;
		} else {
			logger.info("添加报警记录状态失败!");
			return false;
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
	 * @param action
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateAlarmStatus (AlarmMessageBean alarmBean) {
		List<Object> parasList=new ArrayList<Object>();
		parasList.add(alarmBean.getAlarmAction());
		parasList.add(systemUser);
		parasList.add(alarmBean.getAlarmID());
		int i = updateService.update(AlarmConstants.ALARM_RECORD_STATUS_UPDATE, parasList);
		if (i > 0) {
			logger.info("更新报警记录状态");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新报警器基础信息表中的状态
	 * @param jSONObject
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateAlertorStatus(AlarmMessageBean alarmBean){
		String alarmID = alarmBean.getAlarmID();
		String status = alarmBean.getStatus();
		List<Object> parasList = new ArrayList<Object>();
		parasList.add(status);
		parasList.add(systemUser);
		parasList.add(alarmID);

		int i = updateService.update(AlarmConstants.ALERTOR_STATUS_UPDATE, parasList);
		if (i > 0) {
			logger.info("更新报警器基础信息表中的状态成功");
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新设备总数,异常总数,正常总数
	 * @param cusNumber
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateDeviceCount(String cusNumber) {
		List<Object> parasList=new ArrayList<Object>();
		parasList.add(cusNumber);
		parasList.add(cusNumber);
		parasList.add(cusNumber);
		parasList.add(systemUser);
		parasList.add(cusNumber);
		parasList.add(2);// 2--网络报警
		try {
			updateService.update("update_device_normal_abnormal_netAlertor", parasList);
		} catch (Exception e) {
			logger.error("更新设备状态失败", e);
		}
		
	}
	
	public void putAlarmMsg(String recordId,String msg){
		RedisCache.putHash(alarmMsaMap, recordId, msg);
	}
	
	public String getAlarmMsg(String recordId){
		String alarmMsg=null;
		alarmMsg=(String)RedisCache.getObject(alarmMsaMap, recordId);
		return alarmMsg;
	}
}
