package com.cesgroup.prison.common.constant;

import com.cesgroup.framework.util.ConfigUtil;
import com.cesgroup.prison.common.constant.CommonConstant.PhotoCategoryValue;

public class CommonConstant {

	/**
	 * 文件上传路径
	 */
	public static final String jggzUploadsRootPath = ConfigUtil.get("SYSTEM.FILE.ABSOULT.PATH");			// 系统文件全局上传根路径
	public static String uploadGlobalPath = "zhddUploads";					// 系统文件全局上传相对路径

	/**
	 * 视频文件上传路径
	 */
	// public static final String systemRootAbsoultPath =
	// "D:\\tomcats\\apache-tomcat-8.5.20\\webapps\\prison"; //系统根路径
	public static final String systemRootAbsoultPath = ConfigUtil.get("SYSTEM.ROOT.ABSOULT.PATH"); 					// 系统根路径

	public static final String uploadZhddGlobalPath = "zhddUploads";				// 指挥调度系统文件全局上传相对路径
	public static final String ftpVideoPlanPath = "videoplan";						// 视频上传相对路径
	public static final String ftpVideoImgPath = "image";							// 照片上传相对路径
	public static final String ftpVideoRecordPath = "record";						// 录像上传相对路径
	public static final String ftpInspectDocPath = "inspectDoc";					// 巡查通报文档上传相对路径

	public static final String soundPath = "sound";					                // 报警提示音上传相对路径

	/**
	 * 预案配置类别
	 */
	public static final String EPC_CONFIG_TYPE_CZFF_CODE = "1";		// 处置方法
	public static final String EPC_CONFIG_TYPE_GZZ_CODE = "2";		// 工作组

	/**
	 * 状态
	 */
	public static final String STATUS_VALID_CODE = "1";						// 有效
	public static final String STATUS_INVALID_CODE = "0";					// 无效

	/**
	 * 返回结果状态
	 */
	public static final String SUCCESS_CODE = "1";					// 成功
	public static final String FAILURE_CODE = "0";					// 失败
	public static final String VALID_FAILURE_CODE = "2";			// 校验不过

	/**
	 * 设备类型
	 */
	public static final String CAMERA_DEVICE_TYPE = "1";			// 摄像头
	public static final String TALK_DEVICE_TYPE = "2";				// 对讲分机
	public static final String ALERTOR_DEVICE_TYPE = "3";			// 报警器
	public static final String DOOR_DEVICE_TYPE = "4";				// 门禁
	public static final String BROADCAST_DEVICE_TYPE = "5";			// 广播
	public static final String TALK_SERVER_DEVICE_TYPE = "6";		// 对讲主机
	public static final String LABEL_DEVICE_TYPE = "20";			// 地图标签

	/**
	 * 当前报警列表页面
	 */
	public static final String ALARM_DEAL_URL = "/prison/alarm/openDialog/record";

	/**
	 * @Fields CALL_NAMES_REDIS : redis 中点名表表面
	 */
	public static final String CALL_NAMES_REDIS = "call_names_data";
	
	/**
	 * 外来车辆进出状态
	 */
	public interface CarInOut {
	    /**
	     * 进车
	     */
	    public final String IN = "1";
	    /**
	     * 出车
	     */
	    public final String OUT = "2";
	    /**
	     * 未知
	     */
	    public final String NONE = "99";
	}

	/**
	 * 外来车辆进出状态
	 */
	public interface CarInOutCN {
	    /**
	     * 进车
	     */
	    public final String IN = "进车";
	    /**
	     * 出车
	     */
	    public final String OUT = "出车";
	    /**
	     * 未知
	     */
	    public final String NONE = "未知";
	}
	
	/**
	 * 预警类型枚举值
	 */
	public enum CarInOutCNValue {
		IN("进车", "1"), OUT("出车", "2"), NONE("未知", "99");
		
		// 成员变量
	    private String key;  
	    private String value;  
	    // 构造方法
	    private CarInOutCNValue(String key, String value) {  
	        this.key = key;  
	        this.value = value;  
	    }
	    
	    /**
	     * @param key
	     * @return
	     */
	    public static String get(String key) {
	    	for(CarInOutCNValue data : CarInOutCNValue.values()) {
	    		if(data.getKey().equals(key)) {
	    			return data.getValue();
	    		}
	    	}
	    	return null;
	    }
	    
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 人员进出状态
	 */
	public interface InOutFlag {
	    /**
	     * 进
	     */
	    public final String IN = "1";
	    /**
	     * 出
	     */
	    public final String OUT = "2";
	}

	/**
	 * 是否AB门(即，是否进出监狱的大门)  后面改成进出门记录，1是进、2是出
	 */
	public interface ABFlag {
	    /**
	     * 进
	     */
	    public final String NO = "1";
	    /**
	     * 出
	     */
	    public final String YES = "2";
	}
	
	/**
	 * 民警当前位置数据来源
	 */
	public interface PoliceLocationDataSource {
	    /**
	     * 大旗
	     */
	    public final String DAQI = "daqi";
	}

	/**
	 * 广播播放状态
	 */
	public interface BroadcastPlayStatus {
	    /**
	     * 停止
	     */
	    public final String STOP = "0";
	    /**
	     * 播放
	     */
	    public final String PLAY = "1";
	}

	/**
	 * 照片类型中文
	 */
	public interface PhotoCategoryCN {
	    /**
	     * 正面像
	     */
	    public final String A = "正面像";
	    /**
	     * 左侧像
	     */
	    public final String B = "左侧像";
	    /**
	     * 右侧像
	     */
	    public final String C = "右侧像";
	    /**
	     * 背面像
	     */
	    public final String D = "背面像";
	    /**
	     * 全身像
	     */
	    public final String E = "全身像";
	}
	
	/**
	 * 照片类型枚举值
	 */
	public enum PhotoCategoryValue {
		A("正面像", "A"), B("左侧像", "B"), C("右侧像", "C"), D("背面像", "D"), E("全身像", "E");
		
		// 成员变量
	    private String key;  
	    private String value;  
	    // 构造方法
	    private PhotoCategoryValue(String key, String value) {  
	        this.key = key;  
	        this.value = value;  
	    }
	    
	    /**
	     * @param key
	     * @return
	     */
	    public static String get(String key) {
	    	for(PhotoCategoryValue data : PhotoCategoryValue.values()) {
	    		if(data.getKey().equals(key)) {
	    			return data.getValue();
	    		}
	    	}
	    	return null;
	    }
	    
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	/**
	 * 广播状态
	 */
	public interface BroadcastStatus {
		/**
		 * 空闲
		 */
		public static final String IDLE = "0";
		/**
		 * 离线
		 */
		public static final String OFFLINE = "1";
		/**
		 * 使用中
		 */
		public static final String IN_SERVICE = "2";
	}
	/**
	 * 广播类型
	 */
	public interface BroadcastType {
		/**
		 * 文字转语音广播
		 */
		public static final String TEXT = "2";
		/**
		 * 媒体库文件
		 */
		public static final String AUDIO = "1";
	}
	/**
	 * 开始播放广播
	 */
	public interface StartBroadcastMsg {
		/**
		 * 开始播放文字转语音广播
		 */
		public static final String START_TEXT = "Broadcast001";
		/**
		 * 开始播放媒体库文件
		 */
		public static final String START_AUDIO = "Broadcast003";
	}
	/**
	 * 停止播放广播
	 */
	public interface StopBroadcastMsg {
		/**
		 * 停止播放文字转语音广播
		 */
		public static final String STOP_TEXT = "Broadcast002";
		/**
		 * 停止播放媒体库文件
		 */
		public static final String STOP_AUDIO = "Broadcast004";
	}
	/**
	 * 开始广播枚举值
	 */
	public enum StartBroadcastMsgEnum {
		START_TEXT("2", "Broadcast001"), START_AUDIO("1", "Broadcast003");
		
		// 成员变量
	    private String key;  
	    private String value;  
	    // 构造方法
	    private StartBroadcastMsgEnum(String key, String value) {  
	        this.key = key;  
	        this.value = value;  
	    }
	    
	    /**
	     * @param key
	     * @return
	     */
	    public static String get(String key) {
	    	for(StartBroadcastMsgEnum data : StartBroadcastMsgEnum.values()) {
	    		if(data.getKey().equals(key)) {
	    			return data.getValue();
	    		}
	    	}
	    	return null;
	    }
	    
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	/**
	 * 停止广播枚举值
	 */
	public enum StopBroadcastMsgEnum {
		STOP_TEXT("2", "Broadcast002"), STOP_AUDIO("1", "Broadcast004");
		
		// 成员变量
	    private String key;  
	    private String value;  
	    // 构造方法
	    private StopBroadcastMsgEnum(String key, String value) {  
	        this.key = key;  
	        this.value = value;  
	    }
	    
	    /**
	     * @param key
	     * @return
	     */
	    public static String get(String key) {
	    	for(StopBroadcastMsgEnum data : StopBroadcastMsgEnum.values()) {
	    		if(data.getKey().equals(key)) {
	    			return data.getValue();
	    		}
	    	}
	    	return null;
	    }
	    
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	/**
	 * 行为侦测数据来源
	 */
	public interface BehaviornDataSource {
		/**
		 * 大华
		 */
		public static final String DA_HUA = "dahua";
		/**
		 * 海康
		 */
		public static final String HIK = "hik";
		/**
		 * 宇势
		 */
		public static final String YU_SHI = "yushi";
	}
	/**
	 * 电视墙品牌枚举值
	 */
	public enum TvWallBrandEnum {
		DA_HUA("dahua", "1"), HIK("hik", "2"), YU_SHI("yushi", "3");

		// 成员变量
		private String key;
		private String value;
		// 构造方法
		private TvWallBrandEnum(String key, String value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * @param key
		 * @return
		 */
		public static String get(String key) {
			for(TvWallBrandEnum data : TvWallBrandEnum.values()) {
				if(data.getKey().equals(key)) {
					return data.getValue();
				}
			}
			return null;
		}

		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 通信融合终端标识后缀
	 */
	public interface RcsTerFlagSuffix {
		/**
		 * 应急处置记录工作组
		 */
		public static final String EMER_HANDLE_RECORD_GROUP = "EmerHandleRecordGroup";
		/**
		 * 应急处置记录工作组成员
		 */
		public static final String EMER_HANDLE_RECORD_MEMBER = "EmerHandleRecordMember";
	}
}
