package com.cesgroup.cds.service.syslog;

import java.util.List;
import java.util.Map;


public interface ISysLogService{
	// 操作子类型枚举
	public enum SubType{
		NONE(0),// 无
		OPEN_VIDEO(1), // 实时视频
		PLAY_BACK(2), // 视频回放
		PLAY_FILE(3), // 播放录像
		SNAP(4), // 视频截图
		RECORD(5);// 视频录像

		private Object type; 
		private SubType(Object type){
			this.type = type;
		}
		public String toString(){
			return String.valueOf( type );
		}
	};

	boolean getFlag();
	
	boolean addSysLogInfo(String args) throws Exception;

	public boolean addSysLogInfo(List<Object> params) throws Exception;
	
	public List<Map<String,Object>> querySysLogList(String args)throws Exception;
	
	/**
	 * 视频操作日志
	 * @param params 参数集合
	 * params = [机构号, 日志编号, 操作人编号, 操作人名称, 操作类型, 操作子类型, 摄像机编号, 摄像机名称, 操作数量]
	 */
	public void addVideoHandleLog (List<Object> params);
}
