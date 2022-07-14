package com.cesgroup.prison.yjct.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yjct.dao.CzffczxMapper;
import com.cesgroup.prison.yjct.entity.CzffczxEntity;
import com.cesgroup.prison.yjct.service.CzffczxService;

/**
 * 处置方法关联操作项管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/czffczx")
public class CzffczxController extends BaseEntityDaoServiceCRUDController<CzffczxEntity, String, CzffczxMapper, CzffczxService> {
	
	private final Logger logger = LoggerFactory.getLogger(CzffczxController.class);  
	
	@Resource
	private CzffczxService czffczxService;
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<CzffczxEntity> searchAllData(CzffczxEntity czffczxEntity, HttpServletRequest request,
			HttpServletResponse response) {
							 
		return czffczxService.findAllList(czffczxEntity);
	}
}
