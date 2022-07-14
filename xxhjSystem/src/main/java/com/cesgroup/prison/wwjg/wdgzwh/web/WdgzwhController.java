package com.cesgroup.prison.wwjg.wdgzwh.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.wwjg.wdgzwh.dao.WdgzwhMapper;
import com.cesgroup.prison.wwjg.wdgzwh.entity.WdgzwhEntity;
import com.cesgroup.prison.wwjg.wdgzwh.service.WdgzwhService;

 
@Controller
@RequestMapping(value = "/wwjg/wdgzwh")
public class WdgzwhController extends BaseEntityDaoServiceCRUDController<WdgzwhEntity, String, WdgzwhMapper, WdgzwhService> {
	
	@RequestMapping(value = "/deleteWdgzwh")
	@ResponseBody
	public Map<String, Object> deleteWdgzwh(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		try {
			String id = request.getParameter("id");
			this.getService().delete(id);
		} catch(Exception e) {
			
		}
		return result;
	}

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(WdgzwhEntity entity, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		try {
			if (entity.getId() == null || "".equals(entity.getId())) {
				this.getService().insert(entity);
			} else {
				this.getService().update(entity);
			}
		} catch(Exception e) {
			
		}
		return result;
	}
	
	@RequestMapping(value = "/findWdgzwh")
	@ResponseBody
	public Map<String, Object> findWdgzwh(WdgzwhEntity entity, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		try {
			result.put("data", this.getService().selectOne(entity.getId()));
		} catch(Exception e) {
			
		}
		return result;
	}
	
}
