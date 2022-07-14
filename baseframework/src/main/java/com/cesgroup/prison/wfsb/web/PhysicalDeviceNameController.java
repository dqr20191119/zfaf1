package com.cesgroup.prison.wfsb.web;

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
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.wfsb.dao.PhysicalDeviceNameMapper;
import com.cesgroup.prison.wfsb.entity.PhysicalDeviceName;
import com.cesgroup.prison.wfsb.service.IPhysicalDeviceNameService;
import com.cesgroup.prison.wfsb.service.impl.PhysicalDeviceNameServiceImpl;

/**   
*    
* @projectName：prison   
* @ClassName：PhysicalDeviceNameController   
* @Description：   物防设备名称
* @author：zhengke   
* @date：2017-12-13 19:58   
* @version        
*/
@Controller
@RequestMapping(value="/wfsb/physicalDeviceName")
public class PhysicalDeviceNameController extends BaseEntityDaoServiceCRUDController<PhysicalDeviceName,String,PhysicalDeviceNameMapper,PhysicalDeviceNameServiceImpl>{

	@Autowired
	private IPhysicalDeviceNameService service;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("wfsb/physicalDeviceName/list");
		return mv;
	}
	
	@RequestMapping(value="/searchPhysicalDeviceName")
	@ResponseBody
	@Logger(action = "查询物理设备名称信息", logger = "物理设备名称列表")
	public Map<String, Object> searchPhysicalDeviceName(PhysicalDeviceName physicalDeviceName_param){
		PageRequest pageRequest=buildPageRequest();
		Page<Map<String,String>> page=service.searchPhysicalDeviceName(physicalDeviceName_param,pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value="/simplePhysicalDeviceName")
	@ResponseBody
	public List<Map<String,Object>> simplePhysicalDeviceName(String pdnCusNumber){
		return service.simplePhysicalDeviceName(pdnCusNumber);
	}
	@RequestMapping(value="/updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(PhysicalDeviceName physicalDeviceName_param){
		try {
			service.updatePart(physicalDeviceName_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
}
