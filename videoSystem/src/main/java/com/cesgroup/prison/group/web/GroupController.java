package com.cesgroup.prison.group.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.prison.group.dao.GroupMapper;
import com.cesgroup.prison.group.entity.Group;
import com.cesgroup.prison.group.service.IGroupService;
import com.cesgroup.prison.group.service.impl.GroupServiceImpl;

/**   
*    
* @projectName：prison   
* @ClassName：GroupController   
* @Description：群组管理Controller 
* @author：zhengke   
* @date：2017-12-01 15:25   
* @version        
*/
@Controller
@RequestMapping(value = "/groupManage")
public class GroupController extends BaseEntityDaoServiceCRUDController<Group, String, GroupMapper, GroupServiceImpl> {
	@Autowired
	private IGroupService service;
	// 日志对象
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(GroupController.class);
	/**
	* @methodName: getTree
	* @Description: 根据父ID和使用范围查询群组
	* @param id
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @return List<Map<String,Object>>    
	* @throws
	*/
	@RequestMapping("/getTree")
	@ResponseBody
	public List<Map<String, Object>> getTree(Group group) {
		return service.getTree(group);

	}
	
	@RequestMapping("/nullJson")
	@ResponseBody
	public List<Map<String, Object>> nullJson() {
		return new ArrayList<Map<String, Object>>();
	}

	/**
	* @methodName: getMemberTree
	* @Description: 查询群组成员树
	* @param id
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @param gmaTypeIndc群组类别 1、摄像机，2、门禁，3、广播，4、大屏
	* @return List<Map<String,Object>>    
	* @throws
	*/
	/*@RequestMapping("/getGroupMemberTree")
	@ResponseBody
	public List<Map<String, Object>> getGroupMemberTree(String id, String gmaUseRange, String gmaCusNumber,
			String gmaTypeIndc) {
		return service.getGroupMemberTree(id, gmaUseRange, gmaCusNumber, gmaTypeIndc);
	}*/
	
	/**
	* @methodName: simpleGroupMemberTree
	* @Description: 简单树，一次性加载
	* @param gmaUseRange
	* @param gmaCusNumber
	* @param gmaTypeIndc
	* @return List<Map<String,Object>>    
	* @throws
	*/
	@RequestMapping("/simpleGroupMemberTree")
	@ResponseBody
	public List<Map<String, Object>> simpleGroupMemberTree(Group group) {
		List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
		try {
			list=service.simpleGroupMemberTree(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/simpleGroupTree")
	@ResponseBody
	public List<Map<String,Object>> simpleGroupTree(Group group){
		List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
		try {
			list= service.simpleGroupTree(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Logger(action = "进入", logger = "群组管理页面")
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("group/index");
	}

	/**
	 * 进入群组树页面
	 * @return
	 */
	@RequestMapping("/groupTreePage")
	public ModelAndView treePage() {
		ModelAndView mv = new ModelAndView("group/tree");
		return mv;
	}

	@RequestMapping("/findById")
	public Group findById(String id) {
		return service.findById(id);
	}

	@Logger(action = "级联删除", logger = "${id}")
	@RequestMapping(value = "/deleteGroup")
	@ResponseBody
	public AjaxMessage deleteGroup(String id){
		try {
			service.deleteGroup(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			log.error("GroupController-deleteGroup异常：", e);
			return new AjaxMessage(false,null,e.getMessage());
		}
	}

	//局部修改
	@RequestMapping(value = "/updatePart")
	@ResponseBody
	public AjaxMessage updatePart(Group group_param){
		try {
			service.updatePart(group_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			log.error("GroupController-updatePart异常：", e);
			return new AjaxMessage(false,null,e.getMessage());
		}
	}

	/**
	 * 根据监狱编号、工作组名称，查询工作组列表
	 * @param cusNumber
	 * @param grpName
	 * @return
	 */
	@Logger(action = "查询", logger = "根据监狱编号${cusNumber}与工作组名称${grpName}，查询工作组列表")
	@RequestMapping(value = "/queryByCusNumberAndGrpName")
	@ResponseBody
	public AjaxResult queryByCusNumberAndGrpName(
			@RequestParam(value = "cusNumber", defaultValue = "", required = false) String cusNumber, 
			@RequestParam(value = "grpName", defaultValue = "", required = false) String grpName) {
		log.info("GroupController-queryByCusNumberAndGrpName cusNumber = " + cusNumber);
		log.info("GroupController-queryByCusNumberAndGrpName grpName = " + grpName);
		try {
			List<Group> groupList = this.getService().queryByGmaCusNumberAndGmaGrpName(cusNumber, grpName);
			if(groupList != null && groupList.size() > 0) {
				log.info("GroupController-queryByCusNumberAndGrpName groupList size = " + groupList.size());
				return AjaxResult.success(groupList.get(0));
			} else {
				log.info("GroupController-queryByCusNumberAndGrpName groupList is null or size equal 0");
				return AjaxResult.error("名称为【" + grpName + "】的视频预案不存在");
			}
		} catch (Exception e) {
			log.error("GroupController-queryByCusNumberAndGrpName异常：", e);
			String msg = "查询工作组发生异常 ：" + e.getMessage();
			return AjaxResult.error(msg);
		}
	}
	
	@RequestMapping(value = "/querByfrTime")
	@ResponseBody
	public Map querByfrTime(HttpServletRequest request,HttpServletResponse response){
		String frTime = request.getParameter("frTime");
		String cusNumber = request.getParameter("cusNumber");
		String grpName = request.getParameter("grpName");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		String sffh = "0";
		List list = new ArrayList();
		try {
			Date startTime = sdf.parse(frTime);
			Date endTime = new Date();
			long diff = endTime.getTime()-startTime.getTime();
			long xcsj  = (diff/1000)/60;
					//diff%1000*24*60*60%1000*60*60/1000*60;
			System.out.println("*******************************^^^^^^^^^^^^^"+xcsj);
			if(xcsj>7){
				sffh = "1";
				list = this.getService().queryCarame( cusNumber,  grpName);
				String startTimea = frTime;
				String endTimea = sdf.format(DateUtil.addsecound(sdf.parse(startTimea), 300));
				for(int i =0;i<list.size();i++){
					Map mapjg = (Map) list.get(i);
					mapjg.put("STARTTIME", startTimea);
					mapjg.put("ENDTIME", endTimea);
					/*startTimea = sdf.format(DateUtil.addsecound(sdf.parse(startTimea), 20));
					endTimea = sdf.format(DateUtil.addsecound(sdf.parse(startTimea), 300));*/
				}
				
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("jg", sffh);
		map.put("sxj", list);
		return map;
	}		

	
	
}
