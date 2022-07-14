package com.cesgroup.prison.yjct.web;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.yjct.dao.YjjlMapper;
import com.cesgroup.prison.yjct.entity.YjjlEntity;
import com.cesgroup.prison.yjct.service.YjjlService;

/**
 * 处置、演练记录管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/yjjl")
public class YjjlController extends BaseEntityDaoServiceCRUDController<YjjlEntity, String, YjjlMapper, YjjlService> {
	
	private final Logger logger = LoggerFactory.getLogger(YjjlController.class);  
	private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private YjjlService yjjlService;
	 
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("yjct/yjjl/index");
		mv.addObject("type", request.getParameter("type"));
		return mv;
	}
	
	@RequestMapping(value = "/toCzgc")
	public ModelAndView toCzgc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("yjct/yjjl/czgc");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
		
	@RequestMapping(value = "/toTj")
	public ModelAndView toTj(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("yjct/yjjl/tj");
		mv.addObject("type", request.getParameter("type"));
		return mv;
	}
	
	@RequestMapping(value = "/toZxjl")
	public ModelAndView toZxjl(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("yjct/yjjl/zxjl");
		mv.addObject("type", request.getParameter("type"));
		mv.addObject("ehrAlarmPlanFid", request.getParameter("ehrAlarmPlanFid"));
		mv.addObject("ehrAddressFid", request.getParameter("ehrAddressFid"));
		mv.addObject("alarmRecordId", request.getParameter("alarmRecordId"));
		mv.addObject("recordId", request.getParameter("recordId"));
		mv.addObject("ehrTime", df.format(new Date()));
		return mv;
	}
		
	@RequestMapping(value = "/toReport")
	public ModelAndView toReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("yjct/yjjl/report");
		mv.addObject("type", request.getParameter("type"));
		mv.addObject("recordId", request.getParameter("recordId"));
		mv.addObject("ehrEmPlanFid", request.getParameter("ehrEmPlanFid"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public YjjlEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
			 				
		return yjjlService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(YjjlEntity yjjlEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		yjjlEntity.setEhrCusNumber(user.getOrgCode());
		Page<YjjlEntity> pageInfo = (Page<YjjlEntity>) yjjlService.findList(yjjlEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(YjjlEntity yjjlEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = yjjlEntity.getId();
			
			if(id != null && !"".equals(id)) {
				yjjlEntity.setEhrUpdtUserId(user.getUserId());
				yjjlEntity.setEhrUpdtTime(new Date());
			} else {
				yjjlEntity.setEhrCusNumber(user.getOrgCode());
				yjjlEntity.setEhrCrteUserId(user.getUserId());
				yjjlEntity.setEhrCrteTime(new Date());
				yjjlEntity.setEhrUpdtUserId(user.getUserId());
				yjjlEntity.setEhrUpdtTime(new Date());
			}
			
			YjjlEntity yjjl = yjjlService.saveOrUpdate(yjjlEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("data", yjjl.getId());
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/saveOrUpdateEventReport")
	@ResponseBody
	public Map<String, Object> saveOrUpdateEventReport(YjjlEntity yjjlEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			yjjlEntity.setEhrUpdtUserId(user.getUserId());
			yjjlEntity.setEhrUpdtTime(new Date());			 
			yjjlService.saveOrUpdateEventReport(yjjlEntity);
			
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/updateRecordStatus")
	@ResponseBody
	public Map<String, Object> updateRecordStatus(YjjlEntity yjjlEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			yjjlEntity.setEhrUpdtUserId(user.getUserId());
			yjjlEntity.setEhrUpdtTime(new Date());			 
			String msg = yjjlService.updateRecordStatus(yjjlEntity);
			
			if(msg != "") {
				resultMap.put("code", CommonConstant.VALID_FAILURE_CODE);
				resultMap.put("data", msg);
			} else {
				resultMap.put("code", CommonConstant.SUCCESS_CODE);
			}			
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/tj")
	@ResponseBody
	public List<YjjlEntity> tj(YjjlEntity yjjlEntity, HttpServletRequest request,
			HttpServletResponse response) {
			 			
		return yjjlService.tj(yjjlEntity);
	}
	
	@RequestMapping(value = "/findEventRecord")
	@ResponseBody
	public Map<String, Object> findEventRecord(String id, HttpServletRequest request,
			HttpServletResponse response) {
			 				
		return yjjlService.findEventRecord(id);
	}

	/**
	 * 启动应急预案
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryYjjlStatusAndYjyaNameAndGzzName")
	@ResponseBody
	public AjaxResult queryYjjlStatusAndYjyaNameAndGzzName(@RequestParam(value = "yjjlId", defaultValue = "", required = false) String yjjlId, 
			@RequestParam(value = "gzzId", defaultValue = "", required = false) String gzzId) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String, String> map = this.getService().queryYjjlStatusAndYjyaNameAndGzzName(yjjlId, gzzId);
			result.setData(map);
			result.setCode(AjaxResult.success().getCode());
		} catch (Exception e) {
			result.setCode(AjaxResult.error().getCode());
			result.setMessage("查询发生异常");
		}
		return result;
	}
}
