package com.cesgroup.prison.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.code.tool.JsonUtil;
import com.cesgroup.prison.common.bean.login2.LoginRespBean;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.UserCodeUtil;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController extends BaseController{
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("common/ueditor");
		return mv;
	}
	
}
