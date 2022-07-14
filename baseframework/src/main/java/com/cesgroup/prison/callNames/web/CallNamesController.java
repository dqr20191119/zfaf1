package com.cesgroup.prison.callNames.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.callNames.dao.CallNamesMapper;
import com.cesgroup.prison.callNames.entity.CallNamesDoneEntity;
import com.cesgroup.prison.callNames.entity.CallNamesRecordEntity;
import com.cesgroup.prison.callNames.entity.CallNamesUndoneEntity;
import com.cesgroup.prison.callNames.service.CallNamesService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/callNames")
public class CallNamesController
		extends BaseEntityDaoServiceCRUDController<CallNamesRecordEntity, String, CallNamesMapper, CallNamesService> {

	@Resource
	private CallNamesService callNamesService;

	@RequestMapping("/openDialog/rollcall")
	public ModelAndView openRollcallDialog(HttpServletRequest request, HttpServletResponse response) {
		String demptId = request.getParameter("demptId");
		String rollcallId = request.getParameter("rollcallId");
		String floorId = request.getParameter("floorId");
		String cellId = request.getParameter("cellId");
		ModelAndView mv = new ModelAndView("callNames/rollcallList");
		mv.addObject("demptId", demptId);
		mv.addObject("rollcallId", rollcallId);
		mv.addObject("floorId", floorId);
		mv.addObject("cellId", cellId);
		return mv;
	}

	@RequestMapping("/openDialog/index")
	public ModelAndView openDialog(String id) {
		ModelAndView mv = new ModelAndView("callNames/index");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping("/openDialog/fqdm")
	public ModelAndView openListDialog() {
		ModelAndView mv = new ModelAndView("callNames/list");
		return mv;
	}

	@RequestMapping("/openDialog/dmls")
	public ModelAndView openListHDialog() {
		ModelAndView mv = new ModelAndView("callNames/list_h");
		return mv;
	}

	@RequestMapping("/openDialog/dmls/done")
	public ModelAndView openListHDoneDialog(String recordId) {
		ModelAndView mv = new ModelAndView("callNames/done_h");
		mv.addObject("recordId", recordId);
		return mv;
	}

	@RequestMapping("/openDialog/dmls/undone")
	public ModelAndView openListHUndoneDialog(String recordId) {
		ModelAndView mv = new ModelAndView("callNames/undone_h");
		mv.addObject("recordId", recordId);
		return mv;
	}

	@RequestMapping("/openDialog/add")
	public ModelAndView openAddDialog() {
		ModelAndView mv = new ModelAndView("callNames/add");
		return mv;
	}

	/**
	* @methodName: findPrisonerNumForCallNames
	* @Description:  
	* @param para 树节点
	* 			  1、监狱节点 ，查询监狱各监区对应人数 
	* 			  2、监区节点 ，查询当前监区对应人数
	* 			  3、监舍节点，查询各楼层对应监舍人数
	* @param response
	* @return
	* @throws Exception List<Map<String,Object>>
	* @throws  
	*/
	@RequestMapping(value = "/findPrisonerNum")
	@ResponseBody
	public List<Map<String, Object>> findPrisonerNumForCallNames(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("para", request.getParameter("para"));
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱号
		paramMap.put("dempt", request.getParameter("dempt"));// 部门id
		paramMap.put("area", request.getParameter("area"));// 区域编码
		return callNamesService.findPrisonerNumForCallNames(paramMap);
	}

	/**
	* @methodName: getJSPrisonerInfo
	* @Description: 获取监舍罪犯信息
	* @param request
	* @param response
	* @return
	* @throws Exception List<Map<String,Object>>
	* @throws  
	*/
	@RequestMapping(value = "/getJSPrisonerInfo")
	@ResponseBody
	public AjaxMessage getJSPrisonerInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			List<Map<String, Object>> map = callNamesService.getJSPrisonerInfo(request, response);
			if (map.isEmpty()) {
				flag = false;
				obj = "未查询到罪犯";
			} else {
				flag = true;
				obj = map;
			}
		} catch (Exception e) {
			flag = false;
			obj = "获取监舍罪犯信息发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/findForTree")
	@ResponseBody
	public List<Map<String, Object>> findForTree(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return callNamesService.findForTree(request, response);
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(CallNamesRecordEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/listAllForDone")
	@ResponseBody
	public Map<String, Object> listAllForDone(CallNamesDoneEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAllForDone(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/listAllForUndone")
	@ResponseBody
	public Map<String, Object> listAllForUndone(CallNamesUndoneEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAllForUndone(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	/**
	* @methodName: beginRollcall
	* @Description: 发起点名
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@ResponseBody
	@RequestMapping(value = "/beginRollcall")
	public AjaxMessage beginRollcall(CallNamesRecordEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			Map<String, Object> map = service.beginRollcall(entity);
			if ((int) map.get("status") == 0) {
				flag = true;
			} else {
				flag = false;
			}
			obj = "发起点名:" + map.get("message");
		} catch (Exception e) {
			flag = false;
			obj = "发起点名发生异常";
		}
		ajaxMessage.setSuccess(flag);
		ajaxMessage.setMsg((String) obj);
		return ajaxMessage;
	}

	/**
	* @methodName: getNumber
	* @Description: 点名过程中查询
	* @return AjaxMessage
	* @throws  
	*/
	@ResponseBody
	@RequestMapping(value = "/getNumber")
	public AjaxMessage getNumber(HttpServletRequest request, HttpServletResponse response) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			String demptId = request.getParameter("demptId");
			String rollcallId = request.getParameter("rollcallId");
			Map<String, Object> map = service.getNumber(rollcallId, demptId);
			if ((int) map.get("status") == 0) {
				flag = true;
				obj = map.get("result");
			} else {
				flag = false;
				obj = "点名过程查询:" + map.get("message");
			}
		} catch (Exception e) {
			flag = false;
			obj = "点名过程查询发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	* @methodName: getEndRollcallList
	* @Description: 点名详情查询
	* @param request
	* @param response
	* @return AjaxMessage
	* @throws  
	*/
	@ResponseBody
	@RequestMapping(value = "/getEndRollcallList")
	public AjaxMessage getEndRollcallList(HttpServletRequest request, HttpServletResponse response) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			String demptId = request.getParameter("demptId");
			String rollcallId = request.getParameter("rollcallId");
			String floorId = request.getParameter("floorId");
			String cellId = request.getParameter("cellId");
			Map<String, Object> map = service.getEndRollcallList(rollcallId, demptId, floorId, cellId);
			if ((int) map.get("status") == 0) {
				flag = true;
				obj = map.get("result");
			} else {
				flag = false;
				obj = "点名详情查询:" + map.get("message");
			}
		} catch (Exception e) {
			flag = false;
			obj = "点名详情查询发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	* @methodName: endIngRollcall
	* @Description: 结束点名
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@ResponseBody
	@RequestMapping(value = "/endIngRollcall")
	public AjaxMessage endIngRollcall(CallNamesRecordEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			Map<String, Object> map = service.endIngRollcall(entity);
			if ((int) map.get("status") == 0) {
				flag = true;
				obj = "结束点名:" + map.get("message");
			} else {
				flag = false;
				obj = "结束点名:" + map.get("message");
			}
		} catch (Exception e) {
			flag = false;
			obj = "结束点名发生异常";
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
	@RequestMapping(value = "/saveEndRollcallList")
	public AjaxMessage saveEndRollcallList(HttpServletRequest request, HttpServletResponse response) {
		String rollcallId = request.getParameter("rollcallId");
		return service.saveEndRollcallList(rollcallId);
	}

}
