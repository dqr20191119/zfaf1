package com.cesgroup.prison.yjct.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yjct.dao.GzzcyMapper;
import com.cesgroup.prison.yjct.entity.GzzcyEntity;
import com.cesgroup.prison.yjct.service.GzzcyService;

/**
 * 工作组成员管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/gzzcy")
public class GzzcyController extends BaseEntityDaoServiceCRUDController<GzzcyEntity, String, GzzcyMapper, GzzcyService> {
		
	@Resource
	private GzzcyService gzzcyService;
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<GzzcyEntity> searchAllData(GzzcyEntity gzzcyEntity, HttpServletRequest request,
			HttpServletResponse response) {
		 
		List<GzzcyEntity> list = gzzcyService.findAllList(gzzcyEntity);
		return list;
	}
}
