package com.cesgroup.prison.alarm.level.web;

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
import com.cesgroup.prison.alarm.level.dao.AlarmTypeAndLevMapper;
import com.cesgroup.prison.alarm.level.entity.AlarmTypeAndLevEntity;
import com.cesgroup.prison.alarm.level.service.AlarmTypeAndLevService;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;

/**      
* @projectName：prison   
* @ClassName：AlarmTypeAndLevController   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:34:27   
* @version        
*/
@Controller
@RequestMapping(value = "/alarmTypeAndLev")
public class AlarmTypeAndLevController extends
		BaseEntityDaoServiceCRUDController<AlarmTypeAndLevEntity, String, AlarmTypeAndLevMapper, AlarmTypeAndLevService> {

	@Resource
	private AlarmTypeAndLevService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("/alarm/level/index");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开增加窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/add")
	public ModelAndView openAddDialog() {
		ModelAndView mv = new ModelAndView("alarm/level/add");
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
		ModelAndView mv = new ModelAndView("alarm/level/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询报警类别关联等级信息")
	public Map<String, Object> listAll(AlarmTypeAndLevEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "查询", logger = "根据ID查询报警类别关联等级信息")
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.findById(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询报警类型发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/updateInfo")
	@ResponseBody
	public AjaxMessage update(AlarmTypeAndLevEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "修改报警类型发生异常";
		}
		if (flag) {

		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteByIds(@RequestBody List<String> id, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteByIds(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "删除报警类型发生异常";
		}
		if (flag) {
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/saveInfo")
	public AjaxMessage inster(AlarmTypeAndLevEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			AlarmTypeAndLevEntity alarmTypeAndLevEntity = new AlarmTypeAndLevEntity();
			alarmTypeAndLevEntity.setAltTypeId(entity.getAltTypeId());
			alarmTypeAndLevEntity.setAltCusNumber(AuthSystemFacade.getLoginUserInfo().getCusNumber());
			List<AlarmTypeAndLevEntity> list = service.findByCusNumberAndType(alarmTypeAndLevEntity);
			if (!list.isEmpty()) {
				obj = "报警类型重复添加！";
				flag = false;
				ajaxMessage.setMsg((String) obj);
				ajaxMessage.setSuccess(flag);
				return ajaxMessage;
			}
			service.addInfo(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "添加报警类型发生异常";
		}
		if (flag) {
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}
}
