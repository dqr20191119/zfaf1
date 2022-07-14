package com.cesgroup.prison.regionDepart.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.regionDepart.dao.RegionDepartMapper;
import com.cesgroup.prison.regionDepart.entity.RegionDepart;
import com.cesgroup.prison.regionDepart.service.RegionDepartService;
import com.cesgroup.prison.regionDepart.service.impl.RegionDepartServiceImpl;

import net.sf.json.JSONObject;
/**
 * 
 * @author linhe
 * @date 2018-3-9
 */
@Controller
@RequestMapping(value = "/regionDepart")
public class RegionDepartController extends BaseEntityDaoServiceCRUDController<RegionDepart, String, RegionDepartMapper, RegionDepartServiceImpl> {
	@Autowired
	private RegionDepartService service;
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("regionDepart/index");
		return mv;
	}
	/**
	 * 区域树页面
	 * @return
	 */
	@RequestMapping("/regionTreePage")
	public ModelAndView regionTreePage(){
		ModelAndView mv = new ModelAndView("regionDepart/regionTree");
		return mv;
	}
	/**
	 * 部门树页面
	 * @return
	 */
	@RequestMapping("/departTreePage")
	public ModelAndView departTreePage(){
		ModelAndView mv = new ModelAndView("regionDepart/departTree");
		return mv;
	}
	
	/**
	 * 根据区域获取部门
	 * @return
	 */
	@RequestMapping(value = "/getDepartInfo")
	@ResponseBody
	@Logger(action = "查询区域", logger = "根据区域编号查询部门信息")
	public List<RegionDepart> getDepartInfo(String cusNumber,String areaId) throws WebUIException {
		return this.service.findByCusNumberAndAreaId(cusNumber, areaId);
	}
	
	/**
	 * 保存
	 * @return
	 * @throws Exception 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	@Logger(action = "添加区域部门信息", logger = "添加区域部门信息")
	public int save(String objs) throws ParseException, Exception{
		return this.service.insertByBatch(objs);
	}
	
	
}
