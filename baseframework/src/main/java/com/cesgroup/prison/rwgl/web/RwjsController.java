package com.cesgroup.prison.rwgl.web;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.rwgl.dao.RwjsMapper;
import com.cesgroup.prison.rwgl.entity.RwjsEntity;
import com.cesgroup.prison.rwgl.service.RwjsService;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;

import ces.sdk.util.StringUtil;

/**
 * 任务下发
 * 
 */
@Controller
@RequestMapping(value = "/rwgl/rwjs")
public class RwjsController extends BaseEntityDaoServiceCRUDController<RwjsEntity, String, RwjsMapper, RwjsService>{
	
	private final Logger logger = LoggerFactory.getLogger(RwjsController.class);  
	
	@Resource
	private RwjsService rwjsService;
	 
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("rwgl/rwjs/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
		
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("rwgl/rwjs/view");
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("recordId", request.getParameter("recordId"));	
		mv.addObject("type", request.getParameter("type"));
		mv.addObject("openPos", request.getParameter("openPos"));
		mv.addObject("isDisplay", request.getParameter("isDisplay"));		
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public RwjsEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
				 			
		return rwjsService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(RwjsEntity rwjsEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		rwjsEntity.setJyId(user.getOrgCode());
		rwjsEntity.setJqId(user.getDprtmntCode());
		Page<RwjsEntity> pageInfo = null;
		rwjsEntity.setId(id);
		if("swdb".equals(type)) {
			pageInfo = (Page<RwjsEntity>) rwjsService.findDbList(rwjsEntity, pageRequest);
		}else {
			pageInfo = (Page<RwjsEntity>) rwjsService.findList(rwjsEntity, pageRequest);
		}
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchSwdb")
	@ResponseBody
	public List<Map<String, Object>> searchSwdb() throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		List<Map<String, Object>> list = rwjsService.searchSwdb(user);
		return list;
	}
	
	
	
	@RequestMapping("/openDialog/index")
	public ModelAndView openListDialog(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("rwgl/rwjs/index");
		mv.addObject("type", request.getParameter("type"));
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(RwjsEntity rwjsEntity, HttpServletRequest request,
			HttpServletResponse response) {
					
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			rwjsEntity.setJyId(user.getOrgCode());
			List<RwjsEntity> list = rwjsService.findAllList(rwjsEntity);
			
			for(RwjsEntity yabz : list) {
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
	public Map<String, Object> saveOrUpdate(RwjsEntity rwjsEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = rwjsEntity.getId();

			rwjsEntity.setRwjsCrteUserId(user.getUserId());
			rwjsEntity.setRwjsCrteTime(new Date());
			rwjsEntity.setRwjsCrteUserName(user.getUserName());
			
			rwjsEntity = rwjsService.saveOrUpdate(rwjsEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("data", rwjsEntity);
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
			rwjsService.updateStatusByIds(ids,user);				
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
			
			rwjsService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/getJsDeptForCombobox")
	@ResponseBody
	public String getJsDeptForCombobox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int userLevel = AuthSystemFacade.whatLevelForLoginUser();
		if(userLevel==1){
			return AuthSystemFacade.getAllJyJsonInfo();
		}else{
			return AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(AuthSystemFacade.getLoginUserInfo().getOrgCode());
		}
	}
	/**
	 * heqh 20190923 添加监外就诊和住院数据
	 * 
	 * */
	@RequestMapping(value = "/searchJwqk")
	@ResponseBody
	public List<Map<String, Object>> searchJwqk() throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		List<Map<String, Object>> list = rwjsService.searchJwqk(user);
		return list;
	}
	
}
