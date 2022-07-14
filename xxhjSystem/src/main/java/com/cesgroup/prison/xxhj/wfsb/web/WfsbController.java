package com.cesgroup.prison.xxhj.wfsb.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xxhj.wfsb.dao.WfsbMapper;
import com.cesgroup.prison.xxhj.wfsb.entity.Wfsb;
import com.cesgroup.prison.xxhj.wfsb.service.WfsbService;
import com.cesgroup.prison.xxhj.wfsb.service.WfsbServiceImpl;

@Controller
@RequestMapping(value = "/xxhj/wfsb")
public class WfsbController extends BaseEntityDaoServiceCRUDController<Wfsb, String, WfsbMapper, WfsbServiceImpl> {

	@Resource
	private WfsbService wfsbService;
	
	@RequestMapping("/pwfsb")
	public ModelAndView pwfsb() {
		
		ModelAndView mv = new ModelAndView("xxhj/wfsb/pwfsb");
		return mv;
	}
	
	@RequestMapping("/wfsb")
	public ModelAndView wfsb(HttpServletRequest request) throws UnsupportedEncodingException {
		
		ModelAndView mv = new ModelAndView("xxhj/wfsb/wfsb");
		
		if(request.getParameter("prisonName") != null && request.getParameter("prisonId") != null) {
			mv.addObject("prisonName",URLDecoder.decode(request.getParameter("prisonName"), "utf-8") );
			mv.addObject("prisonId",request.getParameter("prisonId"));
	    }
		return mv;
	}
	
	@RequestMapping(value = "/listPhysicalDeviceCount")
	@ResponseBody
	public List<Map<String, String>> listPhysicalDeviceCount(String pdeCusNumber) {
		
		List<Map<String, String>> list = wfsbService.listPhysicalDeviceCount(pdeCusNumber);
		return list;
	}

	@RequestMapping(value = "/listPhysicalDeviceCountPrisonList")
	@ResponseBody
	public List<Map<String, String>> listPhysicalDeviceCountPrisonList(String pdeDeviceName, String obdParentId) throws Exception {
		 
		List<Map<String, String>> list = wfsbService.listPhysicalDeviceCountPrisonList(pdeDeviceName, obdParentId);
		return list;
	}
}
