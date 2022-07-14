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
import com.cesgroup.prison.yjct.dao.FgglMapper;
import com.cesgroup.prison.yjct.entity.FgglEntity;
import com.cesgroup.prison.yjct.service.FgglService;

/**
 * 法规管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/fggl")
public class FgglController extends BaseEntityDaoServiceCRUDController<FgglEntity, String, FgglMapper, FgglService> {
	
	private final Logger logger = LoggerFactory.getLogger(FgglController.class);  
	
	@Resource
	private FgglService fgglService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/fggl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public FgglEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
		 			
		return fgglService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(FgglEntity fgglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		fgglEntity.setLriCusNumber(user.getOrgCode());
		Page<FgglEntity> pageInfo = (Page<FgglEntity>) fgglService.findList(fgglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<FgglEntity> searchAllData(FgglEntity fgglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		fgglEntity.setLriCusNumber(user.getOrgCode());
		List<FgglEntity> list = fgglService.findAllList(fgglEntity);
			 
		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(FgglEntity fgglEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = fgglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				fgglEntity.setLriUpdtUserId(user.getUserId());
				fgglEntity.setLriUpdtTime(new Date());
			} else {
				fgglEntity.setLriCusNumber(user.getOrgCode());
				fgglEntity.setLpiSttsIndc(CommonConstant.STATUS_VALID_CODE);
				fgglEntity.setLriCrteUserId(user.getUserId());
				fgglEntity.setLriCrteTime(new Date());
				fgglEntity.setLriUpdtUserId(user.getUserId());
				fgglEntity.setLriUpdtTime(new Date());
			}
			
			fgglService.saveOrUpdate(fgglEntity);
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
			
			fgglService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}
