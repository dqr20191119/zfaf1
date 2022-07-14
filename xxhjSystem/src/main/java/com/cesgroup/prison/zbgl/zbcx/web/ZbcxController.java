package com.cesgroup.prison.zbgl.zbcx.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.zbcx.dao.ZbcxMapper;
import com.cesgroup.prison.zbgl.zbcx.entity.ZbcxEntity;
import com.cesgroup.prison.zbgl.zbcx.service.ZbcxService;

/**
 * 值班查询
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/zbcx")
public class ZbcxController extends BaseEntityDaoServiceCRUDController<ZbcxEntity, String, ZbcxMapper, ZbcxService> {

	@Resource
	private ZbcxService zbcxService;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("zbgl/zbcx/index");
		return mv;
	}

	@RequestMapping("/searchData")
	@ResponseBody
	public Map<String, Object> searchData(HttpServletRequest request) throws Exception {

		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pageInfo = zbcxService.findList(request, pageRequest);

		return DataUtils.pageToMap(pageInfo);
	}

	@RequestMapping("/queryDutyCount")
	@ResponseBody
	public Map<String, Object> queryDutyCountByDeapmntAndDate(HttpServletRequest request) throws Exception {
		return zbcxService.queryDutyCountByDeapmntAndDate(request);
	}

	@RequestMapping("/queryStaff")
	@ResponseBody
	public Map<String, Object> queryStaffByDeapmntAndDate(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		return DataUtils.pageToMap(zbcxService.queryStaffByDeapmntAndDate(request, pageRequest));
	}
	
	@RequestMapping(value = "/toStaffList")
	public ModelAndView toStaffList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/zbcx/staffList");
		mv.addObject("cusNumber", request.getParameter("cusNumber"));
		mv.addObject("dprtmntId", request.getParameter("dprtmntId"));
		mv.addObject("date", request.getParameter("date"));
		mv.addObject("type", request.getParameter("type"));
		return mv;
	}
	
}
