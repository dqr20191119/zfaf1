package com.cesgroup.prison.callNamesAl.web;

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
import com.cesgroup.prison.callNamesAl.dao.CallNamesMasterMapper;
import com.cesgroup.prison.callNamesAl.entity.CallNamesAreaDtlsEntity;
import com.cesgroup.prison.callNamesAl.entity.CallNamesMasterEntity;
import com.cesgroup.prison.callNamesAl.entity.CallNamesResultEntity;
import com.cesgroup.prison.callNamesAl.service.CallNamesMasterService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/callNames/master")
public class CallNamesMaseterController extends
		BaseEntityDaoServiceCRUDController<CallNamesMasterEntity, String, CallNamesMasterMapper, CallNamesMasterService> {

	@Resource
	private CallNamesMasterService service;

	@RequestMapping("/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("callNamesAl/master/index");
		return mv;
	}

	@RequestMapping("/openDialog/record")
	public ModelAndView openDialogRecord() {
		ModelAndView mv = new ModelAndView("callNamesAl/record/index");
		return mv;
	}

	@RequestMapping("/openDialog/record/detail")
	public ModelAndView openDialogRecord(String id) {
		ModelAndView mv = new ModelAndView("callNamesAl/record/detail");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping("/openDialog/record/zfInfo")
	public ModelAndView openDialogZfInfo(String isCalled, String nadId, String masterId) {
		ModelAndView mv = new ModelAndView("callNamesAl/record/zfInfo");
		mv.addObject("isCalled", isCalled);
		mv.addObject("nadId", nadId);
		mv.addObject("masterId", masterId);
		return mv;
	}

	@RequestMapping("/openDialog/JsZf")
	public ModelAndView openDialog(String jyh, String lch, String jsh) {
		ModelAndView mv = new ModelAndView("callNamesAl/master/JsZf");
		mv.addObject("jyh", jyh);
		mv.addObject("lch", lch);
		mv.addObject("jsh", jsh);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(CallNamesMasterEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/listAll/jsxq")
	@ResponseBody
	public Map<String, Object> listAllForJs(CallNamesAreaDtlsEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAllForJsInfo(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/listAll/zfxx")
	@ResponseBody
	public Map<String, Object> listAllForZf(CallNamesResultEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAllForZfInfo(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findForTree")
	@ResponseBody
	public List<Map<String, Object>> findForTree(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		try {
			list = service.findForTree(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/findJsAndZfsByLc")
	@ResponseBody
	public List<Map<String, Object>> findJsAndZfsByLc(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		try {
			list = service.findJsAndZfsByLc(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/beginCallNames")
	@ResponseBody
	public AjaxMessage beginCallNames(HttpServletRequest request, HttpServletResponse response) {

		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		String re = null;
		try {
			re = service.beginCallNames(request, response);
			if (re.indexOf("失败") == -1) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			re = "点名指令发送发生异常";
		}

		ajaxMessage.setMsg(re);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/isCallNamesIng")
	@ResponseBody
	public AjaxMessage isCallNamesIng(CallNamesMasterEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		int re = 2;
		try {
			if (entity != null) {
				re = service.isCallNamesIng(entity);
				flag = true;
			}
		} catch (Exception e) {
			flag = false;

		}
		ajaxMessage.setObj(re);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	* @methodName: getCallNamesDtlsByJs
	* @Description:  获得楼层所有点名监舍详细信息
	* @param data
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/getCallNamesDtlsByJs")
	@ResponseBody
	public AjaxMessage getCallNamesDtlsByJs(@RequestBody Map<String, Object> data) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			String jyh = (String) data.get("jyh");
			String lch = (String) data.get("lch");

			@SuppressWarnings("unchecked")
			List<String> jshs = (List<String>) data.get("jshs");
			obj = service.getCallNamesDtlsByJs(jyh, lch, jshs);
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

	@RequestMapping(value = "/getCallNamesPrisonerDtlsByJs")
	@ResponseBody
	public AjaxMessage getCallNamesPrisonerDtlsByJs(String jyh, String lch, String jsh) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.getCallNamesPrisonerDtlsByJs(jyh, lch, jsh);
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

	@RequestMapping(value = "/getCallNamesDtlsByLc")
	@ResponseBody
	public AjaxMessage getCallNamesDtlsByLc(String jyh, String lch) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.getCallNamesDtlsByLc(jyh, lch);
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

	@RequestMapping(value = "/getPrisonerInfoByJsFromCache")
	@ResponseBody
	public AjaxMessage getPrisonerInfoByJsFromCache(String jyh, String lch, String jsh) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.getPrisonerInfoByJsFromCache(jyh, lch, jsh);
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

	@RequestMapping(value = "/EndCallNames")
	@ResponseBody
	public AjaxMessage EndCallNames(@RequestBody Map<String, Object> data) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			String jyh = (String) data.get("jyh");
			String lch = (String) data.get("lch");

			@SuppressWarnings("unchecked")
			List<String> jshs = (List<String>) data.get("jshs");
			obj = service.EndCallNames(jyh, lch, jshs);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "结束点名指令发送发生异常";
		}

		ajaxMessage.setMsg((String) obj);

		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
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
	public AjaxMessage update(CallNamesMasterEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			obj = "操作成功";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
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
			obj = "操作成功";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/saveInfo")
	public AjaxMessage inster(CallNamesMasterEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.addInfo(entity);
			flag = true;
			obj = "操作成功";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
		}

		ajaxMessage.setMsg((String) obj);

		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

}
