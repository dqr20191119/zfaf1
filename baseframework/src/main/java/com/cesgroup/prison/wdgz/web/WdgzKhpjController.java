package com.cesgroup.prison.wdgz.web;

import java.util.HashMap;
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
import com.cesgroup.prison.wdgz.dao.WdgzKhpjMapper;
import com.cesgroup.prison.wdgz.entity.WdgzKhpjEntity;
import com.cesgroup.prison.wdgz.service.WdgzKhpjService;

@Controller
@RequestMapping("/wdgz")
public class WdgzKhpjController extends BaseEntityDaoServiceCRUDController<WdgzKhpjEntity, String, WdgzKhpjMapper, WdgzKhpjService> {
	
	@Resource
	private WdgzKhpjService wdgzKhpjService;
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(HttpServletRequest request, HttpServletResponse response){
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pageInfo =  wdgzKhpjService.findList(request, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping("/ymzs")
	public ModelAndView ymzs(HttpServletRequest request, HttpServletResponse response){
		//jbxxId
		String jbxxId = request.getParameter("jbxxId");
		request.setAttribute("jbxxId", jbxxId);
		ModelAndView mv = null;
		mv = new ModelAndView("portal/bj/wdgzymzs");
		return mv;
	}
	@RequestMapping("/ymzsmx")
	public ModelAndView ymzsmx(HttpServletRequest request, HttpServletResponse response){
		//jbxxId
		String zbId = request.getParameter("zbId");
		String lx = request.getParameter("lx");
		request.setAttribute("zbId", zbId);
		request.setAttribute("lx", lx);
		ModelAndView mv = null;
		mv = new ModelAndView("portal/bj/wdgzzsmx");
		return mv;
	}
	
	
	/**
	 * 获取头部主要数据  监区姓名等
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getwdgz")
	@ResponseBody
	public Map<String, Object> getwdgz(HttpServletRequest request, HttpServletResponse response){
		 Map map = new HashMap();
		 String jbxxId = request.getParameter("jbxxId");
		 map = wdgzKhpjService.getWg(jbxxId);
		return map;				
	}
	@RequestMapping(value = "/getwdgzMx")
	@ResponseBody
	public Map<String, Object> getwdgzMx(HttpServletRequest request, HttpServletResponse response){
		 Map map = new HashMap();
			String zbId = request.getParameter("zbId");
			String lx = request.getParameter("lx");
		 map = wdgzKhpjService.getMx(zbId,lx);
		return map;				
	}
}
