package com.cesgroup.prison.yjct.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yjct.dao.YaczMapper;
import com.cesgroup.prison.yjct.entity.YaczEntity;
import com.cesgroup.prison.yjct.service.YaczService;

/**
 * 预案操作管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/yacz")
public class YaczController extends BaseEntityDaoServiceCRUDController<YaczEntity, String, YaczMapper, YaczService> {
		
	@Resource
	private YaczService yaczService;
	 	 
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<YaczEntity> searchAllData(YaczEntity yaczEntity, HttpServletRequest request,
			HttpServletResponse response) {
					
		return yaczService.findAllList(yaczEntity);
	}
}
