package com.cesgroup.prison.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface AlertorWsService {
	
	/**
	 * 获取指定监狱下所有的报警器列表
	 * @param cusNumber 监狱编号
	 * @return
	 */
	public String getAlertorList(@WebParam(name="cusNumber") String cusNumber);
	
	/**
	 * 获取指定监狱下更新时间大于等于updateDate的报警器列表
	 * @param cusNumber 监狱编号
	 * @param updateDate 更新时间
	 * @return
	 */
    public String getAlertorListForTime(@WebParam(name="cusNumber") String cusNumber, @WebParam(name="updateDate") String updateDate);

    /**
	 * 根据类型获取指定监狱下所有的报警器列表
	 * @param cusNumber 监狱编号
	 * @return
	 */
	public String getAlertorListByType(@WebParam(name="cusNumber") String cusNumber, @WebParam(name="type") String type);
}