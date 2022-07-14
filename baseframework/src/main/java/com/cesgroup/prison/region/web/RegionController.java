package com.cesgroup.prison.region.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.cesgroup.framework.bean.AjaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.region.dao.RegionMapper;
import com.cesgroup.prison.region.entity.Region;
import com.cesgroup.prison.region.service.RegionService;
import com.cesgroup.prison.region.service.impl.RegionServiceImpl;
/**
 * 区域维护
 * @author linhe 2017-11-23
 *
 */
@Controller
@RequestMapping(value = "/region")
public class RegionController extends BaseEntityDaoServiceCRUDController<Region, String, RegionMapper, RegionServiceImpl> {
	@Autowired
	private RegionService regionService;
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("region/index");
		return mv;
	}
/*	@RequestMapping("/editRegion")
	public ModelAndView editRegion(){
		ModelAndView mv = new ModelAndView("region/edit");
		return mv;
	}*/
	/**
	 * 区域信息树加载
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getRegionTree")
	@ResponseBody
	public List<Map<String, Object>> getRegionTree(String cusNumber,String areaId,String nodeCusNumber) throws Exception {
		return regionService.getTree(cusNumber,areaId,nodeCusNumber);
	}
	/**
	 * 进入区域树页面
	 * @return
	 */
	@RequestMapping("/regionTreePage")
	public ModelAndView treePage(){
		ModelAndView mv = new ModelAndView("region/tree");
		return mv;
	}
	/**
	 * 区域详情加载
	 * @return
	 */
	@RequestMapping(value = "/getRegionInfo")
	@ResponseBody
	@Logger(action = "查询区域", logger = "根据区域编号查询区域详细信息")
	public List<Region> getRegionInfo(String cusNumber,String id) throws WebUIException {
		return regionService.findByCusNumberAndAreaId(cusNumber,id);
	}
	/**
	 * 添加区域
	 * @param region
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/createRegion")
	@ResponseBody
	@Logger(action = "添加区域", logger = "添加区域信息")
	public ModelAndView createRegion(@Valid Region region, BindingResult result) throws Exception {
		//判断编号是否已存在
		if(region.getAbdAreaId()==null || region.getAbdAreaId().equals("")||region.getAbdAreaName()==null || region.getAbdAreaName().equals("")||region.getAbdTypeIndc()==0) {
			return new ModelAndView("区域信息输入不完整");
		}
		List<Region> list = regionService.findByCusNumberAndAreaId(region.getAbdCusNumber(),region.getAbdAreaId());
		if(list.size()>0) {
			return new ModelAndView("区域编号重复，请重新输入");
		}
		regionService.insertRegion(region);
		return successView();
	}
	
	/**
	 * 修改区域
	 * @param region
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateRegion")
	@ResponseBody
	@Logger(action = "修改区域", logger = "修改区域信息")
	public AjaxMessage updateRegion(@Valid Region region, BindingResult result) throws Exception {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		if(region.getId()==null || region.getId().equals("") || region.getId().equals("null")) {
			ajaxMessage.setMsg("未点击选择区域");

		}
		//判断编号是否已存在
		if(region.getAbdAreaId().equals("")||region.getAbdAreaName().equals("")||region.getAbdTypeIndc()==0) {
			ajaxMessage.setMsg("区域信息输入不完整");
		}
		List<Region> list = regionService.findByCusNumberAndAreaId(region.getAbdCusNumber(),region.getAbdAreaId());
		for(Region reg:list) {
			if(!reg.getId().equals(region.getId())) {
				ajaxMessage.setMsg("区域编号重复，请重新输入");
			}
		}
		String stta = regionService.updateRegion(region);
		if("success".equals(stta)){
			flag = true;
			ajaxMessage.setSuccess(flag);
			ajaxMessage.setMsg("修改成功");
		}
		return ajaxMessage;
	}
	/**
	 * 删除区域
	 * @param region
	 * @return
	 * @throws WebUIException
	 * @throws ParseException 
	 */
	@Logger(action="删除区域",logger="删除区域")
	@RequestMapping(value="/delRegion")
	public ModelAndView delRegion(String cusNumber,String id) throws WebUIException {
		regionService.deleteRegion(cusNumber,id);
		return successView();
	}
	/*//添加区域（有地图）
	@RequestMapping("/index")
	public ModelAndView add3DRegion(){
		ModelAndView mv = new ModelAndView("region/index");
		return mv;
	}*/
	
	
}
