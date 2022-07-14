package com.cesgroup.prison.yjct.web;

import java.util.Date;
import java.util.HashMap;
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
import com.cesgroup.prison.yjct.entity.YjspEntity;
import com.cesgroup.prison.yjct.entity.YljhEntity;
import com.cesgroup.prison.yjct.service.YjspService;
import com.cesgroup.prison.yjct.service.YljhService;

/**
 * 演练发布管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/ylfb")
public class YlfbController extends BaseEntityDaoServiceCRUDController<YjspEntity, String, YjspMapper, YjspService> {
	
	private final Logger logger = LoggerFactory.getLogger(YlfbController.class);  
	
	@Resource
	private YjspService yjspService;
	@Resource
	private YljhService yljhService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/ylfb/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(YjspEntity yjspEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			YljhEntity yljhEntity = new YljhEntity();			
			yljhEntity.setId(yjspEntity.getEhaPhFid());
			yljhEntity.setEdpStatus("4");
			yljhEntity.setEdpUpdtTime(new Date());
			yljhEntity.setEdpUpdtUserId(user.getUserId());
			yljhService.updateStatusById(yljhEntity);
						
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}	
}