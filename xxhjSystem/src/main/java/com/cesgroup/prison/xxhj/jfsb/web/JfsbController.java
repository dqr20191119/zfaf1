package com.cesgroup.prison.xxhj.jfsb.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.jfsb.dao.JfsbMapper;
import com.cesgroup.prison.xxhj.jfsb.entity.Jfsb;
import com.cesgroup.prison.xxhj.jfsb.service.JfsbService;
import com.cesgroup.prison.xxhj.jfsb.service.JfsbServiceImpl;

/**
 * 技防设备
 *
 */
@Controller
@RequestMapping(value = "/xxhj/jfsb")
public class JfsbController extends BaseEntityDaoServiceCRUDController<Jfsb, String, JfsbMapper, JfsbServiceImpl> {

	@Resource
	private JfsbService jfsbService;
	
	@RequestMapping("/pjfsb")
	public ModelAndView pjfsb() {
		
		ModelAndView mv = new ModelAndView("xxhj/jfsb/pjfsb");
		return mv;
	}
	
	@RequestMapping("/jfsb")
	public ModelAndView jfsb(HttpServletRequest request) throws UnsupportedEncodingException {
		
		ModelAndView mv = new ModelAndView("xxhj/jfsb/jfsb");
		
		if(request.getParameter("prisonName") != null && request.getParameter("prisonId") != null) {
			mv.addObject("prisonName", URLDecoder.decode(request.getParameter("prisonName"), "utf-8") );
			mv.addObject("prisonId", request.getParameter("prisonId"));
	    }
		return mv;
	}
	
	/**
	 * 设备信息详情窗口
	 */
	@RequestMapping("/technicalDeviceInfo")
	public ModelAndView technicalDeviceInfo(String cusNumber, String deviceType, String deviceId) {
		
		ModelAndView mv = new ModelAndView("xxhj/jfsb/technicalDeviceInfo");
		mv.addObject("cusNumber", cusNumber);
		mv.addObject("deviceType", deviceType);
		mv.addObject("deviceId", deviceId);
		
		return mv;
	}
	/**
	 * 设备列表窗口
	 */
	 @RequestMapping(value ="/technicalDeviceList")
	 @ResponseBody
   	public ModelAndView technicalDeviceList(String cusNumber, String sttsIndc, String useLimit, String sql) {
   		
   		ModelAndView mv = new ModelAndView("xxhj/jfsb/technicalDeviceList");
		mv.addObject("cusNumber",cusNumber);
		mv.addObject("sttsIndc",sttsIndc);
		mv.addObject("useLimit",useLimit);
		mv.addObject("sql",sql);
		return mv;
   	}
	
	@RequestMapping(value = "/listDeviceMasterInfo")
	@ResponseBody
	public List<Map<String, String>> listDeviceMasterInfo(String cusNumber, String typeIndc) {

		List<Map<String, String>> list = jfsbService.listDeviceMasterInfo(cusNumber, typeIndc);
		return list;
	}
	
	@RequestMapping(value = "/listTechnicalDevicePrisonList")
	@ResponseBody
	public List<Map<String, Object>> listTechnicalDevicePrisonList(HttpServletRequest request) throws Exception {
		 
		List<Map<String, Object>> list = jfsbService.listTechnicalDevicePrisonList(request);
		return list;
	}
	
	@RequestMapping(value = "/listOnePrisonCameraInfo")
	@ResponseBody
	public Map<String, Object> listOnePrisonCameraInfo(HttpServletRequest request) throws Exception {
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pages = jfsbService.listOnePrisonCameraInfo(request,pageRequest);
		return  DataUtils.pageToMap(pages);
	}
	
	@RequestMapping(value = "/listOnePrisonAlertorInfo")
	@ResponseBody
	public Map<String,Object> listOnePrisonAlertorInfo(HttpServletRequest request) throws Exception {
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pages = jfsbService.listOnePrisonAlertorInfo(request,pageRequest);
		return  DataUtils.pageToMap(pages);
	}
	
	@RequestMapping(value = "/listOnePrisonInfraredInfo")
	@ResponseBody
 	public Map<String,Object> listOnePrisonInfraredInfo(HttpServletRequest request) throws Exception {
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pages  = jfsbService.listOnePrisonInfraredInfo(request,pageRequest);
		return  DataUtils.pageToMap(pages);
	}
	
	@RequestMapping(value = "/listOnePrisonPowerNetworkInfo")
	@ResponseBody
 	public Map<String,Object> listOnePrisonPowerNetworkInfo(HttpServletRequest request) throws Exception {
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pages = jfsbService.listOnePrisonPowerNetworkInfo(request,pageRequest);
		return  DataUtils.pageToMap(pages);
	}
	
	@RequestMapping(value = "/listOnePrisonDoorInfo")
	@ResponseBody
 	public Map<String,Object> listOnePrisonDoorInfo(HttpServletRequest request) throws Exception {
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pages = jfsbService.listOnePrisonDoorInfo(request,pageRequest);
		return  DataUtils.pageToMap(pages);
	}
	
	@RequestMapping(value = "/listOnePrisonSnakeInfo")
	@ResponseBody
 	public Map<String,Object> listOnePrisonSnakeInfo(HttpServletRequest request) throws Exception {
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pages = jfsbService.listOnePrisonSnakeInfo(request,pageRequest);
		return  DataUtils.pageToMap(pages);
	}
	
	//二十条数据
	@RequestMapping(value = "/listOnePrisonDevicePart")
	@ResponseBody
	public List<Map<String, Object>> listOnePrisonDevicePart(HttpServletRequest request) {
		
		List<Map<String, Object>> list = jfsbService.listOnePrisonDevicePart(request);
		return  list;
	}
	
	@RequestMapping(value = "/listSysDeviceInfo")
	@ResponseBody
 	public List<Map<String,Object>> listDeviceInfo(HttpServletRequest request) {
		
		List<Map<String, Object>> list = jfsbService.listSysDeviceInfo(request);
		return list;
	}

    @RequestMapping(value ="/listdeviceMaintainRecord")
	@ResponseBody
	public Map<String, Object> listdeviceMaintainRecord(String dmrCusNumber, String dmrDeviceType, String dmrDeviceIdnty, Pageable page) {
    	
 		Page<Map<String, Object>> pages = jfsbService.listdeviceMaintainRecord(dmrCusNumber,dmrDeviceType,dmrDeviceIdnty,page);
		return  DataUtils.pageToMap(pages);
	}
	
   
}
