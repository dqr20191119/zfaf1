package com.cesgroup.prison.yjct.web;

import java.util.Date;
import java.util.HashMap;
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
import com.cesgroup.prison.yjct.dao.YljhMapper;
import com.cesgroup.prison.yjct.entity.YljhEntity;
import com.cesgroup.prison.yjct.service.YljhService;

/**
 * 演练计划管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/yljh")
public class YljhController extends BaseEntityDaoServiceCRUDController<YljhEntity, String, YljhMapper, YljhService> {
	
	private final Logger logger = LoggerFactory.getLogger(YljhController.class);  
	
	@Resource
	private YljhService yljhService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/yljh/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public YljhEntity getById(YljhEntity yljhEntity, HttpServletRequest request,
			HttpServletResponse response) {
				
		return yljhService.getById(yljhEntity);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(YljhEntity yljhEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		yljhEntity.setEdpCusNumber(user.getOrgCode());
		Page<YljhEntity> pageInfo = (Page<YljhEntity>) yljhService.findList(yljhEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(YljhEntity yljhEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = yljhEntity.getId();
			
			if(id != null && !"".equals(id)) {
				yljhEntity.setEdpUpdtUserId(user.getUserId());
				yljhEntity.setEdpUpdtTime(new Date());
			} else {
				yljhEntity.setEdpCusNumber(user.getOrgCode());
				yljhEntity.setEdpStatus("1");
				yljhEntity.setEdpCrteUserId(user.getUserId());
				yljhEntity.setEdpCrteTime(new Date());
				yljhEntity.setEdpUpdtUserId(user.getUserId());
				yljhEntity.setEdpUpdtTime(new Date());
			}
			
			yljhService.saveOrUpdate(yljhEntity);
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
			
			yljhService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}