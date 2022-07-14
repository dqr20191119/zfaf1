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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.yjct.dao.YjspMapper;
import com.cesgroup.prison.yjct.entity.YabzEntity;
import com.cesgroup.prison.yjct.entity.YjspEntity;
import com.cesgroup.prison.yjct.service.YabzService;
import com.cesgroup.prison.yjct.service.YjspService;

/**
 * 预案发布管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/yafb")
public class YafbController extends BaseEntityDaoServiceCRUDController<YjspEntity, String, YjspMapper, YjspService> {
	
	private final Logger logger = LoggerFactory.getLogger(YafbController.class);  
	
	@Resource
	private YjspService yjspService;
	@Resource
	private YabzService yabzService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/yafb/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<YjspEntity> searchAllData(YjspEntity yjspEntity, HttpServletRequest request,
			HttpServletResponse response) {
	 			
		return yjspService.findAllList(yjspEntity);
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(YjspEntity yjspEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			YabzEntity yabzEntity = new YabzEntity();
			yabzEntity.setId(yjspEntity.getEhaPhFid());
			yabzEntity.setEpiPlanStatus("4");
			yabzEntity.setEpiUpdtTime(new Date());
			yabzEntity.setEpiUpdtUserId(user.getUserId());
			yabzService.updatePlanStatus(yabzEntity);
			
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}	
}
