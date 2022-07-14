package com.cesgroup.prison.xxhj.mjxcjl.web;
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

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.mjxcjl.dao.MjxcjlMapper;
import com.cesgroup.prison.xxhj.mjxcjl.entity.MjxcjlEntity;
import com.cesgroup.prison.xxhj.mjxcjl.service.MjxcjlService;


 
@Controller
@RequestMapping(value = "/xxhj/patrol/mjxcjl")
public class MjxcjlController extends BaseEntityDaoServiceCRUDController<MjxcjlEntity, 
	String, MjxcjlMapper, MjxcjlService> {
	
	private final Logger logger = LoggerFactory.getLogger(MjxcjlController.class);  
	
	@Resource
	private MjxcjlService MjxcjlService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/mjxcjl/index");
		return mv;
	}
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(MjxcjlEntity MjxcjlEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<MjxcjlEntity> pageInfo = (Page<MjxcjlEntity>) MjxcjlService.findList(MjxcjlEntity,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	 @RequestMapping(value = "/searchSwdbPage")
		@ResponseBody
		public Map<String, Object> searchSwdbPage(MjxcjlEntity MjxcjlEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
			PageRequest pageRequest = buildPageRequest();	
		 Page<MjxcjlEntity> pageInfo = (Page<MjxcjlEntity>) MjxcjlService.searchSwdbPage(MjxcjlEntity,pageRequest);
			return DataUtils.pageToMap(pageInfo);
		}
	
}
