package com.cesgroup.prison.jfsb.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jfsb.dao.TalkBackServerMapper;
import com.cesgroup.prison.jfsb.entity.TalkBackServerEntity;
import com.cesgroup.prison.jfsb.service.TalkBackServerService;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**      
* @projectName：prison   
* @ClassName：TalkBackServerController   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:34:27   
* @version        
*/
@Controller
@RequestMapping(value = "/talkBackServer")
public class TalkBackServerController extends
		BaseEntityDaoServiceCRUDController<TalkBackServerEntity, String, TalkBackServerMapper, TalkBackServerService> {

	@Resource
	private TalkBackServerService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("jfsb/talkBack/list");
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
		ModelAndView mv = new ModelAndView("jfsb/talkBack/save");
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
		ModelAndView mv = new ModelAndView("jfsb/talkBack/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询对讲主机信息")
	public Map<String, Object> listAll(TalkBackServerEntity entity) throws Exception {
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

	@RequestMapping(value = "/seachTreeData")
	@ResponseBody
	@Logger(action = "树查询", logger = "查询对讲主机信息")
	public Map<String, Object> seachTreeData(String cusNumber) throws Exception {
		Map<String, Object> map = service.searchTreeData(cusNumber);
		return map;
	}

	@RequestMapping(value = "/update/info")
	@ResponseBody
	@Logger(action = "修改", logger = "修改对讲主机信息")
	public AjaxMessage update(TalkBackServerEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			return service.updateInfo(entity);
		} catch (Exception e) {
			flag = false;
			obj = "修改主机发生异常";
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
	public AjaxMessage inster(TalkBackServerEntity entity) {
		return service.addInfo(entity);
	}

	/**
	* @methodName: findByCusNumber
	* @Description: 实时对讲处使用
	* @param cusNumber
	* @return
	* @throws Exception List<Map<String,Object>>
	* @throws  
	*/
	@RequestMapping(value = "/findByCusNumber")
	@ResponseBody
	public List<Map<String, Object>> findByCusNumber(String cusNumber) throws Exception {
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = service.findByCusNumber(cusNumber);
		return maps;
	}
}
