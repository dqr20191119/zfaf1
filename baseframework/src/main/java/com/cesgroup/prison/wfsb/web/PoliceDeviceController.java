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
import com.cesgroup.prison.wfsb.dao.PoliceDeviceMapper;
import com.cesgroup.prison.wfsb.entity.PoliceDevice;
import com.cesgroup.prison.wfsb.service.IPoliceDeviceService;
import com.cesgroup.prison.wfsb.service.impl.PoliceDeviceServiceImpl;

/**   
*    
* @projectName：prison   
* @ClassName：PoliceDeviceController   
* @Description：   警用器材
* @author：zhengke   
* @date：2017-12-13 19:58   
* @version        
*/
@Controller
@RequestMapping(value="/wfsb/policeDevice")
public class PoliceDeviceController extends BaseEntityDaoServiceCRUDController<PoliceDevice,String,PoliceDeviceMapper,PoliceDeviceServiceImpl>{

	@Autowired
	private IPoliceDeviceService service;

	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("wfsb/policeDevice/list");
		return mv;
	}
	
	@RequestMapping(value="/searchPoliceDevice")
	@ResponseBody
	@Logger(action = "查询警用器材信息", logger = "警用器材列表")
	public Map<String, Object> searchPoliceDevice(PoliceDevice policeDevice_param){
		PageRequest pageRequest=buildPageRequest();
		Page<Map<String,String>> page=service.searchPoliceDevice(policeDevice_param,pageRequest);
		return DataUtils.pageToMap(page);
	}
	@RequestMapping(value="/updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(PoliceDevice policeDevice_param){
		try {
			service.updatePart(policeDevice_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
}
