package com.cesgroup.prison.linkage.service;

import com.alibaba.fastjson.JSONObject;

public interface IAlarmService {

	/**
	 * 人工报警
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String manualAlarm(JSONObject params) throws Exception;

	
	public String manualHandle(String cusNumber, String areaId, String alarmRecordId, String oprtnStatus,String alarmType) throws Exception ;

	/**
	 * 报警处置
	 * @param cusNumber 机构编号
	 * @param dvcIdnty	报警器编号
	 * @param alarmRecordId	报警记录编号
	 * @param oprtnStatus 处理状态
	 * @param alarmType 报警类型
	 * @return json字符串
	 * @throws Exception
	 */
	String handle(String cusNumber,String dvcIdnty, String alarmRecordId, String oprtnStatus,String alarmType)throws Exception;
	
	/**
	 * 更改接警状态
	 * @param cusNumber  机构号
	 * @param recordId  报警记录编号
	 * @param receiveStatus  接警状态
	 * @return
	 * @throws Exception
	 */
	String changeReceiveStatus(String cusNumber,String userId,String recordId,String receiveStatus)throws Exception;
	
	/**
	 * 接警
	 * @param params {cusNumber,alarmRecordId,eventId,eventType,
	 * 					alarmPolice,localCase,oprtnDesc,flag,subType,receivePolice}
	 * @return
	 * @throws Exception
	 */
	String receive(JSONObject params)throws Exception;
	
	/**
	 * 人工报警信息保存
	 * @param params
	 * @return
	 * @throws Exception
	 */
	String send(JSONObject params)throws Exception;
	
	/**
	 *报警性质保存
	 * @param params
	 * @return
	 * @throws Exception
	 */
	String addFlowData(JSONObject params)throws Exception;
	
	/**
	 *报警类型添加
	 * @param params
	 * @return
	 * @throws Exception
	 */
	String addAlarmType(JSONObject params)throws Exception;
	
	/**
	 *报警类型修改
	 * @param params
	 * @return
	 * @throws Exception
	 */
	String updateAlarmType(JSONObject params)throws Exception;
	
	/**
	 * 删除报警类型
	 * @param cusNumber
	 * @param alarmTypeId
	 * @return
	 * @throws Exception
	 */
	String deleteAlarmType(String cusNumber,String alarmTypeId)throws Exception;

}
