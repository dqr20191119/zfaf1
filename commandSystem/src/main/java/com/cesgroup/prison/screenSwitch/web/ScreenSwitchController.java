package com.cesgroup.prison.screenSwitch.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.screen.service.ScreenPlanService;
import com.cesgroup.prison.screenSwitch.service.ScreenSwitchService;

/**      
* @projectName：baseframework   
* @ClassName：ScreenSwitchController   
* @Description：   大屏预案切换
* @author：Tao.xu   
* @date：2018年1月22日 下午3:13:04   
* @version        
*/
@Controller
@RequestMapping(value = "/screenSwitch")
public class ScreenSwitchController extends BaseController {

	@Resource
	private ScreenSwitchService service;

	@Resource
	private ScreenPlanService screenPlanService;

	/**
	 * 发送预案消息
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/startScreenSwitch")
	public AjaxMessage startScreenSwitch(HttpServletRequest request) {
		String screenId = request.getParameter("screenId");
		String type = request.getParameter("type");
		String alarmAddress = request.getParameter("alarmAddress");
		List<String> cameraIds = (List<String>) JSON.parse(request.getParameter("cameraIds"));
		return service.startScreenSwitch(screenId, type, cameraIds, alarmAddress);
	}

	@RequestMapping("/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("screenSwitch/screenSwitch");
		return mv;
	}
}
