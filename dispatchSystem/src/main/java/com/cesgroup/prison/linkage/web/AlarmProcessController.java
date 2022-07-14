package com.cesgroup.prison.linkage.web;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;

import com.cesgroup.prison.alarm.record.service.AlarmService;

//@Controller
//@RequestMapping(value = "/alarmprocess")
public class AlarmProcessController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AlarmProcessController.class);
	//@Resource
	//AlarmService alarmService;
	
	@RequestMapping("/index")
	public ModelAndView index(String type){
		ModelAndView mv = new ModelAndView("alarm/process/index");
		mv.addObject("type",type);
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
}
