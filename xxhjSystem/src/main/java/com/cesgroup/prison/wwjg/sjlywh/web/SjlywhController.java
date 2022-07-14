package com.cesgroup.prison.wwjg.sjlywh.web;

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
import com.cesgroup.prison.wwjg.sjlywh.dao.SjlywhMapper;
import com.cesgroup.prison.wwjg.sjlywh.entity.SjlywhEntity;
import com.cesgroup.prison.wwjg.sjlywh.service.SjlywhService;

/**
 * 五维架构
 * 
 */
@Controller
@RequestMapping(value = "/wwjg/sjlywh")
public class SjlywhController extends BaseEntityDaoServiceCRUDController<SjlywhEntity, 
	String, SjlywhMapper, SjlywhService> {
	
	private final Logger logger = LoggerFactory.getLogger(SjlywhController.class);  
	
	@Resource
	private SjlywhService sjlywhService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/sjlywh/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/sjlywh/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public SjlywhEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return sjlywhService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(SjlywhEntity sjlywhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<SjlywhEntity> pageInfo = (Page<SjlywhEntity>) sjlywhService.findList(sjlywhEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(SjlywhEntity sjlywhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		/*wwjgwhEntity.setDcaCusNumber(user.getOrgCode());
		wwjgwhEntity.setDcaStatus(CommonConstant.STATUS_VALID_CODE);*/
		List<SjlywhEntity> list = sjlywhService.findAllList(sjlywhEntity);
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
	public Map<String, Object> saveOrUpdate(SjlywhEntity sjlywhEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = sjlywhEntity.getId();
			
			if(id != null && !"".equals(id)) {
				sjlywhEntity.setcGxr(user.getUserName());
				sjlywhEntity.setcGxrId(user.getUserId());
				sjlywhEntity.setcGxRq(new Date());
			} else {
				sjlywhEntity.setcCjr(user.getUserName());
				sjlywhEntity.setcCjrId(user.getUserId());
				sjlywhEntity.setcCjRq(new Date());
				sjlywhEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
				
			}
			
			sjlywhService.saveOrUpdate(sjlywhEntity);
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
			sjlywhService.deleteByIds(ids);
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
	 		
		SjlywhEntity sjlywhEntity = sjlywhService.getByCode(code); 
		if(sjlywhEntity==null){
			return "0";    //不存在 可以新增
		}else{
			return "1";   //存在 不可新增
		}
	}
}
