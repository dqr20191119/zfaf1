package com.cesgroup.prison.sppz.web;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.sppz.dao.StreamServerMapper;
import com.cesgroup.prison.sppz.entity.StreamServer;
import com.cesgroup.prison.sppz.service.IStreamServerService;
import com.cesgroup.prison.sppz.service.impl.StreamServerServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

import io.netty.handler.codec.http.HttpRequest;

/**   
*    
* @projectName：prison   
* @ClassName：StreamServerController   
* @Description：  流媒体服务Controller 
* @author：zhengke   
* @date：2017-12-01 09:50   
* @version        
*/
@Controller
@RequestMapping(value="/sppz/streamServer")
public class StreamServerController extends BaseEntityDaoServiceCRUDController<StreamServer,String,StreamServerMapper,StreamServerServiceImpl>{
	@Autowired
	private IStreamServerService service;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("sppz/streamServer/list");
		return mv;
	}
	
	@RequestMapping(value="searchStreamServer")
	@ResponseBody
	@Logger(action = "查询流媒体服务信息", logger = "流媒体服务列表")
	public Map<String, Object> searchStreamServer(StreamServer streamServer_param){
		PageRequest pageRequest=buildPageRequest();
		Page<Map<String,String>> page=service.searchStreamServer(streamServer_param,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	/**
	* @methodName: simpleStreamServerList
	* @Description: 简单流媒体服务列表（供combobox使用）
	* @param ssiCusNumber
	* @return List<Map<String,Object>>    
	* @throws
	*/
	@RequestMapping(value="simpleStreamServerList")
	@ResponseBody
	public List<Map<String,Object>> simpleStreamServerList(String ssiCusNumber){
		return service.simpleStreamServerList(ssiCusNumber);
	}
	/**
	 * 批量删除流媒体
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteByIds",method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteByIds(@RequestBody List<String> id ,HttpServletRequest request) {
		try {
			service.deleteByIds(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}
	
}
