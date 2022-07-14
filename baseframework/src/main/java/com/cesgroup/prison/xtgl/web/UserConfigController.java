package com.cesgroup.prison.xtgl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.support.NoModel;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xtgl.dao.UserConfigMapper;
import com.cesgroup.prison.xtgl.entity.UserConfig;
import com.cesgroup.prison.xtgl.service.impl.UserConfigServiceImpl;

@Controller
@RequestMapping(value="/xtgl/userConfig")
public class UserConfigController extends BaseEntityDaoServiceCRUDController<UserConfig,String,UserConfigMapper,UserConfigServiceImpl>{

	@Logger(action = "添加", logger = "${id}")
    @RequestMapping(value = "/insert")
	@ResponseBody
    public AjaxMessage insert(@NoModel final UserConfig model){
		try {
			UserConfig userConfig=getService().insert(model);
			return new AjaxMessage(true,userConfig.getId());
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
	
	@Logger(action = "删除", logger = "${id}")
    @RequestMapping(value = "/delete")
	@ResponseBody
    public AjaxMessage delete(final String id){
		try {
			getService().delete(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
	
	@RequestMapping(value="/updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(UserConfig model){
		try {
			getService().updatePart(model);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	
	
	@RequestMapping("/findByUcUserId")
	@ResponseBody
	public AjaxMessage findByUcUserId(String ucUserId) {
		try {
			UserConfig userConfig=service.findByUcUserId(ucUserId);
			return new AjaxMessage(true,userConfig);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
}
