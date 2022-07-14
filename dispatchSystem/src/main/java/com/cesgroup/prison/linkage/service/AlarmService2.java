package com.cesgroup.prison.linkage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.bean.WebSocketMessage;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.frm.db.util.Seq;
import com.cesgroup.prison.common.cache.AlarmLevelMap;
import com.cesgroup.prison.common.constants.alarm.AlarmConstants;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.sql.service.QueryService;
import com.cesgroup.scrap.db.DeleteProcess;
import com.cesgroup.scrap.db.InsertProcess;
import com.cesgroup.scrap.db.QueryProcess;
import com.cesgroup.scrap.db.SequenceUtil;
import com.cesgroup.scrap.db.UpdateProcess;

@Service
public class AlarmService2 implements IAlarmService{
	private static final Logger logger = LoggerFactory.getLogger(AlarmService2.class);
	
	public final static String alarmMsaMap = "alarm_msg_map";
	
	@Resource
	private QueryProcess queryProcess;
	@Resource
	private QueryService queryService;
	@Resource
	private UpdateProcess updateProcess;
	@Resource
	private InsertProcess insertProcess;
	@Resource
	private DeleteProcess deleteProcess;
	@Resource
	private MqMessageSender mqMessageSender;
	@Resource
	private SequenceUtil sequenceUtil;

	
	/**
	 * 人工报警
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String manualAlarm(JSONObject params) throws Exception {
		String cusNumber = params.getString("cusNumber");				// 机构编号
		String alertorId = params.getString("alertorId");				// 报警设备编号
		String alarmRecordId = null;
		String alarmDvcType = null;
		String result = "人工报警信息保存失败";

		if (addAlarmRecord(params)) {
			alarmRecordId = params.getString("alarmRecordId");
			alarmDvcType = params.getString("alarmDvcType");
			result = manualHandle(cusNumber, alertorId, alarmRecordId, null, alarmDvcType);
		}
		return result;
	}

	/**
	 * 添加报警记录
	 * @param reqJSON
	 * @return
	 * @throws Exception
	 */
	public boolean addAlarmRecord (JSONObject reqJSON) throws Exception {
		String userId = reqJSON.getString("userId");					// 报警记录创建人
		String cusNumber = reqJSON.getString("cusNumber");				// 机构编号
		String alertorId = reqJSON.getString("alertorId");				// 报警设备编号
		String alarmDate = reqJSON.getString("alarmDate");				// 报警日期
		String alarmTime = reqJSON.getString("alarmTime");				// 报警时间
		String alarmPolice = reqJSON.getString("alarmPolice");			// 报警人
		String alarmLocalCase = reqJSON.getString("alarmLocalCase");	// 现场处置情况

		String alarmRecordId = getSeq();
		String alarmDvcType = AlarmConstants.ALARM_DVC_TYPE_8;//人工报警
		Object alarmLevel = AlarmLevelMap.getAlarmLevel(cusNumber, alarmDvcType);
		String sqlId = AlarmConstants.CDS_ADD_ALARM_RECORD;
		List<Object> params = null;

		// 保存参数
		params = new ArrayList<Object>();
		params.add(cusNumber);							// 监狱机构号
		params.add(alarmRecordId);						// 报警记录编号
		params.add(alarmDate);							// 报警记录日期
		params.add(alarmDvcType);						// 报警设备类型
		params.add(alarmDvcType);						// 报警类型
		params.add(alarmPolice);						// 报警人
		params.add(alertorId);							// 报警设备编号
		params.add(alarmLevel);							// 报警等级
		params.add(alarmDate + " " + alarmTime);		// 报警时间
		params.add(AlarmConstants.ALARM_STATUS_1);		// 报警状态
		params.add(AlarmConstants.ARD_OPRTN_STTS_1);	// 报警处置状态
		params.add(alarmLocalCase);						// 现场情况
		params.add("");									// 报警备注
		params.add(userId);								// 报警记录创建人
		params.add(userId);								// 报警记录更新人

		if (insertProcess.insert(sqlId, params) == 1) {
			reqJSON.put("alarmRecordId", alarmRecordId);
			reqJSON.put("alarmDvcType", alarmDvcType);
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 人工报警处置
	 * @param cusNumber			机构号
	 * @param areaId			区域编号
	 * @param alarmRecordId		报警记录编号
	 * @param oprtnStatus		处置状态
	 * @param alarmType			报警类型
	 */
	public String manualHandle(String cusNumber, String areaId, String alarmRecordId, String oprtnStatus, String alarmType) throws Exception {
		List<Object> args = new ArrayList<Object>();
		args.add(cusNumber);
		args.add(areaId);

		List<Map<String, Object>> dprtmntList = queryProcess.process("query_area_relation_department", args);
		/*List<Map<String, Object>> policeList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> prisonerList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> policeListTemp = null;
		List<Map<String, Object>> prisonerListTemp = null;*/
		List<Map<String, Object>> alarmRecordList = null;

		String result = null;
		//String dprtmntId = null;

        /*if (dprtmntList != null && dprtmntList.size() > 0) {
        	for (Map<String,Object> map : dprtmntList) {
        		dprtmntId = map.get("adr_dprtmnt_id").toString();
        		logger.debug("5、查询值班民警信息");
        		policeListTemp = getDutyPolice(cusNumber, dprtmntId);
        		policeList.addAll(policeListTemp);

        		logger.debug("6、查询重点犯人信息");
        		prisonerListTemp = getSeriousPrisoner(cusNumber, dprtmntId);
        		prisonerList.addAll(prisonerListTemp);
        	}
        }*/

		if (Tools.notEmpty(alarmRecordId)) {
			logger.debug("7、查询报警记录信息");
			alarmRecordList = getAlarmRecord(alarmType, cusNumber, alarmRecordId);
		}

		logger.debug("8、数据转换成json格式返回页面展示");
		JSONObject jobj = new JSONObject();
		jobj.put("record", alarmRecordList);
		//jobj.put("doorctrl", doorCtrList);
		//jobj.put("bigscreen", bigScreenList);
		//jobj.put("camera", cameraList);
		//jobj.put("video", videoList);
		//jobj.put("flow", flowList);
		//jobj.put("police", policeList);
		//jobj.put("prisoner", prisonerList);
		jobj.put("dprtmnt", dprtmntList);
		//jobj.put("timer", timerName);
		result = jobj.toJSONString();
		logger.debug(result);
		logger.debug("报警处置逻辑执行结束");
		return result;
	}
	/**
	 * 报警处置
	 * @author maoliang
	 * @param cusNumber 机构编号
	 * @param dvcIdnty	报警器编号
	 * @param alarmRecordId	报警记录编号
	 * @param oprtnStatus 处理状态
	 * @param alarmType 报警类型
	 * @return json字符串
	 * @throws Exception
	 */
	@Override
	public String handle(String cusNumber, String dvcIdnty, String alarmRecordId, String oprtnStatus,String alarmType) throws Exception {
		logger.debug("cusNumber:"+cusNumber);
		logger.debug("dvcIdnty:"+dvcIdnty);
		logger.debug("alarmRecordId:"+alarmRecordId);
		logger.debug("oprtnStatus:"+oprtnStatus);
		logger.debug("alarmType:"+alarmType);
		logger.debug("开始执行报警处置逻辑:");
		String result = null;

		logger.debug("1、查询联动设备");
		List<Object> args = new ArrayList<Object>();
		args.add(cusNumber);
		args.add(dvcIdnty);
		args.add(alarmType);
		List<Map<String, Object>> planDeviceList = queryProcess.process("cds_alarm_queryPlanDevice", "0", "0", args);
		//System.out.println(planDeviceList.size());
		
		logger.debug("2、设备按联动项分组");
		List<String> levelSeq = new ArrayList<String>();
		List<Map<String, Object>> doorCtrList = new ArrayList<Map<String,Object>>();//门禁列表
		List<Map<String, Object>> bigScreenList = new ArrayList<Map<String,Object>>();//大屏列表
		List<Map<String, Object>> cameraList = new ArrayList<Map<String,Object>>();//摄像机列表
		List<Map<String, Object>> videoList = new ArrayList<Map<String,Object>>();//音视频列表
		List<Map<String, Object>> flowList = new ArrayList<Map<String,Object>>();//处理流程列表
		List<Map<String, Object>> doorCtrAutoList = new ArrayList<Map<String,Object>>();//门禁自动处理列表
		List<String> bigScreenAutoList = new ArrayList<String>();//大屏自动处理列表
		List<String> cameraAutoList = new ArrayList<String>();//摄像机自动上大屏列表

		String itemId = null;  //联动项编号
		String autoIndc = null; //是否自动:0手动,1自动
		String sttsIndc = null; //启用标示:0不启用,1启用

		for (Map<String, Object> planDevice : planDeviceList) {
			itemId = planDevice.get("PID_ITEM_ID").toString();
			sttsIndc = planDevice.get("PID_STTS_INDC").toString();
			autoIndc = planDevice.get("PID_OUTO_INDC").toString();

			if ("1".equals(sttsIndc)) {//启用标示:0不启用,1启用
				if ("1".equals(itemId)) {//门禁
					if ("1".equals(autoIndc)) {//是否自动:0手动,1自动
						doorCtrAutoList.add(planDevice);
					}
					doorCtrList.add(planDevice);
					if(!levelSeq.contains("1")) levelSeq.add("1");
				}else if("2".equals(itemId)){//摄像机
					cameraList.add(planDevice);
					cameraAutoList.add((String)planDevice.get("PDR_DVC_IDNTY"));
					if(!levelSeq.contains("2")) levelSeq.add("2");
				}else if("3".equals(itemId)){//广播音视频
					videoList.add(planDevice);
					if(!levelSeq.contains("3")) levelSeq.add("3");
				}else if("4".equals(itemId)){//大屏
					if("1".equals(autoIndc)){//是否自动:0手动,1自动
						bigScreenAutoList.add((String)planDevice.get("PDR_DVC_IDNTY"));
					}
					bigScreenList.add(planDevice);
					if(!levelSeq.contains("4"))levelSeq.add("4");
				}
			}
		}

		logger.debug("3、按优先级执行自动处理项");
		String timerName = "alarmHandle" + Seq.getInstance().get();
		for (int i = 0; i < levelSeq.size(); i++) {
			if(levelSeq.get(i)=="1"){//门禁
				logger.warn("门禁暂不支持自动处理功能");
			}else if(levelSeq.get(i)=="2"){//大屏
				//处理状态=处理完成的操作是历史查询，不需要上大屏查看
				if("处理完成".equals(oprtnStatus)){ 
					logger.info("历史记录查看,不需要上大屏查看");
				}else{
					try {
						if(bigScreenAutoList.size() >= cameraAutoList.size()){
//							ScreenTimerManager.getInstance().videoUpScreen(cusNumber, bigScreenAutoList, cameraAutoList);
						}else{
//							ScreenTimerManager.getInstance().videoUpScreen(timerName, bigScreenAutoList, cameraAutoList);
						}
					} catch (Exception e) {
						logger.warn("打开摄像机异常,请检查摄像机服务端是否已启动.");
					}
				}
			}
		}

		logger.debug("4、根据报警器查询区域部门信息");
		args=new ArrayList<Object>();
		args.add(cusNumber);
		args.add(cusNumber);
		args.add(cusNumber);
		args.add(cusNumber);
		args.add(dvcIdnty);
		args.add(alarmType);
		List<Map<String, Object>> dprtmntList=queryProcess.process("cds_device_queryDprtmnt","0", args);

		//String dprtmntId = "";
		//List<Map<String, Object>> policeList = null;
		//List<Map<String, Object>> prisonerList = null;

		//if (dprtmntList.size() == 1) {
			//dprtmntId = ((BigDecimal)dprtmntList.get(0).get("adr_dprtmnt_id")).toString(); //部门编号
		//}

		//logger.debug("5、查询值班民警信息");
		//policeList = getDutyPolice(cusNumber, dprtmntId);

		//logger.debug("6、查询重点犯人信息");
		//prisonerList = getSeriousPrisoner(cusNumber, dprtmntId);

		List<Map<String, Object>> alarmRecordList = null;
		if (Tools.notEmpty(alarmRecordId)) {
			logger.debug("7、查询报警记录信息");
			alarmRecordList = getAlarmRecord(alarmType, cusNumber, alarmRecordId);
		}

		logger.debug("8、数据转换成json格式返回页面展示");
		JSONObject jobj = new JSONObject();
		jobj.put("record", alarmRecordList);
		jobj.put("doorctrl", doorCtrList);
		jobj.put("bigscreen", bigScreenList);
		jobj.put("camera", cameraList);
		jobj.put("video", videoList);
		jobj.put("flow", flowList);
		//jobj.put("police", policeList);
		//jobj.put("prisoner", prisonerList);
		jobj.put("dprtmnt", dprtmntList);
		jobj.put("timer", timerName);
		result = jobj.toJSONString();
		logger.debug(result);
		logger.debug("报警处置逻辑执行结束");

		return result;
	}
	
	/**
	 * 更改接警状态
	 * @param cusNumber  机构号
	 * @param recordId  报警记录编号
	 * @param receiveStatus  接警状态
	 * @return
	 * @throws Exception
	 */
	public String changeReceiveStatus(String cusNumber,String userId,String recordId,String receiveStatus)throws Exception{
		String result = null;
		List<Object> list = new ArrayList<Object>();
		list.add(receiveStatus);
		list.add(userId);//更新人
		list.add(cusNumber);
		list.add(recordId);
		if(updateProcess.process(AlarmConstants.CDS_ALARM_UPDATERECEIVESTATUS, list) == 1){
			logger.debug("更新编号="+recordId+"的报警记录");
			result = "更新接警状态成功";
		}else{
			result = "更新接警状态出现异常";
		}
		return result;
	}

	@Override
	public String receive(JSONObject params) throws Exception {
		String result = "报警处置登记失败";
//		String timerName = params.getString("timerName");
		String cusNumber = params.getString("cusNumber");
		String userId = params.getString("userId");
		String alarmRecordId = params.getString("alarmRecordId");
		String eventId = params.getString("eventId");
		String eventType = params.getString("eventType");
		String alarmPolice = params.getString("alarmPolice");
		String localCase = params.getString("localCase");
		String oprtnDesc = params.getString("oprtnDesc");
		String receivePolice = params.getString("receivePolice");
		int subType = params.getIntValue("subType");//0提交,1上级处理
		boolean flag= (params.getIntValue("flag") == 1 ? true : false );//0代表没有接警人，1代表有接警人
		String sqlId = null;
		List<Object> list = new ArrayList<Object>();
		if(flag){//有接警人
			sqlId = "cds_alarm_updateRecord3";
			list.add(alarmPolice);
			list.add(eventId);
			list.add(eventType);
			list.add(localCase);
			list.add(receivePolice);
			list.add(oprtnDesc);
			list.add(receivePolice);
			list.add(userId);
			list.add(cusNumber);
			list.add(alarmRecordId);
		}else{
			if(subType == 0){
				sqlId = "cds_alarm_updateRecord1";
				list.add(alarmPolice);
				list.add(eventId);
				list.add(eventType);
				list.add(receivePolice);
				list.add(localCase);
				list.add(receivePolice);
				list.add(oprtnDesc);
				list.add(receivePolice);
				list.add(userId);
				list.add(cusNumber);
				list.add(alarmRecordId);
			}else if(subType == 1){
				String alarmMsg="";
				if(RedisCache.getHashMap(alarmMsaMap, alarmRecordId)!=null){
					alarmMsg=RedisCache.getHashMap(alarmMsaMap, alarmRecordId).toString();
					WebSocketMessage webSocketMessage = new WebSocketMessage();
					webSocketMessage.setMsgType(AlarmConstants.ALARM_MSG_3006);
					webSocketMessage.setContent(JSONObject.toJSONString(alarmMsg));
					// 向前台发送消息
					mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage),String.valueOf(cusNumber));
				}
				sqlId = "cds_alarm_updateRecord2";
				list.add(alarmPolice);
				list.add(eventId);
				list.add(eventType);
				list.add(receivePolice);
				list.add(localCase);
				list.add(oprtnDesc);
				list.add(userId);
				list.add(cusNumber);
				list.add(alarmRecordId);
			}else{
				logger.warn("接警参数[subType:"+subType+"]异常");
			}
		}
		
		if(list.size() > 0){
			int count = updateProcess.process(sqlId, list);
			if(count > 0){
				if(subType == 0){
//					ScreenTimerManager.getInstance().stopTimer(timerName);
//					ScreenTimerManager.getInstance().removeTimer(timerName);
					result = "报警处置登记成功";
				}else if(subType == 1){
					result = "登记信息保存成功,待上级处理.";
				}
			}else{
				result = "报警处置登记异常";
			}
		}
		return result;
	}

	@Override
	@Transactional
	public String send(JSONObject params) throws Exception {
		String cusNumber = params.getString("cusNumber");				// 机构编号
		String alertorId = params.getString("alertorId");				// 报警设备编号
		String alarmRecordId = null;
		String alarmDvcType = null;
		String result = "人工报警信息保存失败";

		if (addAlarmRecord(params)) {
			alarmRecordId = params.getString("alarmRecordId");
			alarmDvcType = params.getString("alarmDvcType");
			result = handle(cusNumber, alertorId, alarmRecordId, null, alarmDvcType);
		}
		return result;
	}

	/**
	 *报警性质保存
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String addFlowData(JSONObject params)throws Exception{
		String result = null;
		int count = 0;
		List<Object> list = null;
		String cusNumber = params.getString("cusNumber");
		String userId = params.getString("userId");
		String flowId = params.getString("flowId");
		String flowName = params.getString("flowName");
		String flowType = params.getString("flowType");
		String showSeq = params.getString("showSeq");
		String alarmLevel = params.getString("alarmLevel");
		JSONArray flowdata = params.getJSONArray("flowdata");
		if(Integer.parseInt(flowId) == 0){ //流程编号=0 查找下一个流程编号
			flowId = queryProcess.process(AlarmConstants.CDS_ALARM_QUERYFLOWSEQ, new ArrayList<Object>()).get(0).get("FLOW_ID").toString();
			list = new ArrayList<Object>();
			list.add(cusNumber);
			list.add(flowId);
			list.add(flowName);
			list.add(flowType);
			list.add(showSeq);
			list.add(alarmLevel);
			list.add(userId);//创建人
			list.add(userId);//更新人
			if(insertProcess.insert(AlarmConstants.CDS_ALARM_ADDFLOW, list) == 1){
				logger.debug("新增编号="+flowId+"的处置流程");
			}
		}else{
			//更新源处理流程
			list = new ArrayList<Object>();
			list.add(flowName);
			list.add(showSeq);
			list.add(alarmLevel);
			list.add(Integer.parseInt(userId));//更新人
			list.add(Integer.parseInt(flowId));
			list.add(Integer.parseInt(cusNumber));
			if(updateProcess.process(AlarmConstants.CDS_ALARM_UPDATEFLOW, list) == 1){
				logger.debug("更新编号="+flowId+"的处置流程");
			}
			
			//删除原处置流程项
			list = new ArrayList<Object>();
			list.add(cusNumber);
			list.add(flowId);
			if(deleteProcess.delete(AlarmConstants.CDS_ALARM_DELTFLOWITEM, list) >= 0){
				logger.debug("删除编号="+flowId+"的处置流程项");
			}
		}
		for (int i = 0; i < flowdata.size(); i++) {
			JSONObject flow = flowdata.getJSONObject(i);
			list = new ArrayList<Object>();
			list.add(cusNumber);//机构编号
			list.add(flowId);//流程编号
			list.add(flow.getString("id"));//流程项编号
			list.add(flow.getString("name"));//流程项名称
			list.add(userId);//创建人
			list.add(userId);//更新人
			count = count + insertProcess.insert(AlarmConstants.CDS_ALARM_ADDFLOWITEM, list);
		}
		if(flowdata.size() == count){
			result = "处置流程保存成功";
		}else{
			result = "保存处置流程出现异常";
		}
		return result;
	}
	
	/**
	 * 报警类型添加
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String addAlarmType(JSONObject params)throws Exception{
		String result = null;
		String cusNumber = params.getString("cusNumber");
		String userId = params.getString("userId");
		String alarmTypeId = params.getString("alarmTypeId");
		String alarmTypeName = params.getString("alarmTypeName");
		String alarmLevel = params.getString("alarmLevel");
		String sqlId = "cds_alarm_addAlarmType";
		List<Object> list = new ArrayList<Object>();
		list.add(cusNumber);
		list.add(alarmTypeId);
		list.add(alarmTypeName);
		list.add(alarmLevel);
		list.add(userId);
		list.add(userId);
		int count = insertProcess.insert(sqlId, list);
		if(count == 1){
			result = "报警类型保存成功";
		}else{
			result = "报警类型保存出现异常";
		}
		return result;
	}
	
	/**
	 *报警类型修改
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String updateAlarmType(JSONObject params)throws Exception{
		String result = null;
		String cusNumber = params.getString("cusNumber");
		String userId = params.getString("userId");
		String alarmTypeId = params.getString("alarmTypeId");
		String alarmType = params.getString("alarmType");
		String alarmLevel = params.getString("alarmLevel");
		String sqlId = "cds_alarm_updateAlarmType";
		List<Object> list = new ArrayList<Object>();
		list.add(alarmType);
		list.add(alarmLevel);
		list.add(userId);
		list.add(cusNumber);
		list.add(alarmTypeId);
		int count = updateProcess.process(sqlId, list);
		if(count == 1){
			result = "报警类型修改成功";
		}else{
			result = "报警类型修改出现异常";
		}
		return result;
	}
	
	/**
	 * 删除报警类型
	 * @param cusNumber
	 * @param alarmTypeId
	 * @return
	 * @throws Exception
	 */
	public String deleteAlarmType(String cusNumber,String alarmTypeId)throws Exception{
		String result = null;
		String sqlId = "cds_alarm_deltAlarmType";
		List<Object> list = new ArrayList<Object>();
		list.add(cusNumber);
		list.add(alarmTypeId);
		int count = deleteProcess.delete(sqlId, list);
		if(count == 1){
			result = "报警类型删除成功";
		}else{
			result = "报警类型删除出现异常";
		}
		return result;
	}

	/**
	 * 获取序列号
	 * @return
	 */
	private String getSeq () {
		return sequenceUtil.getSequence("cds_alert_record_dtls", "ard_record_id");
	}


	/**
	 * 查询值班民警信息
	 * @param cusNumber	机构编号
	 * @param dprtmntId	部门编号
	 * @return
	 */
	private List<Map<String,Object>> getDutyPolice (String cusNumber, String dprtmntId) {
		List<Object> args = new ArrayList<Object>();
		args.add(cusNumber);
		args.add(dprtmntId);

		return queryProcess.process("cds_alarm_queryPolice", "0", "0", args);
	}

	
	/**
	 * 获取重点罪犯信息
	 * @param cusNumber	机构编号
	 * @param dprtmntId	部门编号
	 * @return
	 */
	private List<Map<String,Object>> getSeriousPrisoner (String cusNumber, String dprtmntId) {
		List<Object> args = new ArrayList<Object>();
		args.add(cusNumber);
		args.add(cusNumber);
		args.add(dprtmntId);
		args.add(dprtmntId);

		return queryProcess.process("cds_query_alarm_prisoner_serious", "0", "0", args);
	}


	/**
	 * 获取报警记录
	 * @param alarmType	报警类型
	 * @param cusNumber	机构编码
	 * @param recordId	报警记录ID
	 * @return
	 */
	private List<Map<String,Object>> getAlarmRecord (String alarmType, String cusNumber, String recordId) {
		String sqlId = "cds_query_alarm_record_" + alarmType;
		List<Object> args = new ArrayList<Object>();
		args.add(cusNumber);
		args.add(cusNumber);
		args.add(recordId);
		return queryService.query(sqlId, "0", "0", args);
	}
}
