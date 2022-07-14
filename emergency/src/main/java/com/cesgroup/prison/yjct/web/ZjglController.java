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
import com.cesgroup.prison.yjct.dao.ZjglMapper;
import com.cesgroup.prison.yjct.entity.ZjglEntity;
import com.cesgroup.prison.yjct.service.ZjglService;

/**
 * 专家管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/zjgl")
public class ZjglController extends BaseEntityDaoServiceCRUDController<ZjglEntity, String, ZjglMapper, ZjglService> {
	
	private final Logger logger = LoggerFactory.getLogger(ZjglController.class);  
	
	@Resource
	private ZjglService zjglService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/zjgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public ZjglEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
			 			
		return zjglService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(ZjglEntity zjglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		zjglEntity.setEpiCusNumber(user.getOrgCode());
		Page<ZjglEntity> pageInfo = (Page<ZjglEntity>) zjglService.findList(zjglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<ZjglEntity> searchAllData(ZjglEntity zjglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		zjglEntity.setEpiCusNumber(user.getOrgCode());
		List<ZjglEntity> list = zjglService.findAllList(zjglEntity);		
		
		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(ZjglEntity zjglEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = zjglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				zjglEntity.setEpiUpdtUserId(user.getUserId());
				zjglEntity.setEpiUpdtTime(new Date());
			} else {
				zjglEntity.setEpiCusNumber(user.getOrgCode());
				zjglEntity.setEpiSttsIndc(CommonConstant.STATUS_VALID_CODE);
				zjglEntity.setEpiCrteUserId(user.getUserId());
				zjglEntity.setEpiCrteTime(new Date());
				zjglEntity.setEpiUpdtUserId(user.getUserId());
				zjglEntity.setEpiUpdtTime(new Date());
			}
			
			zjglService.saveOrUpdate(zjglEntity);
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
			
			zjglService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}
