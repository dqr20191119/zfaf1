package com.cesgroup.prison.yjct.web;

import java.util.ArrayList;
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
import com.cesgroup.prison.yjct.dao.YabzMapper;
import com.cesgroup.prison.yjct.entity.YabzEntity;
import com.cesgroup.prison.yjct.service.YabzService;

/**
 * 预案管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/yabz")
public class YabzController extends BaseEntityDaoServiceCRUDController<YabzEntity, String, YabzMapper, YabzService>{
	
	private final Logger logger = LoggerFactory.getLogger(YabzController.class);  
	
	@Resource
	private YabzService yabzService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/yabz/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
		
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/yabz/view");
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("recordId", request.getParameter("recordId"));	
		mv.addObject("type", request.getParameter("type"));
		mv.addObject("openPos", request.getParameter("openPos"));
		mv.addObject("isDisplay", request.getParameter("isDisplay"));		
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public YabzEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
				 			
		return yabzService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		yabzEntity.setEpiCusNumber(user.getOrgCode());
		Page<YabzEntity> pageInfo = (Page<YabzEntity>) yabzService.findList(yabzEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
					
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			yabzEntity.setEpiCusNumber(user.getOrgCode());
			List<YabzEntity> list = yabzService.findAllList(yabzEntity);
			
			for(YabzEntity yabz : list) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("value", yabz.getId());
				resultMap.put("text", yabz.getEpiPlanName());
				resultList.add(resultMap);
			}
 		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
 		}
		
		return resultList;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = yabzEntity.getId();
			
			if(id != null && !"".equals(id)) {
				yabzEntity.setEpiUpdtUserId(user.getUserId());
				yabzEntity.setEpiUpdtTime(new Date());
			} else {
				yabzEntity.setEpiCusNumber(user.getOrgCode());
				yabzEntity.setEpiPlanStatus("0");
				yabzEntity.setEpiCrteUserId(user.getUserId());
				yabzEntity.setEpiCrteTime(new Date());
				yabzEntity.setEpiUpdtUserId(user.getUserId());
				yabzEntity.setEpiUpdtTime(new Date());
			}
			
			yabzEntity = yabzService.saveOrUpdate(yabzEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("data", yabzEntity);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/saveOrUpdateGzzInfo")
	@ResponseBody
	public Map<String, Object> saveOrUpdateGzzInfo(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			yabzService.saveOrUpdateGzzInfo(yabzEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/saveOrUpdateCzffInfo")
	@ResponseBody
	public Map<String, Object> saveOrUpdateCzffInfo(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			String msg = yabzService.saveOrUpdateCzffInfo(yabzEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("data", msg);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/saveOrUpdatePlanAction")
	@ResponseBody
	public Map<String, Object> saveOrUpdatePlanAction(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			String msg = yabzService.saveOrUpdatePlanAction(yabzEntity);			
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("data", msg);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/updatePlanStatus")
	@ResponseBody
	public Map<String, Object> updatePlanStatus(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			yabzEntity.setEpiUpdtUserId(user.getUserId());
			yabzEntity.setEpiUpdtTime(new Date());
			
			yabzService.updatePlanStatus(yabzEntity);				
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
			
			yabzService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/copyYaxx")
	@ResponseBody
	public Map<String, Object> copyYaxx(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			yabzEntity.setEpiCrteUserId(user.getUserId());
			yabzEntity.setEpiCrteTime(new Date());
			
			yabzService.copyYaxx(yabzEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	/**
	 * 事件处置的时候记录的事件记录表--应急处置的时候保存事件记录
	 * 
	 * */
	@RequestMapping(value = "/saveOrUpdatePlanAction1")
	@ResponseBody
	public Map<String, Object> saveOrUpdatePlanAction1(YabzEntity yabzEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			String msg = yabzService.saveOrUpdatePlanActionRecord(yabzEntity);			
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("data", msg);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	
}
