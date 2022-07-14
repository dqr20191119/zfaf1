package com.cesgroup.prison.inspect.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.cesgroup.prison.inspect.dao.InspectLocalMapper;
import com.cesgroup.prison.inspect.entity.InspectLocal;
import com.cesgroup.prison.inspect.entity.InspectLocalRelation;
import com.cesgroup.prison.inspect.service.InspectLocalService;
import com.cesgroup.prison.utils.DataUtils;
/**
 * 现场督查通报
 * @author zhengk
 * @date 2018-03-23
 *
 */
@Controller
@RequestMapping(value = "/inspectlocal")
public class InspectLocalController extends  BaseEntityDaoServiceCRUDController<InspectLocal, String, InspectLocalMapper,InspectLocalService> {
	
	@Autowired
	private InspectLocalService inspectLocalService;
	
	@RequestMapping("/editDialog")
	public ModelAndView editPage(){
		ModelAndView mv = new ModelAndView("inspect/localedit");
		return mv;
	}
	
	@RequestMapping("/recordDialog")
	public ModelAndView listPage(){
		ModelAndView mv = new ModelAndView("inspect/localrecord");
		return mv;
	}
	
	@RequestMapping("/checkdetailDialog")
	public ModelAndView checkdetail(InspectLocal ins){
		ModelAndView mv = new ModelAndView("inspect/localcheckdetail");
		List<InspectLocal> isList = inspectLocalService.inspectlocallist(ins);
		if(isList!=null && isList.size()>0){
			mv.addObject("inspectLocal", isList.get(0));
		}
		return mv;
	}
	
	@RequestMapping("/inspectdetailDialog")
	public ModelAndView inspectdetail(InspectLocal ins){
		ModelAndView mv = new ModelAndView("inspect/localinspectdetail");
		List<InspectLocal> isList = inspectLocalService.inspectlocallist(ins);
		if(isList!=null && isList.size()>0){
			mv.addObject("inspectLocal", isList.get(0));
		}
		return mv;
	}
	
	@RequestMapping("/inspectdetailUpdateDialog")
	public ModelAndView inspectdetailUpdate(InspectLocal ins){
		ModelAndView mv = new ModelAndView("inspect/inspectdetailUpdate");
		mv.addObject("id", ins.getId());
		return mv;
	}
	
	@RequestMapping("/checkDialog")
	public ModelAndView checkPage(){
		ModelAndView mv = new ModelAndView("inspect/localcheck");
		return mv;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	@Logger(action = "添加方法", logger = "本地督察通报登记上报")
	public AjaxMessage add(InspectLocal ins){
		try {
			inspectLocalService.addInspectInfo(ins);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}

	@RequestMapping("/inspectlocallistPage")
	@ResponseBody
	@Logger(action = "查询待审批列表", logger = "查询待审批列表")
	public Map<String, Object> inspectlocallistPage(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("checkPoliceIdnty", request.getParameter("checkPoliceIdnty"));
		paramMap.put("startTime", request.getParameter("startTime"));								
		paramMap.put("endTime", request.getParameter("endTime"));		
		PageRequest pageRequest = buildPageRequest();
		Page<InspectLocal> page = null;
		try {
			page = (Page<InspectLocal>) inspectLocalService.inspectlocallistPage(paramMap,pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  DataUtils.pageToMap(page);
	}
	@RequestMapping(value="/inspectlocalHzListPage")
	@ResponseBody
	@Logger(action = "督查通报汇总信息", logger = "督查通报汇总列表")
	public Map<String, Object> inspectlocalHzListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));	
		paramMap.put("inlCrteUserId", request.getParameter("inlCrteUserId"));	
		paramMap.put("inlCusNumber", request.getParameter("inlCusNumber"));	
		PageRequest pageRequest=buildPageRequest();
		Page<InspectLocal> page=service.inspectlocalHzListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping("/inspectLocalUpdate")
	@ResponseBody
	@Logger(action = "更新方法", logger = "根据条件更新督察通报记录")
	public AjaxMessage inspectLocalUpdate(InspectLocal ins) {
		 try {
			 service.updateInspectLocal(ins);
			 return new AjaxMessage(true);
			} catch (Exception e) {
				return new AjaxMessage(false,null,e.getMessage());
			}
	}
	
	@RequestMapping(value="/inspectLocalRelationListPage")
	@ResponseBody
	public Map<String, Object> inspectLocalRelationListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();	
		paramMap.put("inrTypeIndc", (String)request.getParameter("inrTypeIndc"));	
		paramMap.put("inrInspectId", (String)request.getParameter("inrInspectId"));	
		PageRequest pageRequest=buildPageRequest();
		Page<InspectLocalRelation> page=service.inspectLocalRelationListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
}
