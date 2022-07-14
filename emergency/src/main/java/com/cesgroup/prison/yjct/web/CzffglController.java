package com.cesgroup.prison.yjct.web;

import java.util.Date;
import java.util.HashMap;
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

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.yjct.dao.CzffglMapper;
import com.cesgroup.prison.yjct.entity.CzffglEntity;
import com.cesgroup.prison.yjct.service.CzffglService;

/**
 * 处置方法管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/czffgl")
public class CzffglController extends BaseEntityDaoServiceCRUDController<CzffglEntity, String, CzffglMapper, CzffglService> {
	
	private final Logger logger = LoggerFactory.getLogger(CzffglController.class);  
	
	@Resource
	private CzffglService czffglService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/czffgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public CzffglEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
	 			
		return czffglService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(CzffglEntity czffglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		czffglEntity.setDmiCusNumber(user.getOrgCode());
		Page<CzffglEntity> pageInfo = (Page<CzffglEntity>) czffglService.findList(czffglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<CzffglEntity> searchAllData(CzffglEntity czffglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		czffglEntity.setDmiCusNumber(user.getOrgCode());
		List<CzffglEntity> list = czffglService.findAllList(czffglEntity);
					 
		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(CzffglEntity czffglEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = czffglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				czffglEntity.setDmiUpdtUserId(user.getUserId());
				czffglEntity.setDmiUpdtTime(new Date());
			} else {
				czffglEntity.setDmiCusNumber(user.getOrgCode());
				czffglEntity.setDmiStatus(CommonConstant.STATUS_VALID_CODE);
				czffglEntity.setDmiCrteUserId(user.getUserId());
				czffglEntity.setDmiCrteTime(new Date());
				czffglEntity.setDmiUpdtUserId(user.getUserId());
				czffglEntity.setDmiUpdtTime(new Date());
			}
			
			czffglService.saveOrUpdate(czffglEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
		
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	public Map<String, Object> deleteByIds(String ids, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			czffglService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}