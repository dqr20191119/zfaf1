package com.cesgroup.prison.prisonPath.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cesgroup.prison.prisonPath.dao.PrisonPathMapper;
import com.cesgroup.prison.prisonPath.entity.PrisonPathCameraRltnEntity;
import com.cesgroup.prison.prisonPath.entity.PrisonPathEntity;
import com.cesgroup.prison.prisonPath.service.PrisonPathService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/prisonPath")
public class PrisonPathController
		extends BaseEntityDaoServiceCRUDController<PrisonPathEntity, String, PrisonPathMapper, PrisonPathService> {

	@Resource
	private PrisonPathService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("/prisonPath/index");
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
		ModelAndView mv = new ModelAndView("prisonPath/add");
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
		ModelAndView mv = new ModelAndView("prisonPath/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(PrisonPathEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}
	/**
	 * 根据区域id获取路线列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findPathInfoForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findPathInfoForCombobox(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		paramMap.put("sflDprtmntId",request.getParameter("sflDprtmntId"));
		return service.findPathByAreaIdForCombobox(paramMap);
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

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteByIds(@RequestBody List<String> id, HttpServletRequest request) {
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

	@RequestMapping(value = "/delete/camera", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteCameraByIds(@RequestBody List<String> id, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteCameraByIds(id);
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
	@RequestMapping(value = "/saveInfo")
	public AjaxMessage saveInfo(@RequestBody Object pathData) {
		return service.addInfo(pathData);
	}

	@ResponseBody
	@RequestMapping(value = "/updateInfo")
	public AjaxMessage updateInfo(@RequestBody Object pathData) {
		return service.updateInfo(pathData);
	}

	@RequestMapping(value = "/listAllForSx")
	@ResponseBody
	public Map<String, Object> listAllForSx(String cusNumber, String areaId, String pathId, String cameraName)
			throws Exception {
		List<Map<String, Object>> list = (List<Map<String, Object>>) service.listAllForSx(cusNumber, areaId, pathId,
				cameraName);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		return map;
	}

	@RequestMapping(value = "/listAllForPrisonPathCamera")
	@ResponseBody
	public Map<String, Object> listAllForPrisonPathCamera(PrisonPathCameraRltnEntity entity) throws Exception {
		List<Map<String, Object>> list = (List<Map<String, Object>>) service.listAllForPrisonPathCamera(entity);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		return map;

	}

}
