package com.cesgroup.prison.model.web;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.model.dao.ModelMapper;
import com.cesgroup.prison.model.entity.Model;
import com.cesgroup.prison.model.service.ModelService;
import com.cesgroup.prison.model.service.impl.ModelServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 模型
 * @author hurenjie 2017-12-11
 *
 */
@Controller
@RequestMapping(value = "/model")
public class ModelController extends BaseEntityDaoServiceCRUDController<Model, String, ModelMapper,ModelServiceImpl> {
	
	@Autowired
	private ModelService modelService;
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("model/index");
		return mv;
	}
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("model/list");
		return mv;
	}
	
	/**
	 * 保存、更新模型
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveModel")
	@ResponseBody
	@Logger(action = "保存、更新模型", logger = "保存、更新模型信息")
	public Model saveModel(Model model) throws Exception{
		modelService.saveModel(model);
		return model;
	}
	

	/**
	 * 删除模型
	 * @return
	 */
	@RequestMapping(value = "/deleteModel")
	@ResponseBody
	@Logger(action = "删除模型", logger = "删除模型")
	public int deleteModel(String obj){
		Map<String,String> map = new HashMap<String, String>();
		JSONArray jsonArray = JSONArray.fromObject(obj);
		List<String> ids = JSONArray.toList(jsonArray);
		return modelService.deleteModelByIds(ids);
	}
	
	/**
	 *  根据监狱编号=，区域编号like,模型名称like查询分页查询
	 * @return
	 */
	@RequestMapping(value = "/findByPage")
	@ResponseBody
	@Logger(action = "模型分页查询", logger = "模型分页查询")
	public Map<String, Object> findByPage(String minCusNumber,String minModelName,String minAreaId) {
		Map<String,String> map = new HashMap<String, String>();
		/*JSONObject jsonObject = JSONObject.fromObject(obj);
		Iterator<?> it = jsonObject.keys();*/
        // 遍历jsonObject数据，添加到Map对象  
        /*while (it.hasNext())  
        {  
            String key = String.valueOf(it.next());  
            String value = (String) jsonObject.get(key);  
            map.put(key, value);
        }*/
		if(minCusNumber!=null && !minCusNumber.equals("")){
        	map.put("minCusNumber", minCusNumber);
        }
        if(minModelName!=null && !minModelName.equals("")){
        	map.put("minModelName", URLDecoder.decode(minModelName));
        }
        if(minAreaId!=null && !minAreaId.equals("")){
        	map.put("minAreaId", URLDecoder.decode(minAreaId));
        }
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,String>> page = modelService.findByPage(map, pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	/**
	 * 根据id查询
	 * @return
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "根据id查询", logger = "根据id查询")
	public Model findById(String id) {
		return modelService.findById(id);
	}

	/**
	 * 根据modelNo查询
	 * @return
	 */
	@RequestMapping(value = "findByModel")
	@ResponseBody
	@Logger(action = "根据modelNo查询", logger = "根据modelNo查询")
	public Map<String, String> findByModel(Model model) {
		return modelService.findByModel(model);
	}
	
	/**
	 *  根据监狱编号=，区域编号like,模型名称like查询查询
	 * @return
	 */
	@RequestMapping(value = "/find")
	@ResponseBody
	@Logger(action = "根据区域模型查询模型", logger = "根据区域查询模型")
	public List<Model> find(String obj) {
		Map<String,String> map = new HashMap<String, String>();
		JSONObject jsonObject = JSONObject.fromObject(obj);
		Iterator<?> it = jsonObject.keys();  
        // 遍历jsonObject数据，添加到Map对象  
        while (it.hasNext())  
        {  
            String key = String.valueOf(it.next());  
            String value = (String) jsonObject.get(key);  
            map.put(key, value);
        }  
        List<Model> list = modelService.find(map);
		return list;
	}
	
}
