package com.cesgroup.prison.doorPlan.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cesgroup.prison.doorPlan.dao.DoorPlanMapper;
import com.cesgroup.prison.doorPlan.entity.DoorPlanEntity;
import com.cesgroup.prison.doorPlan.entity.DoorPlanRltnEntity;
import com.cesgroup.prison.doorPlan.service.DoorPlanService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/door/plan")
public class DoorPlanController
		extends BaseEntityDaoServiceCRUDController<DoorPlanEntity, String, DoorPlanMapper, DoorPlanService> {

	@Resource
	private DoorPlanService service;

	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("/doorPlan/list");
		return mv;
	}

	@RequestMapping(value = "/openDialog/save")
	public ModelAndView openAddDialog() {
		ModelAndView mv = new ModelAndView("doorPlan/save");
		return mv;
	}

	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(String id) {
		ModelAndView mv = new ModelAndView("doorPlan/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/openDialog/setting")
	public ModelAndView openDialog(String id) {
		ModelAndView mv = new ModelAndView("doorPlan/setting");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(DoorPlanEntity entity) throws Exception {
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

	@RequestMapping(value = "/updateInfo")
	@ResponseBody
	public AjaxMessage update(DoorPlanEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			obj = "修改成功";
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "修改发生异常";
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
			obj = "删除成功";
		} catch (Exception e) {
			flag = false;
			obj = "删除发生异常";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/saveInfo")
	public AjaxMessage inster(DoorPlanEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.addInfo(entity);
			flag = true;
			obj = "添加成功";
		} catch (Exception e) {
			flag = false;
			obj = "添加发生异常";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	* @methodName: listAll
	* @Description: 列表查询待关联门禁信息
	* @param cusNumber
	* @param areaId
	* @param planId
	* @return
	* @throws Exception Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/listAllForMj")
	@ResponseBody
	public Map<String, Object> listAll(String cusNumber, String areaId, String planId, String doorName)
			throws Exception {
		List<Map<String, Object>> list = (List<Map<String, Object>>) service.listAllForMj(cusNumber, areaId, planId,
				doorName);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		return map;
	}

	@RequestMapping(value = "/listAll/rltn")
	@ResponseBody
	public Map<String, Object> listAll(DoorPlanRltnEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAllForDoor(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/delete/rltn", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteByIds(@RequestBody List<String> id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteByIdsForDoor(id);
			flag = true;
			obj = "取消关联成功";
		} catch (Exception e) {
			flag = false;
			obj = "取消关联发生异常";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/save/rltn")
	public AjaxMessage save(@RequestBody Object doorMap) {
		return service.saveDoorRltn(doorMap);
	}

	@RequestMapping(value = "/findForTree")
	@ResponseBody
	public List<Map<String, Object>> findForCombotree(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("id", request.getParameter("id")); // 节点id
		paramMap.put("stts", request.getParameter("stts")); // 预案状态
		return service.findForCombotree(paramMap);
	}

	/**
	* @methodName: ctrlDoorPlan
	* @Description: 控制门禁预案
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@ResponseBody
	@RequestMapping(value = "/ctrlDoorPlan")
	public AjaxMessage ctrlDoorPlan(@RequestBody List<String> doorPlanIds, String stts) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		String action = stts.equals("1") ? "启用" : "关闭";
		Object obj = null;
		try {
			service.updateSttsByIds(doorPlanIds, stts);
			flag = true;
			obj = action + "成功";
		} catch (Exception e) {
			flag = false;
			obj = action + "异常";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

}
