package com.cesgroup.prison.xtgl.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.xtgl.service.DvcRoleService;

/**   
*      
* @ClassName：DvcRoleController   
* @Description：   设备权限
* @author：zhengk   
* @date：2018年1月17日 下午7:06:23        
*/
@Controller
@RequestMapping(value="/xtgl/dvcRole")
public class DvcRoleController extends BaseController{
	@Autowired
	private DvcRoleService service;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("/xtgl/dvcRole/index");
		return mv;
	}
	
	@RequestMapping(value="/searchExistDprtmnt")
	@ResponseBody
	@Logger(action = "查询关联设备部门信息", logger = "关联设备部门信息")
	public List<Map<String, Object>> searchExistDprtmnt(String ddrCusNumber,String ddrDvcTypeIndc){
		return service.searchExistDprtmnt(ddrCusNumber, ddrDvcTypeIndc);
	}
	
	/**
	* @methodName: batchInsert
	* @Description: 批量新增
	* @param list
	* @return AjaxMessage    
	* @throws
	*/
	@RequestMapping("/batchInsert")
	@ResponseBody
	public AjaxMessage batchInsert(@RequestBody List<Map<String,Object>> list){
		try {
			if(list.size()>0){
				this.service.batchInsert(list);
			}
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}

	}
	/**
	* @methodName: batchDelete
	* @Description: 批量删除
	* @param list
	* @return AjaxMessage    
	* @throws
	*/
	@RequestMapping("/batchDelete")
	@ResponseBody
	public AjaxMessage batchDelete(@RequestBody List<String> dprt_id){
		try {
			this.service.batchDelete(dprt_id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
		
	}
	@RequestMapping("/simpleCameraTreeByXML")
	@ResponseBody
	public List<Map<String,Object>> simpleCameraTreeByXML(String wid,String cusNumber,String dprtmntIdnty,String useLimit){
		return service.simpleCameraTreeByXML(wid, cusNumber, dprtmntIdnty, useLimit);
	}
	
	@RequestMapping("/simpleAreaCameraTreeByXML")
	@ResponseBody
	public List<Map<String,Object>> simpleAreaCameraTreeByXML(String wid,String cusNumber,String dprtmntIdnty,String cbdSttsIndc_except){
		long start_ms=new Date().getTime();
        List<Map<String, Object>> list= service.simpleAreaCameraTreeByXML(wid, cusNumber, dprtmntIdnty, cbdSttsIndc_except);
        long end_ms=new Date().getTime();
        System.out.println("----------------加载区域摄像头树用时:"+(end_ms-start_ms)+"毫秒");
        return list;
	}
	
	@RequestMapping("/simpleDepartmentTree")
	@ResponseBody
	public List<Map<String,Object>> simpleDepartmentTree(String cusNumber) throws Exception{
		return service.simpleDepartmentTree(cusNumber);
	}
}
