package com.cesgroup.prison.crontab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;

@Controller
@RequestMapping(value = "/crontab")
public class CrontabController extends BaseController {

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("crontab/index");
		return mv;
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("crontab/add");
		return mv;
	}
	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(String id) {
		ModelAndView mv = new ModelAndView("crontab/edit");
		mv.addObject("id", id);
		return mv;
	}
	
}
