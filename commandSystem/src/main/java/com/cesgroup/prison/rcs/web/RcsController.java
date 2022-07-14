package com.cesgroup.prison.rcs.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.rcs.dao.RcsDao;
import com.cesgroup.prison.rcs.entity.RcsEntity;
import com.cesgroup.prison.rcs.entity.YjyaEntity;
import com.cesgroup.prison.rcs.service.RcsService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/rcs")
public class RcsController extends BaseEntityDaoServiceCRUDController<RcsEntity, String, RcsDao, RcsService> {

	@Resource
	private RcsService service;

	/**
	 * 融合通讯页面跳转
	 * @return
	 */
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		//ModelAndView mv = new ModelAndView("rcs/rcs_control");
		ModelAndView mv = new ModelAndView("rcs/rcs_control_sb");
		return mv;
	}
	/**
	 * 呼叫页面跳转
	 * @return
	 */
	@RequestMapping("callDailog")
	public ModelAndView callDailog(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("rcs/call_dialog");
		RcsEntity model = service.selectOne(request.getParameter("id"));
		mv.addObject("model", model);
		return mv;
	}
	/**
	 * 融合通讯查询记录页面
	 */
	@RequestMapping(value = "/toIndex")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("rcs/index");
		return mv;
	}
	@RequestMapping(value = "/searchJl")
	public Map<String, Object> searchJl(RcsEntity rcs,HttpServletRequest request) {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, String>> page = service.searchJl(rcs, pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping(value = "/searchRcs")
	@ResponseBody
	public Map<String, Object> searchRcs(RcsEntity rcs,HttpServletRequest request) {
		PageRequest pageRequest = buildPageRequest();
		rcs.setCusNumber(request.getParameter("organizeCode"));
		Page<Map<String, String>> page = service.searchRcs(rcs, pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping("/dailog")
	public ModelAndView dailog(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("rcs/dailog");
		mv.addObject("cellvalue", request.getParameter("cellvalue"));
		mv.addObject("name", request.getParameter("name"));
		mv.addObject("no", request.getParameter("no"));
		return mv;
	}
	
	/**
	 * 发送对讲消息
	 * @param request
	 * @return
	 */
//	@RequestMapping(value = "/startCall")
//	@ResponseBody
//	public AjaxResult startTalk(RcsEntity rcsEntity, HttpServletRequest request) {
//		
//		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
//		getService().startCallDsq();
//		return this.getService().startCall(ip, rcsEntity);
//	}
	/**
	 * 开始呼叫
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/startCall")
	@ResponseBody
	public AjaxResult startTalk(RcsEntity rcsEntity, HttpServletRequest request) {
		
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		return this.getService().startCall(ip, rcsEntity);
	}
	
	/**
	 * 挂断
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hangUp")
	@ResponseBody
	public AjaxResult hangUp(RcsEntity rcsEntity, HttpServletRequest request) {
		
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		return this.getService().hangUp(ip, rcsEntity);
	}
	
	@RequestMapping(value = "/startCallDemo")
	@ResponseBody
	public AjaxResult startCallDemo(RcsEntity rcsEntity, HttpServletRequest request) {
		
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		return this.getService().startCall(ip, rcsEntity);
	}
	
	/**
	 * 融合通讯语音通知Demo跳转
	 * @return
	 */
	@RequestMapping(value = "/openYytzDemoDialog")
	public ModelAndView openYytzDemoDialog() {
		ModelAndView mv = new ModelAndView("rcs/rcs_yytz_demo");
		return mv;
	}
	
	/**
	 * 融合通讯应急预案Demo跳转
	 * @return
	 */
	@RequestMapping(value = "/openYjyaDemoDialog")
	public ModelAndView openYjyaDemoDialog() {
		ModelAndView mv = new ModelAndView("rcs/rcs_yjya_demo");
		return mv;
	}
	
	/**
	 * 启动应急预案
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/startYjya")
	@ResponseBody
	public AjaxResult startYjya(YjyaEntity yjyaEntity, HttpServletRequest request) {
		
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		return this.getService().startYjya(ip, yjyaEntity);
	}
	
	/**
	 * 启动应急预案Demo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/startYjyaDemo")
	@ResponseBody
	public AjaxResult startYjyaDemo(YjyaEntity yjyaEntity, HttpServletRequest request) {
		
		String ip = IpUtil.currentRemoteIp(request);// 当前用户的IP
		return this.getService().startYjya(ip, yjyaEntity);
	}
}
