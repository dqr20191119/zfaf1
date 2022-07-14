package com.cesgroup.prison.screenSwitch.service;

import java.util.List;

import com.cesgroup.framework.bean.AjaxMessage;

public interface ScreenSwitchService {

	/**
	* @methodName: startScreenSwitch
	* @Description: 切换大屏预案
	* @param planId 大屏预案 id
	* @param type 1、切换大屏  2、切换大屏并视频轮询  3、报警切换大屏  4 、三维巡视切换大屏
	* @param cameraId
	* @return AjaxMessage
	* @throws  
	*/
	AjaxMessage startScreenSwitch(String planId, String type, List<String> cameraId, String alarmAddress);

}