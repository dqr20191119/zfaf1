package com.cesgroup.prison.layer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.support.NoModel;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.layer.dao.LayerMapper;
import com.cesgroup.prison.layer.entity.Layer;
import com.cesgroup.prison.layer.service.LayerService;
import com.cesgroup.prison.layer.service.impl.LayerServiceImpl;
import com.cesgroup.prison.model.entity.Model;
@Controller
@RequestMapping(value = "/layer")
public class LayerController extends BaseEntityDaoServiceCRUDController<Layer, String, LayerMapper,LayerServiceImpl> {
	
	@Autowired
	private LayerService layerService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("layer/index");
		return mv;
	}

	/**
	 * 保存、更新图层
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveLayer")
	@ResponseBody
	@Logger(action = "保存、更新图层", logger = "保存、更新图层信息")
	public Layer saveLayer(Layer layer) throws Exception{
		try{
			return layerService.saveLayer(layer);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 删除图层
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deleteLayer")
	@ResponseBody
	@Logger(action = "删除图层", logger = "删除图层信息")
	public int deleteLayer(String id) throws Exception{
		try{
			layerService.delete(id);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 查找图层
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findByLayer")
	@ResponseBody
	@Logger(action = "查找图层", logger = "查找图层信息")
	public Layer findByLayer(Layer layer) throws Exception{
		try{
			return layerService.findByLayer(layer);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
