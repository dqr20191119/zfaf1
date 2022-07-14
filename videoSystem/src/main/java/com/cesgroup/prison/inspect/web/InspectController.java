package com.cesgroup.prison.inspect.web;

import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.inspect.dao.InspectMapper;
import com.cesgroup.prison.inspect.entity.Inspect;
import com.cesgroup.prison.inspect.service.InspectService;
import com.cesgroup.prison.utils.DataUtils;

import dm.jdbc.driver.DmdbClob;
/**
 * 网络督查通报
 * @author zhengk
 * @date 2018-03-24
 *
 */
@Controller
@RequestMapping(value = "/inspect")
public class InspectController extends  BaseEntityDaoServiceCRUDController<Inspect, String, InspectMapper,InspectService> {
	
	@Autowired
	private InspectService inspectService;
	
	@RequestMapping("/editDialog")
	public ModelAndView editPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("inspect/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping("/recordDialog")
	public ModelAndView recordDialog(){
		ModelAndView mv = new ModelAndView("inspect/record");
		return mv;
	}
	
	@RequestMapping("/inspectListDialog")
	public ModelAndView inspectListDialog(){
		ModelAndView mv = new ModelAndView("inspect/inspectList");
		return mv;
	}
	
	@RequestMapping("/checkdetailDialog")
	public ModelAndView checkdetail(Inspect ins){
		ModelAndView mv = new ModelAndView("inspect/checkdetail");
		List<Inspect> isList = inspectService.inspectlist(ins);
		if(isList!=null && isList.size()>0){
			mv.addObject("inspect", isList.get(0));
		}
		return mv;
	}
	
	@RequestMapping("/inspectdetailDialog")
	public ModelAndView inspectdetail(Inspect ins){
		ModelAndView mv = new ModelAndView("inspect/inspectdetail");
		List<Inspect> isList = inspectService.inspectlist(ins);
		if(isList!=null && isList.size()>0){
			mv.addObject("inspect", isList.get(0));
		}
		return mv;
	}
	
	@RequestMapping("/inspectdetailUpdateDialog")
	public ModelAndView inspectdetailUpdate(Inspect ins){
		ModelAndView mv = new ModelAndView("inspect/inspectdetailUpdate");
		mv.addObject("id", ins.getId());
		return mv;
	}
	
	@RequestMapping("/checkDialog")
	public ModelAndView checkPage(){
		ModelAndView mv = new ModelAndView("inspect/check");
		return mv;
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	@Logger(action = "添加方法", logger = "网络督察通报登记上报")
	public AjaxMessage saveOrUpdate(Inspect ins){
		try {
			inspectService.addInspectInfo(ins);
			return new AjaxMessage(true,ins.getId());
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	/*查询本监狱发出的所有巡查通报列表*/
	@RequestMapping(value="/inspectListPage")
	@ResponseBody
	@Logger(action = "督查通报汇总信息", logger = "督查通报汇总列表")
	public Map<String, Object> inspectListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));		
		paramMap.put("inoCusNumber", request.getParameter("inoCusNumber"));	
		PageRequest pageRequest=buildPageRequest();
		Page<Inspect> page=service.inspectListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping("/inspectSpListPage")
	@ResponseBody
	@Logger(action = "查询待审批列表", logger = "查询待审批列表")
	public Map<String, Object> inspectSpListPage(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("checkPoliceIdnty", request.getParameter("checkPoliceIdnty"));
		paramMap.put("startTime", request.getParameter("startTime"));								// 节点id
		paramMap.put("endTime", request.getParameter("endTime"));		
		PageRequest pageRequest = buildPageRequest();
		Page<Inspect> page = null;
		try {
			page = (Page<Inspect>) inspectService.inspectSpListPage(paramMap,pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  DataUtils.pageToMap(page);
	}
	/*查询inoNoticeCusNumber通报监狱为本监狱的，并且审核已通过的*/
	@RequestMapping(value="/inspectHzListPage")
	@ResponseBody
	@Logger(action = "督查通报汇总信息", logger = "督查通报汇总列表")
	public Map<String, Object> inspectHzListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));		
		paramMap.put("inoNoticeCusNumber", request.getParameter("inoNoticeCusNumber"));	
		PageRequest pageRequest=buildPageRequest();
		Page<Inspect> page=service.inspectHzListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping("/inspectUpdate")
	@ResponseBody
	@Logger(action = "更新方法", logger = "根据条件更新督察通报记录")
	public AjaxMessage inspectUpdate(Inspect ins) {
		 try {
			 inspectService.updateInspect(ins);
			 return new AjaxMessage(true);
			} catch (Exception e) {
				return new AjaxMessage(false,null,e.getMessage());
			}
	}
	@RequestMapping("/inspectDelete")
	@ResponseBody
	public AjaxMessage inspectDelete(String id) {
		 try {
			 inspectService.inspectDelete(id);
			 return new AjaxMessage(true);
			} catch (Exception e) {
				return new AjaxMessage(false,null,e.getMessage());
			}
	}
	//创建巡查通报文档
	@RequestMapping("/createInspectFile")
	@ResponseBody
	public AjaxMessage createInspectFile(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 Map<String, Object> paramMap = new HashMap<String, Object>();
			 paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		 	paramMap.put("inspectId", request.getParameter("inspectId"));				// 巡查通报id
			paramMap.put("inspectHTML", request.getParameter("inspectHTML"));			// 巡查通报内容
			
			 inspectService.createInspectFile(paramMap);
			 return new AjaxMessage(true);
			} catch (Exception e) {
				return new AjaxMessage(false,null,e.getMessage());
			}
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	@Logger(action = "查询方法", logger = "根据条件查询督察通报记录")
	public Inspect findById(Inspect ins) {
		Inspect spect = null;
		 try {
			 List<Inspect> isList = inspectService.inspectlist(ins);
			 if(isList!=null && isList.size()>0){
				 spect = isList.get(0);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		return spect;
	}

	@RequestMapping("/findInoInspectDocumentById")
	@ResponseBody
	public String findInoInspectDocumentById(String id) {
		return inspectService.findInoInspectDocumentById(id);
	}
}
