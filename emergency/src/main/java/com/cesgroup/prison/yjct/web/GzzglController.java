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
import com.cesgroup.prison.yjct.dao.GzzglMapper;
import com.cesgroup.prison.yjct.entity.GzzcyEntity;
import com.cesgroup.prison.yjct.entity.GzzglEntity;
import com.cesgroup.prison.yjct.service.GzzcyService;
import com.cesgroup.prison.yjct.service.GzzglService;

/**
 * 工作组管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/gzzgl")
public class GzzglController extends BaseEntityDaoServiceCRUDController<GzzglEntity, String, GzzglMapper, GzzglService> {
	
	private final Logger logger = LoggerFactory.getLogger(GzzglController.class);  
	
	@Resource
	private GzzglService gzzglService;	
	@Resource
	private GzzcyService gzzcyService;
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/gzzgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public GzzglEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
		 			
		GzzglEntity gzzglEntity = gzzglService.getById(id);
		
		GzzcyEntity gzzcyEntity = new GzzcyEntity();			
		gzzcyEntity.setWgmWorkgroupFid(id);
		List<GzzcyEntity> gzzcyEntityList = gzzcyService.findAllList(gzzcyEntity);
		gzzglEntity.setGzzcyEntityList(gzzcyEntityList);
		
		return gzzglEntity;		 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(GzzglEntity gzzglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		gzzglEntity.setWgiCusNumber(user.getOrgCode());
		Page<GzzglEntity> pageInfo = (Page<GzzglEntity>) gzzglService.findList(gzzglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<GzzglEntity> searchAllData(GzzglEntity gzzglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		 
		gzzglEntity.setWgiCusNumber(user.getOrgCode());
		List<GzzglEntity> list = gzzglService.findAllList(gzzglEntity);

		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(GzzglEntity gzzglEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = gzzglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				gzzglEntity.setWgiUpdtUserId(user.getUserId());
				gzzglEntity.setWgiUpdtTime(new Date());
			} else {
				gzzglEntity.setWgiCusNumber(user.getOrgCode());
				gzzglEntity.setWgiStatus(CommonConstant.STATUS_VALID_CODE);
				gzzglEntity.setWgiCrteUserId(user.getUserId());
				gzzglEntity.setWgiCrteTime(new Date());
				gzzglEntity.setWgiUpdtUserId(user.getUserId());
				gzzglEntity.setWgiUpdtTime(new Date());
			}
			
			gzzglService.saveOrUpdate(gzzglEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteGzzcyById")
	@ResponseBody
	public Map<String, Object> deleteGzzcyById(String cyId, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			gzzcyService.delete(cyId);
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
			
			gzzglService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}
