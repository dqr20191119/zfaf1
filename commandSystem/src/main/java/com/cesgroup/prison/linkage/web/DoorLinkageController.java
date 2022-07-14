package com.cesgroup.prison.linkage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.linkage.dto.DoorMessageDto;
import com.cesgroup.prison.linkage.service.DoorCtrlService;

@Controller
@RequestMapping(value = "/doorlinkage")
public class DoorLinkageController extends BaseController {

	@Autowired
	DoorCtrlService doorCtrlService;

	/**
	* @methodName: controlDoor
	* @Description:  门禁控制
	* @return AjaxMessage
	* @throws  
	*/
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/controlDoor")
	public AjaxMessage controlDoor(HttpServletRequest request, HttpServletResponse response) {
		List<String> doorIds = (List<String>) (JSON.parse(request.getParameter("doorIds")));
		String action = request.getParameter("action");
		String time = request.getParameter("time");// 常闭操作有效时间长度 1-254（分钟）
		return doorCtrlService.openDoor(doorIds, action, time);
	}

	/**
	 * 门禁控制Demo页面跳转
	 * @return
	 */
	@RequestMapping(value = "/controlDoorDialog")
	public ModelAndView controlDoorDialog() {
		ModelAndView mv = new ModelAndView("doorCtrl/door_control_dialog");
		return mv;
	}

	/**
	* @methodName: doorCtrlTree
	* @Description: 门禁控制树
	* @return ModelAndView
	* @throws  
	*/
	@RequestMapping(value = "/index")
	public ModelAndView doorCtrlTree() {
		ModelAndView mv = new ModelAndView("doorCtrl/doorCtrlTree");
		return mv;
	}

	/**
	 * 门禁控制Demo页面跳转
	 * @return
	 */
	@RequestMapping(value = "/openDoorDemoDialog")
	public ModelAndView openDoorDemoDialog() {
		ModelAndView mv = new ModelAndView("doorCtrl/door_control_demo");
		return mv;
	}

	/**
	 * 门禁控制Demo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/openDoorDemo")
	@ResponseBody
	public AjaxResult openDoorDemo(DoorMessageDto doorMessageDto, HttpServletRequest request) {
		
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		return this.doorCtrlService.openDoorDemo(ip, doorMessageDto);
	}
}
