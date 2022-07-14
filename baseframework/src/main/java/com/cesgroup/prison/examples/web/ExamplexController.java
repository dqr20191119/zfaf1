package com.cesgroup.prison.examples.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;

@Controller
@RequestMapping("/examples")
public class ExamplexController extends BaseController{
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("examples/index");
		return mv;
	}
	
	/**
	 * 进入文件上传页面
	 * @return
	 */
	@RequestMapping("/{id}/list")
	public ModelAndView list(@PathVariable("id") String id) {
		ModelAndView mv = null;
		if("wjsc".equals(id)){
			 mv=new ModelAndView("examples/fileuploader");
		}
		if("wjxz".equals(id)){
			mv=new ModelAndView("examples/list1");
		}
		return mv;
	}
	
}
