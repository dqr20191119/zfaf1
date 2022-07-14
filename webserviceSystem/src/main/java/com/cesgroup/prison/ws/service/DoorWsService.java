package com.cesgroup.prison.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface DoorWsService {
	
	/**
	 * 获取指定监狱下所有的门禁列表
	 * @param cusNumber 监狱编号
	 * @return
	 */
	public String getDoorList(@WebParam(name="cusNumber") String cusNumber);
	
	/**
	 * 获取指定监狱下更新时间大于等于updateDate的门禁列表
	 * @param cusNumber 监狱编号
	 * @param updateDate 更新时间
	 * @return
	 */
    public String getDoorListForTime(@WebParam(name="cusNumber") String cusNumber, @WebParam(name="updateDate") String updateDate);
}