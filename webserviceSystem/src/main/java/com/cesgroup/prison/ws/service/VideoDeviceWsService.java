package com.cesgroup.prison.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface VideoDeviceWsService {
	
	/**
	 * 获取指定监狱下面的所有录像机列表
	 * @param cusNumber 监狱编号
	 * @return 
	 */
	public String getVideoDeviceList(@WebParam(name="cusNumber") String cusNumber);	
	
	/**
	 * 获取指定监狱下面更新时间大于等于updateDate的录像机列表
	 * @param cusNumber 监狱编号
	 * @param updateDate 更新时间
	 * @return 
	 */
	public String getVideoDeviceListForTime(@WebParam(name="cusNumber") String cusNumber, @WebParam(name="updateDate") String updateDate);
}