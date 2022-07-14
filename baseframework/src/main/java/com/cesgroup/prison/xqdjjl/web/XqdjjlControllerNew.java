package com.cesgroup.prison.xqdjjl.web;

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
import com.cesgroup.prison.httpclient.moodRecord.dto.MoodRecordDto;
import com.cesgroup.prison.utils.DataUtils;
import com.github.pagehelper.PageInfo;
import com.cesgroup.prison.xqdjjl.service.XqdjjlServiceNew;
@Controller
@RequestMapping(value = "/wghgl/yrzq/xqdjjl")
public class XqdjjlControllerNew {
	private final Logger logger = LoggerFactory.getLogger(XqdjjlControllerNew.class);

	@Resource
	private XqdjjlServiceNew XqdjjlServiceNew;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("xqdjjl/index");
		return mv;
	}

	@RequestMapping(value = "/searchIndex")
	@ResponseBody
	public Map<String, Object> searchIndex(
			@RequestParam(value = "sTime", defaultValue = "", required = false) String sTime,
			@RequestParam(value = "eTime", defaultValue = "", required = false) String eTime,
			@RequestParam(value = "statues", defaultValue = "", required = false) String statues,
			@RequestParam(value = "zfName", defaultValue = "", required = false) String zfName,
			@RequestParam(value = "zfMood", defaultValue = "", required = false) String zfMood) {
		PageRequest pageRequest = buildPageRequest();
		if(zfName != null && !zfName.isEmpty()) {
			zfName = Base64Util.decodeString(zfName, 2);
		}
		PageInfo<MoodRecordDto> MoodRecordDtoInfo = XqdjjlServiceNew.getIndex(pageRequest,sTime,eTime,statues,zfName,zfMood);
		return DataUtils.pageInfoToMap(MoodRecordDtoInfo);
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
