package com.cesgroup.prison.yjct.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yjct.dao.BjyaMapper;
import com.cesgroup.prison.yjct.entity.BjyaEntity;
import com.cesgroup.prison.yjct.service.BjyaService;

/**
 * 报警预案管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/bjya")
public class BjyaController extends BaseEntityDaoServiceCRUDController<BjyaEntity, String, BjyaMapper, BjyaService>{
	
	private final Logger logger = LoggerFactory.getLogger(BjyaController.class);  
	
	@Resource
	private BjyaService bjyaService;
	 	 
	@RequestMapping(value = "/searchAllDataForCombobox")
	@ResponseBody
	public List<Map<String, Object>> searchAllDataForCombobox(BjyaEntity bjyaEntity, HttpServletRequest request,
			HttpServletResponse response) {
					
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		try {
			
			bjyaEntity.setPmaCusNumber("3200");
			resultList = bjyaService.findAllListForCombobox(bjyaEntity);		 
 		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
 		}
		
		return resultList;
	}
}
