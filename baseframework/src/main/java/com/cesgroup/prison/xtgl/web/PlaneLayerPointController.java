package com.cesgroup.prison.xtgl.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.support.NoModel;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xtgl.dao.PlaneLayerPointMapper;
import com.cesgroup.prison.xtgl.entity.PlaneLayerPoint;
import com.cesgroup.prison.xtgl.service.PlaneLayerPointService;
import com.cesgroup.prison.xtgl.service.impl.PlaneLayerPointServiceImpl;

@Controller
@RequestMapping(value="/xtgl/planeLayerPoint")
public class PlaneLayerPointController extends BaseEntityDaoServiceCRUDController<PlaneLayerPoint,String,PlaneLayerPointMapper,PlaneLayerPointServiceImpl>{

	@Autowired
	private PlaneLayerPointService service;
	
	@Logger(action = "添加", logger = "${id}")
    @RequestMapping(value = "/insert")
	@ResponseBody
    public AjaxMessage insert(@NoModel final PlaneLayerPoint model){
		try {
			PlaneLayerPoint planeLayerPoint=getService().insert(model);
			return new AjaxMessage(true,planeLayerPoint.getId());
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
	
	@Logger(action = "删除", logger = "${id}")
    @RequestMapping(value = "/delete")
	@ResponseBody
    public AjaxMessage delete(final String id){
		try {
			getService().delete(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
	
	@RequestMapping(value="/updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(PlaneLayerPoint model){
		try {
			service.updatePart(model);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping(value="/refreshPoint")
	@ResponseBody
	@Logger(action = "刷新点位", logger = "刷新点位")
	public AjaxMessage refreshPoint(String plpLayerIdnty,String cusNumber){
		try {
			service.refreshPoint(plpLayerIdnty,cusNumber);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping(value="/deleteByPlpLayerIdnty")
	@ResponseBody
	public AjaxMessage deleteByPlpLayerIdnty(String plpLayerIdnty){
		try {
			service.deleteByPlpLayerIdnty(plpLayerIdnty);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping("/findByPlpLayerIdnty")
	@ResponseBody
	public AjaxMessage findByPlpLayerIdnty(String plpLayerIdnty) {
		try {
			List<PlaneLayerPoint> list=service.findByPlpLayerIdnty(plpLayerIdnty);
			return new AjaxMessage(true,list);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping("/searchPlaneLayerPoint")
	@ResponseBody
	public AjaxMessage searchPlaneLayerPoint(String plpLayerIdnty) {
		try {
			List<Map<String,Object>> list=service.searchPlaneLayerPoint(plpLayerIdnty);
			return new AjaxMessage(true,list);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
}
