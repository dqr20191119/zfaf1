package com.cesgroup.prison.wwjg.wwjgwh.web;

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
import com.cesgroup.prison.wwjg.wwjgwh.dao.WwjgwhMapper;
import com.cesgroup.prison.wwjg.wwjgwh.entity.WwjgwhEntity;
import com.cesgroup.prison.wwjg.wwjgwh.service.WwjgwhService;

/**
 * 五维架构
 * 
 */
@Controller
@RequestMapping(value = "/wwjg/wwjgwh")
public class WwjgwhController extends BaseEntityDaoServiceCRUDController<WwjgwhEntity, String, WwjgwhMapper, WwjgwhService> {
	
	private final Logger logger = LoggerFactory.getLogger(WwjgwhController.class);  
	
	@Resource
	private WwjgwhService wwjgwhService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/wwjgwh/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/wwjgwh/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public WwjgwhEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return wwjgwhService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(WwjgwhEntity wwjgwhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<WwjgwhEntity> pageInfo = (Page<WwjgwhEntity>) wwjgwhService.findList(wwjgwhEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(WwjgwhEntity wwjgwhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		List<WwjgwhEntity> list = wwjgwhService.findAllList(wwjgwhEntity);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i<list.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getId());
			map.put("text", list.get(i).getName());	
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(WwjgwhEntity wwjgwhEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = wwjgwhEntity.getId();
			
			if(id != null && !"".equals(id)) {
				wwjgwhEntity.setcGxr(user.getUserName());
				wwjgwhEntity.setcGxrId(user.getUserId());
				wwjgwhEntity.setcGxRq(new Date());
			} else {
				wwjgwhEntity.setcCjr(user.getUserName());
				wwjgwhEntity.setcCjrId(user.getUserId());
				wwjgwhEntity.setcCjRq(new Date());
				wwjgwhEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
				
			}
			
			wwjgwhService.saveOrUpdate(wwjgwhEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	public Map<String, Object> deleteByIds(String ids, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
	
		try {
			wwjgwhService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
	 
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/getByCode")
	@ResponseBody
	public String getByCode(String code, HttpServletRequest request, HttpServletResponse response) {
	 		
		WwjgwhEntity wwjgwhEntity = wwjgwhService.getByCode(code); 
		if(wwjgwhEntity==null){
			return "0";    //不存在 可以新增
		}else{
			return "1";   //存在 不可新增
		}
	}
	
}
