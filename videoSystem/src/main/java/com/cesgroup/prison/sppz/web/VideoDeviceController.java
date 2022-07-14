package com.cesgroup.prison.sppz.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.sppz.dao.VideoDeviceMapper;
import com.cesgroup.prison.sppz.entity.VideoDevice;
import com.cesgroup.prison.sppz.service.IVideoDeviceService;
import com.cesgroup.prison.sppz.service.impl.VideoDeviceServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

/**   
*    
* @projectName：prison   
* @ClassName：VideoDeviceController   
* @Description：视频设备Controller
* @author：zhengke   
* @date：2017-12-01 09:48   
* @version        
*/
@Controller
@RequestMapping(value="/sppz/videoDevice")
public class VideoDeviceController extends BaseEntityDaoServiceCRUDController<VideoDevice,String,VideoDeviceMapper,VideoDeviceServiceImpl>{
	@Autowired
	private IVideoDeviceService service;
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("sppz/videoDevice/list");
		return mv;
	}
	
	@RequestMapping(value="searchVideoDevice")
	@ResponseBody
	@Logger(action = "查询视频设备信息", logger = "视频设备列表")
	public Map<String, Object> searchVideoDevice(VideoDevice videoDevice_param){
		
		PageRequest pageRequest=buildPageRequest();
		Page<Map<String,String>> page=service.searchVideoDevice(videoDevice_param,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	/**
	* @methodName: simpleVideoDeviceList
	* @Description: 简单视频设备列表（供combobox使用）
	* @param vdiCusNumber
	* @return List<Map<String,Object>>    
	* @throws
	*/
	@RequestMapping(value="simpleVideoDeviceList")
	@ResponseBody
	public List<Map<String,Object>> simpleVideoDeviceList(String vdiCusNumber){
		return service.simpleVideoDeviceList(vdiCusNumber);
	}
	
}
