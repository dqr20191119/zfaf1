package com.cesgroup.prison.linkage.web;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.netamq.service.MqMessageSender;

@Controller
@RequestMapping(value = "/testlinkage")
public class TestLinkageController extends BaseController {
	//private static final Logger logger = LoggerFactory.getLogger(TestLinkageController.class);
	@Resource
	private MqMessageSender mqMessageSender;
	
	
	
	/**
	 * 发送测试websocket消息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	public AjaxMessage controlDoor(HttpServletRequest request){
		AjaxMessage ajaxMsg = new AjaxMessage();
		
		String cusNumber ="3261";
		String msg ="{\"name\":\"test\",\"msgType\":\"1003\",\"content\":{\"INSIDE_POLICE_COUNT\":\"67\"}}";//当前监内民警
		mqMessageSender.sendNettyMessage(msg, cusNumber);
		
		return ajaxMsg;
	}
	
	/**
	 * 模拟发送测试报警
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testalarm")
	public AjaxMessage testsendAlarm(HttpServletRequest request){
		AjaxMessage ajaxMsg = new AjaxMessage();
		
		String cusNumber ="3261";
		
		String head ="\"header\":{\"cusNumber\":\"6501\",\"msgID\":\"201712210000000002\",\"msgType\":\"Alarm001\",\"sender\":\"带监狱编号的前置机程序名\",\"sendTime\":\"2017-12-21 13:16:07\",\"recevier\":\"Server\",\"length\":0}";
		String body ="\"body\":{\"alarmID\":\"1000001\",\"alarmTime\":\"2018-01-01 17:51:42.940000\",\"alarmDeviceType\":\"5\",\"alarmType\":\"1\",\"alarmAction\":1}";
		
		String msg ="{"+head+","+body+"}";
		mqMessageSender.sendAlarmTestMessage(msg, cusNumber,"5");
		
		return ajaxMsg;
	}
	
	
}
