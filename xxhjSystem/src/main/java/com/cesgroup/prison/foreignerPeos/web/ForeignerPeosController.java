package com.cesgroup.prison.foreignerPeos.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.foreignerPeos.dao.ForeignerPeosMapper;
import com.cesgroup.prison.foreignerPeos.entity.ForeignerPeosEntity;
import com.cesgroup.prison.foreignerPeos.service.ForeignerPeosService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/xxhj/foreignerPeos")
public class ForeignerPeosController extends BaseEntityDaoServiceCRUDController<ForeignerPeosEntity, String, ForeignerPeosMapper, ForeignerPeosService> {
	private final Logger logger = LoggerFactory.getLogger(ForeignerPeosController.class);
	
	@Resource
	private ForeignerPeosService foreignerPeosService;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(@RequestParam(value = "onlyToday", defaultValue = "", required = false) String onlyToday) {
		ModelAndView mv = new ModelAndView("xxhj/foreignerPeos/index");
		mv.addObject("onlyToday", onlyToday);
		return mv;
	}

	@RequestMapping(value = "/findPhoto")
	public ModelAndView findPhoto(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getParameter("pUrl");
		ModelAndView mv = new ModelAndView("xxhj/foreignerPeos/findPhoto");
		mv.addObject("photoUrl", url);
		return mv;
	}

	@RequestMapping(value = "/searchCounts")
	@ResponseBody
	public Integer searchCounts() {
		return foreignerPeosService.searchCounts();
	}

	@RequestMapping(value = "/findList")
	@ResponseBody
	public Map<String, Object> findList(
			@RequestParam(value = "onlyToday", defaultValue = "", required = false) String onlyToday, 
			@RequestParam(value = "cName", defaultValue = "", required = false) String cName, 
			@RequestParam(value = "cSy", defaultValue = "", required = false) String cSy) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cName", cName.trim());
		paramMap.put("cSy", cSy);
		paramMap.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getOrgCode());
        if("1".equals(onlyToday)){
        	paramMap.put("dDjrq", Util.toStr(Util.DF_DATE));
        }
		PageRequest pageRequest = buildPageRequest();
		Page<ForeignerPeosEntity> pageInfo = (Page<ForeignerPeosEntity>) foreignerPeosService.findList(paramMap, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}
}
