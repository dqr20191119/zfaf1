package com.cesgroup.prison.jfsb.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.cesgroup.prison.jfsb.dao.DoorControlMapper;
import com.cesgroup.prison.jfsb.entity.DoorControlEntity;
import com.cesgroup.prison.jfsb.service.DoorControlService;
import com.cesgroup.prison.utils.DataUtils;

/**      
* @projectName：prison   
* @ClassName：DoorCtlController   
* @Description： 门禁控制器对应控制层  
* @author：Tao.xu   
* @date：2017年12月13日 下午6:27:42   
* @version        
*/
@Controller
@RequestMapping(value = "/doorControl")
public class DoorCtrlController
		extends BaseEntityDaoServiceCRUDController<DoorControlEntity, String, DoorControlMapper, DoorControlService> {

	@Resource
	private DoorControlService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("jfsb/door/control/list");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开增加窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/save")
	public ModelAndView openAddDialog() {
		ModelAndView mv = new ModelAndView("jfsb/door/control/save");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开修改窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(String id) {
		ModelAndView mv = new ModelAndView("jfsb/door/control/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询门禁控制器信息")
	public Map<String, Object> listAll(DoorControlEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/seachComboData")
	@ResponseBody
	@Logger(action = "控件下拉查询", logger = "查询门禁控制器信息")
	public List<Map<String, Object>> seachComboData(String cusNumber) throws Exception {
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = service.searchCombData(cusNumber);
		return maps;
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "查询", logger = "根据ID查询门禁控制器信息")
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.findById(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/update/info")
	@ResponseBody
	public AjaxMessage update(DoorControlEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			flag = true;
			obj = "修改成功";
		} catch (Exception e) {
			flag = false;
			obj = "修改发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(@RequestBody List<String> id, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteByIds(id);
			flag = true;
			obj = "删除成功";

		} catch (Exception e) {
			flag = false;
			obj = "删除发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public AjaxMessage inster(DoorControlEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.addInfo(entity);
			flag = true;
			obj = "保存成功";
		} catch (Exception e) {
			flag = false;
			obj = "保存发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}
}
