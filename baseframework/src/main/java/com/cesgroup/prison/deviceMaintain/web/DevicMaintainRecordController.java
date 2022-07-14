package com.cesgroup.prison.deviceMaintain.web;

import java.util.HashMap;
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

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.deviceMaintain.dao.DeviceMaintainRecordMapper;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainRecordEntity;
import com.cesgroup.prison.deviceMaintain.service.DeviceMaintainRecordService;
import com.cesgroup.prison.deviceMaintain.service.impl.DeviceMaintainRecordServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

/**      
* @projectName：alarmSystem   
* @ClassName：DevicMaintainRecordController   
* @Description：   维修记录
* @author：Tao.xu   
* @date：2018年2月28日 上午10:28:41   
* @version        
*/
@Controller
@RequestMapping(value = "/deviceMaintain/record")
public class DevicMaintainRecordController extends
		BaseEntityDaoServiceCRUDController<DeviceMaintainRecordEntity, String, DeviceMaintainRecordMapper, DeviceMaintainRecordServiceImpl> {

	@Resource
	private DeviceMaintainRecordService service;

	/**
	 * 雁南监狱统计设备异常数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findYnSbyc")
	@ResponseBody
	public Map<String, Object> findYnSbyc() throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.findYnSbyc(pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(DeviceMaintainRecordEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
		return DataUtils.pageToMap(page);
	}

	/**
	* @methodName: findDeviceList
	* @Description: 根据区域查询待关联的设备信息
	* @param type   1、摄像头  2、对讲分机  3、 报警器  4、门禁   5、广播   6、对讲主机   7、高压电网
	* @param cusNumber 监狱号
	* @param areaId 区域编号
	* @throws Exception Map<String,Object>
	*/
	@RequestMapping(value = "/findDeviceList")
	@ResponseBody
	public Object findDeviceList(String type, String cusNumber, String areaId) {
		Map<String, Object> map = new HashMap<>();
		Object obj = service.findDeviceList(type, cusNumber, areaId);
		map.put("data", obj);
		return map;
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			DeviceMaintainRecordEntity entity = service.findById(id);
			flag = true;
			obj = entity;
		} catch (Exception e) {
			flag = false;
			obj = "查询维修记录发生异常";
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
	public AjaxMessage updateRecord(DeviceMaintainRecordEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateRecordInfo(entity);
			flag = true;
			obj = "修改维修记录成功";
		} catch (Exception e) {
			flag = false;
			obj = "修改维修记录发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public AjaxMessage insertRecord(@RequestBody List<Map<String, Object>> devices) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.addRecordInfo(devices);
			flag = true;
			obj = "添加成功";
		} catch (Exception e) {
			flag = false;
			obj = "添加维修记录发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(@RequestBody List<String> id, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleteByIds(id);
			flag = true;
			obj = "删除维修记录成功";
		} catch (Exception e) {
			flag = false;
			obj = "删除维修记录发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/openList")
	public ModelAndView openList() {
		ModelAndView mv = new ModelAndView("deviceMaintain/deviceRecord/list");
		return mv;
	}
	
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/deviceRecord/index");
		return mv;
	}

	@RequestMapping(value = "/openDialog/save")
	public ModelAndView openSaveDialog() {
		ModelAndView mv = new ModelAndView("deviceMaintain/deviceRecord/save");
		return mv;
	}

	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(String id) {
		ModelAndView mv = new ModelAndView("deviceMaintain/deviceRecord/update");
		mv.addObject("ID", id);
		return mv;
	}

}
