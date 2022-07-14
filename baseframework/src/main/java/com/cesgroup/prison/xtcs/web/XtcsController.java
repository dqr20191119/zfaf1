package com.cesgroup.prison.xtcs.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xtcs.dao.XtcsMapper;
import com.cesgroup.prison.xtcs.entity.XtcsEntity;
import com.cesgroup.prison.xtcs.service.XtcsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @projectName：prison
 * @ClassName：AlertorController
 * @Description：
 * @author：Tao.xu
 * @date：2017年12月12日 下午4:05:30
 */
@Controller
@RequestMapping(value = "/xtcs")
public class XtcsController extends BaseEntityDaoServiceCRUDController<XtcsEntity, String, XtcsMapper, XtcsService> {
	/**
	 * @return ModelAndView
	 * @throws
	 * @methodName: openDialog
	 * @Description: 打开窗口
	 */
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("xtcs/index");
		return mv;
	}

	/**
	 * Description: 打开更新页面
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
		String id = request.getParameter("id");

		ModelAndView mv = new ModelAndView("xtcs/edit");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value="/queryWithPage")
	@ResponseBody
	@Logger(action = "查询系统参数", logger = "系统参数列表", model = "系统参数管理")
	public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
		String csbm = request.getParameter("csbm");//参数编码
		String csmc = request.getParameter("csmc");//参数名称

		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
		if (csbm != null && !"".equals(csbm)) {
			paramMap.put("csbm", csbm);
		}
		if (csmc != null && !"".equals(csmc)) {
			paramMap.put("csmc", csmc);
		}
		try {
			Page<Map<String, Object>> pageInfo = (Page<Map<String, Object>>) this.getService().queryWithPage(paramMap, buildPageRequest());
			return DataUtils.pageToMap(pageInfo);
		} catch (ServiceException e) {
			throw new WebUIException(e.getMessage());
		} catch (Exception e) {
			throw new WebUIException(e.getMessage());
		}
	}

	/**
	 * 根据主键ID，查询系统参数信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryById")
	@ResponseBody
	@Logger(action = "查询", logger = "根据 ID 查询系统参数信息", model = "系统参数管理")
	public AjaxMessage queryById(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();

		try {
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				result = this.getService().queryById(id);
				flag = true;
			} else {
				flag = false;
				result = "查询失败 ";
			}
		} catch (ServiceException e) {
			flag = false;
			result = "查询失败：" + e.getMessage();
		} catch (Exception e) {
			flag = false;
			result = "查询失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg((String) result);
		}
		ajaxMsg.setSuccess(flag);

		return ajaxMsg;
	}

	/**
	 * 根据主键ID，查询系统参数信息
	 *
	 * @param xtcsEntity
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	@Logger(action = "新增或更新", logger = "新增或更新系统参数", model = "系统参数管理")
	public AjaxMessage saveOrUpdate(XtcsEntity xtcsEntity) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			this.getService().saveOrUpdate(xtcsEntity);
			flag = true;
		} catch (ServiceException e) {
			flag = false;
			result = "系统参数操作失败：" + e.getMessage();
		} catch (Exception e) {
			flag = false;
			result = "系统参数操作失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg((String) result);
		}
		ajaxMsg.setSuccess(flag);

		return ajaxMsg;
	}

	/**
	 * 删除系统参数信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	@Logger(action = "删除", logger = "删除系统参数", model = "系统参数管理")
	public AjaxMessage deleteByIds(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			String ids = request.getParameter("ids");
			this.getService().deleteByIds(ids);
			flag = true;
		} catch (ServiceException e) {
			flag = false;
			result = "删除系统参数失败：" + e.getMessage();
		} catch (Exception e) {
			flag = false;
			result = "删除系统参数失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg((String) result);
		}
		ajaxMsg.setSuccess(flag);

		return ajaxMsg;
	}

	/**
	 * 根据参数编码，查询系统参数信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryCszByCsbm")
	@ResponseBody
	@Logger(action = "查询", logger = "根据 参数编码 查询系统参数值信息", model = "系统参数管理")
	public AjaxMessage queryCszByCsbm(HttpServletRequest request, HttpServletResponse response) {
		String csbm = request.getParameter("csbm");// 参数编码

		try {
			return new AjaxMessage(true, this.getService().queryCzsByCsbm(csbm));
		} catch (ServiceException e) {
			return new AjaxMessage(false, null, e.getMessage());
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}
}
