package com.cesgroup.prison.xxhj.mjczdj.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.mjczdj.dao.DoorOperationRegistrationMapper;
import com.cesgroup.prison.xxhj.mjczdj.entity.DoorOperationRegistrationEntity;
import com.cesgroup.prison.xxhj.mjczdj.service.DoorOperationRegistrationService;

@Controller
@RequestMapping(value = "xxhj/mjczdj")
public class DoorOperationRegistrationController extends
		BaseEntityDaoServiceCRUDController<DoorOperationRegistrationEntity, String, DoorOperationRegistrationMapper, DoorOperationRegistrationService> {

	@Resource
	private DoorOperationRegistrationService service;

	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("/xxhj/mjczdj/index");
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(DoorOperationRegistrationEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.findById(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询记录发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/updateInfo")
	@ResponseBody
	public AjaxMessage update(DoorOperationRegistrationEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			flag = true;
			obj = "闭门登记成功";
		} catch (Exception e) {
			flag = false;
			obj = "闭门登记发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteByIds(@RequestBody List<String> id, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteByIds(id);
			flag = true;
			obj = "删除记录成功";
		} catch (Exception e) {
			flag = false;
			obj = "删除记录发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/saveInfo")
	public AjaxMessage inster(DoorOperationRegistrationEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			String cusNumber = userBean.getCusNumber();
			String dprtmntCode = userBean.getDprtmntCode();
			Date date = new Date();
			entity.setDorCusNumber(cusNumber);
			entity.setDorCrteTime(date);
			entity.setDorCrteUserId(userId);
			entity.setDorCrteUser(userBean.getRealName());
			entity.setDorDprtmntId(dprtmntCode);
			entity.setDorDprtmnt(userBean.getDprtmntName());

			List<Map<String, Object>> list = service.findTodayRegistrationByDprtmntAndCusNumber(cusNumber, dprtmntCode);
			if (list == null) {
				flag = false;
				obj = "开门登记失败，查询当日记录参数错误，请重新登陆或联系代码工程师";
			} else if (list.isEmpty()) {
				service.addInfo(entity);
				flag = true;
				obj = "开门登记成功";
			} else {
				flag = false;
				obj = "开门登记失败，" + userBean.getDprtmntName() + "今日已登记";
			}
		} catch (Exception e) {
			flag = false;
			obj = "开门登记发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}
}
