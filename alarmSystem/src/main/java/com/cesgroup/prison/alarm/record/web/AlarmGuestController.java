package com.cesgroup.prison.alarm.record.web;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.service.AlarmService;

/**      
* @projectName：alarmSystem   
* @ClassName：AlarmHandleController   
* @Description：报警显示（非登陆） 
* @author：Taoxu   
* @date：2018年4月24日 上午10:14:37   
* @version        
*/
@Controller
@RequestMapping(value = "/alarm/guest")
public class AlarmGuestController extends BaseController {

	@Resource
	private AlarmService service;

	
	@RequestMapping("/showAlarmMsg")
	public ModelAndView index(String id) {
		ModelAndView mv = new ModelAndView("alarm/process/alarmMsg");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(AlarmRecordEntity entity) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				Map<String, Object> entityMap = service.findAlarmRecordById(entity);
				flag = true;
				result = entityMap;
			} else {
				flag = false;
				result = "查询失败 ";
			}
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
