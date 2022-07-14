package com.cesgroup.prison.dagl.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.framework.springmvc.web.BaseEntityController;
import com.cesgroup.prison.dagl.entity.Atth;
import com.cesgroup.prison.dagl.entity.Dagl;
import com.cesgroup.prison.dagl.service.DaglService;
import com.cesgroup.prison.dagl.vo.DaglInfo;
import com.cesgroup.prison.utils.DataUtils;
import com.github.pagehelper.PageInfo;

/**
 * 档案管理controller
 * 
 * @author xiexiaqin
 * @date 2016-04-19
 *
 */
@Controller
@RequestMapping(value = "/dagl")
public class DaglController extends BaseEntityController<Dagl> {

	@Autowired
	private DaglService service;

	@Override
	public BaseService<Dagl, String> getService() {
		return service;
	}

	/**
	 * 档案管理首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String dagl(HttpServletRequest request) {
		return "dagl/index";
	}

	/**
	 * 列表展示
	 * @param entp
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	@Logger(action = "查询", logger = "")
	public Map<String, Object> searchData(@ModelAttribute Dagl dagl) {
		Page<Dagl> page = processSearch();
		return DataUtils.pageToMap(page);
	}

	/**
	 * 进入新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/openAddPage")
	@Logger(action = "进入新增页面", logger = "${档案录入页面}")
	public ModelAndView openAddPage() {
		ModelAndView mav = new ModelAndView("dagl/add");
		return mav;
	}

	/**
	 * 进入修改/查看页面
	 * @param opt
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "openShow/{opt}/{id}")
	@Logger(action = "查看", logger = "${id}")
	public ModelAndView show(@PathVariable("opt") String opt, @PathVariable("id") String id) {
		Dagl dagl = service.getDaglpById(id);
		ModelAndView mav = new ModelAndView("dagl/show");
		mav.addObject("model", dagl);
		mav.addObject("mode", opt);
		return mav;

	}

	/**
	 * 修改
	 * @param dagl
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify")
	@Logger(action = "修改", logger = "${档案修改}")
	public void update(@ModelAttribute Dagl dagl) throws Exception {
		if (dagl.getId() != null) {
			service.updateDagl(dagl);
		}
	}
	/**
	 * 新增
	 * @param dagl
	 * @throws Exception
	 */
	@RequestMapping(value = "/create")
	@Logger(action = "新增", logger = "${档案新增}")
	public void create(Dagl dagl) throws Exception {
		dagl.setTempForm("temp");
		service.insertDagl(dagl);
	}

	/**
	 * 物理删除
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/destroy")
	@Logger(action = "彻底删除", logger = "${id}")
	public void destroy(String ids, HttpServletResponse response) {
		if (ids != null && !ids.equals("")) {
			for (String id : ids.split(",")) {
				service.deleteDaglById(id);
			}

		}
		try {
			response.getWriter().print("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 逻辑删除
	 * @param ids
	 */
	@RequestMapping(value = "/delete")
	@Logger(action = "删除", logger = "${id}")
	public void delete(@RequestParam String ids) {
		if (ids != null && !ids.equals("")) {
			for (String id : ids.split(",")) {
				Dagl dagl = service.getDaglpById(id);
				dagl.setDelStatus(1);
				service.updateDagl(dagl);
			}

		}

	}

	/**
	 * 测试一对多查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/oneToMany/{id}")
	@ResponseBody
	public ModelAndView oneToMany(@PathVariable("id") String id) {
		Dagl dagl=service.getDaAnAtth(id);
		List<Atth> atths=dagl.getAtths();
		ModelAndView mv=new ModelAndView("/dagl/list");
		mv.addObject("dagl",dagl);
		mv.addObject("atths",atths);
		return mv;

	}
	/**
	 * 进入关联查询页面
	 * @return
	 */
	@RequestMapping(value = "/relevanceIndex")
	@Logger(action = "打开关联查询页面", logger = "打开关联查询页面")
	public String relevanceIndex() {
		return "dagl/index3";
	}

	/**
	 * 关联查询展示
	 * 
	 * @param entp
	 * @return
	 */
	@RequestMapping(value = "/relevance")
	@ResponseBody
	@Logger(action = "关联查询", logger = "关联查询")
	public Map<String, Object> relevanceSearch(DaglInfo daglInfo, @RequestParam("P_pagesize") Integer pageSize,
			@RequestParam("P_pageNumber") Integer pageNumber, @RequestParam("sord") String sort) {
		
		List<DaglInfo> daglList = service.getAllDaglInfo(daglInfo);
		// 组装分页对象
		PageInfo<DaglInfo> page = new PageInfo<>(daglList);
		return DataUtils.pageInfoToMap(page);

	}

}
