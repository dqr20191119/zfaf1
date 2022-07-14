package com.cesgroup.prison.jfsb.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jfsb.dao.TalkBackBaseMapper;
import com.cesgroup.prison.jfsb.entity.TalkBackBaseEntity;
import com.cesgroup.prison.jfsb.service.TalkBackBaseService;
import com.cesgroup.prison.utils.DataUtils;

/**      
* @projectName：prison   
* @ClassName：TalkBackBaseController   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:34:27   
* @version        
*/
@Controller
@RequestMapping(value = "/talkBackBase")
public class TalkBackBaseController extends
		BaseEntityDaoServiceCRUDController<TalkBackBaseEntity, String, TalkBackBaseMapper, TalkBackBaseService> {

	@Resource
	private TalkBackBaseService service;

	/**
	* @methodName: openDialog
	* @Description: type 0、主页  1、增加 2、修改  3、显示详细
	* @param request
	* @param response
	* @return ModelAndView
	* @throws
	* @author：Tao.xu 
	* @date：2017年12月21日 下午3:51:49      
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = null;
		String type = request.getParameter("type");
		if (StringUtils.isNotBlank(type)) {
			if (type.equals("0")) {
				mv = new ModelAndView("jfsb/talkBack/base/list");
			} else {
				mv = new ModelAndView("jfsb/talkBack/base/edit");
			}
			mv.addObject("type", type);
			String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				mv.addObject("ID", id);
			}
		}
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询对讲分机信息")
	public Map<String, Object> listAll(TalkBackBaseEntity entity) throws Exception {
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
	public AjaxMessage update(TalkBackBaseEntity entity) {
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
	public AjaxMessage delete(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteById(id);
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
	public AjaxMessage inster(TalkBackBaseEntity entity) {
		return service.addInfo(entity);
	}

	/**
	* @methodName: findBaseByMain
	* @Description: 通过主机编号查询分机,实时对讲使用
	* @param tbdMainIdnty
	* @return List<Map<String,Object>>
	* @throws  
	*/
	@ResponseBody
	@RequestMapping(value = "/findBaseByMain")
	public List<Map<String, Object>> findBaseByMain(String tbdMainIdnty) {
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = service.findBaseByMain(tbdMainIdnty);
		return maps;
	}

	@RequestMapping(value = "/seachComboData")
	@ResponseBody
	public List<Map<String, Object>> seachComboData(String cusNumber, String areaId) throws Exception {
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = service.searchCombData(cusNumber, areaId);
		return maps;
	}
}
