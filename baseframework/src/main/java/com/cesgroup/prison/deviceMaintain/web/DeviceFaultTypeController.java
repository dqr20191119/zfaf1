package com.cesgroup.prison.deviceMaintain.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.deviceMaintain.dao.DeviceFaultTypeMapper;
import com.cesgroup.prison.deviceMaintain.entity.DeviceFaultTypeEntity;
import com.cesgroup.prison.deviceMaintain.entity.FaultDepmtReltEntity;
import com.cesgroup.prison.deviceMaintain.service.DeviceFaultTypeService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/deviceFaultType")
public class DeviceFaultTypeController extends
		BaseEntityDaoServiceCRUDController<DeviceFaultTypeEntity, String, DeviceFaultTypeMapper, DeviceFaultTypeService> {

	@Resource
	private DeviceFaultTypeService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/deviceFaultType/index");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开增加窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/add")
	public ModelAndView openAddDialog(String type) {
		ModelAndView mv = null;
		if (type.equals("1")) {
			mv = new ModelAndView("deviceMaintain/deviceFaultType/add_type");
		} else {
			mv = new ModelAndView("deviceMaintain/deviceFaultType/add_content");
		}
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开修改窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(String id, String type) {
		ModelAndView mv = null;
		if (type.equals("1")) {
			mv = new ModelAndView("deviceMaintain/deviceFaultType/update_type");
		} else {
			mv = new ModelAndView("deviceMaintain/deviceFaultType/update_content");
		}
		mv.addObject("ID", id);
		return mv;
	}

	/**
	* @methodName: listAll
	* @Description: 分页查询故障类型信息
	* @param entity
	* @param type
	* @return
	* @throws Exception Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(DeviceFaultTypeEntity entity, String type) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, type, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(String id, String type) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			if (type.equals("2")) {
				Map<String, Object> mapEntity = service.findContentById(id);
				flag = true;
				obj = mapEntity;
			} else {
				DeviceFaultTypeEntity entity = service.findById(id);
				flag = true;
				obj = entity;
			}
		} catch (Exception e) {
			flag = false;
			obj = "查询故障信息发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/seachComboData")
	@ResponseBody
	public List<Map<String, Object>> seachComboData(String cusNumber, String typeClassify, String sttsIndc,
			String faultId) throws Exception {
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = service.searchCombData(cusNumber, typeClassify, sttsIndc, faultId);
		return maps;
	}

	@RequestMapping(value = "/updateDeviceFaultType")
	@ResponseBody
	public AjaxMessage update(DeviceFaultTypeEntity entity, FaultDepmtReltEntity reltEntity, String type) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateDeviceFaultTypeInfo(entity);
			if (type.equals("2")) {
				reltEntity.setFdrFaultId(entity.getId());
				service.updateRelationInfo(reltEntity);
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "修改故障信息发生异常";
		}
		if (flag) {
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public AjaxMessage delete(DeviceFaultTypeEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteById(entity);
			service.deleRelationDepartment(entity.getId());// 根据主表id物理删除部门关联表信息
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "删除故障信息发生异常";
		}
		if (flag) {
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/inster")
	public AjaxMessage inster(DeviceFaultTypeEntity entity, FaultDepmtReltEntity reltEntity, String type) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			String id = UUID.randomUUID().toString().replace("-", "");
			entity.setId(id);
			service.addDeviceFaultTypeInfo(entity);
			if (type.equals("2")) {
				reltEntity.setFdrFaultId(id);
				service.addRelationInfo(reltEntity);
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "增加故障信息发生异常";
		}
		if (flag) {
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/findDprtmnt")
	@ResponseBody
	public AjaxMessage findDprtmntByCusNumberAndFaultType(String cusNumber, String faultId) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.findDprtmntByCusNumberAndFaultType(cusNumber, faultId);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询维修部门和协助部门发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

}
