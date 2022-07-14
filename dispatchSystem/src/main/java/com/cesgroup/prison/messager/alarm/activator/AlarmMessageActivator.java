package com.cesgroup.prison.messager.alarm.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;
import com.cesgroup.fm.MsgServiceMap;
import com.cesgroup.prison.common.constants.alarm.AlarmConstants;
import com.cesgroup.prison.fm.service.IMessageActivator;

/**
 * cesgroup
 * 接收前置机报警消息处理类
 * @author lihh
 */
@Service
public class AlarmMessageActivator implements IMessageActivator{
	private static final Logger log = LoggerFactory.getLogger(AlarmMessageActivator.class);
	private MsgDispatcher msgDispatcher;

	// 静态初始化消息处理服务映射
	static {
		try {
			//报警处理
			//MsgServiceMap.put(AlarmConstants.ALARM_MSG_001, "alarmMessageProcess");
			MsgServiceMap.put(AlarmConstants.ALARM_MSG_001, "alarmProcessService");
			
			MsgServiceMap.put(AlarmConstants.ALARM_MSG_002, "alarmMessageProcess");
			MsgServiceMap.put(AlarmConstants.ALARM_MSG_003, "alarmMessageProcess");
			MsgServiceMap.put(AlarmConstants.ALARM_MSG_004, "alarmMessageProcess");
			log.info("初始化前置消息服务映射：报警消息处理");
		} catch (Exception e) {
			log.error("初始化报警消息处理服务类映射失败：", e);
		}
	}

	/**
	 * 创建对象时同时创建消息分发器对象
	 */
	public AlarmMessageActivator(){
		msgDispatcher = new MsgDispatcher();
	}

    /**
     * 报警
     * @param message
     */
	@Override
	public void handle(String message, String cusNumber) {
		log.info("收到报警消息：cusNumber=" + cusNumber + ", msg=" + message);
		msgDispatcher.dispatch(cusNumber, message);
	}
}
