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
import com.cesgroup.prison.yjct.dao.WzglMapper;
import com.cesgroup.prison.yjct.entity.WzglEntity;
import com.cesgroup.prison.yjct.service.WzglService;

/**
 * 物资管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/wzgl")
public class WzglController extends BaseEntityDaoServiceCRUDController<WzglEntity, String, WzglMapper, WzglService> {
	
	private final Logger logger = LoggerFactory.getLogger(WzglController.class);  
	
	@Resource
	private WzglService wzglService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/wzgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public WzglEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
	 		
		return wzglService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(WzglEntity wzglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		wzglEntity.setMriCusNumber(user.getOrgCode());
		Page<WzglEntity> pageInfo = (Page<WzglEntity>) wzglService.findList(wzglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<WzglEntity> searchAllData(WzglEntity wzglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		wzglEntity.setMriCusNumber(user.getOrgCode());
		List<WzglEntity> list = wzglService.findAllList(wzglEntity);
		
		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(WzglEntity wzglEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = wzglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				wzglEntity.setMriUpdtUserId(user.getUserId());
				wzglEntity.setMriUpdtTime(new Date());
			} else {
				wzglEntity.setMriCusNumber(user.getOrgCode());
				wzglEntity.setMriStatus(CommonConstant.STATUS_VALID_CODE);
				wzglEntity.setMriCrteUserId(user.getUserId());
				wzglEntity.setMriCrteTime(new Date());
				wzglEntity.setMriUpdtUserId(user.getUserId());
				wzglEntity.setMriUpdtTime(new Date());
			}
			
			wzglService.saveOrUpdate(wzglEntity);
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
			
			wzglService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}
