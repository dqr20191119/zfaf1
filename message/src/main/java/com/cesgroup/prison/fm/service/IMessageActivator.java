package com.cesgroup.prison.fm.service;

/**
 * cesgroup
 * 消息处理激活器接口（用于接收前置机MQ通道消息）
 * @author lihh
 */
public interface IMessageActivator {

	/**
	 * 消息处理
	 * @param message	收到的消息
	 * @param cusNumber	监狱机构号
	 */
	public void handle (String message, String cusNumber);
}
