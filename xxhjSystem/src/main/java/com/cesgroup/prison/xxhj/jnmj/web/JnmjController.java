package com.cesgroup.prison.xxhj.jnmj.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.jnmj.dao.JnmjMapper;
import com.cesgroup.prison.xxhj.jnmj.entity.Jnmj;
import com.cesgroup.prison.xxhj.jnmj.service.JnmjService;

/**
 * 监内民警
 * 
 */
@Controller
@RequestMapping(value = "/xxhj/jnmj")
public class JnmjController extends BaseEntityDaoServiceCRUDController<Jnmj, String, JnmjMapper, JnmjService> {
	/**
	 * 省局统计
	 */
	@RequestMapping("/pjnmj")
	public ModelAndView pjnmj() {

		ModelAndView mv = new ModelAndView("xxhj/jnmj/pjnmj");
		return mv;
	}

	/**
	 * 监狱统计
	 */
	@RequestMapping("/jnmj")
	public ModelAndView jnmj(HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("xxhj/jnmj/jnmj");

		if (request.getParameter("prisonName") != null && request.getParameter("prisonId") != null) {
			mv.addObject("prisonName", URLDecoder.decode(request.getParameter("prisonName"), "utf-8"));
			mv.addObject("prisonId", request.getParameter("prisonId"));
		}

		return mv;
	}

	/**
	 * 监区统计
	 */
	@RequestMapping("/jqjnmj")
	public ModelAndView jqjnmj() {

		ModelAndView mv = new ModelAndView("xxhj/jnmj/jq_jnmj");
		return mv;
	}

	/**
	 * 民警信息
	 */
	@RequestMapping("/jnmjPoliceInfo")
	public ModelAndView jnmjPoliceInfo(@RequestParam(value = "drptmntId", defaultValue = "", required = false) String drptmntId, 
			@RequestParam(value = "cusNumber", defaultValue = "", required = false) String cusNumber, 
			@RequestParam(value = "config", defaultValue = "", required = false) String config, 
			@RequestParam(value = "policeIdntyOrName", defaultValue = "", required = false) String policeIdntyOrName,
			@RequestParam(value = "policeNoList", defaultValue = "", required = false) String policeNoList) {
		ModelAndView mv = new ModelAndView("xxhj/jnmj/jnmjPoliceInfo");
		mv.addObject("drptmntId", drptmntId);
		mv.addObject("cusNumber", cusNumber);
		mv.addObject("config", config);
		mv.addObject("policeIdntyOrName", Base64Util.decodeString(policeIdntyOrName, 2));
		mv.addObject("policeNoList", policeNoList);

		return mv;
	}

	/**
	 * 民警进出记录
	 */
	@RequestMapping("/jnmjPoliceInoutRecord")
	public ModelAndView jnmjPoliceInoutRecord(String policeId, String cusNumber, String drpmntId) {

		ModelAndView mv = new ModelAndView("/xxhj/jnmj/jnmjPoliceInoutRecord");
		mv.addObject("policeId", policeId);
		mv.addObject("cusNumber", cusNumber);
		mv.addObject("drpmntId", drpmntId);
		return mv;
	}

	/**
	 * 查询监狱列表
	 */
	@RequestMapping("/queryPrison")
	@ResponseBody
	public List<OrgEntity> queryPrison() throws Exception {

		List<OrgEntity> list = this.getService().queryPrison();
		return list;
	}

	/**
	 * 查询监狱下监区信息
	 */
	@RequestMapping("/queryPrisonDepartment")
	@ResponseBody
	public List<OrgEntity> queryPrisonDepartment(String cusNumber) throws Exception {

		List<OrgEntity> list = this.getService().queryPrisonDepartment(cusNumber);
		return list;
	}
	
	/**
	 * 查询监狱查询类别 
	 * FLAG 1为刷卡 2为值班 其他为组织机构
	 */
	@RequestMapping("/queryDutyConfig")
	@ResponseBody
	public Map<String, Object> queryDutyConfig(String cusNumber) {

		Map<String, Object> map = this.getService().queryDutyConfig(cusNumber);
		return map;
	}
	
	/**
	 * 查询在监民警数量
	 *//*
	@RequestMapping("/queryInsidePoliceCount")
	@ResponseBody
	public List<Map<String, String>> queryInsidePoliceCount(String cusNumber, String drpmntId) {

		List<Map<String, String>> list = this.getService().queryInsidePoliceCount(cusNumber, drpmntId);
		return list;
	}*/

	/**
	 * 按监狱查询民警数量（省局）
	 */
	@RequestMapping("/queryInsidePoliceCountByPrison")
	@ResponseBody
	public List<Map<String, String>> queryInsidePoliceCountByPrison() throws Exception {

		List<Map<String, String>> list = this.getService().queryInsidePoliceCountByPrison();
		return list;
	}

	/**
	 * 按监区查询民警数量(一个sql查出整个页面的数据)
	 */
	@RequestMapping("/queryPrisonDrptmntPoliceCount")
	@ResponseBody
	public List<Map<String, String>> queryPrisonDrptmntPoliceCount(String cusNumber, String drpmntId,String config) {

		List<Map<String, String>> list = this.getService().queryPrisonDrptmntPoliceCount(cusNumber,drpmntId,config);
		return list;
	}

	/**
	 * 查询民警信息
	 */
	@RequestMapping("/queryPrisonDrptmntPoliceInfo")
	@ResponseBody
	public List<Map<String, Object>> queryPrisonDrptmntPoliceInfo(HttpServletRequest request) throws Exception {

		return this.getService().queryPrisonDrptmntPoliceInfo(request);
	}

	/**
	 * 查询民警进出记录信息
	 */
	@RequestMapping(value = "/listPoliceInoutRecordInfo")
	@ResponseBody
	public Map<String, Object> listPoliceInoutRecordInfo(HttpServletRequest request) {

		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = this.getService().listPoliceInoutRecordInfo(request, pageRequest);
        System.out.println( DataUtils.pageToMap(page));
		return DataUtils.pageToMap(page);
	}

	/**
	* @methodName: queryPoliceByDid
	* @Description: add by tao
	* @param request
	* @return
	* @throws Exception Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/queryPoliceByDid")
	@ResponseBody
	public Map<String, Object> queryPoliceByDid(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) this.getService().queryPoliceByDid(request, pageRequest);
		return DataUtils.pageToMap(page);
	}

	/**
	* @methodName: findPoliceByUserId
	* @Description: add by tao
	* @param request
	* @return
	* @throws Exception Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/findPoliceByUserId")
	@ResponseBody
	public Map<String, Object> findPoliceByUserId(HttpServletRequest request) throws Exception {
		return this.getService().findPoliceByUserId(request);
	}

	/**
	* @methodName: openPoliceList
	* @Description: add by tao
	* @param request
	* @return
	* @throws Exception ModelAndView
	* @throws  
	*/
	@RequestMapping("/openPoliceList")
	public ModelAndView openPoliceList(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("xxhj/jnmj/policeList");
		String pbdcusNumber = request.getParameter("pbdcusNumber");
		String pbdDrptmntId = request.getParameter("pbdDrptmntId");
		mv.addObject("pbdcusNumber", pbdcusNumber);
		mv.addObject("pbdDrptmntId", pbdDrptmntId);
		return mv;
	}

	/**
	* @methodName: openPoliceInfo
	* @Description: add by tao
	* @param request
	* @return
	* @throws Exception ModelAndView
	* @throws  
	*/
	@RequestMapping("/openPoliceInfo")
	public ModelAndView openPoliceInfo(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("xxhj/jnmj/policeInfo");
		String pbdcusNumber = request.getParameter("pbdcusNumber");
		String pbdUserId = request.getParameter("pbdUserId");
		mv.addObject("pbdcusNumber", pbdcusNumber);
		mv.addObject("pbdUserId", pbdUserId);
		return mv;
	}
	
	@RequestMapping("/rygl")
	public ModelAndView rygl(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("xxhj/jnmj/rygl/rygl");
		mv.addObject("mjbh", request.getParameter("mjbh"));
		return mv;
	}
	@RequestMapping(value = "/queryRylb")
	@ResponseBody
	public List<Map<String, Object>> queryRylb(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			 list= this.getService().queryRylb(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/queryCaramer")
	@ResponseBody
	public List<Map<String, Object>> queryCaramer(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			 list= this.getService().queryCaramer(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
