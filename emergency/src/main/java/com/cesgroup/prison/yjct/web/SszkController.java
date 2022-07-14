package com.cesgroup.prison.yjct.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.yjct.dao.SszkMapper;
import com.cesgroup.prison.yjct.entity.SszkEntity;
import com.cesgroup.prison.yjct.service.SszkService;

/**
 * 实时指控
 * 
 */
@Controller
@RequestMapping(value = "/yjct/sszk")
public class SszkController extends BaseEntityDaoServiceCRUDController<SszkEntity, String, SszkMapper, SszkService> {
	
	private final Logger logger = LoggerFactory.getLogger(SszkController.class);  
	
	@Resource
	private SszkService sszkService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", user.getOrgCode());
		Map<String, Object> configMap = sszkService.findConfig(paramMap);
		
		ModelAndView mv = new ModelAndView("yjct/sszk/index");
		mv.addObject("configMap", configMap);
		return mv;
	}
}
