package com.cesgroup.prison.criminal.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.criminal.dao.CriminalMapper;
import com.cesgroup.prison.criminal.entity.CriminalEntity;
import com.cesgroup.prison.criminal.service.CriminalService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/criminal")
public class CriminalController  extends BaseEntityDaoServiceCRUDController<CriminalEntity, String, CriminalMapper, CriminalService> {
	
	private final Logger logger = LoggerFactory.getLogger(CriminalController.class);
	
	@Resource
	private CriminalService criminalService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("criminal/index");
		mv.addObject("deptName", request.getParameter("deptName"));
		return mv;
	}
	
	
	/**
	 * 查询罪犯数量
	 */
	@RequestMapping(value="/getCriminalCount")
	@ResponseBody
	public Map<String, Object> getCriminalCount(HttpServletRequest request){
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String deptCode = request.getParameter("deptCode");
		String jyId = user.getCusNumber();
		String level = AuthSystemFacade.getLoginUserInfo().getUserLevel().toString();
		Map<String, Object> map = new HashedMap();
		if(level.equals("1")) {//省局用户传入监狱编码
			map.put("jyId", deptCode);
		}else {
			map.put("deptCode", deptCode);
			map.put("jyId", jyId);
			
		}
		return criminalService.getCriminalCount(map);
	}
	
	/**
	 * 查询罪犯详细信息
	 */
	@RequestMapping(value="/searchListPage")
	@ResponseBody
	public Map<String, Object> searchListPage(HttpServletRequest request){
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		String deptName = request.getParameter("deptName");
		String xm = request.getParameter("xm");
		String bh = request.getParameter("bh");
		String jyId = user.getCusNumber();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptName", deptName);
		map.put("xm", xm);
		map.put("bh", bh);
		map.put("jyId", jyId);
		Map<String, Object> m = criminalService.searchListPage(map, pageRequest);
		return m;
	}
	
}
