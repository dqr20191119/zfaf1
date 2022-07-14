package com.cesgroup.prison.realTimeTalk.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.service.TalkBackServerService;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.realTimeTalk.service.RealTimeTalkService;

@Service
public class RealTimeTalkServiceImpl implements RealTimeTalkService {
	private static final Logger log = LoggerFactory.getLogger(RealTimeTalkServiceImpl.class);
	
	@Resource
	private MqMessageSender mqMessageSender;

	@Resource
	private TalkBackServerService service;

	@Override
	public AjaxMessage startTalk(String pcIp, String cusNumber, List<String> idnty) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		String msg = null;
		try {
			log.info("根据当前用户的IP，查找当前IP所绑定的对讲主机。cusNumber = " + cusNumber + ", pcIp = " + pcIp + ", baseIdnty = " + idnty.get(0));
			List<Map<String, Object>> talkMap = service.findInfoByCusNumberAndPcIpAndBaseIdnty(cusNumber, pcIp, idnty.get(0)); // 根据当前用户的IP，查找当前IP所绑定的对讲主机
			if (talkMap != null && !talkMap.isEmpty()) {
				String talkID = (String) talkMap.get(0).get("TSE_IDNTY");
				String brand = (String) talkMap.get(0).get("TSE_BRAND");// 对讲品牌
				String toID = "";
				for (String string : idnty) {
					toID = toID + string + ",";
				}
				toID = toID.substring(0, toID.length() - 1);
				/*
				 * String body = "\"body\":{\"talkID\":\"" + talkID + "\",\"toID\":\"" + toID +
				 * "\",\"brand\":\"" + brand + "\",\"action\":1}";
				 */
				UserBean userBean = AuthSystemFacade.getLoginUserInfo();
				String userId = userBean.getUserId();
				JSONObject msgBody = new JSONObject();
				msgBody.put("talkID", talkID);
				msgBody.put("toID", toID);
				msgBody.put("action", "1");
				msgBody.put("brand", brand);
				msgBody.put("userID", userId);
				String sendMsg = this.createMessage(cusNumber, msgBody);
				log.info("呼叫对讲指令消息 = " + sendMsg);
				mqMessageSender.sendTalkMessage(sendMsg, cusNumber, "5");
				flag = true;
				msg = "呼叫对讲指令发送成功";
				log.info("呼叫对讲指令发送成功。cusNumber = " + cusNumber + ", pcIp = " + pcIp + ", baseIdnty = " + idnty.get(0));
			} else {
				flag = false;
				msg = "呼叫对讲指令发送失败，未找到匹配的对讲主机";
				log.info("呼叫对讲指令发送失败，未找到匹配的对讲主机。cusNumber = " + cusNumber + ", pcIp = " + pcIp + ", baseIdnty = " + idnty.get(0));
			}
		} catch (Exception e) {
			flag = false;
			msg = "呼叫对讲指令发送失败, 发生异常 ：" + e.getMessage();
			log.info("呼叫对讲指令发送失败, 发生异常 ：" + e.getMessage());
		}
		ajaxMessage.setMsg(msg);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	private String createMessage(String cusNumber, JSONObject msgBody) {
		/*
		 * String head = "\"header\":{\"cusNumber\":\"" + cusNumber + "\",\"msgID\":\""
		 * + MsgIdUtil.getMsgSeq("") +
		 * "\",\"msgType\":\"TALK001\",\"sender\":\"Server\",\"sendTime\":\"" +
		 * currentTime + "\",\"recevier\":\"Server\",\"length\":0}";
		 */
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		msgHeader.setMsgType("TALK001");
		msgHeader.setLength(0);
		msgHeader.setSender("Server");
		msgHeader.setRecevier("Server");
		msgHeader.setSendTime(DateUtil.getDateString(new Date(), DateUtil.sdf));
		JSONObject out = new JSONObject();
		out.put("header", msgHeader);
		out.put("body", msgBody);
		return out.toJSONString();
	}
}
