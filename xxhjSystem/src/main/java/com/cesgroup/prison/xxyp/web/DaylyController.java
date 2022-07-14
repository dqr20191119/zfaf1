package com.cesgroup.prison.xxyp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xxyp.dao.DaylyMapper;
import com.cesgroup.prison.xxyp.entity.Dayly;
import com.cesgroup.prison.xxyp.service.DaylyService;
import com.cesgroup.prison.xxyp.service.impl.DaylyServiceImpl;

@Controller
@RequestMapping(value="/xxyp/dayly")
public class DaylyController extends BaseEntityDaoServiceCRUDController<Dayly,String,DaylyMapper,DaylyServiceImpl>{
	@Autowired
	private DaylyService service;
	
	@RequestMapping("/prisonDayly")
	public ModelAndView prisonDayly(String cusNumber){
		ModelAndView mv = new ModelAndView("/xxyp/dayly/prisonDayly");
		mv.addObject("cusNumber", cusNumber);
		return mv;
	}
	@RequestMapping("/daylyLayout")
	public ModelAndView prisonDayly(){
		ModelAndView mv = new ModelAndView("/xxyp/dayly/daylyLayout");
		return mv;
	}

	@RequestMapping("/initDaylyData")
	@ResponseBody
	public AjaxMessage initDaylyData(String cusNumber){
		try {
			service.initDaylyData(cusNumber);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}

	}
	
	@RequestMapping("/getDaylyData")
	@ResponseBody
	public List<Map<String,Object>> getDaylyData(Dayly dayly){
		return service.getDaylyData(dayly);
	}
}
