package com.cesgroup.prison.qjjcjl.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.utils.SearchHelper;
import com.cesgroup.framework.commons.CesStringUtils;
import com.cesgroup.framework.springmvc.utils.SpringMvcWebUtils;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.prison.httpclient.clearCheck.dto.ClearDto;
import com.cesgroup.prison.qjjcjl.service.QjjcjlService;
import com.cesgroup.prison.utils.DataUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/wghgl/yrzq/qjjc")
public class QjjcjlController {
	private final Logger logger = LoggerFactory.getLogger(QjjcjlController.class);

	@Resource
	private QjjcjlService QjjcjlService;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("qjjc/index");
		return mv;
	}

	@RequestMapping(value = "/searchIndex")
	@ResponseBody
	public Map<String, Object> searchIndex(
			@RequestParam(value = "sTime", defaultValue = "", required = false) String sTime,
			@RequestParam(value = "eTime", defaultValue = "", required = false) String eTime,
			@RequestParam(value = "statues", defaultValue = "", required = false) String statues,
			@RequestParam(value = "qjName", defaultValue = "", required = false) String qjName) {
		PageRequest pageRequest = buildPageRequest();
		if(qjName != null && !qjName.isEmpty()) {
			qjName = Base64Util.decodeString(qjName, 2);
		}
		PageInfo<ClearDto> ClearDtoInfo = QjjcjlService.getIndex(pageRequest,sTime,eTime,statues,qjName);
		return DataUtils.pageInfoToMap(ClearDtoInfo);
	}

	public PageRequest buildPageRequest() {
		HttpServletRequest request = SpringMvcWebUtils.getRequest();
		boolean pageQuery = false;
		int pagesize = 20;
		int pageNumber = 1;
		String[] orderStrs = null;

		if (StringUtils.isNumeric(request.getParameter("P_pagesize"))) {
			pagesize = Integer.parseInt(request.getParameter("P_pagesize"), 10);
			pageQuery = true;
		}
		if (StringUtils.isNumeric(request.getParameter("P_pageNumber"))) {
			pageNumber = Integer.parseInt(request.getParameter("P_pageNumber"), 10);
			pageQuery = true;
		}
		if (StringUtils.isNotEmpty(request.getParameter("P_orders"))) {
			orderStrs = CesStringUtils.trim(request.getParameter("P_orders").split("[, ]"));
			pageQuery = true;
		}

		if (!pageQuery) {
			return new PageRequest(0, 20);
		}

		return SearchHelper.buildPageRequest(pageNumber, pagesize, orderStrs);
	}
}
