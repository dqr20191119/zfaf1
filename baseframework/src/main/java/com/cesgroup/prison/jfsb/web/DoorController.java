package com.cesgroup.prison.jfsb.web;

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
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.AreadeviceService;
import com.cesgroup.prison.jfsb.dao.DoorMapper;
import com.cesgroup.prison.jfsb.entity.DoorEntity;
import com.cesgroup.prison.jfsb.service.DoorService;
import com.cesgroup.prison.utils.DataUtils;

/**      
* @projectName：prison   
* @ClassName：DoorController   
* @Description：  门禁对应控制层   
* @author：Tao.xu   
* @date：2017年12月14日 上午10:54:58   
* @version        
*/
@Controller
@RequestMapping(value = "/door")
public class DoorController extends BaseEntityDaoServiceCRUDController<DoorEntity, String, DoorMapper, DoorService> {

	@Resource
	private DoorService service;

	@Resource
	private AreadeviceService areadeviceService;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("jfsb/door/list");
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
		ModelAndView mv = new ModelAndView("jfsb/door/save");
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
		ModelAndView mv = new ModelAndView("jfsb/door/update");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询门禁信息")
	public Map<String, Object> listAll(DoorEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);

		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "查询", logger = "根据ID查询门禁信息")
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.findById(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询异常： " + e.getMessage();
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/updateDoor")
	@ResponseBody
	@Logger(action = "修改", logger = "修改门禁信息")
	public AjaxMessage update(DoorEntity entity) {
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
			obj = "删除异常： " + e.getMessage();
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public AjaxMessage inster(DoorEntity entity) {
		return service.addInfo(entity);
	}

	/**
	* @methodName: allPrisonAreaCameraTree
	* @Description: 设备类型deviceType:	1-摄像头，2-对讲分机，3-报警器，4-门禁，5-广播，6-对讲主机，7-高压电网，20-地图标签，0-只查询区域
	* @param request
	* @param response
	* @return List<Map<String,Object>>
	* @throws  
	*/
	@RequestMapping("/findForTree")
	@ResponseBody
	public List<Map<String, Object>> allPrisonAreaCameraTree(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if (userBean.getUserLevel().equals(EUserLevel.PROV) || userBean.getUserLevel().equals(EUserLevel.PRIS)) {
				// 构造参数
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
				paramMap.put("id", request.getParameter("id"));
				return service.findForTree(paramMap);
			} else {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
				paramMap.put("id", request.getParameter("id")); // 节点id
				paramMap.put("dprtmntId", userBean.getDprtmntCode()); // 设备类型
				return service.findForJqTree(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
