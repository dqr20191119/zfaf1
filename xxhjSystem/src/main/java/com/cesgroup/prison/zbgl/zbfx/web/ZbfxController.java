package com.cesgroup.prison.zbgl.zbfx.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.zbgl.zbcx.service.ZbcxService;

/**
 * 值班查询
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/zbfx")
public class ZbfxController extends BaseController {
	
	@Resource
	private ZbcxService zbcxService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/zbfx/index");
		return mv;
	}
	
	@RequestMapping("/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(HttpServletRequest request) throws Exception {
		
		
		List<Map<String, Object>> list = zbcxService.findAllList(request);
		
		return list;
	}
}


