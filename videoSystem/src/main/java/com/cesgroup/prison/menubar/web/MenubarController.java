package com.cesgroup.prison.menubar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.springmvc.web.BaseController;

@Controller
@RequestMapping(value = "/menubar")
public class MenubarController extends BaseController {
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("rightside/index");
		return mv;
	}

	@RequestMapping("/displayRight")
	public ModelAndView displayRight(String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		return mv;
	}

	//add by zk
	@RequestMapping("/displayRightAddParam")
	public ModelAndView displayRightAddParam(String viewName,String params) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		
		if(params != null){
			System.out.println(params);
			JSONObject reqJSON = JSON.parseObject(params);
			if(reqJSON.size()>0){
				for(String key:reqJSON.keySet()){
					mv.addObject(key,reqJSON.get(key));
				}
			}
		}
		return mv;
	}

	@RequestMapping("/policeInPrison")
	public ModelAndView policeInPrison() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/policeInPrison");
		return mv;
	}

	@RequestMapping("/proPoliceInPrison")
	public ModelAndView proPoliceInPrison() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/p_policeInPrison");
		return mv;
	}

	@RequestMapping("/prisonerToday")
	public ModelAndView prisonerToday() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/prisonerToday");
		return mv;
	}

	@RequestMapping("/proPrisonerToday")
	public ModelAndView proPrisonerToday() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/p_prisonerToday");
		return mv;
	}

	@RequestMapping("/proDefenseDevice")
	public ModelAndView proDefenseDevice() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/p_defenseDevice");
		return mv;
	}
	@RequestMapping("/defenseDevice")
	public ModelAndView defenseDevice() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/defenseDevice");
		return mv;
	}
	
	@RequestMapping("/proTechnicalDevice")
	public ModelAndView proTechnicalDevice() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/p_technicalDevice");
		return mv;
	}
	@RequestMapping("/technicalDevice")
	public ModelAndView technicalDevice() {
		ModelAndView mv = new ModelAndView("rightside/rightInfocenter/technicalDevice");
		return mv;
	}

	/**
	* @methodName: sporadicFlow
	* @Description: 右侧零星流动视图
	* @return ModelAndView
	* @throws 
	* @author：Tao.xu 
	* @date：2017年12月15日 上午11:33:42      
	*/
	@RequestMapping("/sporadicFlow")
	public ModelAndView sporadicFlow() {
		ModelAndView mv = new ModelAndView("rightside/sporadicFlow/rightSporadicFlow");
		return mv;
	}

	/**
	* @methodName: talkBackTree
	* @Description: 对讲信息树
	* @return ModelAndView
	* @throws
	* @author：Tao.xu 
	* @date：2017年12月21日 上午11:52:46      
	*/
	@RequestMapping("/talkBackTree")
	public ModelAndView talkBackTree() {
		ModelAndView mv = new ModelAndView("rightside/talkBack/rightTalkBackTree");
		return mv;
	}

	@RequestMapping("/alarmHandle")
	public ModelAndView alarmHandle() {
		ModelAndView mv = new ModelAndView("rightside/alarm/alarmHandle");
		return mv;
	}
}
