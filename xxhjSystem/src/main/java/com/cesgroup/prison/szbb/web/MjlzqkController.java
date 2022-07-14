package com.cesgroup.prison.szbb.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.szbb.dao.MjlzqkDao;
import com.cesgroup.prison.szbb.entity.Mjlzqk;
import com.cesgroup.prison.szbb.service.MjlzqkService;


@Controller
@RequestMapping("/sjfx/mjlzqk")
public class MjlzqkController extends BaseEntityDaoServiceCRUDController<Mjlzqk, String, MjlzqkDao, MjlzqkService> {
	
	@RequestMapping(value = "/findMjnlgc")
    @ResponseBody
	public Map<String, Object> findMjnlgc() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getCusNumber());
			Map<String, Object> data = this.getService().findMjnlgc(map);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/findLnmjsl")
    @ResponseBody
	public Map<String, Object> findLnmjsl() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getCusNumber());
			Map<String, Object> data = this.getService().findLnmjsl(map);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/findMjxlgc")
    @ResponseBody
	public Map<String, Object> findMjxlgc() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getCusNumber());
			Map<String, Object> data = this.getService().findMjxlgc(map);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/findJqmjsl")
    @ResponseBody
	public Map<String, Object> findJqmjsl() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getCusNumber());
			Map<String, Object> data = this.getService().findJqmjsl(map);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/findJgmjfb")
    @ResponseBody
	public Map<String, Object> findJgmjfb() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getCusNumber());
			Map<String, Object> data = this.getService().findJgmjfb(map);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/findJqjqb")
    @ResponseBody
	public Map<String, Object> findJqjqb() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getCusNumber());
			Map<String, Object> data = this.getService().findJqjqb(map);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
