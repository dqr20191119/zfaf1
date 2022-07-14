package com.cesgroup.prison.wwjg.sjfwgl.web;

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
import com.cesgroup.prison.wwjg.sjfwgl.dao.SjfwglMapper;
import com.cesgroup.prison.wwjg.sjfwgl.entity.SjfwglEntity;
import com.cesgroup.prison.wwjg.sjfwgl.service.SjfwglService;

 
@Controller
@RequestMapping(value = "/wwjg/sjfwgl")
public class SjfwglController extends BaseEntityDaoServiceCRUDController<SjfwglEntity, 
	String, SjfwglMapper, SjfwglService> {
	
	private final Logger logger = LoggerFactory.getLogger(SjfwglController.class);  
	
	@Resource
	private SjfwglService SjfwglService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/sjfwgl/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/sjfwgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public SjfwglEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return SjfwglService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(SjfwglEntity SjfwglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<SjfwglEntity> pageInfo = (Page<SjfwglEntity>) SjfwglService.findList(SjfwglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(SjfwglEntity SjfwglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		List<SjfwglEntity> list = SjfwglService.findAllList(SjfwglEntity);
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
	public Map<String, Object> saveOrUpdate(SjfwglEntity SjfwglEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = SjfwglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				SjfwglEntity.setcGxr(user.getUserName());
				SjfwglEntity.setcGxrId(user.getUserId());
				SjfwglEntity.setcGxRq(new Date());
			} else {
				SjfwglEntity.setcCjr(user.getUserName());
				SjfwglEntity.setcCjrId(user.getUserId());
				SjfwglEntity.setcCjRq(new Date());
				SjfwglEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
				
			}
			
			SjfwglService.saveOrUpdate(SjfwglEntity);
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
			SjfwglService.deleteByIds(ids);
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
	 		
		SjfwglEntity SjfwglEntity = SjfwglService.getByCode(code); 
		if(SjfwglEntity==null){
			return "0";    //不存在 可以新增
		}else{
			return "1";   //存在 不可新增
		}
	}
}
