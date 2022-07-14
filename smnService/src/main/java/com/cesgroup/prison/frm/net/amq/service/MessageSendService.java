package com.cesgroup.prison.frm.net.amq.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.frm.net.netty.service.IMsgChannel;
import com.cesgroup.frm.net.netty.service.impl.MsgChannelManager;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

/**
 * 消息统一发送者
 * @author ziyang
 *
 */
public class MessageSendService {
	
	private final static Logger logger = LoggerFactory.getLogger(MessageSendService.class);
	
	@Autowired
	private SendMessageAction<String> sendAction = null;
	
	@Resource
	private MessageSubscribe messageSubscribe = null;
	
	/**
	 * 处理向前置机发送的队列消息
	 * @param message
	 * @param cusNumber
	 * @return
	 */
	public String handleMessage(String message, String cusNumber,String msgId) {
		sendAction.send(message, cusNumber,msgId);
		return null;
	}
	
	public String handleNettyMessage(String message, String cusNumber) {
		
		JSONObject msgObject = JSONObject.parseObject(message);
		String msgTypeString = msgObject.getString("msgType");	
		String sendType = msgObject.getString("sendType");
		String sendTo = msgObject.getString("sendTo");
		
		if(msgTypeString == null || "".equals(msgTypeString)) {
			logger.error("消息类型为空，message=" + message);
			return null;
		}
			
		if(sendType == null || "".equals(sendType)) {
			logger.error("消息发送类型为空，message=" + message);
			return null;
		}
		
		if(sendTo == null || "".equals(sendTo)) {
			logger.error("消息发送目标对象为空，message=" + message);
			return null;
		}
		
		if("1".equals(sendType)) {
			// 按监狱订阅
			// 根据消息类型获取订阅的用户
			JSONArray userArray = messageSubscribe.getUserId(msgTypeString);
			
			//通用消息不需要订阅
			if(msgTypeString.equals(IMsgTypeConst.COMMON_MESSAGE)) {
				
				List<String> userList = MsgChannelManager.getMsgChannelManager().getUserList();
				for(String userId : userList) {
					IMsgChannel channel = MsgChannelManager.getMsgChannelManager().getMsgChannel(userId);
					if (null != channel) {
						String userCusNumber = channel.getBranch();
						
						String[] sendToArray = sendTo.split(",");
						for(String s : sendToArray) {
							if(userCusNumber != null && s.equals(userCusNumber)) {
								channel.write(message);
							}
						}
					}	
				}
			} else {
				
				if(userArray != null) {
					for(int i = 0; i < userArray.size(); i++) {
						
						String jsonUser = userArray.getString(i);
						JSONObject userObj = JSONObject.parseObject(jsonUser);
						String userCusNumber = userObj.getString("cusNumber");
						
						String[] sendToArray = sendTo.split(",");
						for(String s : sendToArray) {
							if((!StringUtil.isNull(userCusNumber) && userCusNumber.equals(s))) {
								String userId = userObj.getString("userId");
								IMsgChannel channel = MsgChannelManager.getMsgChannelManager().getMsgChannel(userId);
								if (null != channel) {
									channel.write(message);
								}
							}
						}
					}
				}
			}
		} else if("2".equals(sendType)) {
			
			// 按用户
			String[] sendToArray = sendTo.split(",");
			for(String s : sendToArray) {
				IMsgChannel channel = MsgChannelManager.getMsgChannelManager().getMsgChannel(s);
				if (null != channel) {
					channel.write(message);
				}
			}
		} else if("3".equals(sendType)) {
			
			// 按组织部门
			String[] sendToArray = sendTo.split(",");
			for(String s : sendToArray) {
				try {
					List<UserEntity> userList = AuthSystemFacade.getAllUserInfoByOrgKey(s);
					for(UserEntity user : userList) {
						IMsgChannel channel = MsgChannelManager.getMsgChannelManager().getMsgChannel(user.getUserId() + "");
						if (null != channel) {
							channel.write(message);
						}
					}
				} catch (Exception e) {
					logger.error("消息发送错误，message=" + message);
				}
			}
		}
		
		return null;
	}

	public SendMessageAction<String> getSendAction() {
		return sendAction;
	}

	public void setSendAction(SendMessageAction<String> sendAction) {
		this.sendAction = sendAction;
	}
}
