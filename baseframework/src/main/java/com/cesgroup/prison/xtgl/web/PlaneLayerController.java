package com.cesgroup.prison.xtgl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.support.NoModel;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.xtgl.dao.PlaneLayerMapper;
import com.cesgroup.prison.xtgl.entity.PlaneLayer;
import com.cesgroup.prison.xtgl.service.PlaneLayerService;
import com.cesgroup.prison.xtgl.service.impl.PlaneLayerServiceImpl;

@Controller
@RequestMapping(value="/xtgl/planeLayer")
public class PlaneLayerController extends BaseEntityDaoServiceCRUDController<PlaneLayer,String,PlaneLayerMapper,PlaneLayerServiceImpl>{

	@Autowired
	private PlaneLayerService service;
	
	@Logger(action = "添加", logger = "${id}")
    @RequestMapping(value = "/insert")
	@ResponseBody
    public AjaxMessage insert(@NoModel final PlaneLayer model){
		try {
			PlaneLayer planeLayer=getService().insert(model);
			return new AjaxMessage(true,planeLayer.getId());
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
			service.deleteFile(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
	
	@RequestMapping(value="/updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(PlaneLayer planeLayer_param){
		try {
			service.updatePart(planeLayer_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping(value="/saveFile")
	@ResponseBody
	@Logger(action = "保存二维图层背景图信息", logger = "保存二维图层背景图信息")
	public AjaxMessage saveFile(String id, String files){
		try {
			service.saveFile(id, files);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	@RequestMapping(value="/findFile")
	@ResponseBody
	@Logger(action = "查看二维图层背景图信息", logger = "查看二维图层背景图信息")
	public AjaxMessage findFile(String id){
		try {
			List<AffixEntity> affixList=service.findFile(id);
			return new AjaxMessage(true,affixList);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping("/findByPliAreaId")
	@ResponseBody
	public List<PlaneLayer> findByPliAreaId(String pliAreaId) {
		return service.findByPliAreaId(pliAreaId);
	}
}
