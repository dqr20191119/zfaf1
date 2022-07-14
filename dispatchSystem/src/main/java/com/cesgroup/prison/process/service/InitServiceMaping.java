package com.cesgroup.prison.process.service;
import com.cesgroup.prison.ysjg.entity.PurchaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cesgroup.fm.MsgServiceMap;
import com.cesgroup.prison.fm.util.MsgTypeConst;

@Component
public class InitServiceMaping {
	private static final Logger log = LoggerFactory.getLogger(InitServiceMaping.class);
	
	private InitServiceMaping(){

	};
	//lihh added
	public static void init() {
		
	}

	// 静态初始服务映射
	static {
		try {
			//外来车辆登记,进出处理
			MsgServiceMap.put(MsgTypeConst.FOREIGN_CAR_INOUT, "vehicleProcess");
			
			MsgServiceMap.put(MsgTypeConst.FOREIGN_CAR_REGIST, "vehicleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_CAR_ENTER, "vehicleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_CAR_LEAVE, "vehicleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_CAR_RGIMG, "vehicleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_CAR_INOUTIMG, "vehicleProcess");
			log.info("初始化前置消息服务映射：外来车辆登记,进出处理");
			
			//外来人员登记,进出处理
			MsgServiceMap.put(MsgTypeConst.FOREIGN_PEOPLE_REGIST, "foreignPeopleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_PEOPLE_ENTER, "foreignPeopleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_PEOPLE_LEAVE, "foreignPeopleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_PEOPLE_RGIMG, "foreignPeopleProcess");
			MsgServiceMap.put(MsgTypeConst.FOREIGN_PEOPLE_INOUTIMG, "foreignPeopleProcess");
			log.info("初始化前置消息服务映射：外来人员登记,进出处理");

			//一卡通消息处理
			MsgServiceMap.put(MsgTypeConst.ONE_CARD_WORK, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.SPORADIC_FLOW_START, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.SPORADIC_FLOW_END, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.ONE_CARD_FLOW, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.CALL_START, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.CALL_DTLS, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.PRISONER_ONLINE, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.CALL_TIMEOUT, "oneCardProcess");
			MsgServiceMap.put(MsgTypeConst.EXCEPTION_FLOW, "oneCardProcess");
			log.info("初始化前置消息服务映射：一卡通消息处理");

			//高压电网消息处理
			MsgServiceMap.put(MsgTypeConst.POWER_DW, "powerProcess");
			log.info("初始化前置消息服务映射：高压电网消息处理");

			//门禁消息处理
			MsgServiceMap.put(MsgTypeConst.DOOR_CARD, "doorProcess");
			MsgServiceMap.put(MsgTypeConst.DOOR_STATUS, "doorProcess");
			MsgServiceMap.put(MsgTypeConst.POLICE_INOUT, "doorProcess");
			MsgServiceMap.put(MsgTypeConst.POLICE_INOUT_IMG, "doorProcess");
			MsgServiceMap.put(MsgTypeConst.POLICE_INPRISON, "doorProcess");
			MsgServiceMap.put(MsgTypeConst.POLICE_INOUT_H, "doorProcess");
			MsgServiceMap.put(MsgTypeConst.DOOR_RETURN_STATUS, "doorProcess");
			log.info("初始化前置消息服务映射：门禁消息处理");

			//摄像机消息处理
			MsgServiceMap.put(MsgTypeConst.CAMERA_STATUS, "cameraProcess");
			log.info("初始化前置消息服务映射：摄像机消息处理");

			//预警信息
			MsgServiceMap.put(MsgTypeConst.PRISONER_GOOUT, "warmProcess");
			MsgServiceMap.put(MsgTypeConst.PRISONER_GOOUTIMG, "warmProcess");
			MsgServiceMap.put(MsgTypeConst.PRISONER_BREAKOUTWARN, "warmProcess");
			MsgServiceMap.put(MsgTypeConst.SUSPECT_PRISONER, "warmProcess");
			log.info("初始化前置消息服务映射：预警信息");
			
			//手机监测
			MsgServiceMap.put(MsgTypeConst.PHONE_CHECK_INFO, "phoneProcess");
			
			// 对讲信息
			MsgServiceMap.put(MsgTypeConst.TALK_RETURN_STATUS, "talkProcess");
			//融合通信呼叫或挂断返回
                 MsgServiceMap.put(MsgTypeConst.TALK_RESULT_RSP, "talkProcess");
			// 点名信息
			MsgServiceMap.put(MsgTypeConst.CALL_NAME_RETURN_02, "callNameProcess");
			MsgServiceMap.put(MsgTypeConst.CALL_NAME_RETURN_03, "callNameProcess");
			MsgServiceMap.put(MsgTypeConst.CALL_NAME_RETURN_05, "callNameProcess");
			
			// 湖南点名信息
			MsgServiceMap.put(MsgTypeConst.CALL_ROLL_IN, "callRollProcess");
			
			// 通信融合消息处理
			MsgServiceMap.put(MsgTypeConst.RCS_COMMAND_IN, "rcsService");
			
			// 民警所在位置消息处理
			MsgServiceMap.put(MsgTypeConst.POLICE_LOCATION, "policeLocationProcess");
			
			// 人脸识别黑名单消息处理
			MsgServiceMap.put(MsgTypeConst.RLSB_HMD, "facemarkProcess");

			// 安检消息处理
			MsgServiceMap.put(MsgTypeConst.SECURITY_CHECK, "securityCheckProcess");
			
			// 巡更记录消息处理
			MsgServiceMap.put(MsgTypeConst.XGJL, "patrolrecordService");
			
			// 外来人员消息处理
			MsgServiceMap.put(MsgTypeConst.OUT_SIDER, "outsiderProcess");
			
			// 行为侦测消息处理
			MsgServiceMap.put(MsgTypeConst.BEHAVIORN_IN, "behaviornProcess");

			//门禁状态
			MsgServiceMap.put(MsgTypeConst.DOORSTATE_IN, "doorStateProcess");

			//布防撤防
                    MsgServiceMap.put(MsgTypeConst.BFCF_TYPE, "bfCfProcess");
                    MsgServiceMap.put(MsgTypeConst.BFCF_STATUS, "bfCfProcess");
			// 电视墙消息处理
			//MsgServiceMap.put(MsgTypeConst.SCREEN_IN, "screenProcess");
                    MsgServiceMap.put(MsgTypeConst.SCENE_ALL_INFO,"screenProcess");
                    MsgServiceMap.put(MsgTypeConst.TVWALL_WINDOWS,"screenProcess");
                    MsgServiceMap.put(MsgTypeConst.SCREEN_PLAN_QH,"screenProcess");
			// 广播调用返回信息处理
			MsgServiceMap.put(MsgTypeConst.BROADCAST_RETURN, "broadcastProcess");
		} catch (Exception e) {
			log.error("初始化前置消息服务映射关系异常：", e);
		}
	}
}
