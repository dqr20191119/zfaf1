package com.cesgroup.prison.xxhj.znys.web;
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
import com.cesgroup.prison.xxhj.znys.dao.ZnysMapper;
import com.cesgroup.prison.xxhj.znys.entity.ZnysEntity;
import com.cesgroup.prison.xxhj.znys.service.ZnysService;


 
@Controller
@RequestMapping(value = "/xxhj/znys")
public class ZnysController extends BaseEntityDaoServiceCRUDController<ZnysEntity, 
	String, ZnysMapper, ZnysService> {
	
	private final Logger logger = LoggerFactory.getLogger(ZnysController.class);  
	
	@Resource
	private ZnysService ZnysService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/znys/index");
		return mv;
	}
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(ZnysEntity ZnysEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<ZnysEntity> pageInfo = (Page<ZnysEntity>) ZnysService.findList(ZnysEntity,pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	 @RequestMapping(value = "/searchSwdbPage")
		@ResponseBody
		public Map<String, Object> searchSwdbPage(ZnysEntity ZnysEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
			PageRequest pageRequest = buildPageRequest();	
		 Page<ZnysEntity> pageInfo = (Page<ZnysEntity>) ZnysService.searchSwdbPage(ZnysEntity,pageRequest);
			return DataUtils.pageToMap(pageInfo);
		}
	
}
