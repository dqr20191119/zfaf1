package com.cesgroup.prison.alarm.dateD.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.alarm.dateD.service.AlarmDataService;

@Controller
@RequestMapping(value = "/alarm/date")
public class AlarmDateController extends BaseController {

	@Resource
	private AlarmDataService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("dateD/index");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "loadAlertorData")
	public AjaxMessage loadAlertorDate(String cusNumber, String dvcType, String areaId) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			if ("9".equals(dvcType)) {
				service.loadAlertorData(cusNumber, dvcType, areaId);
				flag = true;
				obj = "添加报警器成功";
			} else {
				flag = false;
				obj = "设备不支持，后台没写，谁用谁写";
			}
		} catch (Exception e) {
			flag = false;
			obj = "添加报警器发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "loadPlanData")
	public AjaxMessage loadPlanData(HttpServletRequest request, HttpServletResponse response) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.loadPlanData(request, response);
			flag = true;
			obj = "添加预案成功";

		} catch (Exception e) {
			flag = false;
			obj = "添加预案发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

}
