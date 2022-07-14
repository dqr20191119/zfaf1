package com.cesgroup.prison.deviceMaintain.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.deviceMaintain.dao.DeviceMaintainMapper;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainEntity;
import com.cesgroup.prison.deviceMaintain.service.DeviceMaintainService;
import com.cesgroup.prison.deviceMaintain.service.impl.DeviceMaintainServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/deviceMaintain")
public class DevicMaintainController extends
		BaseEntityDaoServiceCRUDController<DeviceMaintainEntity, String, DeviceMaintainMapper, DeviceMaintainServiceImpl> {

	@Resource
	private DeviceMaintainService service;

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(DeviceMaintainEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		Object obj = null;
		boolean flag = false;
		try {
			obj = service.findById(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询发生异常";
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
	@RequestMapping(value = "/save")
	public AjaxMessage addInfo(DeviceMaintainEntity entity, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		Object obj = null;
		boolean flag = false;
		try {
			if (entity != null) {
				service.addInfo(entity, request);
				flag = true;
				obj = "事务报备成功";
			} else {
				flag = false;
				obj = "事务报备失败，参数错误";
			}
		} catch (Exception e) {
			flag = false;
			obj = "事务报备发生异常";
		}
		ajaxMessage.setSuccess(flag);
		ajaxMessage.setMsg((String) obj);
		return ajaxMessage;
	}

	@RequestMapping(value = "/signIn")
	@ResponseBody
	public AjaxMessage signIn(DeviceMaintainEntity entity, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		Object obj = null;
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				service.signIn(entity, request);
				flag = true;
				obj = "签收成功";
			} else {
				flag = false;
				obj = "签收失败，参数错误";
			}
		} catch (Exception e) {
			flag = false;
			obj = "签收异常";
		}

		ajaxMessage.setSuccess(flag);
		ajaxMessage.setMsg((String) obj);
		return ajaxMessage;
	}

	@RequestMapping(value = "/affairsDone")
	@ResponseBody
	public AjaxMessage affairsDone(DeviceMaintainEntity entity, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		Object obj = null;
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				service.affairsDone(entity, request);
				flag = true;
				obj = "处理成功";
			} else {
				flag = false;
				obj = "处理失败，参数错误";
			}
		} catch (Exception e) {
			flag = false;
			obj = "处理异常";
		}
		ajaxMessage.setSuccess(flag);
		ajaxMessage.setMsg((String) obj);
		return ajaxMessage;
	}

	@RequestMapping(value = "/feedback")
	@ResponseBody
	public AjaxMessage feedBack(DeviceMaintainEntity entity, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		Object obj = null;
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				service.feedBack(entity, request);
				flag = true;
				obj = "反馈成功";
			} else {
				flag = false;
				obj = "反馈失败，参数错误";
			}
		} catch (Exception e) {
			flag = false;
			obj = "反馈异常";
		}
		ajaxMessage.setSuccess(flag);
		ajaxMessage.setMsg((String) obj);
		return ajaxMessage;
	}

	@RequestMapping(value = "/remind")
	@ResponseBody
	public AjaxMessage remind(DeviceMaintainEntity entity, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		Object obj = null;
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				service.remind(entity, request);
				flag = true;
				obj = "提醒成功";
			} else {
				flag = false;
				obj = "提醒失败，参数错误";
			}
		} catch (Exception e) {
			flag = false;
			obj = "提醒异常";
		}
		ajaxMessage.setSuccess(flag);
		ajaxMessage.setMsg((String) obj);
		return ajaxMessage;
	}

	@RequestMapping(value = "/openDialog/record")
	public ModelAndView openRecordDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/record");
		return mv;
	}

	@RequestMapping(value = "/openDialog/handle")
	public ModelAndView openHandleDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/handle/list");
		return mv;
	}

	@RequestMapping(value = "/openDialog/signIn")
	public ModelAndView openAffairsSignInDialog(String id) {
		ModelAndView mv = new ModelAndView("deviceMaintain/handle/signIn");
		mv.addObject("ID", id.trim());
		return mv;
	}

	@RequestMapping(value = "/openDialog/affairsDone")
	public ModelAndView openAffairsDoneDialog(String id) {
		ModelAndView mv = new ModelAndView("deviceMaintain/handle/handle");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/openDialog/feedback")
	public ModelAndView openAffairsFeedbackDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/feedback/list");
		return mv;
	}

	@RequestMapping(value = "/openDialog/feedback/handle")
	public ModelAndView openFeedbackDialog(String id) {
		ModelAndView mv = new ModelAndView("deviceMaintain/feedback/feedback");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/openDialog/oversee")
	public ModelAndView openAffairsOverseeDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/oversee/list");
		return mv;
	}

	@RequestMapping(value = "/openDialog/remind")
	public ModelAndView openRemindDialog(String id) {
		ModelAndView mv = new ModelAndView("deviceMaintain/oversee/remind");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/openDialog/gather")
	public ModelAndView openAffairsGatherDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/gather/list");
		return mv;
	}

}
