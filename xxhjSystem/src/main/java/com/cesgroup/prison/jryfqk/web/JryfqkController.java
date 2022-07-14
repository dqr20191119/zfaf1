package com.cesgroup.prison.jryfqk.web;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jryfqk.dao.JryfqkDao;
import com.cesgroup.prison.jryfqk.entity.JryfqkEntity;
import com.cesgroup.prison.jryfqk.service.JryfqkService;
import com.cesgroup.prison.utils.DataUtils;
 
@Controller
@RequestMapping("/jyshouye/jryfqk")
public class JryfqkController  extends BaseEntityDaoServiceCRUDController<JryfqkEntity,String,JryfqkDao,JryfqkService>{
	
	@Autowired
	private JryfqkService jryfqkService;
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public List query() {
		List list = jryfqkService.getJryafk();
		return list;				
	}
	
	
	@RequestMapping(value = "/searchList")
	@ResponseBody
	public Map<String, Object> searchList( HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,Object>> pageInfo = (Page<Map<String,Object>>) jryfqkService.findList(request,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
 
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jryfqk/index");
		mv.addObject("type", request.getParameter("type"));
		mv.addObject("time", request.getParameter("time"));
		return mv;
	}
	//searchListZaiCe
	@RequestMapping(value = "/searchListZaiCe")
	@ResponseBody
	public Map<String, Object> searchListZaiCe( HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,Object>> pageInfo = (Page<Map<String,Object>>) jryfqkService.findListZaiCe(request,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/toIndexPoliceZaiCe")
	public ModelAndView toIndexPoliceZaiCe() {
		ModelAndView mv = new ModelAndView("jryfqk/indexZaiCe");
		return mv;
	}
	@RequestMapping(value = "/searchListZaiYa")
	@ResponseBody
	public Map<String, Object> searchListZaiYa  ( HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,Object>> pageInfo = (Page<Map<String,Object>>) jryfqkService.findListZaiYa(request,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/toIndexPoliceInPrison")
	public ModelAndView toIndexPoliceInPrison() {
		ModelAndView mv = new ModelAndView("jryfqk/indexZaiYa");
		return mv;
	}
}
