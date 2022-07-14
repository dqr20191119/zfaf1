package com.cesgroup.prison.wghf.wgzrfp.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;

import com.cesgroup.prison.wghf.wgzrfp.dao.WgglDao;

import com.cesgroup.prison.wghf.wgzrfp.entiy.Wggl;

import com.cesgroup.prison.wghf.wgzrfp.service.WgglService;

//@Controller
//@RequestMapping("/wghf/wgzrfp")
//public class WgglController extends BaseEntityDaoServiceCRUDController<Wggl, String, WgglDao, WgglService> {
//	private final Logger logger = LoggerFactory.getLogger(WgglController.class);
//	
////	@RequestMapping("/lsit/{ccodeId}")
////	@ResponseBody
////	public List<TreeDto> saveWgglData(){
////		
////		
////		return null;
////		
////	}
//}
