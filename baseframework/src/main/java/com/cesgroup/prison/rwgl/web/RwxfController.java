package com.cesgroup.prison.rwgl.web;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.prison.code.tool.JsonUtil;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.rwgl.dao.RwxfMapper;
import com.cesgroup.prison.rwgl.entity.RwjsEntity;
import com.cesgroup.prison.rwgl.entity.RwxfEntity;
import com.cesgroup.prison.rwgl.service.RwxfService;
import com.cesgroup.prison.utils.DataUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;

/**
 * 任务下发
 * 
 */
@Controller
@RequestMapping(value = "/rwgl/rwxf")
public class RwxfController extends BaseEntityDaoServiceCRUDController<RwxfEntity, String, RwxfMapper, RwxfService>{
	
	private final Logger logger = LoggerFactory.getLogger(RwxfController.class);  
	
	@Resource
	private RwxfService rwxfService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("rwgl/rwxf/edit");
		String type = request.getParameter("type");
		if("fxcj".equals(type)){//风险采集需要带入的参数 
			mv.addObject("title", Base64Util.decodeString(request.getParameter("title"), 2));
			mv.addObject("jhJjsj", request.getParameter("jhJjsj"));
			mv.addObject("fxcjId", request.getParameter("fxcjId"));
		}
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
		
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("rwgl/rwxf/view");
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("recordId", request.getParameter("recordId"));	
		mv.addObject("type", request.getParameter("type"));
		mv.addObject("openPos", request.getParameter("openPos"));
		mv.addObject("isDisplay", request.getParameter("isDisplay"));		
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public RwxfEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
				 			
		return rwxfService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(RwxfEntity rwxfEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		rwxfEntity.setJyId(user.getOrgCode());
		Page<RwxfEntity> pageInfo = (Page<RwxfEntity>) rwxfService.findList(rwxfEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping("/openDialog/index")
	public ModelAndView openListDialog() {
		ModelAndView mv = new ModelAndView("rwgl/rwxf/index");
		return mv;
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(RwxfEntity rwxfEntity, HttpServletRequest request,
			HttpServletResponse response) {
					
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			rwxfEntity.setJyId(user.getOrgCode());
			List<RwxfEntity> list = rwxfService.findAllList(rwxfEntity);
			
			for(RwxfEntity yabz : list) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("value", yabz.getId());
				resultMap.put("text", yabz.getRwTitle());
				resultList.add(resultMap);
			}
 		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
 		}
		
		return resultList;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(RwxfEntity rwxfEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = rwxfEntity.getId();
			if(id != null && !"".equals(id)) {
				rwxfEntity.setRwxfUpdtUserId(user.getUserId());
				rwxfEntity.setRwxfUpdtTime(new Date());
			} else {
				
				rwxfEntity.setRwxfCrteUserId(user.getUserId());
				rwxfEntity.setRwxfCrteTime(new Date());
				rwxfEntity.setRwxfUpdtUserId(user.getUserId());
				rwxfEntity.setRwxfUpdtTime(new Date());
			}
			rwxfEntity.setXfPolice(user.getUserName());
			rwxfEntity.setJyId(user.getOrgCode());
			rwxfEntity.setJqId(user.getDprtmntCode());
		//	rwxfEntity.setRwStatus("0");
			String[] jsDw = rwxfEntity.getJsDept().split(",");
			rwxfEntity.setXfdwzs(jsDw.length);
			rwxfEntity.setJsdwzs(0);
			rwxfEntity.setWfks(jsDw.length);
			rwxfEntity.setRwSituation(jsDw.length+"/0/"+jsDw.length);
			if("1".equals(rwxfEntity.getRwStatus())){
				
				
			}
			
			
			rwxfEntity = rwxfService.saveOrUpdate(rwxfEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("data", rwxfEntity);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public Map<String, Object> updateStatus(String ids, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			rwxfService.updateStatusByIds(ids,user);				
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
			
			rwxfService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/getJsDeptForCombobox")
	@ResponseBody
	public static String getJsDeptForCombobox() throws Exception {
		int userLevel = AuthSystemFacade.whatLevelForLoginUser();
		if(userLevel==1){
			return AuthSystemFacade.getAllJyJsonInfo();
		}else{
			return AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(AuthSystemFacade.getLoginUserInfo().getOrgCode());
		}
	}
}
