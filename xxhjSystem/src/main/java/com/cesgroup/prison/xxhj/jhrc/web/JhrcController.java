package com.cesgroup.prison.xxhj.jhrc.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.plugins.jobs.entity.ScheduleJob;
import com.cesgroup.framework.plugins.jobs.service.ScheduleJobService;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.code.utils.CodeFacade;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.jhrc.dao.JhrcMapper;
import com.cesgroup.prison.xxhj.jhrc.entity.JhrcEntity;
import com.cesgroup.prison.xxhj.jhrc.service.JhrcService;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;
import com.cesgroup.prison.yrzq.service.YrzqService;

import ces.sdk.util.StringUtil;


/**
 * 计划日程
 *
 */
@Controller
@RequestMapping(value = "xxhj/jhrc")
public class JhrcController extends BaseEntityDaoServiceCRUDController<JhrcEntity, String, JhrcMapper, JhrcService> {

	private final Logger logger = LoggerFactory.getLogger(JhrcController.class);

	@Resource
	private JhrcService jhrcService;

	@Resource
	private YrzqService yrzqService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("xxhj/jhrc/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("xxhj/jhrc/edit");
		mv.addObject("id", request.getParameter("id"));
		
		return mv;
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public 	JhrcEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {

		return jhrcService.getById(id);
	}

	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(JhrcEntity jhrcEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		jhrcEntity.setCpsCusNumber(user.getOrgCode());
		Page<JhrcEntity> pageInfo = (Page<JhrcEntity>) jhrcService.findList(jhrcEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}

	//查询某一天的日程
	@RequestMapping(value = "/searchRcByDay")
	@ResponseBody
	public List<Map<String, Object>> searchRcByDay(String day,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = null;
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		EUserLevel eUserLevel = user.getUserLevel();
		if (EUserLevel.PRIS == eUserLevel) {
			list = jhrcService.searchRcByDay(user.getOrgCode(), null, day);
		} else if(EUserLevel.AREA == eUserLevel){
			list = jhrcService.searchRcByDay(user.getOrgCode(), user.getDprtmntCode(), day);
		}
		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public AjaxMessage saveOrUpdate(JhrcEntity jhrcEntity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		try {
			flag = true;
			Map<String, Object> map = new HashMap<String, Object>();
			if(StringUtil.isBlank(jhrcEntity.getId())){
				map = jhrcService.getZbr(user.getCusNumber(),jhrcEntity.getCpsDrpmntId());
			}
			jhrcService.saveOrUpdate(jhrcEntity);
			obj = "操作成功！";
			String  cDate=jhrcEntity.getCpsStartDate();
			String detail=CodeFacade.getCodeNameByCodeKey("4.20.60", jhrcEntity.getCpsPlanDetail());
			String zbr = "";
			String zbrId = "";
			if(map!=null&&map.size() != 0){
				zbr = map.get("ZBR").toString();
				zbrId = map.get("ZBRID").toString();
			}
			String tzsb = jhrcEntity.getTzsb();
			if(StringUtils.isNotBlank(tzsb)) {
				String[] sb = tzsb.split(",");
				if(StringUtil.isNotBlank(jhrcEntity.getTzsj())){
					for(int i = 0;i < sb.length;i++) {
						if("0".equals(sb[i])){
							jhrcService.insertDsq(jhrcEntity.getTzsj());
						}else if("1".equals(sb[i])) {
							jhrcService.insertDsqGb(jhrcEntity.getTzsj());
						}
					}
					
				}
			}
			
			yrzqService.insertOnedayDutyDB(user.getUserId(), jhrcEntity.getCpsCusNumber(), jhrcEntity.getCpsDrpmntId(), 
				jhrcEntity.getCpsDrpmntName(), cDate+" "+jhrcEntity.getCpsScheduleTime(),
				cDate+" "+jhrcEntity.getCpsScheduleEndTime(), "0", 
				detail,jhrcEntity.getCpsLx(),jhrcEntity.getTzsj(),jhrcEntity.getTznr(),zbr,zbrId);
			
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
			obj = "操作失败";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteByIds")
	public AjaxMessage deleteByIds(String ids) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			jhrcService.deleteByIds(ids);
			obj = "删除成功！";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getZbr")
	public Map<String, Object> getZbr(){
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		Map<String, Object> map = jhrcService.getZbr(user.getCusNumber(),user.getDprtmntCode());
		return map;
	}
	
	@RequestMapping(value = "/saveOrUpdateJb")
	@ResponseBody
	public AjaxMessage saveOrUpdateJb(@RequestBody Map<String, Object> map,HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		String id = map.get("id").toString();
		String mark = map.get("mark").toString();
		String fxcjId = map.get("fxcjId").toString();
		String zbrz = map.get("zbrz").toString();
		String zbrVal = map.get("zbr").toString();
		String zbrText = map.get("zbrText").toString();
		String sxsj = map.get("sxsj").toString();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String departId = user.getDprtmntCode();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			yrzqService.saveOrUpdate(id,mark,fxcjId,zbrz);
			jhrcService.inserYrzq(zbrVal, zbrText,sxsj,departId);
			obj = "操作成功！";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	
	}
	
}
