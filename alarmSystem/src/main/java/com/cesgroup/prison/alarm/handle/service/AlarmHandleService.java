package com.cesgroup.prison.alarm.handle.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.param.AlarmRecordParam;
import com.cesgroup.prison.alarm.record.result.AlarmRecordResult;

public interface AlarmHandleService {

	/**
	 * @throws Exception 
	* @methodName: manualAlarm
	* @Description: 人工报警 添加报警信息
	* @param entity
	* @return String 报警记录id
	* @throws  
	*/
	public void manualAlarm(AlarmRecordEntity entity) throws Exception;

	/**
	 * @throws Exception
	 * @methodName: manualAlarmApi
	 * @Description: 人工报警 添加报警信息(接口提供)
	 * @param entity
	 * @return String 报警记录id
	 * @throws
	 */
	public AlarmRecordResult manualAlarmApi(AlarmRecordParam entity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: receive
	* @Description: 报警处置
	* @param entity 报警记录实体
	* @param subType   1 提交 2  上级处理
	* @param flag  0代表没有接警人，1代表有接警人
	* @param userName 
	* @return String
	* @throws  
	*/
	public String receive(AlarmRecordEntity entity, String subType, String flag, String userId, String userName,
			String cusNumber) throws Exception;

	/**
	* @methodName: cancelTheAlarm
	* @Description: 取消报警
	* @param entity
	* @throws Exception String
	*/
	public String cancelTheAlarm(AlarmRecordEntity entity) throws Exception;

	/**
	* @methodName: ctrlDevice
	* @Description: 设备控制
	* @param deviceType 设备类型  1 "门禁"; 4 "大屏"; 5 "对讲";
	* @param cusNumber 监狱号
	* @param deviceIds 设备ids (对讲传的编号)
	* @param cameraIds 摄像头ids (报警预案关联的所有摄像头，大屏切换时需要)
	* @param action 门禁控制动作 
	* @param pcIp 呼叫对讲分机的本机ip
	* @return AjaxMessage
	* @throws  
	*/
	AjaxMessage ctrlDevice(String deviceType, String cusNumber, List<String> deviceIds, String action, String pcIp,
			List<String> cameraIds, String alarmAddress);

	/**
	* @methodName: queryAlarmPlanDtls
	* @Description: 查询报警预案关联项及设备信息
	* @param cusNumber 监狱号
	* @param dvcIdnty 报警器编号
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> queryAlarmPlanDtls(String cusNumber, String dvcIdnty, String alarmPlanId);

	/**
	* @methodName: judgeUserAndDemptCorrespondingByAreaId
	* @Description:  根据 报警器编号判断用户和部门是否关联
	* @param cusNumber
	* @param userId
	* @param alertorIdnty
	* @return int 1、true 0、fasle
	* @throws  
	*/
	public boolean judgeUserAndDemptCorrespondingByAlertorIdnty(String cusNumber, String userId, String alertorIdnty);

	public void getAlarmSound(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
