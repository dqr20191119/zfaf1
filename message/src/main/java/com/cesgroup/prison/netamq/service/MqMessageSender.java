package com.cesgroup.prison.netamq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.prison.frm.net.amq.service.SendMessageAction;
import com.cesgroup.prison.frm.net.amq.service.SendNettyMessageAction;

@Service
public class MqMessageSender {
	
	@Autowired
	private SendMessageAction<String> sendFrontEndMessage = null;
	/**
	 * 布防撤防
	 */
	@Autowired
	private SendMessageAction<String> sendBfCfMessage = null;
	
	/**
	 * 门禁
	 */
	@Autowired
	private SendMessageAction<String> sendDoorMessage = null;
	
	/**
	 * netty
	 */
	@Autowired
	private SendNettyMessageAction<String> sendNettyMessage = null;
	
	/**
	 * 报警
	 */
	@Autowired
	private SendMessageAction<String> sendAlarmMessage = null;
	
	/**
	 * 对讲
	 */
	@Autowired
	private SendMessageAction<String> sendTalkMessage = null;
	
	/**
	 * 大屏
	 */
	@Autowired
	private SendMessageAction<String> sendScreenMessage = null;
	
	/**
	 * 
	 */
	@Autowired
	private SendMessageAction<String> sendCallNamesMessage = null;

	/**
	 * 融合通讯
	 */
	@Autowired
	private SendMessageAction<String> sendRcsMessage = null;

	/**
	 * 广播
	 */
	@Autowired
	private SendMessageAction<String> sendBroadcastMessage = null;
	
	/**
	 * 布防撤防发送消息
	 * @param cusNumber
	 * @param msg
	 */
	public void sendBfCfMessage(String msg, String cusNumber,String msgID) {
		sendBfCfMessage.send(msg, cusNumber,msgID);
	}
	
	
	/**
	 * 向前置机发送消息
	 * @param cusNumber
	 * @param msg
	 */
	public void sendFeMessage(String msg, String cusNumber,String msgID) {
		sendFrontEndMessage.send(msg, cusNumber,msgID);
	}
	
	/**
	 * 向客户端发送消息
	 * @param userId
	 * @param msg
	 */
	public void sendNettyMessage(String msg, String cusNumber) {
		
		sendNettyMessage.send(msg, cusNumber);
	}
	
	/**
	 * 向前置机 door 发送消息
	 * @param cusNumber
	 * @param msg
	 */
	public void sendDoorMessage(String msg, String cusNumber,String msgID) {
		sendDoorMessage.send(msg, cusNumber,msgID);
	}
	
	/**
	 * 向前置机 对讲 发送消息
	 * @param cusNumber
	 * @param msg
	 */
	public void sendTalkMessage(String msg, String cusNumber,String msgID) {
		sendTalkMessage.send(msg, cusNumber,msgID);
	}
	
	/**
	 * 模拟发送报警消息
	 * @param cusNumber
	 * @param msg
	 * @param msgType
	 */
	public void sendAlarmTestMessage(String msg, String cusNumber,String msgType) {
		sendAlarmMessage.send(msg, cusNumber,msgType);
	}
	
	/**
	 * 向前置机 大屏 发送消息
	 * @param cusNumber
	 * @param msg
	 */
	public void sendScreenMessage(String msg, String cusNumber,String msgID) {
		sendScreenMessage.send(msg, cusNumber,msgID);
	}
	
	/**
	* @methodName: sendCallNamesMessage
	* @Description: 发生发起点名请求
	* @param msg
	* @param cusNumber
	* @param msgID void
	* @throws  
	*/
	public void sendCallNamesMessage(String msg, String cusNumber,String msgID) {
		sendCallNamesMessage.send(msg, cusNumber,msgID);
	}

	/**
	 * 融合通讯消息发送
	 * @param msg
	 * @param cusNumber
	 * @param msgID
	 */
	public void sendRcsMessage(String msg, String cusNumber,String msgID) {
		sendRcsMessage.send(msg, cusNumber, msgID);
	}

	/**
	 * 广播消息发送
	 * @param msg
	 * @param cusNumber
	 * @param msgID
	 */
	public  void sendBroadcastMessage(String msg, String cusNumber,String msgID) {
		sendBroadcastMessage.send(msg, cusNumber, msgID);
	}
}
