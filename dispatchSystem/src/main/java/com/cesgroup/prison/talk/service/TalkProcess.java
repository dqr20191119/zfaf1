package com.cesgroup.prison.talk.service;

import java.util.HashMap;
import java.util.Map;

import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;

/**
 * 对讲消息的处理类
 * @author zxh
 *
 */
@Service
public class TalkProcess implements IMessageProcess {
	
	private final Logger logger = LoggerFactory.getLogger(TalkProcess.class);
	
	@Override
	@Transactional
	public void processMessage(String cusNumber, JSONObject jsonObject) {
		
		MsgHeader msgHead = JSON.toJavaObject(jsonObject.getJSONObject("header"), MsgHeader.class);		
		String msgType = msgHead.getMsgType();
		
		if(MsgTypeConst.TALK_RETURN_STATUS.equals(msgType)) {
			logger.info("收到对讲指令返回信息：" + jsonObject.toJSONString());
				
			try {
				// 向前台发送消息
				Map<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("msgType", IMsgTypeConst.MSG_TALK_RETURN_STATUS);
				msgMap.put("sendType", "2");
				msgMap.put("sendTo", jsonObject.getJSONObject("body").get("userID") + "");
				msgMap.put("content", JSONObject.toJSONString(jsonObject.getJSONObject("body")));
				msgMap.put("isSendToGzt", "0");
				msgMap.put("url", null);
				msgMap.put("ywId", null);
				msgMap.put("cusNumber", cusNumber);
				msgMap.put("noticeContent", "收到对讲指令返回信息");
				MessageSendFacade.send(msgMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(MsgTypeConst.TALK_RESULT_RSP.equals(msgType)){

                logger.info("收到融合通信呼叫或挂断返回信息：" + jsonObject.toJSONString());
                try {
                    JSONObject body = jsonObject.getJSONObject("body");
                   // body.put("action","1");
                    // 向前台发送消息
                   // body.getString("userID");
                    Map<String, String> msgMap = new HashMap<String, String>();
                    msgMap.put("msgType", IMsgTypeConst.MSG_TALK_RESULT_STATUS);
                    msgMap.put("sendType", "2");
                    msgMap.put("sendTo", body.getString("userID"));
                    msgMap.put("content", JSONObject.toJSONString(body));
                    msgMap.put("isSendToGzt", "0");
                    msgMap.put("url", null);
                    msgMap.put("ywId", null);
                    msgMap.put("cusNumber", cusNumber);
                    MessageSendFacade.send(msgMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

	}
}
