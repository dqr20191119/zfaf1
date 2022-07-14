package com.cesgroup.prison.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ScreenPlanWsService {

	/**
	* @methodName: getScreenPlanList
	* @Description:  获取大屏预案关联的轮巡信息（区域、信号源、窗口号）
	* @param cusNumber
	* @param screenPlanId
	* @return String
	* @throws  
	*/
	public String getScreenPlanList(@WebParam(name = "cusNumber") String cusNumber, @WebParam(name = "screenPlanId") String screenPlanId);

}