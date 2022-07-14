package com.cesgroup.prison.jswh.web;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jswh.dao.JswhMapper;
import com.cesgroup.prison.jswh.entity.JswhEntity;
import com.cesgroup.prison.jswh.service.JswhService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "xxhj/jswh")
public class JswhController extends BaseEntityDaoServiceCRUDController<JswhEntity, String, JswhMapper, JswhService> {

	private final Logger logger = LoggerFactory.getLogger(JswhController.class);

	@Resource
	private JswhService jswhService;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("jswh/index");
		return mv;
	}

	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("jswh/add");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("jswh/edit");
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("areaId", request.getParameter("areaId"));
		
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public JswhEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {

		return jswhService.getById(id);
	}

	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(JswhEntity jswhEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		jswhEntity.setCpjCusNumber(user.getOrgCode());
		Page<JswhEntity> pageInfo = (Page<JswhEntity>) jswhService.findList(jswhEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}

	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(JswhEntity jswhEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserBean user = AuthSystemFacade.getLoginUserInfo();
		jswhEntity.setCpjCusNumber(user.getOrgCode());
		List<Map<String, Object>> list = jswhService.findAllList(jswhEntity);
		return list;
	}

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public AjaxMessage saveOrUpdate(@RequestBody List<Map<String, Object>> jswhs) {
		
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			jswhService.saveOrUpdate(jswhs);
			obj = "操作成功！";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteByIds")
	public AjaxMessage deleteByIds(String ids) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			jswhService.deleteByIds(ids);
			obj = "删除成功！";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
		}
		if (flag) {
			/*ajaxMessage.setCode(code);*/
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}
}
