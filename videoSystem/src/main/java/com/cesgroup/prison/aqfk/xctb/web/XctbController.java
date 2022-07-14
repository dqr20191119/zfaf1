package com.cesgroup.prison.aqfk.xctb.web;

import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.aqfk.monitor.service.MonitorService;
import com.cesgroup.prison.aqfk.xctb.service.XtglService;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.inspect.entity.Inspect;
import com.cesgroup.prison.inspect.service.InspectService;

import dm.jdbc.driver.DmdbClob;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/xctb")
public class XctbController extends BaseController{
	@Autowired
	private XtglService xtglService;
	@Autowired
	private InspectService inspectService;
	@Autowired
	private MonitorService monitorService;
	
	@RequestMapping("/index")
	public ModelAndView index(String inspectId,String params){
		ModelAndView mv = new ModelAndView("xctb/index");
		mv.addObject("inspectId", inspectId);		
		Inspect inspect = inspectService.findById(inspectId);
		String cusNumber ="";
		String cusName ="";
		String inoInspectPhase ="";
		String inoInspectBj ="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"); 
		String inspectTime = sdf.format(new Date());  
		if(inspect != null) {
			cusNumber=inspect.getInoCusNumber();
			inoInspectPhase=inspect.getInoInspectPhase();
			inoInspectBj=inspect.getInoInspectBj();
			inspectTime = sdf.format(inspect.getInoInspectTime());
			try {
				List<OrgEntity> list =	AuthSystemFacade.getAllJyInfo();
				for (OrgEntity orgEntity : list) {
					if(cusNumber.equals(orgEntity.getOrgKey())) {
						cusName = orgEntity.getOrgName();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		mv.addObject("cusNumber", cusNumber);
		mv.addObject("cusName", cusName);
		mv.addObject("inoInspectPhase", inoInspectPhase);
		mv.addObject("inoInspectBj", inoInspectBj);
		mv.addObject("inspectTime", inspectTime);
		List<Map<String, String>> monitorIds = monitorService.searchMonitorIdsByInspectId(inspectId);
		List<Map<String,Object>> monitorList =xtglService.getMonitorList(monitorIds);
		mv.addObject("monitorList", monitorList);
		/*if(params != null){
			JSONArray jsonArray = JSONArray.fromObject(params);
			List<String> monitorIds = JSONArray.toList(jsonArray);
			List<Map<String,Object>> monitorList =xtglService.getMonitorList(monitorIds);
			mv.addObject("monitorList", monitorList);
		}*/
		return mv;
	}
	
	
	@RequestMapping("/show")
	public ModelAndView show(String inspectId){
		ModelAndView mv = new ModelAndView("xctb/show");
		String inspectDocument = inspectService.findInoInspectDocumentById(inspectId);
		mv.addObject("inspectDocument",inspectDocument);
		return mv;
	}
	
}
