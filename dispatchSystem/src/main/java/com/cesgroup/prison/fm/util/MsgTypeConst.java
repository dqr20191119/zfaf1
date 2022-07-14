package com.cesgroup.prison.fm.util;

import com.cesgroup.prison.common.constant.CommonConstant.PhotoCategoryValue;

public class MsgTypeConst {
	/**
	 * 外来车辆进出状态
	 */
	public static final String FOREIGN_CAR_INOUT="FC000";
	/**
	 * 外来车辆登记
	 */
	public static final String FOREIGN_CAR_REGIST="FC001";
	/**
	 * 外来车辆进入
	 */
	public static final String FOREIGN_CAR_ENTER="FC002";
	/**
	 * 外来车辆离开
	 */
	public static final String FOREIGN_CAR_LEAVE="FC003";
	/**
	 * 外来车辆登记照片
	 */
	public static final String FOREIGN_CAR_RGIMG="FC004";
	/**
	 * 外来车辆进出照片
	 */
	public static final String FOREIGN_CAR_INOUTIMG="FC005";
	/**
	 * 外来人员登记
	 */
	public static final String FOREIGN_PEOPLE_REGIST="FP001";
	/**
	 * 外来人员进入
	 */
	public static final String FOREIGN_PEOPLE_ENTER="FP002";
	/**
	 * 外来人员离开
	 */
	public static final String FOREIGN_PEOPLE_LEAVE="FP003";
	/**
	 * 外来人员登记照片
	 */
	public static final String FOREIGN_PEOPLE_RGIMG="FP004";
	/**
	 * 外来人员进出照片
	 */
	public static final String FOREIGN_PEOPLE_INOUTIMG="FP005";
	/**
	 * 出工收工
	 */
	public static final String ONE_CARD_WORK="POC001";
	/**
	 * 零星流动发起
	 */
	public static final String SPORADIC_FLOW_START="POC002";
	/**
	 * 零星流动到目的地和归队
	 */
	public static final String SPORADIC_FLOW_END="POC003";
	/**
	 * 人员流动
	 */
	public static final String ONE_CARD_FLOW="POC004";
	/**
	 * 点名开始和结束
	 */
	public static final String CALL_START="POC005";
	/**
	 * 点名中的消息
	 */
	public static final String CALL_DTLS="POC006";
	/**
	 * 罪犯实时在线统计
	 */
	public static final String PRISONER_ONLINE="POC007";
	/**
	 * 超时未点名
	 */
	public static final String CALL_TIMEOUT="POC008";
	/**
	 * 罪犯异常外出
	 */
	public static final String EXCEPTION_FLOW = "POC009";

	/**
	 * 门禁刷卡
	 */
	public static final String DOOR_CARD="DOOR001";
	
	/**
	 * 门禁状态
	 */
	public static final String DOOR_STATUS="DOOR004";
	/**
	 * 开关门指令返回
	 */
	public static final String DOOR_RETURN_STATUS="DOOR003";
	
	/**
	 * 警员进出
	 */
	public static final String POLICE_INOUT="POLICE001";
	/**
	 * 警员进出照片
	 */
	public static final String POLICE_INOUT_IMG="POLICE002";
	/**
	 * 当前在监民警
	 */
	public static final String POLICE_INPRISON="POLICE003";
	/**
	 * 警员进出历史纪录
	 */
	public static final String POLICE_INOUT_H="POLICE004";
	/**
	 * 摄像机状态
	 */
	public static final String CAMERA_STATUS="CAMERA003";
	/**
	 * 对讲指令返回
	 */
	public static final String TALK_RETURN_STATUS="TALK003";


    /**
     * 通信融合呼叫或挂断返回
     */
    public static final String TALK_RESULT_RSP="TalkControlRsp";
	/**
	 * 犯人离监
	 */
	public static final String PRISONER_GOOUT="WARN001";
	/**
	 * 犯人离监照片
	 */
	public static final String PRISONER_GOOUTIMG="WARN002";
	/**
	 * 防逃预警
	 */
	public static final String PRISONER_BREAKOUTWARN="WARN003";
	/**
	 * 预警记录疑似犯人信息
	 */
	public static final String SUSPECT_PRISONER="WARN004";
	
	public static final String PHONE_CHECK_INFO="PHONE001";
	
	/**
	 * 开始点名指令返回 
	 */
	public static final String CALL_NAME_RETURN_02="CALLNAME002";
	
	/**
	 * 点名中指令返回
	 */
	public static final String CALL_NAME_RETURN_03="CALLNAME003";
	
	/**
	 * 结束点名指令返回
	 */
	public static final String CALL_NAME_RETURN_05="CALLNAME005";
	
	/**
	 * 湖南点名信息返回
	 */
	public static final String CALL_ROLL_IN="CallRoll001";
	
	/**
	 * 通信融合指令发出
	 */
	public static final String RCS_COMMAND_OUT = "DISPATCH001";
	/**
	 * 通信融合指令返回
	 */
	public static final String RCS_COMMAND_IN = "DISPATCH002";

	/**
	 * 民警所在位置消息类型
	 */
	public static final String POLICE_LOCATION = "PL001";
	
	/**
	 * 人脸识别黑名单标识
	 */
	public static final String RLSB_HMD = "FaceMark000";

	/**
	 * 安检消息类型
	 */
	public static final String SECURITY_CHECK = "SC000";
	
	/**
	 * 巡更记录标识
	 */
	public static final String XGJL = "PR000";
	/**
	 * 外来人员标识
	 */
	public static final String OUT_SIDER = "OUTSIDER000";
	/**
	 * 高压电网消息
	 */
	public static final String POWER_DW="Power001";
	/**
	 * 行为侦测数据
	 */
	public static final String BEHAVIORN_IN = "Behaviorn01";
	/**
	 * 电视墙数据
	 */
	public static final String SCREEN_IN = "TvWall001";
	/**
	 *  门禁状态
	 */
	public static final String DOORSTATE_IN = "DOOR000";
	/**
	 * 广播返回消息
	 */
	public static final String BROADCAST_RETURN = "BROADCAST000";

    /**
     * 电视墙所有预案数据
     */
    public static final String SCENE_ALL_INFO = "SceneAllInfo";
    /**
     * 电视墙窗口数据
     */
    public static final String TVWALL_WINDOWS = "TvWallWindows";

    /**
     * 电视墙切换消息返回
     */
    public static final String SCREEN_PLAN_QH = "SCREEN002RSP";

    /**
     * 布防撤防类型返回指令
     */
    public final static String BFCF_TYPE = "bfcfRsp001";

    /**
     * 布防撤防状态
     */
    public final static String BFCF_STATUS = "bfcfStatus001";

}
