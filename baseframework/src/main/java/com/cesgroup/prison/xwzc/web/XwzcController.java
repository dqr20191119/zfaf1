package com.cesgroup.prison.xwzc.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.AuthSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xwzc.dao.XwzcMapper;
import com.cesgroup.prison.xwzc.entity.XwzcEntity;
import com.cesgroup.prison.xwzc.service.XwzcService;

@Controller
@RequestMapping(value = "/xwzc")
public class XwzcController extends BaseEntityDaoServiceCRUDController<XwzcEntity, String, XwzcMapper, XwzcService> {
	private final Logger logger = LoggerFactory.getLogger(XwzcController.class);
	
	@Resource
	private XwzcService xwzcService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(String type) {
		ModelAndView mv = new ModelAndView("xwzc/index");
		mv.addObject("type", type);
		return mv;
	}
	
	@RequestMapping(value = "/searchXwzcList")
	@ResponseBody
	public Map<String, Object> searchXwzcList(XwzcEntity xwzcEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		String type=request.getParameter("type");
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		xwzcEntity.setCusNumber(user.getCusNumber());
		Page<XwzcEntity> pageInfo = (Page<XwzcEntity>) xwzcService.searchXwzcList(xwzcEntity, pageRequest,type);
		return DataUtils.pageToMap(pageInfo);
	}
	
	@RequestMapping(value = "/searchXwzcCount")
	@ResponseBody
	public AjaxMessage searchXwzcCount(HttpServletRequest request) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			 
			Map<String, Object> map = xwzcService.searchXwzcCount();
			flag = true;
			result = map;
		} catch (Exception e) {
			flag = false;
			result = "查询失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg((String) result);
		}
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}
	
	 @RequestMapping(value = "/openImage")
	    public ModelAndView openImage(
	    		@RequestParam(value="id", defaultValue="", required=false) String id) {
	        ModelAndView mv = new ModelAndView("/xwzc/openImage");
	        mv.addObject("id", id);
	        return mv;
	    }
	 
	   @RequestMapping(value = "/dkImage")
	    public Map<String, Object>dkImage(
	    		@RequestParam(value="id", defaultValue="", required=false) String id) {
	    	
	        return this.getService().dkImage(id);
	    }
}
