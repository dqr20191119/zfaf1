package com.cesgroup.prison.securityCheck.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.securityCheck.dao.SecurityCheckDao;
import com.cesgroup.prison.securityCheck.entity.SecurityCheck;
import com.cesgroup.prison.securityCheck.service.SecurityCheckService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/securityCheck")
public class SecurityCheckController extends BaseEntityDaoServiceCRUDController<SecurityCheck, String, SecurityCheckDao, SecurityCheckService> {

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("securityCheck/list");
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询安检信息")
	public Map<String, Object> listAll(
			@RequestParam(value="deviceName", defaultValue="", required=false) String deviceName
			) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceName", deviceName);
		
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) this.getService().listAll(paramMap, pageRequest);

		return DataUtils.pageToMap(page);
	}
}
