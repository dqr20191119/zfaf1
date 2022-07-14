package com.cesgroup.prison.fm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;

/**
 * cesgroup
 * 消息处理抽象类
 * 
 */
public abstract class AMessageProcess<T> implements IMessageProcess{

	/**
	 * 消息处理
	 * @param cusNumber	机构号
	 * @param jsonObj	消息-JSON对象
	 */
	@Override
	public void processMessage(String cusNumber, JSONObject jsonObj) {
		// 转化消息实体类对象
		MsgHeader msgHead = JSON.toJavaObject(jsonObj.getJSONObject("header"), MsgHeader.class);
		T msgBody = JSON.toJavaObject(jsonObj.getJSONObject("body"), getBodyClass());

		// 设置机构号
		msgHead.setCusNumber(cusNumber);

		// 消息处理实现
		process(msgHead, msgBody);
	}

	/**
	 * 获取消息体对象类型
	 * @return
	 */
	public abstract Class<T> getBodyClass();

	/**
	 * 消息处理
	 * @param msgHead 消息头
	 * @param msgBody 消息体
	 */
	protected abstract void process (MsgHeader msgHead, T msgBody);
}
