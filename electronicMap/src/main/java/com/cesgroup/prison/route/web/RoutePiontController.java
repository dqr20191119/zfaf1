package com.cesgroup.prison.route.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.route.dao.CdsRoamPointRltnMapper;
import com.cesgroup.prison.route.dao.RouteMapper;
import com.cesgroup.prison.route.entity.CdsRoamPointRltn;
import com.cesgroup.prison.route.entity.Route;
import com.cesgroup.prison.route.service.RoutePointService;
import com.cesgroup.prison.route.service.RouteService;
import com.cesgroup.prison.utils.CommonUtil;

@Controller
@RequestMapping(value = "/routePoint")
public class RoutePiontController extends
		BaseEntityDaoServiceCRUDController<CdsRoamPointRltn, String, CdsRoamPointRltnMapper, RoutePointService> {

	@Autowired
	private RoutePointService routePointservice;

	@RequestMapping("/Page")
	@ResponseBody
	@Logger(action = "查询巡视点列表", logger = "根据查询条件查询巡视列表")
	public Page<CdsRoamPointRltn> routePiontPage(CdsRoamPointRltn routePoint, PageRequest pageable) throws Exception{
		Map<String ,String> map =new HashMap<String ,String>();
			map.put("rprPointName", routePoint.getRprPointName());		
		    map.put("rprCusNumber", routePoint.getRprCusNumber());
		    map.put("rprRoamIdnty", routePoint.getRprRoamIdnty());
		    map.put("rprEquipmentId", routePoint.getRprEquipmentId());
            Page<CdsRoamPointRltn> findByPage = getService().findByPage(map, pageable);
            return findByPage;
      }
	
	@RequestMapping("/addOrUpdatePoint")
	@ResponseBody
	@Logger(action = "新增巡视点", logger = "添加巡视点位")
	public Map<String ,String> addPoint(CdsRoamPointRltn routePoint) {
		Map<String ,String> map =new HashMap<String ,String>();
		 try {
		  if(routePoint.getId().isEmpty()) {					
			 getService().insertSelectivePoint(routePoint);
		   }else {
			   getService().updateByPrimaryKeySelective(routePoint);
		   }		    
			 map.put("message", "操作成功");
		 }catch(Exception e) {
			 map.put("message", "操作失败："+e.getMessage());
		 } 
		return map;
      }
	
	@RequestMapping("/delPoint")
	@ResponseBody
	@Logger(action = "删除巡视点", logger = "通过id删除巡视点位")
	public Map<String ,String> delPoint(String id) {
		Map<String ,String> map =new HashMap<String ,String>();
		if(id==null||id.isEmpty()) {
			 map.put("message", "删除失败，缺少id");
			 return map;
		 }
		 try {
			 getService().deleteByPrimaryKey(id);
			 map.put("message", "删除成功");
		 }catch(Exception e) {
			 map.put("message", "删除失败");
		 }
		return map;
      }
	
	@RequestMapping("/selectPointsByRouteID")
	@ResponseBody
	@Logger(action = "查询巡视点", logger = "通过routeId查询巡视点位")
	public List<CdsRoamPointRltn> selectPointsByRouteID(CdsRoamPointRltn cdsRoamPointRltn) {
		Map<String ,String> map =new HashMap<String ,String>();
		if(cdsRoamPointRltn.getRprRoamIdnty()==null||cdsRoamPointRltn.getRprRoamIdnty().isEmpty()) {
             throw new RuntimeException("缺少路径id");
		 }
		 try {
			 List<CdsRoamPointRltn> selectPointsByRouteID = getService().selectPointsByRouteID(cdsRoamPointRltn);
			return selectPointsByRouteID;
		 }catch(Exception e) {
			 throw new RuntimeException("获取路线点失败");
		 }
	
      }
}
