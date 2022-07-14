package com.cesgroup.prison.common.constants.socket;

public interface IMsgTypeConst {
	//web 请求响应的统一type
      String REQUEST_RESPONT="0001";
	//公用消息类型
      String COMMON_MESSAGE="9001";
	//警力区域分布
      String POLICE_AREA_COUNT="1001";
	// 当前会见
      String CURRENT_MEETING="3002";
	// 当前零星流动
      String CURRENT_FLOW_PEOPLE="3003";
	// 当前刷卡人
      String CURRENT_BRUSH_PEOPLE="3004";
	// 当前对讲
      String CURRENT_TALK="3005";
	//安检门报警
      String SECURITY_DOOR="5001";
	
	
	//现在在用的消息类型
	//外来人员
      String FOREIGN_PEOPLE_COUNT="2001";
	//外来车辆
      String FOREIGN_CAR_COUNT="2002";
	//外来车辆
      String FOREIGN_CAR_INFO="2003";
	//当前监内民警
      String CURRENT_POLICE_COUNT="1003";
	//监内民警超过24小时
      String POLICE_IN_24="1004";
	
	//点名
      String PRISONER_CALL="4001";
	//罪犯在线统计
      String PRISONER_ONLINE="4002";
	// 当前报警
      String CURRENT_ALERT="3001";

    /**
     * 行为侦测信息
     */
    String CURRENT_XWZC="3099";
	//上级报警处理的报警
      String SUPERIOR_ALERT="3006";
	//省局的报警消息
      String PROVINCE_ALERT="3007";
	
	//罪犯外出
      String PERSION_GOOUT="8001";
	String PERSION_GOOUT_NUM="8002";
	
	String PHNOE_CHECK_INFO="7001";
	
	//监督单提醒
      String PROVINCE_MONITOR_NOTICE="6001";
	//故障维修
      String MSG_FAULT_MAINTAIN="6002";
	//开关门指令返回
      String MSG_DOOR_RETURN_STATUS="6003";
	//对讲指令返回
      String MSG_TALK_RETURN_STATUS="6004";

      //融合通信呼叫和挂断信息返回
      String MSG_TALK_RESULT_STATUS="6010";
        //融合通信呼叫和挂断信息返回
      String MSG_BROADCAST_RESULT_STATUS="6011";
	//三级报警
      String MSG_WARM3_STATUS="6005";
	//行为侦测
      String MSG_ACTION_SENSE="6006";
	//设定时间开门
      String SET_OPENDOOR_TIME="6007";


}
