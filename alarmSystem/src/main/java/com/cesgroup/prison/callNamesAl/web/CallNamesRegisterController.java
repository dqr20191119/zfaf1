package com.cesgroup.prison.callNamesAl.web;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.callNamesAl.dao.CallNamesRegisterMapper;
import com.cesgroup.prison.callNamesAl.entity.CallNamesRegisterEntity;
import com.cesgroup.prison.callNamesAl.service.CallNamesRegisterService;
import com.cesgroup.prison.common.cache.CallNamesPrisonerDtls;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.scrap.db.QueryProcess;

@Controller
@RequestMapping(value = "/callNames/register")
public class CallNamesRegisterController extends
		BaseEntityDaoServiceCRUDController<CallNamesRegisterEntity, String, CallNamesRegisterMapper, CallNamesRegisterService> {

	@Resource
	private CallNamesRegisterService service;

	@Autowired
	protected QueryProcess queryProcess;

	@RequestMapping("/openDialog")
	public ModelAndView openDialog(String id) {
		ModelAndView mv = new ModelAndView("callNamesAl/register/index");
		return mv;
	}

	@RequestMapping(value = "/openDialog/add")
	public ModelAndView openAddDialog() {
		ModelAndView mv = new ModelAndView("callNamesAl/register/add");
		return mv;
	}

	@RequestMapping(value = "/openDialog/uploadAndRegister")
	public ModelAndView openUpdateDialog(String id) {
		ModelAndView mv = new ModelAndView("callNamesAl/register/uploadAndRegister");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(CallNamesRegisterEntity entity) throws Exception {
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
			obj = "查询注册信息发生异常";
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
	public AjaxMessage update(CallNamesRegisterEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "修改注册信息发生异常";
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
	public AjaxMessage deleteByIds(@RequestBody Map<String, List<String>> data, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteByIds(data.get("ids"));
			RedisCache.getTemplate().opsForHash().delete( CallNamesPrisonerDtls.tableName + "_HASH", data.get("zfbhs").toArray());
			flag = true;
			obj = "删除成功";
		} catch (Exception e) {
			flag = false;
			obj = "删除注册信息发生异常";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage logout(@RequestBody List<String> ids, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.logout(ids);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "注销发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/saveInfo")
	public AjaxMessage inster(@RequestBody List<String> prisoner) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			obj = service.addInfo(prisoner);
		} catch (Exception e) {
			flag = false;
			obj = "添加注册信息发生异常";
		}

		ajaxMessage.setMsg((String) obj);

		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/refresh")
	public AjaxMessage refresh(String cusNumber) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		String msg = null;
		List<Object> params = new ArrayList<>();
		params.add(cusNumber);
		params.add(cusNumber);

		try {
			RedisCache.putHash(CallNamesPrisonerDtls.tableName, new String[] { CallNamesPrisonerDtls.ZFBH }, null,
					queryProcess.process(CallNamesPrisonerDtls.sqlId, CallNamesPrisonerDtls.whereId, "", params));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			msg = "操作成功";
		} else {
			msg = "操作失败";
		}
		ajaxMessage.setSuccess(flag);
		ajaxMessage.setMsg(msg);
		return ajaxMessage;
	}

	@RequestMapping(value = "/save/file")
	@ResponseBody
	public AjaxMessage addPrisonerPicFile(String id, String file) {
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if (StringUtils.isNotBlank(file)) {
				result = service.addPrisonerPicFile(id, file);
				if (result.indexOf("失败") == -1) {
					flag = true;
				}
			} else {
				flag = false;
				result = "操作失败,参数错误";
			}
		} catch (Exception e) {
			flag = false;
			result = "操作失败：" + e.getMessage();
		}
		ajaxMsg.setMsg(result);
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}

	@RequestMapping(value = "/findPrisonerPicFile")
	@ResponseBody
	public AjaxMessage findPrisonerPicFile(String id) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			flag = true;
			result = service.findPrisonerPicFile(id);
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
	* @methodName: findJsForTree
	* @Description: 区域监舍异步树
	* @param request
	* @param response
	* @return List<Map<String,Object>>
	* @throws  
	*/
	@RequestMapping("/findJsForTree")
	@ResponseBody
	public List<Map<String, Object>> findJsForTree(HttpServletRequest request, HttpServletResponse response) {
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("id", request.getParameter("id"));
		return service.findForTree(paramMap);
	}

	@RequestMapping("/findPrisonerByJs")
	@ResponseBody
	public List<Map<String, Object>> findPrisonerByJs(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("lch", request.getParameter("lch"));
		paramMap.put("jsh", request.getParameter("jsh"));
		return service.findPrisonerByJs(paramMap);
	}

	@RequestMapping("/findRegisterPrisonerByJs")
	@ResponseBody
	public List<Map<String, Object>> findRegisterPrisonerByJs(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("lch", request.getParameter("lch"));
		paramMap.put("jsh", request.getParameter("jsh"));
		return service.findRegisterPrisonerByJs(paramMap);
	}

	/**
	* @methodName: getZfPicInfo
	* @Description: 获取罪犯照片
	* @param request
	* @param response
	* @return
	* @throws Exception List<Map<String,Object>>
	* @throws  
	*/
	@RequestMapping(value = "/getZfPicInfo")
	@ResponseBody
	public List<Map<String, Object>> getZfPicInfo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String imgUrl = request.getParameter("imgUrl");
		String imgName = request.getParameter("imgName");
		String imgSize = request.getParameter("imgSize");

		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(imgName, "utf-8"));
		response.addHeader("Content-Length", imgSize);
		response.setContentType("application/octet-stream");

		if (StringUtils.isNotBlank(imgUrl)) {
			String realPath = "";
			if (imgUrl.indexOf(CommonConstant.uploadZhddGlobalPath) == -1) {
				realPath = "Z:" + imgUrl.replaceAll("/", "\\\\");// 系统文件全局上传根路径
			} else {
				realPath = CommonConstant.jggzUploadsRootPath + imgUrl.replaceAll("/", "\\\\");
			}
			IOUtils.copy(new FileInputStream(new File(realPath)), response.getOutputStream());
		}

		return null;
	}

}
