package com.cesgroup.prison.superMap.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;

/**
 * 超图示例
 * 
 * @author lincoln.cheng
 *
 */
@Controller
@RequestMapping(value = "/superMapDemo")
public class SuperMapDemoController extends BaseController {
	/**
	 * @return
	 */
	@RequestMapping(value = "/demo")
	public ModelAndView demo() {
		ModelAndView mav = new ModelAndView("superMap/super_map_demo");
		
		return mav;
	}
}
