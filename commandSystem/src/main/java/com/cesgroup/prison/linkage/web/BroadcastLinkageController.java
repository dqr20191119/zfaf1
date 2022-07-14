package com.cesgroup.prison.linkage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;

/**
 * 广播控制器
 * @author lincoln.cheng
 *
 */
@Controller
@RequestMapping(value = "/broadcastLinkage")
public class BroadcastLinkageController extends BaseController {
	/**
	 * 广播控制树跳转
	 * @return
	 */
	@RequestMapping(value = "/controlBroadcastTree")
	public ModelAndView controlBroadcastTree() {
		ModelAndView mv = new ModelAndView("broadcastCtrl/broadcast_control_tree");
		return mv;
	}

	/**
	 * 广播控制窗口打开
	 * @return
	 */
	@RequestMapping(value = "/controlBroadcastDialog")
	public ModelAndView controlBroadcastDialog(@RequestParam(value = "broadcastId", defaultValue = "", required = false) String broadcastId) {
		ModelAndView mv = new ModelAndView("broadcastCtrl/broadcast_control_dialog");
		mv.addObject("broadcastId", broadcastId);
		return mv;
	}
	
	/**
	 * 广播控制窗口打开
	 * @return
	 */
	@RequestMapping(value = "/controlMultiDialog")
	public ModelAndView controlMultiDialog(@RequestParam(value = "broadcastId", defaultValue = "", required = false) String broadcastId) {
		ModelAndView mv = new ModelAndView("broadcastCtrl/broadcast_multi_dialog");
		mv.addObject("broadcastId", broadcastId);
		return mv;
	}
	
}
