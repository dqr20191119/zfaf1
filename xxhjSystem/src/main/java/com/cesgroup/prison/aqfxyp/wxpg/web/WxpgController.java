package com.cesgroup.prison.aqfxyp.wxpg.web;

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

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.aqfxyp.wxpg.dao.WxpgMapper;
import com.cesgroup.prison.aqfxyp.wxpg.entity.WxpgEntity;
import com.cesgroup.prison.aqfxyp.wxpg.service.WxpgService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "aqfxyp/wxpg")
public class WxpgController  extends BaseEntityDaoServiceCRUDController<WxpgEntity, String, WxpgMapper, WxpgService> {
	private final Logger logger = LoggerFactory.getLogger(WxpgController.class);

	@Resource
	private WxpgService wxpgService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		String zt = request.getParameter("zt");
		ModelAndView mv = new ModelAndView("aqfxyp/wxpg/index");
		mv.addObject("zt", zt);
		return mv;
	}
	
	@RequestMapping(value = "/searchWxpg")
	@ResponseBody
	public AjaxResult searchWxpg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			List<Map<String, Object>> list = this.getService().getWxpg();
			return AjaxResult.success(list);
		}catch(Exception e){
			return AjaxResult.error("初始化危险评估图发生异常");
		}
		
	}
	
	@RequestMapping(value = "/searchListPage")
	@ResponseBody
	public Map<String, Object> searchListPage(WxpgEntity wxpgEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		String zt = request.getParameter("zt");
		Page<WxpgEntity> pageInfo = (Page<WxpgEntity>) this.getService().findListPage(zt, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}
	
	
}
