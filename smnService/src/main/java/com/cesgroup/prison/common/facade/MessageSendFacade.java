package com.cesgroup.prison.common.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.bean.WebSocketMessage;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.prison.common.service.MessageService;
import com.cesgroup.prison.frm.net.amq.service.MessageSubscribe;
import com.cesgroup.prison.netamq.service.MqMessageSender;

/**
 * 统一消息发送入口
 * @author zxh
 * 
 */
public class MessageSendFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSendFacade.class);

	private static MqMessageSender mqMessageSender = (MqMessageSender) SpringContextUtils.getBean("mqMessageSender");
	private static MessageSubscribe messageSubscribe = (MessageSubscribe) SpringContextUtils.getBean("messageSubscribe");
	private static MessageService messageService = (MessageService) SpringContextUtils.getBean("messageService");
	
	/**
	 * msgType: 	消息类型: 3001-当前报警,3006-上级报警处理,6001-监督单提醒,6002-故障维修
	 * sendType: 	发送类型：1-按监狱订阅、2-按用户、3-按组织部门
	 * sendTo: 		发送目标对象: 对应上面的具体值-多个值逗号分隔
 	 * content: 	回调给前台处理数据---json字符串
	 * url: 		业务模块处理url---带上下文路径列表页面
	 * isSendToGzt: 是否发送到工作台消息	0-否，1-是(默认传1)
	 * ywId:		业务id
	 * cusNumber:	当前监狱id
	 * 
	 * @param msgMap
	 */
	public static void send(Map<String, String> msgMap) throws Exception {
		
		String sendType = msgMap.get("sendType");
		
		if("1".equals(sendType)) {
			// 按监狱订阅
			sendByCusNumber(msgMap);
		}
		
		if("2".equals(sendType)) {
			// 按用户
			sendByUserId(msgMap);
		}
		
		if("3".equals(sendType)) {
			// 按组织部门
			sendByOrgKey(msgMap);
		}
	}
	
	/**
	 * 按当前监狱订阅此类消息的消息发送
	 * @param msgMap
	 * @throws Exception 
	 */
	private static void sendByCusNumber(Map<String, String> msgMap) throws Exception {
		
		String msgType = msgMap.get("msgType");
		String[] cusNumberArray = msgMap.get("sendTo").split(",");
		String content = msgMap.get("content");
 		String url = msgMap.get("url");
		String isSendToGzt = msgMap.get("isSendToGzt");
		String ywId = msgMap.get("ywId");
		String noticeContent = msgMap.get("noticeContent") != null ? msgMap.get("noticeContent").toString() : "";
		
		// 获取当前订阅此类消息的用户
		JSONArray userArray = messageSubscribe.getUserId(msgType);
		List<String> userIdList = new ArrayList<String>();
		if(userArray != null) {
			for(int i = 0; i < userArray.size(); i++) {
				
				String jsonUser = userArray.getString(i);
				JSONObject userObj = JSONObject.parseObject(jsonUser);
				String userCusNumber = userObj.getString("cusNumber");
				
				for(int j = 0; j < cusNumberArray.length; j++) {
					if((!StringUtil.isNull(userCusNumber) && userCusNumber.equals(cusNumberArray[j]))) {
						String userId = userObj.getString("userId");
						userIdList.add(userId);
					}	
				}
			}
		}
		
		if(userIdList.size() > 0) {
			
			if(!"0".equals(isSendToGzt)) {
				try {
					String[] userIdArray = new String[userIdList.size()];
					userIdList.toArray(userIdArray);
					
					// 插入工作台消息
					messageService.generalMessageByUser(userIdArray, url, msgType, ywId, noticeContent);
				} catch(Exception e) {
					logger.error("插入工作台消息报错: " + e.toString(), e.fillInStackTrace());
				}
			}
			
			// 发送前台消息websocket
			WebSocketMessage webSocketMessage = new WebSocketMessage();
			webSocketMessage.setSendType(msgMap.get("sendType"));
			webSocketMessage.setSendTo(msgMap.get("sendTo"));
			webSocketMessage.setMsgType(msgType);
			webSocketMessage.setContent(content);
			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), msgMap.get("cusNumber"));
		}
	}
	
	/**
	 * 按用户发送消息
	 * @param msgMap
	 * @throws Exception 
	 */
	private static void sendByUserId(Map<String, String> msgMap) throws Exception {
		
		String msgType = msgMap.get("msgType");					
		String[] userIdArray = msgMap.get("sendTo").split(","); 			
		String content = msgMap.get("content"); 				
 		String url = msgMap.get("url"); 
 		String isSendToGzt = msgMap.get("isSendToGzt"); 
 		String ywId = msgMap.get("ywId");
		String noticeContent = msgMap.get("noticeContent") != null ? msgMap.get("noticeContent").toString() : "";
 		
 		if(!"0".equals(isSendToGzt)) {
			try {
				// 插入工作台消息
				messageService.generalMessageByUser(userIdArray, url, msgType, ywId, noticeContent);
			} catch(Exception e) {
				logger.error("插入工作台消息报错: " + e.toString(), e.fillInStackTrace());
			}
 		}
		
		// 发送前台消息websocket
		WebSocketMessage webSocketMessage = new WebSocketMessage();
		webSocketMessage.setSendType(msgMap.get("sendType"));
		webSocketMessage.setSendTo(msgMap.get("sendTo"));
		webSocketMessage.setMsgType(msgType);
		webSocketMessage.setContent(content);
         // webSocketMessage.set
		mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), msgMap.get("cusNumber"));	 
	}
	
	/**
	 * 按组织部门发送消息
	 * @param msgMap
	 * @throws Exception 
	 */
	private static void sendByOrgKey(Map<String, String> msgMap) throws Exception {
		
		String msgType = msgMap.get("msgType");					
		String[] orgKeyArray = msgMap.get("sendTo").split(","); 			
		String content = msgMap.get("content"); 				
 		String url = msgMap.get("url"); 
 		String isSendToGzt = msgMap.get("isSendToGzt"); 
 		String ywId = msgMap.get("ywId");
		String noticeContent = msgMap.get("noticeContent") != null ? msgMap.get("noticeContent").toString() : "";
 		
 		if(!"0".equals(isSendToGzt)) {
			try {
				// 插入工作台消息
				messageService.generalMessageByOrg(orgKeyArray, url, msgType, ywId, noticeContent);
			} catch(Exception e) {
				logger.error("插入工作台消息报错: " + e.toString(), e.fillInStackTrace());
			}
 		}
		
		// 发送前台消息websocket
		WebSocketMessage webSocketMessage = new WebSocketMessage();
		webSocketMessage.setSendType(msgMap.get("sendType"));
		webSocketMessage.setSendTo(msgMap.get("sendTo"));
		webSocketMessage.setMsgType(msgType);
		webSocketMessage.setContent(content);
		mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), msgMap.get("cusNumber"));
	}
}
