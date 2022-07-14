package com.cesgroup.prison.alarm.record.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.alarm.record.dao.AlarmMapper;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.service.AlarmService;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;


/**      
* @projectName：prison   
* @ClassName：AlarmController   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月4日 下午2:39:22   
* @version        
*/
@Controller
@CacheNamespace()
@RequestMapping(value = "/alarm")
public class AlarmController
		extends BaseEntityDaoServiceCRUDController<AlarmRecordEntity, String, AlarmMapper, AlarmService> {

	
	/**
	* @methodName: receiveAlarm
	* @Description: 快速处置 ， 和报警处置中的接警处置逻辑代码一样，为了方便之后修改，逻辑上区分开，拷贝一份，重新指向
	* @param id
	* @return ModelAndView
	* @throws  
	*/
	@RequestMapping("/openDialog/receive")
	public ModelAndView receiveAlarm(String id) {
		ModelAndView mv = new ModelAndView("alarm/record/receiveAlarm");
		mv.addObject("ID", id);
		return mv;
	}
	
	/**
	* @methodName: listAll
	* @Description: 查询报警记录
	* @param record
	* @return
	* @throws Exception Map<String,Object>    
	* @throws
	*/
	@RequestMapping(value = "/alarmRecordList")
	@ResponseBody
	@Logger(action = "查询", logger = "查询报警记录信息列表")
	public Map<String, Object> listAll(AlarmRecordEntity record, String startTime, String endTime, String dprtmntId,HttpServletRequest request,@RequestParam(value="type", defaultValue="", required=false) String type)
			throws Exception {
		PageRequest pageRequest = buildPageRequest();
		String startStr="";
		String Now="";
		if(!type.equals("")){
			Calendar calendar = new GregorianCalendar();  
		    calendar.add(Calendar.DAY_OF_MONTH,-1);  
		  
		    //一天的开始时间 yyyy:MM:dd 00:00:00  
		    calendar.set(Calendar.HOUR_OF_DAY,23);  
		    calendar.set(Calendar.MINUTE,59);  
		    calendar.set(Calendar.SECOND,59);  
		    calendar.set(Calendar.MILLISECOND,0);  
		    Date dayStart = calendar.getTime();  
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    startStr = simpleDateFormat.format(dayStart);
		  //当前时间 yyyy:MM:dd 00:00:00  
		    Date date = new Date(); 
		    Now = simpleDateFormat.format(date);
		}
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(record,startStr, Now,
				dprtmntId,type, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "查询", logger = "根据 ID 查询报警记录信息")
	public AjaxMessage findById(AlarmRecordEntity entity) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				Map<String, Object> entityMap = service.findAlarmRecordById(entity);
				flag = true;
				result = entityMap;
			} else {
				flag = false;
				result = "查询失败 ";
			}
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
	* @methodName: updateRecord
	* @Description: 修改报警记录
	* @param entity
	* @return Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/update/record")
	@ResponseBody
	@Logger(action = "修改", logger = "根据 ID 修改报警记录信息")
	public AjaxMessage updateRecord(AlarmRecordEntity entity) {
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if (StringUtils.isNotBlank(entity.getId())) {
				service.updateAlarmRecord(entity);
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			result = "修改失败：" + e.getMessage();
		}
		ajaxMsg.setMsg(result);
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}

	/**
	* @methodName: addAlertFile
	* @Description: 保存报警证据附件
	* @param id 报警记录id
	* @param files 文件id
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/save/file")
	@ResponseBody
	public AjaxMessage addAlertFile(String id, String files) {
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if (StringUtils.isNotBlank(files)) {
				service.addAlertFile(id, files);
			} else {
				if (service.findAlertFile(id).isEmpty()) {
					flag = false;
					result = "保存报警证据附件失败,请选择文件或关联证据信息";
					return new AjaxMessage(flag, null, result);
				}
			}
			AlarmRecordEntity alarmRecordEntity = new AlarmRecordEntity();
			alarmRecordEntity.setArdFileStts("1");
			alarmRecordEntity.setId(id);
			service.updateAlarmRecord(alarmRecordEntity);
			flag = true;
			result = "保存报警证据附件成功";
		} catch (Exception e) {
			flag = false;
			result = "保存报警证据附件失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}

	/**
	* @methodName: findAlertFile
	* @Description: 获得报警证据附件
	* @param id 报警记录id
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/findAlertFile")
	@ResponseBody
	public AjaxMessage findAlertFile(String id) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			flag = true;
			result = service.findAlertFile(id);

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
	* @methodName: glzjxx
	* @Description: 关联证据信息
	* @param id
	* @param ywId
	* @param fjfl
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/glzjxx")
	@ResponseBody
	public AjaxMessage glzjxx(HttpServletRequest request, HttpServletResponse response) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			@SuppressWarnings("unchecked")
			List<String> ids = (List<String>) (JSON.parse(request.getParameter("ids")));
			obj = service.glZjByzjIds(ids, request.getParameter("ywId"), request.getParameter("fjfl"));
		} catch (Exception e) {
			flag = false;
			obj = "关联失败，发生异常 ";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	* @methodName: openAlarmRecordDialog
	* @Description: 打开报警记录窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/record")
	public ModelAndView openAlarmRecordDialog(@RequestParam(value="type", defaultValue="", required=false) String type,@RequestParam(value="DpName", defaultValue="", required=false) String DpName) {
		ModelAndView mv = new ModelAndView("alarm/record/list");
				mv.addObject("type", type);//报警等级
				mv.addObject("DpName", DpName);//哪个监区报警
		return mv;
	}

	/**
	* @methodName: openFilesDialog
	* @Description:  打开归档窗口
	* @param id 报警记录id
	* @param type 0、未归档   否则就是已归档
	* @return ModelAndView
	* @throws  
	*/
	@RequestMapping(value = "/openDialog/files")
	public ModelAndView openFilesDialog(String id, String type) {
		ModelAndView mv = new ModelAndView("alarm/record/alarmEventReport");
		mv.addObject("id", id);
		mv.addObject("type", type);
		return mv;
	}

	/**
	* @methodName: openZjxxDialog
	* @Description: 打开证据信息列表
	* @param startingDate  
	* @param endingDate  
	* @return ModelAndView
	*/
	@RequestMapping(value = "/openDialog/zjxx")
	public ModelAndView openZjxxDialog(String startingDate, String endingDate, String ywId) {
		ModelAndView mv = new ModelAndView("alarm/record/zjxxList");
		mv.addObject("startingDate", startingDate);
		mv.addObject("endingDate", endingDate);
		mv.addObject("ywId", ywId);
		return mv;
	}

	@RequestMapping(value = "/queryAlarmLevRecord")
	@ResponseBody
	@Logger(action = "查询", logger = "根据 ID 查询报警记录信息")
	public AjaxMessage queryAlarmLevRecord(String cusNumber,String DpName) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		 //一天的开始时间 yyyy:MM:dd 00:00:00  
		Calendar calendar = new GregorianCalendar();  
	    calendar.add(Calendar.DAY_OF_MONTH,-1);  
	    calendar.set(Calendar.HOUR_OF_DAY,23);  
	    calendar.set(Calendar.MINUTE,59);  
	    calendar.set(Calendar.SECOND,59);  
	    calendar.set(Calendar.MILLISECOND,0);  
	    Date dayStart = calendar.getTime();  
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String startStr = simpleDateFormat.format(dayStart);
	  //当前时间 yyyy:MM:dd 00:00:00  
	    Date date = new Date(); 
	    String Now = simpleDateFormat.format(date);
		try {
			Map<String, Object> map = service.queryAlarmLevRecord(cusNumber,startStr,Now,DpName);
			flag = true;
			result = map;
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

	@RequestMapping(value = "/queryJqAlarmLevRecord")
	@ResponseBody
	@Logger(action = "查询", logger = "根据 ID 查询报警记录信息")
	public AjaxMessage queryJqAlarmLevRecord(String cusNumber, String dprtmntId, HttpServletRequest request, HttpServletResponse response) {
		AjaxMessage ajaxMsg = new AjaxMessage();
		boolean flag = false;
		Object result = null;
		try {
			 //一天的开始时间 yyyy:MM:dd 00:00:00  
			Calendar calendar = new GregorianCalendar();  
		    calendar.add(Calendar.DAY_OF_MONTH,-1);  
		    calendar.set(Calendar.HOUR_OF_DAY,23);  
		    calendar.set(Calendar.MINUTE,59);  
		    calendar.set(Calendar.SECOND,59);  
		    calendar.set(Calendar.MILLISECOND,0);  
		    Date dayStart = calendar.getTime();  
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    String startStr = simpleDateFormat.format(dayStart);
		  //当前时间 yyyy:MM:dd 00:00:00  
		    Date date = new Date(); 
		    String Now = simpleDateFormat.format(date);
			Map<String, Object> map = service.queryJqAlarmLevRecord(cusNumber, startStr, Now, dprtmntId, null);
			flag = true;
			result = map;
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@RequestMapping(value = "/searchRecordList")
	@ResponseBody
	public Map<String, Object> searchRecordList(AlarmRecordEntity Entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		int level = AuthSystemFacade.whatLevelForLoginUser();
		if (level != 1 && (Entity.getArdCusNumber() == null || "".equals(Entity.getArdCusNumber()))) {
			Entity.setArdCusNumber(AuthSystemFacade.getLoginUserInfo().getOrgCode());
		}
		String startTime=request.getParameter("STime");
		String endTime=request.getParameter("ETime");
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.searchRecordList(Entity,pageRequest,startTime,endTime);
			return DataUtils.pageToMap(page);
		
	}
}
