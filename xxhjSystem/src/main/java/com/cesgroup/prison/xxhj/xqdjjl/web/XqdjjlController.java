package com.cesgroup.prison.xxhj.xqdjjl.web;
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
import com.cesgroup.prison.xxhj.xqdjjl.dao.XqdjjlMapper;
import com.cesgroup.prison.xxhj.xqdjjl.entity.XqdjjlEntity;
import com.cesgroup.prison.xxhj.xqdjjl.service.XqdjjlService; 
@Controller
@RequestMapping(value = "/xxhj/patrol/xqdjjl")
public class XqdjjlController extends BaseEntityDaoServiceCRUDController<XqdjjlEntity, 
	String, XqdjjlMapper, XqdjjlService> {
	
	private final Logger logger = LoggerFactory.getLogger(XqdjjlController.class);  
	
	@Resource
	private XqdjjlService XqdjjlService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/xqdjjl/index");
		return mv;
	}
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(XqdjjlEntity XqdjjlEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<XqdjjlEntity> pageInfo = (Page<XqdjjlEntity>) XqdjjlService.findList(XqdjjlEntity,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	 @RequestMapping(value = "/searchSwdbPage")
		@ResponseBody
		public Map<String, Object> searchSwdbPage(XqdjjlEntity XqdjjlEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
			PageRequest pageRequest = buildPageRequest();	
		 Page<XqdjjlEntity> pageInfo = (Page<XqdjjlEntity>) XqdjjlService.searchSwdbPage(XqdjjlEntity,pageRequest);
			return DataUtils.pageToMap(pageInfo);
		}
	
}
