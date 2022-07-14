package com.cesgroup.prison.examples.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;

@Controller
@RequestMapping("/exp-authorzation")
public class AuthorizationController extends BaseController {
	/**
	 * 进入用户权限页面
	 * @return
	 */
	@RequestMapping("/userList")
	public ModelAndView userList() {
		return new ModelAndView("examples/uPermission");
	}
	/**
	 * 进入角色权限页面
	 * @return
	 */
	@RequestMapping("/roleList")
	public ModelAndView roleList() {
		return new ModelAndView("examples/rPermission");
	}
}
