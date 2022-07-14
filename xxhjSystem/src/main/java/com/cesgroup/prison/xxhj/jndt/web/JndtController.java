package com.cesgroup.prison.xxhj.jndt.web;

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
import com.cesgroup.prison.common.dao.PoliceMapper;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.jfsb.dao.JfsbMapper;
import com.cesgroup.prison.xxhj.jfsb.entity.Jfsb;
import com.cesgroup.prison.xxhj.jfsb.service.JfsbServiceImpl;
import com.cesgroup.prison.xxhj.jndt.entity.JndtEntity;
import com.cesgroup.prison.xxhj.jndt.service.JndtService;
import com.cesgroup.prison.zbgl.mbsz.web.MbszController;
/**
 * 监内动态
 *
 */
@Controller
@RequestMapping({"/xxhj/jndt", "/guest/jndt"})
public class JndtController extends BaseEntityDaoServiceCRUDController<Jfsb, String, JfsbMapper, JfsbServiceImpl> {

	@Resource
	private JndtService jndtService;
	@Resource
	private PoliceMapper policeMapper;
	
	private final Logger logger = LoggerFactory.getLogger(MbszController.class); 
	
	@RequestMapping("/jctj")
	public ModelAndView jctj() {
		ModelAndView mv = new ModelAndView("xxhj/jctj/index");
		return mv;
	}
	
	@RequestMapping("/toIndex")
	public ModelAndView toIndex() {
		
		ModelAndView mv = new ModelAndView("xxhj/jndt/index");
		return mv;
	}
	
	@RequestMapping("/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/jndt/edit");
		mv.addObject("id", request.getParameter("id"));
		
		return mv;
	}
	
	@RequestMapping("/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/jndt/view");
		mv.addObject("id", request.getParameter("id"));
		
		return mv;
	}
	
	/*监内动态人数统计页面*/
	@RequestMapping("/toViewCount")
	public ModelAndView toViewCount(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/jndt/viewCount");
		return mv;
	}
	
	/*大屏显示页面*/
	@RequestMapping("/toShowScreen")
	public ModelAndView toShowScreen(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/jndt/showScreen");
		mv.addObject("cusNumber", request.getParameter("cusNumber"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public JndtEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return jndtService.getById(id); 
	}
	
	@RequestMapping(value = "/getHistoryById")
	@ResponseBody
	public JndtEntity getHistoryById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return jndtService.getHistoryById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(JndtEntity jndtEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		jndtEntity.setParCusNumber(user.getOrgCode());
		Page<JndtEntity> pageInfo = (Page<JndtEntity>) jndtService.findList(jndtEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	/*查询监内动态历史表*/
	@RequestMapping(value = "/searchHistoryData")
	@ResponseBody
	public Map<String, Object> searchHistoryData(JndtEntity jndtEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		jndtEntity.setParCusNumber(user.getOrgCode());
		Page<JndtEntity> pageInfo = (Page<JndtEntity>) jndtService.findHistoryList(jndtEntity,pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<JndtEntity> searchAllData(JndtEntity jndtEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String cusNumber = request.getParameter("cusNumber");
		jndtEntity.setParStatus(CommonConstant.STATUS_VALID_CODE);
		
		if(cusNumber != null && !"".equals(cusNumber)) {
			jndtEntity.setParCusNumber(cusNumber);
		} else {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			jndtEntity.setParCusNumber(user.getOrgCode());
		}
		
		List<JndtEntity> list = jndtService.findAllList(jndtEntity);
		return list;				
	}   
	
	/*实时统计监内动态民警和罪犯数*/
	@RequestMapping(value = "/countPeople")
	@ResponseBody
	public   List<Map<String, String>> countPeople(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String cusNumber = request.getParameter("cusNumber");
		String parOutCategory = request.getParameter("parOutCategory");
		List<Map<String, String>> list = jndtService.countPeople(cusNumber,parOutCategory);
		return list;				
	}   
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(JndtEntity jndtEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			jndtEntity.setParStatus(CommonConstant.STATUS_VALID_CODE);
			jndtEntity.setParCusNumber(user.getOrgCode());
			jndtEntity.setParStartCrteUserId(user.getUserId());
			jndtEntity.setParStartCrteUserName(user.getRealName());
			jndtEntity.setParStartCrteTime(new Date());
			jndtEntity.setParUpdtUserId(user.getUserId());
			jndtEntity.setParUpdtUserName(user.getRealName());
			jndtEntity.setParUpdtTime(new Date());
			
			jndtService.saveOrUpdate(jndtEntity);
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
			jndtService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}

	@RequestMapping(value = "/completeJndtByIds")
	@ResponseBody
	public Map<String, Object> completeJndtByIds(String ids, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<>();

		try {
			jndtService.completeJndtByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}

		return resultMap;
	}
	
}
