package com.cesgroup.prison.alarm.handle.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.prison.alarm.record.param.AlarmRecordParam;
import com.cesgroup.prison.alarm.record.result.AlarmRecordResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.alarm.handle.service.AlarmHandleService;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;

/**      
* @projectName：alarmSystem   
* @ClassName：AlarmHandleController   
* @Description：报警处置   
* @author：Taoxu   
* @date：2018年4月24日 上午10:14:37   
* @version        
*/
@Controller
@RequestMapping(value = "/alarm/handle")
public class AlarmHandleController extends BaseController {

	@Resource
	private AlarmHandleService service;

	/**
	* @methodName: index
	* @Description: 右侧栏报警处理相关
	* @param type 0： 需要传id  1：人工报警 不需要报警记录
	* @param id 记录id
	* @param isList 是否从报警列表进入报警处置界面     0、 否    1、 是  (不传默认 0)
	* @return ModelAndView
	* @throws  
	*/
	@RequestMapping("/index")
	public ModelAndView index(String type, String id, String isList) {
		ModelAndView mv = new ModelAndView("alarm/process/index");
		mv.addObject("type", type);
		mv.addObject("id", id);
		mv.addObject("isList", StringUtils.isEmpty(isList) ? "0" : isList);
		return mv;
	}

	/**
	* @methodName: receiveAlarm
	* @Description: 接警处置
	* @param id
	* @return ModelAndView
	* @throws  
	*/
	@RequestMapping("/openDialog/receive")
	public ModelAndView receiveAlarm(String id) {
		ModelAndView mv = new ModelAndView("alarm/process/receiveAlarm");
		mv.addObject("ID", id);
		return mv;
	}

	/**
	* @methodName: manualAlarm
	* @Description: 人工报警插入报警记录
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping("manualAlarm")
	@ResponseBody
	public AjaxMessage manualAlarm(AlarmRecordEntity entity) {
		boolean flag = false;
		String msg = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if (entity != null) {
				service.manualAlarm(entity);
				msg = "人工报警成功";
				flag = true;
			} else {
				flag = false;
				msg = "人工报警：传参错误";
			}

		} catch (Exception e) {
			flag = false;
			msg = "人工报警：发生异常";
		}

		ajaxMsg.setMsg(msg);
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}


	/**
	 * @methodName: manualAlarm
	 * @Description: 人工报警插入报警记录
	 * @param entity
	 * @return AjaxMessage
	 * @throws
	 */
	@RequestMapping(value = "manualAlarmApi")
	@ResponseBody
	public AlarmRecordResult manualAlarmApi(AlarmRecordParam entity) {
		AlarmRecordResult alarmRecordResult = new  AlarmRecordResult();
		try {
			alarmRecordResult = service.manualAlarmApi(entity);
		} catch (Exception e) {
			alarmRecordResult.setCode(-1);
			alarmRecordResult.setMsg("error");
		}
		return alarmRecordResult;
	}
	@RequestMapping(value = "/receive")
	@ResponseBody
	@Logger(action = "接警", logger = "接警处置")
	public AjaxMessage receive(AlarmRecordEntity entity, String subType, String flag, String userId, String userName,
			String cusNumber) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean success = false;
		String res = null;
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				String message = service.receive(entity, subType, flag, userId, userName, cusNumber);
				success = true;
				res = message;
			} else {
				success = false;
				res = "处置失败： 参数异常";
			}
		} catch (Exception e) {
			success = false;
			res = "处置失败： " + e.getMessage();
		}

		ajaxMessage.setMsg(res);
		ajaxMessage.setSuccess(success);
		return ajaxMessage;
	}

	/**
	* @methodName: cancelTheAlarm
	* @Description: 取消报警
	* @param entity
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/cancel")
	@ResponseBody
	public AjaxMessage cancelTheAlarm(AlarmRecordEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		String res = null;
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				flag = true;
				res = service.cancelTheAlarm(entity);
			} else {
				flag = false;
				res = "取消报警失败： 参数异常";
			}
		} catch (Exception e) {
			flag = false;
			res = "取消报警失败： " + e.getMessage();
		}

		ajaxMessage.setMsg(res);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	* @methodName: ctrlDevice
	* @Description:  报警预案关联的设备控制
	* @param request
	* @param response
	* @return AjaxMessage
	* @throws  
	*/
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/ctrlDevice")
	public AjaxMessage ctrlDevice(HttpServletRequest request, HttpServletResponse response) {
		List<String> deviceIds = (List<String>) (JSON.parse(request.getParameter("deviceIds")));
		List<String> cameraIds = (List<String>) (JSON.parse(request.getParameter("cameraIds")));
		String action = request.getParameter("action");// 门禁操作动作
		String deviceType = request.getParameter("deviceType"); // 设备类型
		String pcIp = IpUtil.currentRemoteIp(request);// 当前用户的IP
		String cusNumber = request.getParameter("cusNumber");// 从前台传入
		String alarmAddress = request.getParameter("alarmAddress");// 报警地点 ，大屏投屏时使用，从前台传入

		return service.ctrlDevice(deviceType, cusNumber, deviceIds, action, pcIp, cameraIds, alarmAddress);
	}

	/**
	* @methodName: queryAlarmPlanDtls
	* @Description: 查询报警预案关联项及关联设备信息  
	* @param cusNumber 监狱号
	* @param dvcIdnty   报警器编号
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/queryAlarmPlanDtls")
	@ResponseBody
	public AjaxMessage queryAlarmPlanDtls(String cusNumber, String dvcIdnty, String alarmPlanId) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			Map<String, Object> reMap = service.queryAlarmPlanDtls(cusNumber, dvcIdnty, alarmPlanId);
			flag = true;
			obj = reMap;
		} catch (Exception e) {
			flag = false;
			obj = "查询报警预案关联项及关联设备信息发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/judge/UserAndDempt")
	@ResponseBody
	public AjaxMessage judgeUserAndDemptCorrespondingByAlertorIdnty(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		String msg = null;
		try {
			String cusNumber = request.getParameter("cusNumber");
			String userId = request.getParameter("userId");
			String alertorIdnty = request.getParameter("alertorIdnty");
			obj = service.judgeUserAndDemptCorrespondingByAlertorIdnty(cusNumber, userId, alertorIdnty);
			flag = true;
		} catch (Exception e) {
			flag = false;
			msg = "判断用户部门是否对应，发生异常";
		}

		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg(msg);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/getAlarmSound")
	@ResponseBody
	public Object getAlarmSound(HttpServletRequest request, HttpServletResponse response) {
		try {
			service.getAlarmSound(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
