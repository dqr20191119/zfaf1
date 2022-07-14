package com.cesgroup.prison.xxhj.rzcx.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xxhj.rzcx.dao.RzcxMapper;
import com.cesgroup.prison.xxhj.rzcx.entity.RzcxEntity;
import com.cesgroup.prison.xxhj.rzcx.service.RzcxService;
import com.cesgroup.prison.utils.DataUtils;

 
@Controller
@RequestMapping(value = "/xxhj/rzcx")
public class RzcxController extends BaseEntityDaoServiceCRUDController<RzcxEntity, 
	String, RzcxMapper, RzcxService> {
	
	private final Logger logger = LoggerFactory.getLogger(RzcxController.class);  
	
	@Resource
	private RzcxService RzcxService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/rzcx/index");
		return mv;
	}
	
	@RequestMapping(value = "/toIndextb")
	public ModelAndView toIndextb(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/rzcx/indextb");
		return mv;
	}
	
	@RequestMapping(value = "/toIndextj")
	public ModelAndView toIndextj(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/rzcx/indextj");
		return mv;
	}
	
	
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(RzcxEntity RzcxEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<RzcxEntity> pageInfo = (Page<RzcxEntity>) RzcxService.findList(RzcxEntity,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	 @RequestMapping(value = "/searchSwdbPage")
		@ResponseBody
		public Map<String, Object> searchSwdbPage(RzcxEntity RzcxEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
			PageRequest pageRequest = buildPageRequest();	
			String startTime=request.getParameter("STime");
			String endTime=request.getParameter("ETime");
			String SearchDate=request.getParameter("SearchDate");
		 Page<RzcxEntity> pageInfo = (Page<RzcxEntity>) RzcxService.searchSwdbPage(RzcxEntity,pageRequest,startTime , endTime,SearchDate);
			return DataUtils.pageToMap(pageInfo);
		}
	 
	 

	@RequestMapping(value = "/sjChart")
	@ResponseBody
	public AjaxResult sjChart(RzcxEntity RzcxEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			PageRequest pageRequest = buildPageRequest();	
			String startTime=request.getParameter("STime");
			String endTime=request.getParameter("ETime");
			String SearchDate=request.getParameter("SearchDate");
			List<Map<String, Object>> list = this.getService().sjChart(RzcxEntity, pageRequest, startTime, endTime, SearchDate);
			return AjaxResult.success(list);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxResult.error("日志图表发生异常");
		}
	}
	

	@RequestMapping(value = "/czChart")
	@ResponseBody
	public AjaxResult czChart(RzcxEntity RzcxEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			PageRequest pageRequest = buildPageRequest();	
			String startTime=request.getParameter("STime");
			String endTime=request.getParameter("ETime");
			String SearchDate=request.getParameter("SearchDate");
			List<Map<String, Object>> list = this.getService().czChart(RzcxEntity, pageRequest, startTime, endTime, SearchDate);
			return AjaxResult.success(list);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxResult.error("日志图表发生异常");
		}
	}
	
	@RequestMapping(value = "/searchSwdbPage1")
	@ResponseBody
	public Map<String, Object> searchSwdbPage1(RzcxEntity RzcxEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();	
		String startTime=request.getParameter("STime");
		String endTime=request.getParameter("ETime");
		String SearchDate=request.getParameter("SearchDate");
		Page<RzcxEntity> pageInfo = (Page<RzcxEntity>) RzcxService.searchSwdbPage1(RzcxEntity,pageRequest,startTime , endTime,SearchDate);
		return DataUtils.pageToMap(pageInfo);
	}
	 
	 
	
}
