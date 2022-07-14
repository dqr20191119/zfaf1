package com.cesgroup.prison.jfsb.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.DoorOpenMapper;
import com.cesgroup.prison.jfsb.entity.DoorOpenEntity;
import com.cesgroup.prison.jfsb.service.DoorOpenService;
import com.cesgroup.prison.utils.DataUtils;
@Controller
@RequestMapping(value = "/dooropen")
public class DoorOpenController extends BaseEntityDaoServiceCRUDController<DoorOpenEntity, String, DoorOpenMapper, DoorOpenService>{
	
	@Resource
	private DoorOpenService service;
	
	

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("jfsb/door/open/list");
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
		ModelAndView mv = new ModelAndView("jfsb/door/open/save");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开修改窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView("jfsb/door/open/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询门禁信息")
	public Map<String, Object> listAll(DoorOpenEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page =null;
		try {
			page = (Page<Map<String, Object>>) service.listAll(entity,pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DataUtils.pageToMap(page);
	}

	
	@RequestMapping(value = "/getById")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询门禁信息")
	public AjaxMessage getById(DoorOpenEntity entity) {
		DoorOpenEntity dooropen = service.getByjyid(entity.getJyid());
		AjaxMessage ajaxMessage =null;
		if(dooropen!=null) {
			ajaxMessage = new AjaxMessage(true,dooropen);
		}else {
			ajaxMessage = new AjaxMessage(false);
		}
		return ajaxMessage;
	}
	
	
	@RequestMapping(value = "/updateDoor")
	@ResponseBody
	@Logger(action = "修改", logger = "修改门禁信息")
	public AjaxMessage update(DoorOpenEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			flag = true;
			obj = "修改成功";
		} catch (Exception e) {
			flag = false;
			obj = "修改异常： " + e.getMessage();
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(@RequestParam("id") String id, HttpServletRequest request) {
		AjaxMessage ajaxMessage = null;
		try {
			DoorOpenEntity dooropen = service.selectOne(id);
			ajaxMessage = new AjaxMessage(true,dooropen);
			return ajaxMessage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public AjaxMessage inster(DoorOpenEntity entity) {
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			entity.setJyid(user.getCusNumber());
			DoorOpenEntity byjyid = service.getByjyid(entity.getJyid());
			if(byjyid!=null) {
				entity.setId(byjyid.getId());
				service.updateInfo(entity);
			}else {
				 service.insert(entity);
			}
			 return	 new AjaxMessage(true,null,"保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		 return	new AjaxMessage(false,null,"保存失败");
		}
	}
}
