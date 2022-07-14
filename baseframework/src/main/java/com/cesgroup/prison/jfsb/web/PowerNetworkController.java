package com.cesgroup.prison.jfsb.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jfsb.dao.PowerNetworkMapper;
import com.cesgroup.prison.jfsb.entity.PowerNetwork;
import com.cesgroup.prison.jfsb.service.PowerNetworkService;
import com.cesgroup.prison.jfsb.service.impl.PowerNetworkServiceImpl;
import com.cesgroup.prison.utils.DataUtils;


/**   
*      
* @ClassName：PowerNetworkController   
* @Description：   
* @author：zhengk   
* @date：2018年1月16日 下午1:32:55        
*/
@Controller
@RequestMapping(value="/jfsb/powerNetwork")
public class PowerNetworkController extends BaseEntityDaoServiceCRUDController<PowerNetwork,String,PowerNetworkMapper,PowerNetworkServiceImpl>{

	@Autowired
	private PowerNetworkService service;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("jfsb/powerNetwork/list");
		return mv;
	}
	
	@RequestMapping(value="/searchPowerNetwork")
	@ResponseBody
	@Logger(action = "查询电网信息", logger = "电网列表")
	public Map<String, Object> searchPowerNetwork(PowerNetwork powerNetwork_param){
		PageRequest pageRequest=buildPageRequest();
		Page<Map<String,String>> page=service.searchPowerNetwork(powerNetwork_param,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	//局部修改
	@RequestMapping(value = "/updatePart")
	@ResponseBody
	public AjaxMessage updatePart(PowerNetwork powerNetwork_param){
		try {
			service.updatePart(powerNetwork_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping(value="/findPowerNetworkInfo")
	@ResponseBody
	public List<Map<String, Object>> findPowerNetworkInfo(PowerNetwork powerNetwork_param){
		return service.findPowerNetworkInfo(powerNetwork_param);
	}
	@RequestMapping(value="/findCountByPnbCusNumber")
	@ResponseBody
	public AjaxMessage findCountByPnbCusNumber(String cusNumber) {
		try {
			Integer count = service.findCountByPnbCusNumber(cusNumber);
			return new AjaxMessage(true,count);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
}
