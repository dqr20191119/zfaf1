package com.cesgroup.prison.alarm.handle.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.prison.alarm.record.dao.AlarmLogMapper;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntityLog;
import com.cesgroup.prison.alarm.record.enums.AlarmLevelEnum;
import com.cesgroup.prison.alarm.record.enums.AlarmTypeEnum;
import com.cesgroup.prison.alarm.record.param.AlarmRecordParam;
import com.cesgroup.prison.alarm.record.result.AlarmRecordResult;
import com.cesgroup.prison.alarm.record.util.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordService;
import com.cesgroup.prison.alarm.handle.service.AlarmHandleService;
import com.cesgroup.prison.alarm.level.dao.AlarmTypeAndLevMapper;
import com.cesgroup.prison.alarm.level.entity.AlarmTypeAndLevEntity;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanBroadcastPlanMapper;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanItemDtlsMapper;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanBroadcastPlan;
import com.cesgroup.prison.alarm.record.dao.AlarmMapper;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.service.AlarmService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.common.service.PoliceService;
import com.cesgroup.prison.jfsb.dao.AlertorMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.linkage.service.DoorCtrlService;
import com.cesgroup.prison.realTimeTalk.service.RealTimeTalkService;
import com.cesgroup.prison.screenSwitch.service.ScreenSwitchService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

@Service
public class AlarmHandleServiceImpl implements AlarmHandleService {

	@Autowired
	private AlarmTypeAndLevMapper alarmTypeAndLevMapper;

	@Autowired
	private AlarmMapper mapper;

	@Autowired
	private AlarmLogMapper logMapper;

	@Resource
	private AlarmService alarmService;

	@Resource
	private DoorCtrlService doorCtrlService;

	@Resource
	private RealTimeTalkService realTimeTalkService;

	@Resource
	private ScreenSwitchService screenSwitchService;

	@Resource
	private PoliceService policeService;

	@Autowired
	private AlarmPlanItemDtlsMapper itemMapper;

	@Autowired
	private AlertorMapper alertorMapper;
	@Resource
	private AlarmPlanBroadcastPlanMapper alarmPlanBroadcastPlanMpper;
	@Resource
	private EmerRecordService emerRecordService;

	@Override
	public String receive(AlarmRecordEntity entity, String subType, String flag, String userId, String userName,
			String cusNumber) throws Exception {
		String msg = "";
		boolean isReceive = flag.equals("1") ? true : false;// 0代表没有接警人，1代表有接警人
		String id = entity.getId();
		if (isReceive) {// 更新报警记录,有接警人,点提交
			Date date = new Date();
			entity.setArdCusNumber(cusNumber);
			entity.setArdOprtr(userName);
			entity.setArdOprtrId(userId);
			entity.setArdOprtnSttsIndc("3");
			entity.setArdFinishSurePoliceId(userId);
			entity.setArdFinishSurePolice(userName);
			entity.setArdOprtnFinishTime(date);
			entity.setArdOprtnTime(date);
			alarmService.updateAlarmRecord(entity);
			msg = "报警处置成功";
			this.sendAlarmMsg(IMsgTypeConst.CURRENT_ALERT, "0", entity);

			//处理在启动报警预案时，点击快速处置（启动报警预案后，点击快速处置，把所有启动的报警预案都变为处置完成）
			this.closeYuAn(entity);
		} else {
			if (subType.equals("0")) {// 更新报警记录 无接警人,点提交
				Date date = new Date();
				entity.setArdCusNumber(cusNumber);
				entity.setArdReceiveAlarmPolice(userName);
				entity.setArdReceiveAlarmPoliceId(userId);
				entity.setArdReceiveTime(date);
				entity.setArdOprtr(userName);
				entity.setArdOprtrId(userId);
				entity.setArdOprtnSttsIndc("3");
				entity.setArdFinishSurePoliceId(userId);
				entity.setArdFinishSurePolice(userName);
				entity.setArdOprtnFinishTime(date);
				entity.setArdOprtnTime(date);
				alarmService.updateAlarmRecord(entity);
				msg = "报警处置成功";
				this.sendAlarmMsg(IMsgTypeConst.CURRENT_ALERT, "0", entity);

				//处理在启动报警预案时，点击快速处置（启动报警预案后，点击快速处置，把所有启动的报警预案都变为处置完成）
				this.closeYuAn(entity);
			} else if (subType.equals("1")) {
				// 更新报警记录 无接警人,点上级处理
				Date date = new Date();
				entity.setArdCusNumber(cusNumber);
				entity.setArdReceiveAlarmPolice(userName);
				entity.setArdReceiveAlarmPoliceId(userId);
				entity.setArdReceiveTime(date);
				entity.setArdOprtnSttsIndc("0");// 0、上级处置
				alarmService.updateAlarmRecord(entity);
				this.sendAlarmMsg(IMsgTypeConst.SUPERIOR_ALERT, "0", alarmService.selectOne(id));
				msg = "提交上级处理成功";
				this.sendAlarmMsg(IMsgTypeConst.CURRENT_ALERT, "0", entity);
			} else {
				return msg = "接警参数[subType:" + subType + "]异常";
			}
		}
		return msg;

	}

	@Override
	public void manualAlarm(AlarmRecordEntity entity) throws Exception {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setArdCusNumber(userBean.getCusNumber());
			entity.setArdBsnsDate(date);
			entity.setArdCrteUserId(userId);
			entity.setArdCrteTime(date);
			entity.setArdUpdtUserId(userId);
			entity.setArdUpdtTime(date);
			entity.setArdTypeIndc("0");// 如果是人工报警，报警器类型就默认都为0，插入报警器只是为了方便处理的时候查询预案信息
			entity.setArdReceiveStts("0");// 接警状态
			entity.setArdAlertSttsIndc("1");// 报警状态
			entity.setArdOprtnSttsIndc("1");// 处置状态
			
			// 根据类型和监狱号查询等级
			AlarmTypeAndLevEntity alarmTypeAndLevEntity = new AlarmTypeAndLevEntity();
			alarmTypeAndLevEntity.setAltTypeId(entity.getArdTypeIndc());
			alarmTypeAndLevEntity.setAltCusNumber(entity.getArdCusNumber());
			
			// 报警等级查询
			String alarmLevel = "";
			List<AlarmTypeAndLevEntity> alarmTypeAndLevEntityList = alarmTypeAndLevMapper.selectByEntity(alarmTypeAndLevEntity);
			if(alarmTypeAndLevEntityList != null && alarmTypeAndLevEntityList.size() > 0) {
				alarmLevel = alarmTypeAndLevMapper.selectByEntity(alarmTypeAndLevEntity).get(0).getAltLevel();
			}
			if(Util.isNull(alarmLevel)) {
				throw new Exception("未设置报警等级");
			}
			entity.setArdAlertLevelIndc(alarmLevel);
			mapper.insert(entity);
			// this.sendAlarmMsg(IMsgTypeConst.MSG_WARM3_STATUS, "1", entity);
			this.sendAlarmMsg(IMsgTypeConst.CURRENT_ALERT, "1", entity);
			//发送post请求
			this.synGiveAnAlarm(userBean.getCusNumber(),alarmLevel,entity);
		} catch (Exception e) {
			this.saveAlarmRecordEntityLog(entity,"-1","失败",null);
		}
	}

	@Override
	public AlarmRecordResult manualAlarmApi(AlarmRecordParam entity) throws Exception {
		AlarmRecordResult alarmRecordResult = new AlarmRecordResult();

		try {
			AlarmRecordEntity alarmRecordEntity = new AlarmRecordEntity();
//			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
//			String userId = userBean.getUserId();
			Date date = new Date();
			alarmRecordEntity.setArdCusNumber(entity.getpOrgId());
			alarmRecordEntity.setArdBsnsDate(date);
//			alarmRecordEntity.setArdCrteUserId(userId);
			alarmRecordEntity.setArdCrteTime(date);
//			alarmRecordEntity.setArdUpdtUserId(userId);
			alarmRecordEntity.setArdUpdtTime(date);
			alarmRecordEntity.setArdTypeIndc(String.valueOf(Integer.parseInt(entity.getWarnDefId())+20));// 传过来的类型要特殊处理
			alarmRecordEntity.setArdReceiveStts("0");// 接警状态
			alarmRecordEntity.setArdAlertSttsIndc("1");// 报警状态
			alarmRecordEntity.setArdOprtnSttsIndc("1");// 处置状态
			alarmRecordEntity.setArdAlarmPoliceId(entity.getuId());//报警人
			alarmRecordEntity.setArdAlertTime(entity.getWarnTime());//报警时间
			alarmRecordEntity.setArdRemark(entity.getWarnRemark());//报警描述

//			// 根据类型和监狱号查询等级
//			AlarmTypeAndLevEntity alarmTypeAndLevEntity = new AlarmTypeAndLevEntity();
//			alarmTypeAndLevEntity.setAltTypeId(alarmRecordEntity.getArdTypeIndc());
//			alarmTypeAndLevEntity.setAltCusNumber(alarmRecordEntity.getArdCusNumber());
//
//			// 报警等级查询
//			String alarmLevel = "";
//			List<AlarmTypeAndLevEntity> alarmTypeAndLevEntityList = alarmTypeAndLevMapper.selectByEntity(alarmTypeAndLevEntity);
//			if(alarmTypeAndLevEntityList != null && alarmTypeAndLevEntityList.size() > 0) {
//				alarmLevel = alarmTypeAndLevMapper.selectByEntity(alarmTypeAndLevEntity).get(0).getAltLevel();
//			}
//			if(Util.isNull(alarmLevel)) {
//				throw new Exception("未设置报警等级");
//			}
//			alarmRecordEntity.setArdAlertLevelIndc(alarmLevel);
			mapper.insert(alarmRecordEntity);
			alarmRecordResult.setCode(0);
			alarmRecordResult.setMsg("成功");
			alarmRecordResult.setData(alarmRecordEntity.getId());
			// this.sendAlarmMsg(IMsgTypeConst.MSG_WARM3_STATUS, "1", entity);
//			this.sendAlarmMsg(IMsgTypeConst.CURRENT_ALERT, "1", entity);
//			//发送post请求
//			this.synGiveAnAlarm(userBean.getCusNumber(),"0",entity);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return alarmRecordResult;
	}
	/**
	 * 发送post
	 * @param cusNumber			监狱Id
	 * @param alarmLevel		报警类型
	 */
	private void synGiveAnAlarm(String cusNumber, String alarmLevel, AlarmRecordEntity entity) {
		String url = "http://34.64.4.110:7878/dxapi/docking/synGiveAnAlarm";
//		String url = "http://10.126.156.152//esignpro/rest/process/getSealAdminProcess";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("prisonId",cusNumber);
		paramMap.put("giveAnAlarmType", AlarmLevelEnum.getStatusEnum(alarmLevel));
//		paramMap.put("sealId","decec82d-f30f-466b-8aac-b30457e6dafc");
//		paramMap.put("mcdUserNumber", "15024391");
		String body = JSONObject.toJSONString(paramMap);
		try{
			String result = HttpClientUtil.httpPost(url,body);
			if(StringUtils.isNotBlank(result)){
				HashMap map = JSONObject.parseObject(result,HashMap.class);
				if (null != map && null != map.get("code")){
					if ("0".equals(String.valueOf(map.get("code")))){
						this.saveAlarmRecordEntityLog(entity,"0",String.valueOf(map.get("msg")),body);
					}else {
						this.saveAlarmRecordEntityLog(entity,"-1",String.valueOf(map.get("msg")),body);
					}
				}else {
					this.saveAlarmRecordEntityLog(entity,"-1","失败",body);
				}
			}else {
				this.saveAlarmRecordEntityLog(entity,"-1","失败",body);
			}
		}catch (Exception e){
			this.saveAlarmRecordEntityLog(entity,"-1","失败",body);
		}
	}

	public void saveAlarmRecordEntityLog(AlarmRecordEntity entity,String errCode,String msg,String body){
		AlarmRecordEntityLog alarmRecordEntityLog = new AlarmRecordEntityLog();
		Date date = new Date();
		alarmRecordEntityLog.setAlertRecordDtlsId(entity.getId());
		alarmRecordEntityLog.setErrCode(errCode);
		alarmRecordEntityLog.setMsg(msg);
		alarmRecordEntityLog.setSendValues(body);
		alarmRecordEntityLog.setArdCrteTime(date);
		logMapper.insert(alarmRecordEntityLog);
	}
	@Override
	public String cancelTheAlarm(AlarmRecordEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		String userName = userBean.getRealName();
		Date date = new Date();
		entity.setArdCusNumber(userBean.getCusNumber());
		entity.setArdAlertSttsIndc("2"); // 取消报警
		entity.setArdReceiveStts("1");// 接警状态
		entity.setArdOprtnSttsIndc("3");// 处置状态
		entity.setArdReceiveTime(date);// 接警时间
		entity.setArdReceiveAlarmPoliceId(userId); // 接警人
		entity.setArdReceiveAlarmPolice(userName);
		entity.setArdOprtnTime(date);// 处置时间
		entity.setArdOprtrId(userId);// 处置人
		entity.setArdOprtr(userName);
		entity.setArdOprtnFinishTime(date);// 处置完成时间
		entity.setArdFinishSurePoliceId(userId);// 确认人
		entity.setArdFinishSurePolice(userName);
		alarmService.updateAlarmRecord(entity);
		this.sendAlarmMsg(IMsgTypeConst.CURRENT_ALERT, "0", entity);
		return "报警取消成功";
	}

	// deviceType 设备类型 1 "门禁"; 4 "大屏"; 5 "对讲";
	@Override
	public AjaxMessage ctrlDevice(String deviceType, String cusNumber, List<String> deviceIds, String action,
			String pcIp, List<String> cameraIds, String alarmAddress) {
		AjaxMessage ajaxMessage = null;
		switch (deviceType) {
		case "1":
			String time = "";
			if ("2".equals(action)) {
				time = "254";// 常闭时间长度，默认最大254分钟，报警处置面板操作
			}
			ajaxMessage = doorCtrlService.openDoor(deviceIds, action, time);
			break;
		case "4":
			ajaxMessage = screenSwitchService.startScreenSwitch(deviceIds.get(0), "3", cameraIds, alarmAddress);
			break;
		case "5":
			ajaxMessage = realTimeTalkService.startTalk(pcIp, cusNumber, deviceIds);
			break;
		default:
			ajaxMessage = new AjaxMessage(false, null, "操作失败");
			break;
		}
		return ajaxMessage;
	}

	@Override
	public Map<String, Object> queryAlarmPlanDtls(String cusNumber, String dvcIdnty, String alarmPlanId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber) && StringUtils.isNotBlank(dvcIdnty)) {
			map.put("cusNumber", cusNumber);
			map.put("dvcIdnty", dvcIdnty);
		}

		if (StringUtils.isNotBlank(alarmPlanId)) {
			map.put("planId", alarmPlanId);
		}
		// 查询联动设备
		List<Map<String, Object>> itemsDtls = itemMapper.queryItemsDtlsAndDevice(map);

		if(itemsDtls == null || itemsDtls.size() <= 0) {
			return null;
		}
		
		// 设备按联动项分组"
		String planName = itemsDtls.get(0).get("PLAN_NAME").toString();
		String planId = itemsDtls.get(0).get("APR_PLAN_ID").toString();
		Map<String, Object> deviceMap = new HashMap<>();// 存放設備列表
		Map<String, Object> deviceNameMap = new HashMap<>();
		Map<String, Object> autoMap = new HashMap<>();// 存放自動列表
		Map<String, Object> autoNameMap = new HashMap<>();
		List<Map<String, Object>> doorCtrList = new ArrayList<Map<String, Object>>();// 门禁列表
		List<Map<String, Object>> cameraList = new ArrayList<Map<String, Object>>();// 摄像机列表
		List<Map<String, Object>> broadcastList = new ArrayList<Map<String, Object>>();// 广播列表
		List<Map<String, Object>> bigScreenList = new ArrayList<Map<String, Object>>();// 大屏列表
		List<Map<String, Object>> talkBackList = new ArrayList<Map<String, Object>>();// 对讲列表
		List<String> doorCtrAutoList = new ArrayList<String>();// 门禁自动处理列表
		List<String> bigScreenAutoList = new ArrayList<String>();// 大屏自动处理列表
		List<String> cameraAutoList = new ArrayList<String>();// 摄像机自动打开
		List<String> talkBackAutoList = new ArrayList<String>();// 对讲自动打开列表
		List<String> broadcastAutoList = new ArrayList<String>();// 广播自动打开列表

		String itemId = null; // 联动项编号
		String autoIndc = null; // 是否自动:0手动,1自动
		String sttsIndc = null; // 启用标示:0不启用,1启用
		
		//返回的报警联动广播预案
		AlarmPlanBroadcastPlan alarmPlanBroadcastPlan = null;
		//查询联动广播预案
		if(StringUtils.isNotBlank(planName)) {
			AlarmPlanBroadcastPlan apbp = new AlarmPlanBroadcastPlan();
			apbp.setBprPlanId(planId);
			List<AlarmPlanBroadcastPlan> apbpList = alarmPlanBroadcastPlanMpper.selectByEntity(apbp);
			//因为设置报警预案只关联一条广播预案 取第一条即可
			if(apbpList!=null &&apbpList.size()>0) {
				 alarmPlanBroadcastPlan = apbpList.get(0);
			}
		}
		
		
		// 1、门禁 2、摄像机 3、 广播 4、大屏 5、对讲
		for (Map<String, Object> planDevice : itemsDtls) {
			itemId = planDevice.get("PID_ITEM_ID").toString();
			sttsIndc = planDevice.get("PID_STTS_INDC").toString();
			autoIndc = planDevice.get("PDR_OUTO_INDC").toString();

			if ("1".equals(sttsIndc)) {// 启用标示:0不启用,1启用
				if ("1".equals(itemId)) {// 门禁
					if ("1".equals(autoIndc)) {// 是否自动:0手动,1自动
						doorCtrAutoList.add((String) planDevice.get("PDR_DVC_IDNTY"));
						if (!autoMap.containsKey("1")) {
							autoMap.put("1", doorCtrAutoList);
							autoNameMap.put("1", "门禁");
						}
					}
					doorCtrList.add(planDevice);
					if (!deviceMap.containsKey("1")) {
						deviceMap.put("1", doorCtrList);
						deviceNameMap.put("1", "门禁");
					}
				} else if ("2".equals(itemId)) {// 摄像机
					if ("1".equals(autoIndc)) {// 是否自动:0手动,1自动
						cameraAutoList.add((String) planDevice.get("PDR_DVC_IDNTY"));
						if (!autoMap.containsKey("2")) {
							autoMap.put("2", cameraAutoList);
							autoNameMap.put("2", "摄像机");
						}
					}
					cameraList.add(planDevice);

					if (!deviceMap.containsKey("2")) {
						deviceMap.put("2", cameraList);
						deviceNameMap.put("2", "摄像机");
					}
				} else if ("3".equals(itemId)) {// 广播
					/*
					 * if("1".equals(autoIndc)) {// 是否自动:0手动,1自动 broadcastAutoList.add((String)
					 * planDevice.get("PDR_DVC_IDNTY")); if (!autoMap.containsKey("3")) {
					 * autoMap.put("3", cameraAutoList); autoNameMap.put("3", "广播"); } }
					 * broadcastList.add(planDevice); if (!deviceMap.containsKey("3")) {
					 * deviceMap.put("3", broadcastList); deviceNameMap.put("3", "广播"); }
					 */
				} else if ("4".equals(itemId)) {// 大屏
					if ("1".equals(autoIndc)) {// 是否自动:0手动,1自动
						bigScreenAutoList.add((String) planDevice.get("PDR_DVC_IDNTY"));
						if (!autoMap.containsKey("4")) {
							autoMap.put("4", bigScreenAutoList);
							autoNameMap.put("4", "大屏");
						}
					}
					bigScreenList.add(planDevice);
					if (!deviceMap.containsKey("4")) {
						deviceMap.put("4", bigScreenList);
						deviceNameMap.put("4", "大屏");
					}
				} else if ("5".equals(itemId)) {// 对讲
					if ("1".equals(autoIndc)) {// 是否自动:0手动,1自动
						talkBackAutoList.add((String) planDevice.get("PDR_DVC_IDNTY"));
						if (!autoMap.containsKey("5")) {
							autoMap.put("5", talkBackAutoList);
							autoNameMap.put("5", "对讲");
						}
					}
					talkBackList.add(planDevice);
					if (!deviceMap.containsKey("5")) {
						deviceMap.put("5", talkBackList);
						deviceNameMap.put("5", "对讲");
					}
				}
			}
		}
		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("planId", planId);
		resultMap.put("planName", planName);
		if (!deviceNameMap.isEmpty()) {
			resultMap.put("device", deviceMap);
			resultMap.put("deviceName", deviceNameMap);
		}
		if (!autoNameMap.isEmpty()) {
			resultMap.put("auto", autoMap);
			resultMap.put("autoName", autoNameMap);
		}
		if(alarmPlanBroadcastPlan!=null) {
			resultMap.put("alarmPlanBroadcastPlan", alarmPlanBroadcastPlan);
			deviceNameMap.put("6", "广播预案");
		}
		return resultMap;
	}

	/**
	 * @throws Exception 
	* @methodName: sendAlarmMsg
	* @Description: 发送报警消息
	* @param msgType 消息类型: 3001-当前报警,3006-上级报警处理,6001-省局监督单提醒
	* @param isSendToGzt 是否发送到工作台消息	0-否，1-是(默认传1)
	* @param entity 报警记录entity
	* @throws  
	*/
	private void sendAlarmMsg(String msgType, String isSendToGzt, AlarmRecordEntity entity) throws Exception {
		String sendTo = null;
		//1 设置 sendTo
		if (!"3".equals(entity.getArdAlertLevelIndc()) && !"0".equals(entity.getArdOprtnSttsIndc())) {
			sendTo = entity.getArdCusNumber() + ","+AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG;
		} else {
			sendTo = entity.getArdCusNumber();
		}

		//2 报警器相关属性
		String alertorId = null, alertorName = null, areaId = null, areaName = null, dprtmntId = null, dprtmntName = null;
		//2.1 根据监狱/单位编号、报警器ID，查询报警器所关联的区域对应的系统管理平台部门数据
		Map<String, Object> alarmAreaDeptMapping = null;
		if (entity.getArdAlertorIdnty() != null && !"".equals(entity.getArdAlertorIdnty())) {
			alarmAreaDeptMapping = this.alarmService.queryAlertorAreaMappingDprtmntByCusNumberAndAlertorId(entity.getArdCusNumber(), entity.getArdAlertorIdnty());
		} else {
			alarmAreaDeptMapping = this.alarmService.findAlarmRecordById(entity);
		}
		//2.2 报警器相关属性赋值
		if (alarmAreaDeptMapping != null) {
			alertorId = alarmAreaDeptMapping.get("ALERTOR_ID") != null ? alarmAreaDeptMapping.get("ALERTOR_ID").toString() : null;
			alertorName = alarmAreaDeptMapping.get("ALERTOR_NAME") != null ? alarmAreaDeptMapping.get("ALERTOR_NAME").toString() : null;
			areaId = alarmAreaDeptMapping.get("AREA_ID") != null ? alarmAreaDeptMapping.get("AREA_ID").toString() : null;
			areaName = alarmAreaDeptMapping.get("AREA_NAME") != null ? alarmAreaDeptMapping.get("AREA_NAME").toString() : null;
			dprtmntId = alarmAreaDeptMapping.get("DPRTMNT_ID") != null ? alarmAreaDeptMapping.get("DPRTMNT_ID").toString() : null;
			dprtmntName = alarmAreaDeptMapping.get("DPRTMNT_NAME") != null ? alarmAreaDeptMapping.get("DPRTMNT_NAME").toString() : null;
		}

		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("msgType", msgType);
		msgMap.put("sendType", "1");
		msgMap.put("sendTo", sendTo);
		msgMap.put("content", JSON.toJSONString(entity));
		msgMap.put("url", "/prison/alarm/openDialog/record");
		msgMap.put("isSendToGzt", isSendToGzt);
		msgMap.put("ywId", entity.getId());
		msgMap.put("cusNumber", entity.getArdCusNumber());
		msgMap.put("noticeContent", alertorName + "发生报警");
		MessageSendFacade.send(msgMap);
	}

	@Override
	public boolean judgeUserAndDemptCorrespondingByAlertorIdnty(String cusNumber, String userId, String alertorIdnty) {
		AlertorEntity entity = new AlertorEntity();
		entity.setAbdCusNumber(cusNumber);
		entity.setId(alertorIdnty);
		List<AlertorEntity> list = alertorMapper.selectByEntity(entity); // 根据监狱号号和报警器编码查询报警器
		if (!list.isEmpty()) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cusNumber", cusNumber); // 监狱id
			paramMap.put("areaId", list.get(0).getAbdAreaId()); // 区域id
			List<Map<String, Object>> maps = policeService.findPoliceByAreaIdForCombobox(paramMap); // 根据监狱号和区域查询警员
			if (!maps.isEmpty()) {
				for (Map<String, Object> map : maps) {
					if (userId.equals(map.get("value").toString())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void getAlarmSound(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 设置响应内容类型
		request.setCharacterEncoding("UTF-8");// 解决乱码
		sayPlay(request, response);
	}

	private void sayPlay(HttpServletRequest request, HttpServletResponse response) {

		// 获取路径
		String ROOT = CommonConstant.jggzUploadsRootPath + "\\" + CommonConstant.uploadZhddGlobalPath + "\\"
				+ CommonConstant.soundPath + "\\" + request.getParameter("cusNumber") + "\\"
				+ request.getParameter("deviceId") + ".wav";
		// 输出 wav IO流
		try {
			response.setHeader("Content-Type", "audio/wav");
			File file = new File(ROOT);
			if (file.exists() && file.isFile()) {
				int len_l = (int) file.length();
				byte[] buf = new byte[2048];
				FileInputStream fis = new FileInputStream(file);
				OutputStream out = response.getOutputStream();
				len_l = fis.read(buf);
				while (len_l != -1) {
					out.write(buf, 0, len_l);
					len_l = fis.read(buf);
				}
				out.flush();
				out.close();
				fis.close();
			} else {
				saveToWav(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	* @methodName: saveToWav
	* @param text 需要转换的文字
	* @param filePath void 文件路径
	* @throws  
	*/
	private void saveToWav(HttpServletRequest request, HttpServletResponse response) {
		AlertorEntity alertorEntity = alertorMapper.selectOne(request.getParameter("deviceId"));

		String text = alertorEntity.getAbdName() + "发生报警";
		// 获取路径
		String filePath = CommonConstant.jggzUploadsRootPath + "\\" + CommonConstant.uploadZhddGlobalPath + "\\"
				+ CommonConstant.soundPath + "\\" + request.getParameter("cusNumber") + "\\"
				+ request.getParameter("deviceId") + ".wav";

		// 创建输出文件流对象
		Dispatch spFileStream = new ActiveXComponent("Sapi.SpFileStream").getObject();
		Dispatch spVoice = new ActiveXComponent("Sapi.SpVoice").getObject();
		// 创建音频流格式对象
		Dispatch spAudioFormat = new ActiveXComponent("Sapi.SpAudioFormat").getObject();
		try {
			// 设置音频流格式类型
			Dispatch.put(spAudioFormat, "Type", new Variant(22));// 音频的输出格式，默认为：SAFT22kHz16BitMono
			// 设置文件输出流的格式
			Dispatch.putRef(spFileStream, "Format", spAudioFormat);
			File file = new File(CommonConstant.jggzUploadsRootPath + "\\" + CommonConstant.uploadZhddGlobalPath
					+ "\\sound\\" + request.getParameter("cusNumber"));
			if (!file.exists()) {// 判断文件目录是否存在
				file.mkdirs();
			}
			// 调用输出文件流对象的打开方法，创建一个.wav文件
			Dispatch.call(spFileStream, "Open", new Variant(filePath), new Variant(3), new Variant(true));
			// 设置声音对象的音频输出流为输出文件流对象
			Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
			// 调整音量和读的速度
			Dispatch.put(spVoice, "Volume", new Variant(100));// 设置音量
			Dispatch.put(spVoice, "Rate", new Variant(0));// 设置速率
			// 开始朗读
			Dispatch.call(spVoice, "Speak", new Variant(text));

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 关闭输出文件流对象，释放资源
			Dispatch.call(spFileStream, "Close");
			Dispatch.putRef(spVoice, "AudioOutputStream", null);
			sayPlay(request, response);
		}

	}
	/**
	 *
	 *
	 * 处理在启动报警预案时，点击快速处置（启动报警预案后，点击快速处置，把所有启动的报警预案都变为处置完成）
	 * */
	private void closeYuAn(AlarmRecordEntity entity){
		List<EmerRecord> emerRecords=  alarmService.queryQdyaByid(entity.getId());
		for (EmerRecord emerRecord : emerRecords) {
			emerRecord.setRecordStatus("4");
			emerRecordService.update(emerRecord);
		}
	}
}
