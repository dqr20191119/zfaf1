package com.cesgroup.prison.common.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.CommonEvidenceUseMapper;
import com.cesgroup.prison.common.entity.CommonEvidenceUseEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.CommonEvidenceUseService;

/**
 * 证据使用管理
 * 
 */
@Controller
@RequestMapping(value = "/common/evidenceUse")
public class CommonEvidenceUseController extends BaseEntityDaoServiceCRUDController<CommonEvidenceUseEntity, String, CommonEvidenceUseMapper, CommonEvidenceUseService> {
	
	private final Logger logger = LoggerFactory.getLogger(CommonEvidenceUseController.class);  
	
	@Resource
	private CommonEvidenceUseService commonEvidenceUseService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("common/evidenceUse/index");
		mv.addObject("ceuYwId", request.getParameter("ceuYwId"));
		mv.addObject("ceuYwType", request.getParameter("ceuYwType"));
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public CommonEvidenceUseEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
		 			
		return commonEvidenceUseService.getById(id);
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<CommonEvidenceUseEntity> searchAllData(CommonEvidenceUseEntity commonEvidenceUseEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		commonEvidenceUseEntity.setCeuCusNumber(user.getOrgCode());
		List<CommonEvidenceUseEntity> list = commonEvidenceUseService.findAllList(commonEvidenceUseEntity);
			 
		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(CommonEvidenceUseEntity commonEvidenceUseEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			commonEvidenceUseEntity.setCeuCusNumber(user.getOrgCode());
			commonEvidenceUseEntity.setCeuCrteUserId(user.getUserId());
			commonEvidenceUseEntity.setCeuCrteTime(new Date());
			
			commonEvidenceUseService.saveOrUpdate(commonEvidenceUseEntity);
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
			
			commonEvidenceUseService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}
