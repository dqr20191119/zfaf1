package com.cesgroup.prison.zbgl.zbjk.web;

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
import com.cesgroup.prison.zbgl.zbjk.dao.ZbjkMapper;
import com.cesgroup.prison.zbgl.zbjk.entity.ZbjkEntity;
import com.cesgroup.prison.zbgl.zbjk.service.ZbjkService;

/**
 * 值班监控
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/zbjk")
public class ZbjkController extends BaseEntityDaoServiceCRUDController<ZbjkEntity, String, ZbjkMapper, ZbjkService> {
	
	@Resource
	private ZbjkService zbjkService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response)  {
		ModelAndView mv = new ModelAndView("zbgl/zbjk/index");
		return mv;
	}
	
	@RequestMapping("/searchData")
	@ResponseBody
	public Map<String, Object> searchData(HttpServletRequest request) throws Exception {
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pageInfo = zbjkService.findList(request,pageRequest);
		
		return DataUtils.pageToMap(pageInfo);
	}

	
}


