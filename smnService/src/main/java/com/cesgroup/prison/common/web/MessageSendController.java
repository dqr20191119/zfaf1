package com.cesgroup.prison.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.common.facade.MessageSendFacade;

/**
 * 统一消息发送入口
 * @author zxh
 * 
 */
@Controller
@RequestMapping("/common/messageSend")
public class MessageSendController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSendController.class);

	/**
	 * msgType: 	消息类型: 3001-当前报警,3006-报警上级处理,6001-监督单提醒,6002-故障维修
	 * sendType: 	发送类型：1-按监狱订阅、2-按用户、3-按组织部门
	 * sendTo: 		发送目标对象: 对应上面的具体值-多个值逗号分隔
 	 * content: 	回调给前台处理数据---json字符串
	 * url: 		业务模块处理url---带上下文路径列表页面
	 * isSendToGzt: 是否发送到工作台消息	0-否，1-是(默认传1)
	 * ywId:		业务id
	 * cusNumber:	当前监狱id
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("send")
	@ResponseBody
	public Map<String, Object> send(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			Map<String, String> msgMap = new HashMap<String, String>();
			msgMap.put("msgType", request.getParameter("msgType"));
			msgMap.put("sendType", request.getParameter("sendType"));
			msgMap.put("sendTo", request.getParameter("sendTo"));
			msgMap.put("content", request.getParameter("content"));
 			msgMap.put("url", request.getParameter("url"));
 			msgMap.put("isSendToGzt", request.getParameter("isSendToGzt"));
 			msgMap.put("ywId", request.getParameter("ywId"));
 			msgMap.put("cusNumber", request.getParameter("cusNumber"));
			msgMap.put("noticeContent", request.getParameter("noticeContent"));
			
			MessageSendFacade.send(msgMap);
			resultMap.put("code", "1");
		} catch(Exception e) {
			
			logger.error("发送消息报错: " + e.toString(), e.fillInStackTrace());
			resultMap.put("code", "0");
		}
		
		return resultMap;
	}
}
