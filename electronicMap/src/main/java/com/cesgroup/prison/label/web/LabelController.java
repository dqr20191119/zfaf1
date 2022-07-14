package com.cesgroup.prison.label.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.label.dao.LabelMapper;
import com.cesgroup.prison.label.entity.Label;
import com.cesgroup.prison.label.service.LabelService;
@Controller
@RequestMapping(value="/labels")
public class LabelController extends BaseEntityDaoServiceCRUDController<Label,String,LabelMapper,LabelService>{
	
	@RequestMapping("/labelManager")
	public ModelAndView labelManager(){
		ModelAndView mv = new ModelAndView("label/labelManager");
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("label/list");
		return mv;
	}
	
	@RequestMapping("/addLabel")
	@ResponseBody
	@Logger(action = "插入标签列表", logger = "标签列表插入数据")
	public Map<String,String> addLabel(Label label){
		 Map<String ,String> map =new HashMap<String ,String>();
		 try {
			 getService().insertLabel(label);
			 map.put("message", "保存成功");
		 }catch(Exception e) {
			 map.put("message", "操作失败");
		 }
		return map;
	}
	
	@RequestMapping("/updLabel")
	@ResponseBody
	@Logger(action = "更新标签列表", logger = "更新列表插入数据")
	public Map<String,String> updLabel(Label label){
		 Map<String ,String> map =new HashMap<String ,String>();
		 try {
			 getService().updateLabel(label);
			 map.put("message", "保存成功");
		 }catch(Exception e) {
			 map.put("message", "操作失败");
		 }
		return map;
	}
	
	@RequestMapping("/delLabel")
	@ResponseBody
	@Logger(action = "删除标签列表", logger = "标签列表删除数据")
	public Map<String,String> delLabel(String id){
		 Map<String ,String> map =new HashMap<String ,String>();
		 try {
			 getService().delLabel(id);
			 map.put("message", "操作成功");
		 }catch(Exception e) {
			 map.put("message", "操作失败");
		 }
		return map;
	}
	
	@RequestMapping("/labelPage")
	@ResponseBody
	@Logger(action = "分页查询标签列表", logger = "分页查询标签列表")
	public Page<Label> labelPage(Label label, PageRequest pageable){
		 Page<Label> findByPage =null;
		   try {
		       findByPage = getService().selectPageLabel(label, pageable);
		    }catch (Exception e) {
				throw e;
			}
		return findByPage;
	}
	
	@RequestMapping("/labelList")
	@ResponseBody
	@Logger(action = "根据监狱编号和区域编号查询", logger = "根据监狱编号和区域编号查询")
	public List<Label> labelList(String mliCusNumber, String mliAreaId){
		/* List<Label> list =null;
		   try {
		       list = 
		    }catch (Exception e) {
				throw e;
			}*/
		return getService().labelList(mliCusNumber, mliAreaId);
	}
}
