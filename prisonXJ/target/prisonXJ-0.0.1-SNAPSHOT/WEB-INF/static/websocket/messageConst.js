/**
 * 说明：js常量定义
 */
function messageConst() {
}
//websocket url
messageConst.SOCKET_URL="";
//WebSocket后台响应类
messageConst.WEBSOCKET_SERVICEURL='LoginService'; 
//WebSocket消息读取间隔时间
messageConst.WEBSOCKET_INTERVAL = 200;
//WebSocket登录失败,重新连接间隔时间
messageConst.WEBSOCKET_RELOGINTIME=2000;
//WebSocket相同类型消息的处理时间间隔
messageConst.WEBSOCKET_REEXCUTETIME=2*1000;
//WebSocket方法处理完成标志,0开1关,默认开
messageConst.WEBSOCKET_MSG_OPEN=0;
messageConst.WEBSOCKET_MSG_CLOSE=1;
//WebSocket消息类型-反馈
messageConst.WEBSOCKET_TYPE_ACK = '0001';
//WebSocket消息类型-通用消息
messageConst.WEBSOCKET_TYPE_COMMON = '9001';
//WebSocket消息类型-值班管理
messageConst.WEBSOCKET_TYPE_ONDUTY = '1000';
//WebSocket消息类型-警力分布
messageConst.WEBSOCKET_TYPE_POLICE = '1001';
//民警在监24小时消息提示
messageConst.POLICE_IN_24="1004";
//WebSocket消息类型-当前监内民警
messageConst.WEBSOCKET_TYPE_CURRENT_POLICE_COUNT = '1003';
//WebSocket消息类型-实时押犯
messageConst.WEBSOCKET_TYPE_PERSONER = '1002';
//WebSocket消息类型-外来人员
messageConst.WEBSOCKET_TYPE_FOREIGNPERSON = '2001';
//WebSocket消息类型-外来车辆
messageConst.WEBSOCKET_TYPE_FOREIGNCAR = '2002';
//WebSocket消息类型-外来车辆详细信息
messageConst.WEBSOCKET_TYPE_FOREIGNCAR_INFO = '2003';
//WebSocket消息类型-实时信息
messageConst.WEBSOCKET_TYPE_REALTIMEMSG = '1005';
//WebSocket消息类型-技防设备
messageConst.WEBSOCKET_TYPE_TECHNIQUEDVC = '1006';


//WebSocket消息类型-报警处置
messageConst.WEBSOCKET_TYPE_ALARMHANDLE ="3001";
//WebSocket消息类型-报警上级处置
messageConst.WEBSOCKET_TYPE_ALARM_SUPERIOR_HANDLE ="3006";
//WebSocket消息类型-报警省局处置
messageConst.WEBSOCKET_TYPE_ALARM_PROVINCE_HANDLE ="3007";
//WebSocket消息类型-当前会见
messageConst.WEBSOCKET_TYPE_MEETING="3002";
//WebSocket消息类型-当前零星流动
messageConst.WEBSOCKET_TYPE_FLOW_PEOPLE="3003";
//WebSocket消息类型-当前刷卡人
messageConst.WEBSOCKET_TYPE_BRUSH_PEOPLE="3004";
//WebSocket消息类型-当前对讲
messageConst.WEBSOCKET_TYPE_TALKBACK="3005";
//WebSocket消息类型-点名
messageConst.WEBSOCKET_TYPE_CALLNAME="4001";
//WebSocket消息类型-罪犯在线统计
messageConst.WEBSOCKET_TYPE_PRISONER_ONLINE="4002";

messageConst.WEBSOCKET_TYPE_SECURITYDOOR="5001";

//罪犯外出
messageConst.WEBSOCKET_TYPE_PERSION_GOOUT="8001";
//罪犯外出就医
messageConst.WEBSOCKET_TYPE_PERSION_GOOUT_NUM="8002";

//手机监测
messageConst.WEBSOCKET_TYPE_PHNOE_CHECK_INFO = "7001";

//监督单提醒
messageConst.WEBSOCKET_TYPE_PROVINCE_MONITOR_NOTICE="6001";
//故障维修
messageConst.WEBSOCKET_TYPE_MSG_FAULT_MAINTAIN="6002";
//开关门指令返回
messageConst.WEBSOCKET_TYPE_MSG_DOOR_RETURN_STATUS="6003";
//对讲指令返回
messageConst.WEBSOCKET_TYPE_MSG_TALK_RETURN_STATUS="6004";

//融合通信呼叫和挂断信息返回
messageConst.WEBSOCKET_TYPE_MSG_TALK_RESULT_STATUS="6010";

//广播指令信息返回
messageConst.WEBSOCKET_TYPE_MSG_BROADCAST_RESULT_STATUS="6011";
//智能监控轮巡 行为侦测 返回摄像头的数据
messageConst.WEBSOCKET_TYPE_MSG_XWZC_RETURN_STATUS="3099";
//广播指令信息返回回调函数
messageConst.WEBSOCKET_FN_BROADCAST_RESULT = 'callbackBroadcastResult';
//消息回调函数 -- 智能监控轮巡 行为侦测 返回摄像头的数据
messageConst.WEBSOCKET_FN_XWZC_CAMERA = 'callbackXwzcCamera';
//WebSocket消息回调函数-通用方法
messageConst.WEBSOCKET_FN_COMMON = 'commonMessage';
//WebSocket消息回调函数-警力分布
messageConst.WEBSOCKET_FN_POLICE = 'showReversePoliceDis';

messageConst.WEBSOCKET_FN_24_POLICE = 'callback24Police';
//当前监内民警
messageConst.WEBSOCKET_FN_CURRENT_POLICE = 'parseInPoliceCount';
//
messageConst.WEBSOCKET_FN_FOREIGNPERSON = 'parseForeignPeopleCount';
//WebSocket消息回调函数-外来车辆
messageConst.WEBSOCKET_FN_FOREIGNCAR = 'parseForeignCarCount';

messageConst.WEBSOCKET_FN_FOREIGNCARHANDLE = 'callbackForeignCarHandle';
//WebSocket消息回调函数-报警处置
messageConst.WEBSOCKET_FN_ALARMHANDLE = 'callbackAlarmHandleEXT';
//WebSocket消息回调函数-报警处置
messageConst.WEBSOCKET_FN_PROVINCE_ALARMHANDLE = 'callbackAlarmProvinceHandle';
//WebSocket消息回调函数-当前零星流动
messageConst.WEBSOCKET_FN_FLOW_PEOPLE = 'tabConvictFluxion';
//WebSocket消息回调函数-当前刷卡人
messageConst.WEBSOCKET_FN_BRUSH_PEOPLE = 'tabDoorControl';
//WebSocket消息回调函数-当前对讲
messageConst.WEBSOCKET_FN_TALKBACK = 'tabTalkback';
//WebSocket消息回调函数-点名
messageConst.WEBSOCKET_FN_CALLNAME='callbackCallName';
//WebSocket消息回调函数-安检门
messageConst.WEBSOCKET_FN_SECURITYDOOR='callbackSecurityDoor';
//WebSocket消息回调函数-罪犯外出
messageConst.WEBSOCKET_FN_PERSION_GOOUT='callbackPersionGoout';
//WebSocket消息回调函数-罪犯外出就医
messageConst.WEBSOCKET_FN_OUT_DOCTOR='callbackOutDoctor';
//WebSocket消息回调函数-罪犯在线统计
messageConst.WEBSOCKET_FN_PRISONER_ONLINE='prisonerOnline';
//WebSocket消息类型-设备状态
messageConst.WEBSOCKET_FN_Facility="callbackFacility";

//WebSocket消息类型 - 手机监测回调函数
messageConst.WEBSOCKET_FN_PHNOE_CHECK = "callbackPhoneCheck";

//WebSocket消息类型 - 监督单提醒回调函数
messageConst.WEBSOCKET_FN_PROVINCE_MONITOR_NOTICE = "callbackProvinceMonitorNotice";
//WebSocket消息类型 - 故障维修回调函数
messageConst.WEBSOCKET_FN_MSG_FAULT_MAINTAIN = "callbackMsgFaultMaintain";
//WebSocket消息类型 - 开关门指令返回回调函数
messageConst.WEBSOCKET_FN_MSG_DOOR_RETURN_STATUS = "callbackMsgDoorReturnStatus";
//WebSocket消息类型 - 对讲指令返回回调函数
messageConst.WEBSOCKET_FN_MSG_TALK_RETURN_STATUS = "callbackMsgTalkReturnStatus";

//WebSocket消息类型 -融合通信呼叫和挂断信息返回回调函数
messageConst.WEBSOCKET_FN_MSG_TALK_RESULT_STATUS="callbackMsgTalkResultStatus";

//定义子系统首页需要实时刷新的模块
messageConst.REFRESH_MODUAL_MAP=new ces.Map();
messageConst.REFRESH_MODUAL_MAP.put("cis",[messageConst.WEBSOCKET_TYPE_POLICE,"prisoner",messageConst.WEBSOCKET_TYPE_FOREIGNCAR,messageConst.WEBSOCKET_TYPE_FOREIGNPERSON,messageConst.WEBSOCKET_TYPE_ALERT,messageConst.WEBSOCKET_TYPE_FLOW_PEOPLE,messageConst.WEBSOCKET_TYPE_BRUSH_PEOPLE,messageConst.WEBSOCKET_TYPE_TALKBACK]);
messageConst.REFRESH_MODUAL_MAP.put("leftScreenPlay",[messageConst.WEBSOCKET_TYPE_POLICE,messageConst.WEBSOCKET_TYPE_FOREIGNPERSON,messageConst.WEBSOCKET_TYPE_BRUSH_PEOPLE,messageConst.WEBSOCKET_TYPE_CURRENT_POLICE_COUNT]);
messageConst.REFRESH_MODUAL_MAP.put("rightScreenPlay",[messageConst.WEBSOCKET_TYPE_FOREIGNPERSON,messageConst.WEBSOCKET_TYPE_FOREIGNCAR]);
messageConst.REFRESH_MODUAL_MAP.put("cds",[messageConst.WEBSOCKET_TYPE_CURRENT_POLICE_COUNT,messageConst.WEBSOCKET_TYPE_FOREIGNPERSON,messageConst.WEBSOCKET_TYPE_FOREIGNCAR,messageConst.WEBSOCKET_TYPE_FOREIGNCAR_INFO,messageConst.WEBSOCKET_TYPE_ALARMHANDLE,messageConst.WEBSOCKET_TYPE_FOREIGNCAR_INFO,messageConst.WEBSOCKET_TYPE_CALLNAME,messageConst.WEBSOCKET_TYPE_SECURITYDOOR,messageConst.WEBSOCKET_TYPE_PERSION_GOOUT,messageConst.WEBSOCKET_TYPE_PERSION_GOOUT_NUM,messageConst.POLICE_IN_24,messageConst.WEBSOCKET_TYPE_PRISONER_ONLINE,messageConst.WEBSOCKET_TYPE_ALARM_SUPERIOR_HANDLE,messageConst.WEBSOCKET_TYPE_COMMON,messageConst.WEBSOCKET_TYPE_ALARM_PROVINCE_HANDLE,messageConst.WEBSOCKET_TYPE_PHNOE_CHECK_INFO,messageConst.WEBSOCKET_TYPE_PROVINCE_MONITOR_NOTICE,messageConst.WEBSOCKET_TYPE_MSG_FAULT_MAINTAIN,messageConst.WEBSOCKET_TYPE_MSG_DOOR_RETURN_STATUS,messageConst.WEBSOCKET_TYPE_MSG_TALK_RETURN_STATUS,messageConst.WEBSOCKET_TYPE_MSG_TALK_RESULT_STATUS,messageConst.WEBSOCKET_TYPE_MSG_XWZC_RETURN_STATUS]);

//定义实时刷新模块对应的js显示方法
messageConst.REFRESH_FUN_MAP=new ces.Map();
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_POLICE,messageConst.WEBSOCKET_FN_POLICE);
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_FOREIGNPERSON,messageConst.WEBSOCKET_FN_FOREIGNPERSON);
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_FOREIGNCAR,messageConst.WEBSOCKET_FN_FOREIGNCAR);
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_ALERT,messageConst.WEBSOCKET_FN_ALERT);
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_FLOW_PEOPLE,messageConst.WEBSOCKET_FN_FLOW_PEOPLE);
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_BRUSH_PEOPLE,messageConst.WEBSOCKET_FN_BRUSH_PEOPLE);
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_TALKBACK,messageConst.WEBSOCKET_FN_TALKBACK);

//左大屏，当前监内民警
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_CURRENT_POLICE_COUNT,messageConst.WEBSOCKET_FN_CURRENT_POLICE);
//当前在监民警超过24小时
messageConst.REFRESH_FUN_MAP.put(messageConst.POLICE_IN_24,messageConst.WEBSOCKET_FN_24_POLICE);

//指挥调度系统,报警处置
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_ALARMHANDLE,messageConst.WEBSOCKET_FN_ALARMHANDLE);
//指挥调度系统,省局报警处置
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_ALARM_PROVINCE_HANDLE,messageConst.WEBSOCKET_FN_PROVINCE_ALARMHANDLE);
//报警上级处理
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_ALARM_SUPERIOR_HANDLE,messageConst.WEBSOCKET_FN_ALARMHANDLE);
//指挥调度系统,外来车辆
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_FOREIGNCAR_INFO,messageConst.WEBSOCKET_FN_FOREIGNCARHANDLE);
//点名
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_CALLNAME,messageConst.WEBSOCKET_FN_CALLNAME);
//安检门
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_SECURITYDOOR,messageConst.WEBSOCKET_FN_SECURITYDOOR);
//罪犯外出
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_PERSION_GOOUT,messageConst.WEBSOCKET_FN_PERSION_GOOUT);
//罪犯外出就医
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_PERSION_GOOUT_NUM,messageConst.WEBSOCKET_FN_OUT_DOCTOR);
//罪犯在线统计
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_PRISONER_ONLINE,messageConst.WEBSOCKET_FN_PRISONER_ONLINE);

//messageConst.WEBSOCKET_TYPE_COMMON
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_COMMON,messageConst.WEBSOCKET_FN_COMMON);

messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_PHNOE_CHECK_INFO,messageConst.WEBSOCKET_FN_PHNOE_CHECK);

//监督单提醒
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_PROVINCE_MONITOR_NOTICE,messageConst.WEBSOCKET_FN_PROVINCE_MONITOR_NOTICE);
//故障维修
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_MSG_FAULT_MAINTAIN,messageConst.WEBSOCKET_FN_MSG_FAULT_MAINTAIN);
//开关门指令返回
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_MSG_DOOR_RETURN_STATUS,messageConst.WEBSOCKET_FN_MSG_DOOR_RETURN_STATUS);
//对讲指令返回
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_MSG_TALK_RETURN_STATUS,messageConst.WEBSOCKET_FN_MSG_TALK_RETURN_STATUS);
// 智能监控轮巡 行为侦测 返回摄像头信息  用于智能视频轮巡
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_MSG_XWZC_RETURN_STATUS,messageConst.WEBSOCKET_FN_XWZC_CAMERA);
//融合通信呼叫和挂断回调
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_MSG_TALK_RESULT_STATUS,messageConst.WEBSOCKET_FN_MSG_TALK_RESULT_STATUS);

//广播指令回调
messageConst.REFRESH_FUN_MAP.put(messageConst.WEBSOCKET_TYPE_MSG_BROADCAST_RESULT_STATUS,messageConst.WEBSOCKET_FN_BROADCAST_RESULT);