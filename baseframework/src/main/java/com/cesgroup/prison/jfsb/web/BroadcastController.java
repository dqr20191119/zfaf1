package com.cesgroup.prison.jfsb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.framework.exception.BusinessLayerException;
import org.slf4j.LoggerFactory;
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
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.BroadcastMapper;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;
import com.cesgroup.prison.jfsb.service.BroadcastService;
import com.cesgroup.prison.utils.DataUtils;

/**      
* @projectName：prison   
* @ClassName：BroadcastController   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:34:27   
* @version        
*/
@Controller
@RequestMapping(value = "/broadcast")
public class BroadcastController extends BaseEntityDaoServiceCRUDController<BroadcastEntity, String, BroadcastMapper, BroadcastService> {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BroadcastController.class);

	@Resource
	private BroadcastService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("jfsb/broadcast/list");
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
		ModelAndView mv = new ModelAndView("jfsb/broadcast/save");
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
		ModelAndView mv = new ModelAndView("jfsb/broadcast/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询广播信息")
	public Map<String, Object> listAll(BroadcastEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);

		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.findById(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询广播发生异常！";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	 * 根据广播设备ID，查询广播数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findBroadcastList")
	@ResponseBody
	public AjaxMessage findBroadcastList(String ids) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = this.getService().findBroadcastList(ids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			obj = "查询广播发生异常！";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}
	
	/**
	 * 根据广播设备ID，查询广播数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryBroadcastDtoById")
	@ResponseBody
	public AjaxMessage queryBroadcastDtoById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = this.getService().queryBroadcastEntityDtoById(id);
			flag = true;
		} catch (BusinessLayerException e) {
			flag = false;
			obj = "查询广播发生异常！";
		} catch (Exception e) {
			flag = false;
			obj = "查询广播发生异常！";
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
	public AjaxMessage update(BroadcastEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			flag = true;
			obj = "修改成功";
		} catch (Exception e) {
			flag = false;
			obj = "修改广播发生异常！";
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
			obj = "删除广播发生异常！";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public AjaxMessage inster(BroadcastEntity entity) {
		return service.addInfo(entity);
	}
}
