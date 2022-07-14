package com.cesgroup.prison.alarm.event.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.alarm.event.dao.AlertEventRepMapper;
import com.cesgroup.prison.alarm.event.entity.AlertEventRepEntity;
import com.cesgroup.prison.alarm.event.entity.AlertEvidenceRelationEntity;
import com.cesgroup.prison.alarm.event.service.AlertEventRepService;

/**      
* @projectName：alarmSystem   
* @ClassName：AlertEventRepController   
* @Description：   报警证据信息
* @author：Tao.xu   
* @date：2018年1月19日 上午10:29:24   
* @version        
*/
@Controller
@RequestMapping(value = "/alert/event")
public class AlertEventRepController extends
		BaseEntityDaoServiceCRUDController<AlertEventRepEntity, String, AlertEventRepMapper, AlertEventRepService> {

	@Resource
	private AlertEventRepService service;

	/**
	* @methodName: addEventRep
	* @Description: 保存报警事件报告
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/save/rep")
	@ResponseBody
	public AjaxMessage addEventRep(AlertEventRepEntity entity) {
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			service.addEventRep(entity);
			flag = true;
			result = "保存报警事件报告信息成功";
		} catch (Exception e) {
			flag = false;
			result = "保存报警事件报告信息失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}

	/**
	* @methodName: findEventRep
	* @Description: 查询报警事件信息
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/findEventRep")
	@ResponseBody
	public AjaxMessage findEventRep(AlertEventRepEntity entity) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			flag = true;
			result = service.findEventRepByEntity(entity);

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

	/**
	* @methodName: addEvidenceRelation
	* @Description: 添加报警证据信息
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/save/evidence")
	@ResponseBody
	public AjaxMessage addEvidenceRelation(AlertEvidenceRelationEntity entity) {
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			service.addEvidenceRelation(entity);
			flag = true;
			result = "保存报警证据信息成功";
		} catch (Exception e) {
			flag = false;
			result = "保存报警证据信息失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}

	/**
	* @methodName: findEvidenceRelation
	* @Description: 查询报警证据信息
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/findEvidenceRelation")
	@ResponseBody
	public AjaxMessage findEvidenceRelation(AlertEvidenceRelationEntity entity) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			flag = true;
			result = service.findEvidenceRelationByEntity(entity);

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

}
