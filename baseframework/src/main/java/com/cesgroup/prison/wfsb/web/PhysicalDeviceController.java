package com.cesgroup.prison.wfsb.web;

import java.util.Map;

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
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.wfsb.dao.PhysicalDeviceMapper;
import com.cesgroup.prison.wfsb.entity.PhysicalDevice;
import com.cesgroup.prison.wfsb.service.IPhysicalDeviceService;
import com.cesgroup.prison.wfsb.service.impl.PhysicalDeviceServiceImpl;

/**   
*    
* @projectName：prison   
* @ClassName：PhysicalDeviceController   
* @Description：   物防设备
* @author：zhengke   
* @date：2017-12-13 19:58   
* @version        
*/
@Controller
@RequestMapping(value="/wfsb/physicalDevice")
public class PhysicalDeviceController extends BaseEntityDaoServiceCRUDController<PhysicalDevice,String,PhysicalDeviceMapper,PhysicalDeviceServiceImpl>{

	@Autowired
	private IPhysicalDeviceService service;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("wfsb/physicalDevice/list");
		return mv;
	}
	
	@RequestMapping(value="/searchPhysicalDevice")
	@ResponseBody
	@Logger(action = "查询物理设备信息", logger = "物理设备列表")
	public Map<String, Object> searchPhysicalDevice(PhysicalDevice physicalDevice_param){
		PageRequest pageRequest=buildPageRequest();
		Page<Map<String,String>> page=service.searchPhysicalDevice(physicalDevice_param,pageRequest);
		return DataUtils.pageToMap(page);
	}
	@RequestMapping(value="/updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(PhysicalDevice physicalDevice_param){
		try {
			service.updatePart(physicalDevice_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
}
