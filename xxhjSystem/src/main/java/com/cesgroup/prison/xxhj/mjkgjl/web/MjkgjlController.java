package com.cesgroup.prison.xxhj.mjkgjl.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.mjkgjl.dao.MjkgjlMapper;
import com.cesgroup.prison.xxhj.mjkgjl.service.MjkgjlService;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;
import com.cesgroup.prison.xxhj.mjkgjl.entity.MjkgjlEntity;

 
@Controller
@RequestMapping(value = "/xxhj/mjkgjl")
public class MjkgjlController extends BaseEntityDaoServiceCRUDController<MjkgjlEntity, 
	String, MjkgjlMapper, MjkgjlService> {
	
	private final Logger logger = LoggerFactory.getLogger(MjkgjlController.class);  
	
	@Resource
	private MjkgjlService MjkgjlService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/mjkgjl/index");
		return mv;
	}
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(MjkgjlEntity mjkgjlEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<MjkgjlEntity> pageInfo = (Page<MjkgjlEntity>) MjkgjlService.findList(mjkgjlEntity,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	 @RequestMapping(value = "/searchSwdbPage")
		@ResponseBody
		public Map<String, Object> searchSwdbPage(MjkgjlEntity MjkgjlEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
			PageRequest pageRequest = buildPageRequest();	
		 Page<MjkgjlEntity> pageInfo = (Page<MjkgjlEntity>) MjkgjlService.searchSwdbPage(MjkgjlEntity,pageRequest);
			return DataUtils.pageToMap(pageInfo);
		}
	
}
