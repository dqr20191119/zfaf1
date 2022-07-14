package com.cesgroup.prison.xxhj.cgsgxx.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.prison.xxhj.cgsgxx.vo.CgsgxxVo;
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
import com.cesgroup.prison.xxhj.cgsgxx.dao.CgsgxxMapper;
import com.cesgroup.prison.xxhj.cgsgxx.entity.CgsgxxEntity;
import com.cesgroup.prison.xxhj.cgsgxx.service.CgsgxxService;
import com.cesgroup.prison.xxhj.cgsgxx.service.CgsgxxServiceImpl;
import com.cesgroup.prison.zbgl.mbsz.web.MbszController;

/**
 * 出工收工信息
 *
 */
@Controller
@RequestMapping(value = "/xxhj/cgsgxx")
public class CgsgxxController extends BaseEntityDaoServiceCRUDController<CgsgxxEntity, String, CgsgxxMapper, CgsgxxServiceImpl> {

	@Resource
	private CgsgxxService cgsgxxService;
	@Resource
	private PoliceMapper policeMapper;
	
	private final Logger logger = LoggerFactory.getLogger(MbszController.class); 
	
	@RequestMapping("/toIndex")
	public ModelAndView toIndex() {
		
		ModelAndView mv = new ModelAndView("xxhj/cgsgxx/index");
		return mv;
	}
	
	@RequestMapping("/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/cgsgxx/edit");
		mv.addObject("id", request.getParameter("id"));
		
		return mv;
	}
	
	@RequestMapping("/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("xxhj/jndt/view");
		mv.addObject("id", request.getParameter("id"));
		
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public CgsgxxEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return cgsgxxService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(CgsgxxVo cgsgxxVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();

		cgsgxxVo.setPwrCusNumber(user.getOrgCode());
		Page<CgsgxxEntity> pageInfo = (Page<CgsgxxEntity>) cgsgxxService.findList(cgsgxxVo, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(CgsgxxEntity cgsgxxEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			cgsgxxEntity.setPwrStatus(CommonConstant.STATUS_VALID_CODE);
			cgsgxxEntity.setPwrCusNumber(user.getOrgCode());
			cgsgxxEntity.setPwrStartCrteUserId(user.getUserId());
			cgsgxxEntity.setPwrStartCrteUserName(user.getRealName());
			cgsgxxEntity.setPwrStartCrteTime(new Date());
			cgsgxxEntity.setPwrUpdtUserId(user.getUserId());
			cgsgxxEntity.setPwrUpdtUserName(user.getRealName());
			cgsgxxEntity.setPwrUpdtTime(new Date());
			
			cgsgxxService.saveOrUpdate(cgsgxxEntity);
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
			cgsgxxService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	@RequestMapping(value = "/completeByIds")
	@ResponseBody
	public Map<String, Object> completeByIds(String ids, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<>();

		try {
			cgsgxxService.completeByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}

		return resultMap;
	}
}
