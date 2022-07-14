package com.cesgroup.prison.yrzq.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.yrzq.dao.YrzqMapper;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;
import com.cesgroup.prison.yrzq.service.YrzqService;

@Controller
@RequestMapping(value = "wghgl/yrzq")
public class YrzqController extends BaseEntityDaoServiceCRUDController<YrzqEntity, String, YrzqMapper, YrzqService> {

	private final Logger logger = LoggerFactory.getLogger(YrzqController.class);

	@Resource
	private YrzqService yrzqService;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		String type=request.getParameter("type")==null?"":request.getParameter("type");
		String state="";
		String isTimeout="";
		if("yzx".equals(type)){
			state="2";
		}else if("wzx".equals(type)){
			state="0";
			isTimeout="0";
		}else if("yc".equals(type)){
			isTimeout="1";
			state="0";
		}
		request.setAttribute("state", state);
		request.setAttribute("isTimeout", isTimeout);
		ModelAndView mv = new ModelAndView("yrzq/index");
		if("zqgl".equals(type)){
			mv.addObject("type", type);
		}
		return mv;
	}

	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("yrzq/edit");
		try {
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("fxcjId", request.getParameter("fxcjId"));
		mv.addObject("areaId", request.getParameter("areaId"));
		mv.addObject("sxsj", request.getParameter("sxsj"));
		
			mv.addObject("title", URLDecoder.decode(request.getParameter("title"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("yrzq/view");
		mv.addObject("departId", request.getParameter("departId"));
		mv.addObject("dateTime", request.getParameter("dateTime"));
		mv.addObject("state", request.getParameter("state"));
		return mv;
	}
	@RequestMapping(value = "/toList")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("yrzq/list");
		return mv;
	}
	@RequestMapping(value = "/toNew")
	public ModelAndView toNew(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("yrzq/new");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	

	@RequestMapping(value = "/getById")
	@ResponseBody
	public YrzqEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {

		return yrzqService.getById(id);
	}

	@RequestMapping(value = "/searchData")
	@ResponseBody
	public List<Map<String, Object>> searchData(YrzqEntity yrzqEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String userLevel = user.getUserLevel().toString();
		if("1".equals(userLevel)||"2".equals(userLevel)){//省局、监狱用户
			yrzqEntity.setPrisonId(user.getCusNumber());
		}else if("3".equals(userLevel)){//监区
			yrzqEntity.setDepartId(user.getDprtmntId());
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String  cDate=df.format(new Date());
		yrzqEntity.setStartTime(cDate);//当日
		yrzqEntity.setZbrId(user.getUserId());
		yrzqEntity.setDepartId(user.getDprtmntCode());
		List<Map<String, Object>> list = yrzqService.findList(yrzqEntity);
		return list;
	}
	
	@RequestMapping(value = "/searchListPage")
	@ResponseBody
	public Map<String, Object> searchListPage(YrzqEntity yrzqEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		PageRequest pageRequest = buildPageRequest();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String userLevel = user.getUserLevel().toString();
		if("1".equals(userLevel)||"2".equals(userLevel)){//省局、监狱用户
			yrzqEntity.setPrisonId(user.getCusNumber());
		}else if("3".equals(userLevel)){//监区
			yrzqEntity.setDepartId(user.getDprtmntId());
		}
		Page<YrzqEntity> pageInfo = null;
		if("zqgl".equals(type)){
			pageInfo = (Page<YrzqEntity>) yrzqService.findAllListPage(yrzqEntity, pageRequest);
		}else {
			pageInfo = (Page<YrzqEntity>) yrzqService.findListPage(yrzqEntity, pageRequest);
		}
		return DataUtils.pageToMap(pageInfo);
	}
	
	@RequestMapping(value = "/searchYrzq")
	@ResponseBody
	public List<Map<String, Object>> searchYrzq(YrzqEntity yrzqEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String userLevel = user.getUserLevel().toString();
		String prisonId = "";
		String departId = "";
		if("1".equals(userLevel)||"2".equals(userLevel)){//省局、监狱用户
			prisonId = user.getCusNumber();
		}else if("3".equals(userLevel)){//监区
			departId = user.getDprtmntId();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String pDate = sdf.format(DateUtil.getToday());//当日
		List<Map<String, Object>> list = yrzqService.searchYrzq(prisonId, departId, pDate);
		return list;
	}

	@RequestMapping(value = "/searchDataList")
	@ResponseBody
	public Map<String, Object> searchDataList(YrzqEntity jndtEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		String userLevel = user.getUserLevel().toString();
		if("1".equals(userLevel)||"2".equals(userLevel)){//省局、监狱用户
			jndtEntity.setPrisonId(user.getCusNumber());
		}else if("3".equals(userLevel)){//监区
			jndtEntity.setDepartId(user.getDprtmntId());
		}
		
		Page<YrzqEntity> pageInfo = (Page<YrzqEntity>) yrzqService.findDataList(jndtEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public AjaxMessage saveOrUpdate(@RequestBody Map<String, Object> map,HttpServletRequest request) {
		String id = map.get("id").toString();
		String mark = map.get("mark").toString();
		String fxcjId = map.get("fxcjId").toString();
		String zbrz = map.get("zbrz").toString();
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			yrzqService.saveOrUpdate(id,mark,fxcjId,zbrz);
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
	
	@ResponseBody
	@RequestMapping(value = "/deleteByIds")
	public AjaxMessage deleteByIds(String ids) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			flag = true;
			yrzqService.deleteByIds(ids);
			obj = "删除成功！";
		} catch (Exception e) {
			flag = false;
			obj = "操作失败";
		}
		if (flag) {
			/*ajaxMessage.setCode(code);*/
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	
	@RequestMapping(value = "/searchSwdbPage")
	@ResponseBody
	public Map<String, Object> searchSwdbPage(YrzqEntity yrzqEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String userLevel = user.getUserLevel().toString();
		if("1".equals(userLevel)||"2".equals(userLevel)){//省局、监狱用户
			//yrzqEntity.setPrisonId(user.getCusNumber());
		}else if("3".equals(userLevel)){//监区
			//yrzqEntity.setDepartId(user.getDprtmntId());
		}
		Page<YrzqEntity> pageInfo = (Page<YrzqEntity>) yrzqService.searchSwdbPage(yrzqEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}
	
	@RequestMapping(value = "/searchSwdbList")
	@ResponseBody
	public Map<String, Object> searchSwdbList(YrzqEntity yrzqEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		String dateTime = request.getParameter("dateTime");
		String departId = request.getParameter("departId");
		String state = request.getParameter("state");
		Page<YrzqEntity> pageInfo = (Page<YrzqEntity>) yrzqService.searchSwdbList(departId, dateTime, state, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}

		
	@RequestMapping(value = "/openCarame")
	@ResponseBody
	public List<Map<String, Object>> openCarame( HttpServletRequest request,HttpServletResponse response) throws Exception {
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 String xlId = request.getParameter("xlId");
		 list = this.getService().openCamare(xlId);
		return list;
	}
	
	@RequestMapping(value = "/getYwyth")
	@ResponseBody
	public List<Map<String, Object>> getYwyth( HttpServletRequest request,HttpServletResponse response) throws Exception {
		List list = new ArrayList();
		
		list = this.getService().getYwyth();
		
		return list;
	}
	
	@RequestMapping(value = "/getsjzs")
	@ResponseBody
	public List<Map<String, Object>> getsjzs( HttpServletRequest request,HttpServletResponse response) throws Exception {
		List list = new ArrayList();
		
		list = this.getService().getsbxx();
		
		return list;
	}
	@RequestMapping(value = "/getFxcj")
	@ResponseBody
	public Map<String, Object> getFxcj( HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		return yrzqService.getFxcj(id);
	}
	@RequestMapping(value = "/getMj")
	@ResponseBody
	public String getMj( HttpServletRequest request,HttpServletResponse response) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		return yrzqService.getMj(user.getDprtmntCode());
	}
	
	@RequestMapping(value = "/searchZbrz")
	@ResponseBody
	public AjaxMessage searchZbrz(YrzqEntity yrzqEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		AjaxMessage ajaxMessage = new AjaxMessage();
		String sxsj = request.getParameter("sxsj");
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String departId = user.getDprtmntCode();
		String str = yrzqService.searchZbrz(departId, sxsj);
		ajaxMessage.setObj(str);
		return ajaxMessage;
	}
	
	@RequestMapping(value = "/getZbrz")
	@ResponseBody
	public AjaxMessage getZbrz(HttpServletRequest request,HttpServletResponse response) throws Exception {
		AjaxMessage ajaxMessage = new AjaxMessage();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String departId = user.getDprtmntCode();
		String str = yrzqService.getZbrz(departId);
		ajaxMessage.setObj(str);
		return ajaxMessage;
	}
	
	@RequestMapping(value = "/saveOrUpdateZb")
	@ResponseBody
	public AjaxMessage saveOrUpdateZb(@RequestBody Map<String, Object> map) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		try {
			yrzqService.updateZbr(map);
			ajaxMessage.setSuccess(true);
		}catch(Exception e){
			ajaxMessage.setSuccess(false);
			e.printStackTrace();
		}
		
		return ajaxMessage;
	}
	
	
}
