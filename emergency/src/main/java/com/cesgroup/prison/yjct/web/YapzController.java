package com.cesgroup.prison.yjct.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yjct.dao.YapzMapper;
import com.cesgroup.prison.yjct.entity.YapzEntity;
import com.cesgroup.prison.yjct.service.YapzService;

/**
 * 预案配置管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/yapz")
public class YapzController extends BaseEntityDaoServiceCRUDController<YapzEntity, String, YapzMapper, YapzService> {
		
	@Resource
	private YapzService yapzService;
	 	 
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<YapzEntity> searchAllData(YapzEntity yapzEntity, HttpServletRequest request,
			HttpServletResponse response) {
					 
		return yapzService.findAllList(yapzEntity);
	}
}
