package com.cesgroup.prison.xxhj.zfxxcx.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xxhj.zfxxcx.dao.ZfxxcxMapper;
import com.cesgroup.prison.xxhj.zfxxcx.entity.ZfxxcxEntity;
import com.cesgroup.prison.xxhj.zfxxcx.service.ZfxxcxService;
/**
 * Description: 罪犯信息页面查询 控制器
 * 
 * @author monkey
 * 
 * 2019年03月19日
 */
@Controller
@RequestMapping("/xxhj/zfxxcx")
public class ZfxxcxController  extends BaseEntityDaoServiceCRUDController<ZfxxcxEntity, String, ZfxxcxMapper, ZfxxcxService>{
	@Resource
	private ZfxxcxService zfxxcxService;
	 /**
	  *  罪犯个人基本信息窗口
	  */
	@RequestMapping("/zfxxcxInfo")
	public ModelAndView zfxxcxInfo(@RequestParam(value = "cXfbh", defaultValue = "", required = false) String cZfbh) { 
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/zfxxcxInfo");
		mv.addObject("cZfbh", cZfbh);
		return mv;
	}
}
