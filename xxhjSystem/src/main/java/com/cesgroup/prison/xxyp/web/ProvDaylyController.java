package com.cesgroup.prison.xxyp.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xxyp.dao.ProvDaylyMapper;
import com.cesgroup.prison.xxyp.entity.ProvDayly;
import com.cesgroup.prison.xxyp.service.ProvDaylyService;
import com.cesgroup.prison.xxyp.service.impl.ProvDaylyServiceImpl;

@Controller
@RequestMapping(value="/xxyp/provDayly")
public class ProvDaylyController extends BaseEntityDaoServiceCRUDController<ProvDayly,String,ProvDaylyMapper,ProvDaylyServiceImpl>{
	@Autowired
	private ProvDaylyService service;
	
	@RequestMapping("/provinceDayly")
	public ModelAndView provinceDayly(){
		ModelAndView mv = new ModelAndView("/xxyp/dayly/provinceDayly");
		return mv;
	}
	@RequestMapping("/initProvDaylyData")
	@ResponseBody
	public AjaxMessage initProvDaylyData(){
		try {
			service.initProvDaylyData();
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}

	}
	@RequestMapping("/getProvDaylyData")
	@ResponseBody
	public List<Map<String,Object>> getProvDaylyData(ProvDayly provDayly){
		
		List<Map<String,Object>>   prisonBaseInfoList = null;
		
		prisonBaseInfoList  = service.getProvDaylyData(provDayly);
		
		return  prisonBaseInfoList ; 
	}
}
