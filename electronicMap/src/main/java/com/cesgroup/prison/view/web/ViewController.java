package com.cesgroup.prison.view.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.view.dao.ViewMapper;
import com.cesgroup.prison.view.entity.View;
import com.cesgroup.prison.view.service.ViewService;
import com.cesgroup.prison.view.service.impl.ViewServiceImpl;

import net.sf.json.JSONObject;
/**
 * 视角维护
 * @author linhe 2017-12-11
 *
 */
@Controller
@RequestMapping(value = "/view")
public class ViewController extends BaseEntityDaoServiceCRUDController<View, String, ViewMapper,ViewServiceImpl> {
	
	@Autowired
	private ViewService viewService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("view/index");
		return mv;
	}
	@RequestMapping("/treePage")
	public ModelAndView treePage(){
		ModelAndView mv = new ModelAndView("view/tree");
		return mv;
	}
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("view/list");
		return mv;
	}
	@RequestMapping("/searchView")
	public ModelAndView searchView(){
		ModelAndView mv = new ModelAndView("view/search");
		return mv;
	}
	
	/**
	 * 视角信息集合
	 * @return
	 */
	@RequestMapping(value = "/findByCusNumberAndType")
	@ResponseBody
	@Logger(action = "查询视角", logger = "根据监狱编号和视觉类型查询视角")
	public List<Map<String, Object>> findByCusNumberAndType(String cusNumber,String type) {
		return viewService.findByCusNumberAndType(cusNumber, type);
	}
	
	/**
	 * 视角信息集合
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveView")
	@ResponseBody
	@Logger(action = "保存视角", logger = "保存视角信息")
	public int saveView(View view) throws Exception {
		return viewService.saveView(view);
	}
	
	/**
	 * 视角信息列表（分页）
	 * @return
	 */
	@RequestMapping(value = "/findByPage")
	@ResponseBody
	@Logger(action = "视角分页查询", logger = "视角分页查询")
	public Map<String, Object> findByPage(String cusNumber, String areaId, String viewType, String viewStts, String viewName) {
		Map<String,String> map = new HashMap<String, String>();
		/*JSONObject jsonObject = JSONObject.fromObject(obj);
		Iterator<?> it = jsonObject.keys();  
        // 遍历jsonObject数据，添加到Map对象  
        while (it.hasNext())  
        {  
            String key = String.valueOf(it.next());  
            String value = (String) jsonObject.get(key);  
            map.put(key, value);  
        }  */
		if(cusNumber!=null && !cusNumber.equals("")) {
			map.put("cusNumber", cusNumber);
		}
		if(areaId!=null && !areaId.equals("")) {
			map.put("areaId", areaId);
		}
		if(viewType!=null && !viewType.equals("")) {
			map.put("viewType", viewType);
		}
		if(viewStts!=null && !viewStts.equals("")) {
			map.put("viewStts", viewStts);
		}
		if(viewName!=null && !viewName.equals("")) {
			map.put("viewName", viewName);
		}
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,String>> page = viewService.findByPage(map, pageRequest);
		return DataUtils.pageToMap(page);
	}
	/**
	 * 根据id查询
	 * @return
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "根据id查询", logger = "保存视角信息")
	public View findById(String id) {
		return null;
	}
	@RequestMapping(value = "/deleteView")
	@ResponseBody
	@Logger(action = "删除", logger = "删除视角")
	public int deleteView(String ids) {
		return this.viewService.batchDelete(ids);
	}
	/**
	 * 根据监狱编号、父级区域编号、视角类型查询默认视角信息
	 * @param cusNumber
	 * @param parentAreaId
	 * @param viewType
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findRegionView")
	@ResponseBody
	@Logger(action = "查询视角定位", logger = "查询视角定位信息")
	public List<Map<String,Object>> findRegionView(String cusNumber, String parentAreaId, String confine) throws Exception {
		return this.viewService.findRegionView(cusNumber, parentAreaId, confine);
	}

	@RequestMapping(value = "/findRegionView_2d")
	@ResponseBody
	@Logger(action = "查询视角定位", logger = "查询视角定位信息")
	public List<Map<String,Object>> findRegionView_2D(String cusNumber) throws Exception {
		return this.viewService.findRegionView_2D(cusNumber);
	}
	
	@RequestMapping(value = "/findViewByAreaId")
	@ResponseBody
	@Logger(action = "根据区域编号查询视角信息", logger = "根据区域编号查询视角信息")
	public View findViewByAreaId(String cusNumber, String areaId, String confine) {
		return this.viewService.findByCusNumberAndAreaIdAndViewType(cusNumber, areaId, confine);
	}
	
	
}
