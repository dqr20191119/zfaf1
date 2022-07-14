package com.cesgroup.prison.route.web;

import java.io.UnsupportedEncodingException;
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
import com.cesgroup.prison.route.dao.RouteMapper;
import com.cesgroup.prison.route.entity.Route;
import com.cesgroup.prison.route.service.RouteService;

@Controller
@RequestMapping(value = "/route")
public class RouteController extends BaseEntityDaoServiceCRUDController<Route, String, RouteMapper, RouteService> {

	@RequestMapping("/walkManager")
	public ModelAndView walkManager() {
		ModelAndView mv = new ModelAndView("route/walkManager");
		return mv;
	}

	@RequestMapping("/manager")
	public ModelAndView tab() {
		ModelAndView mv = new ModelAndView("route/route");
		return mv;
	}

	@RequestMapping("/routeList")
	public ModelAndView routeList() {
		ModelAndView mv = new ModelAndView("route/routeList");
		return mv;
	}

	@RequestMapping("/routePointList")
	public ModelAndView routePointList() {
		ModelAndView mv = new ModelAndView("route/routePointList");
		return mv;
	}

	/**
	 * 分页查询巡查路线
	 * @param route
	 * @param pageable
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/routePage")
	@ResponseBody
	@Logger(action = "查询巡视列表", logger = "查询巡视列表")
	public Page<Route> routePage(Route route, PageRequest pageable) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rpiName", route.getRpiName());
		map.put("rpiCusNumber", route.getRpiCusNumber());
		map.put("rpiRouteType", route.getRpiRouteType());
		map.put("rpiDepartCode", route.getRpiDepartCode());
		Page<Route> findByPage = getService().findByPage(map, pageable);
		return findByPage;
	}

	/**
	 * 插入一条记录
	 * @param route
	 * @return
	 */
	@RequestMapping("/addRoute")
	@ResponseBody
	@Logger(action = "插入巡视列表", logger = "巡视列表插入数据")
	public Map<String, String> addRoute(Route route) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			getService().insertRoute(route);
			map.put("message", "插入成功");
		} catch (Exception e) {
			e.getMessage();
			map.put("message", "插入失败");
		}
		return map;
	}

	@RequestMapping("/delRoute")
	@ResponseBody
	@Logger(action = "删除巡视列表", logger = "巡视列表删除数据")
	public Map<String, String> delRoute(String id) {
		Map<String, String> map = new HashMap<String, String>();
		if (id == null || id.isEmpty()) {
			map.put("message", "删除失败，缺少id");
			return map;
		}
		try {
			getService().deleteById(id);
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("message", "删除失败");
		}
		return map;
	}

	@RequestMapping("/updateRoute")
	@ResponseBody
	@Logger(action = "更新巡视列表", logger = "巡视列表更新数据")
	public Map<String, String> updateRoute(Route route) {
		Map<String, String> map = new HashMap<String, String>();
		if (route.getId().isEmpty()) {
			map.put("message", "更新失败，缺少id");
			return map;
		}
		try {
			getService().updateRoute(route);
			map.put("message", "更新成功");
		} catch (Exception e) {
			map.put("message", "更新失败");
		}
		return map;
	}

	@RequestMapping("/findByPrisonCode")
	@ResponseBody
	@Logger(action = "查询巡视列表", logger = "根据监狱编号查询巡视列表")
	public List<Map<String, String>> findByPrisonCode(String cusNumber, String departCode) throws Exception {
		List<Map<String, String>> map = getService().findByPrisonCode(cusNumber, departCode);
		return map;
	}

	@RequestMapping("/findByRouteId")
	@ResponseBody
	@Logger(action = "查询巡视列表", logger = "根据路线id查询巡视列表")
	public Route findByRouteId(String id) {
		Route findById = getService().findById(id);
		return findById;
	}

}
