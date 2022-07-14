package com.cesgroup.prison.jfsb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jfsb.dao.BroadcastFileDao;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;
import com.cesgroup.prison.jfsb.service.BroadcastFileService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * 广播曲目维护控制器
 * 
 * @author lincoln.cheng
 *
 */
@Controller
@RequestMapping(value = "broadcastFile")
public class BroadcastFileController extends BaseEntityDaoServiceCRUDController<BroadcastFile, String, BroadcastFileDao, BroadcastFileService> {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BroadcastFileController.class);

	/**
	 * 曲目列表页面
	 * @return
	 */
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("jfsb/broadcast/file/list");
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
		ModelAndView mv = new ModelAndView("jfsb/broadcast/file/save");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开修改窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(@RequestParam(value = "id", defaultValue = "", required = false) String id) {
		ModelAndView mv = new ModelAndView("jfsb/broadcast/file/update");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询广播曲目信息")
	public Map<String, Object> listAll(@RequestParam(value = "bfdName", defaultValue = "", required = false) String bfdName) {
		try {
			Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
			
			if(bfdName != null && !bfdName.isEmpty()) {
				paramMap.put("bfdName", bfdName);
			}
			
			PageRequest pageRequest = buildPageRequest();
			Page<BroadcastFile> page = this.getService().listAll(paramMap, pageRequest);
			return DataUtils.pageToMap(page);
		} catch (BusinessLayerException e) {
			logger.error("分页查询广播曲目信息发生异常");
			return null;
		}
	}

	@RequestMapping(value = "/queryById")
	@ResponseBody
	public AjaxMessage queryById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.queryById(id);
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

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public AjaxMessage saveOrUpdate(BroadcastFile entity) {
		try {
			return this.getService().saveOrUpdate(entity);
		} catch (BusinessLayerException e) {
			AjaxMessage ajaxMessage = new AjaxMessage();
			ajaxMessage.setSuccess(false);
			ajaxMessage.setMsg("新增或修改广播曲目发生异常");
			return ajaxMessage;
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(@RequestBody List<String> id) {
		try {
			return service.deleteByIds(id);
		} catch (BusinessLayerException e) {
			AjaxMessage ajaxMessage = new AjaxMessage();
			ajaxMessage.setSuccess(false);
			ajaxMessage.setMsg("删除广播曲目发生异常");
			return ajaxMessage;
		}
	}

	/**
	 * 根据监狱编号获取广播曲目列表的下拉框格式数据
	 * 
	 * @param cusNumber
	 * @return
	 */
	@RequestMapping(value="/queryBroadcastFileCombobox")
	@ResponseBody
	public List<Map<String, Object>> queryBroadcastFileCombobox(@RequestParam(value = "cusNumber", defaultValue="", required = false) String cusNumber) {
    	List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			List<BroadcastFile> bfList = this.getService().queryByBfdCusNumber(cusNumber);
			if(bfList != null && bfList.size() > 0) {
				for(BroadcastFile bf : bfList) {
	    			Map<String, Object> map = new HashMap<String, Object>();
	    			map.put("value", bf.getId());
	    			map.put("text", bf.getBfdName());
	    			resultList.add(map);
	    		}
			}
		} catch (BusinessLayerException e) {
			return null;
		}
		return resultList;
	}
}
