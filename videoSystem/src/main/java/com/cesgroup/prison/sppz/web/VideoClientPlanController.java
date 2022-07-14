package com.cesgroup.prison.sppz.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.sppz.dao.VideoClientMapper;
import com.cesgroup.prison.sppz.entity.VideoClient;
import com.cesgroup.prison.sppz.service.VideoClientSettingService;
import com.cesgroup.prison.sppz.service.impl.VideoClientServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value="/sppz/videoClient")
public class VideoClientPlanController extends BaseEntityDaoServiceCRUDController<VideoClient,String,VideoClientMapper,VideoClientServiceImpl>{
	@Autowired
	private VideoClientSettingService service;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("sppz/videoClient/list");
		return mv;
	}
	
	@RequestMapping(value="searchVideoClient")
	@ResponseBody
	@Logger(action = "查询视频客户端信息", logger = "视频客户端列表")
	public Map<String, Object> searchVideoClient(VideoClient videoClient_param, HttpServletRequest request){
		if(videoClient_param.getVccClientIp()==null || "".equals(videoClient_param.getVccClientIp())) {
			// videoClient_param.setVccClientIp(request.getRemoteAddr());
			videoClient_param.setVccClientIp(IpUtil.getIpAddress());
		}
		PageRequest pageRequest=buildPageRequest();
		Page<Map<String,String>> page=service.searchVideoClient(videoClient_param,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping(value="updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(VideoClient videoClient_param){
		try {
			service.updatePart(videoClient_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
		
	}
	
}
