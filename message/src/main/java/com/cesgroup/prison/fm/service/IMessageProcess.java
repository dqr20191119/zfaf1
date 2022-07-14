package com.cesgroup.prison.fm.service;

import com.alibaba.fastjson.JSONObject;

/**
 * cesgroup
 * 消息处理接口
 * @author lihh
 * 
 */
public interface IMessageProcess {

	/**
	 * 消息处理
	 * @param cusNumber	机构号
	 * @param jsonObj	消息-JSON对象
	 */
	public void processMessage(String cusNumber, JSONObject jsonObj);

}