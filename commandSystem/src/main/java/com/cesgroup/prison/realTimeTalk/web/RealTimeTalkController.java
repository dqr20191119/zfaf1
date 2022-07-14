package com.cesgroup.prison.realTimeTalk.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.jfsb.entity.Camera;
import com.cesgroup.prison.jfsb.service.ICameraService;
import com.cesgroup.prison.realTimeTalk.service.RealTimeTalkService;

@Controller
@RequestMapping("realTimeTalk")
public class RealTimeTalkController extends BaseController {

	@Resource
	private RealTimeTalkService service;

	@Autowired
	private ICameraService cameraService;
	/**
	 * 实时对讲页面跳转
	 * @return
	 */
	@RequestMapping("openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("realTimeTalk/talkbackControl");
		return mv;
	}

	/**
	 * 发送对讲消息
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/startTalk")
	public AjaxMessage startTalk(HttpServletRequest request) {
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		String cusNumber = request.getParameter("cusNumber");// 从前台传入
		List<String> talkIdntys = (List<String>) (JSON.parse(request.getParameter("talkIdntys")));
		return service.startTalk(ip, cusNumber, talkIdntys);

	}
	/**
	 * 根据摄像头id呼叫关联的对讲
	 * @param request
	 * @author zk
	 */
	@ResponseBody
	@RequestMapping("/talkByCameraId")
	public AjaxMessage talkByCameraId(HttpServletRequest request) {
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		String cusNumber = request.getParameter("cusNumber");// 从前台传入
		String cameraId = request.getParameter("cameraId");
		if(cameraId==null || "".equals(cameraId)) {
			return new AjaxMessage(false, null, "请开启视频客户端，并选择一个摄像");
		}
		Camera camera=cameraService.findById(cameraId);
		String talkIdnty=camera.getCbdTalkbackId();
		if(talkIdnty==null || "".equals(talkIdnty)) {
			return new AjaxMessage(false, null, "该摄像头未关联对讲！");
		}
		List<String> talkIdntys = new ArrayList<String>();
		talkIdntys.add(talkIdnty);
		return service.startTalk(ip, cusNumber, talkIdntys);
	}
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/startTalk") public AjaxMessage
	 * testsendAlarm(HttpServletRequest request) { AjaxMessage ajaxMsg = new
	 * AjaxMessage(); boolean flag = false; Object result = null; try {
	 * 
	 * String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP String cusNumber =
	 * request.getParameter("cusNumber");// 从前台传入 Map<String, Object> map =
	 * service.findMainIdntyByIp(cusNumber, ip);// 数据库查询
	 * 
	 * // 根据当前用户的IP，查找当前IP所绑定的对讲主机 String talkID = (String) map.get("TSE_IDNTY");
	 * 
	 * String brand = (String) map.get("TSE_BRAND");// 对讲品牌
	 * 
	 * String toID = request.getParameter("toID");// 被呼叫的对象,多个对讲编号以逗号隔开
	 * 
	 * //String currentTime =
	 * com.cesgroup.prison.code.tool.DateUtils.formatDateTimess(new Date());// 当前时间
	 * 
	 * String currentTime = DateUtil.getDateString(new Date(), DateUtil.sdf);
	 * //String msgID = CommonUtil.genTimeSequence();// 生成消息ID
	 * //格式：20171009151522888
	 * 
	 * String msgID = MsgIdUtil.getMsgSeq(""); String head =
	 * "\"header\":{\"cusNumber\":\"" + cusNumber + "\",\"msgID\":\"" + msgID +
	 * "\",\"msgType\":\"TALK001\",\"sender\":\"Server\",\"sendTime\":\"" +
	 * currentTime + "\",\"recevier\":\"Server\",\"length\":0}"; String body =
	 * "\"body\":{\"talkID\":\"" + talkID + "\",\"toID\":\"" + toID +
	 * "\",\"brand\":\"" + brand + "\",\"action\":1}";
	 * 
	 * String msg = "{" + head + "," + body + "}";
	 * mqMessageSender.sendTalkMessage(msg, cusNumber, "5"); flag = true; result =
	 * "呼叫成功"; } catch (Exception e) { flag = false; result = "呼叫失败：" +
	 * e.getMessage(); }
	 * 
	 * if (flag) { ajaxMsg.setObj(result); } else { ajaxMsg.setMsg((String) result);
	 * } ajaxMsg.setSuccess(flag);
	 * 
	 * return ajaxMsg; }
	 */
}
