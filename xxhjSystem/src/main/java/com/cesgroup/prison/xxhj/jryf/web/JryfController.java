package com.cesgroup.prison.xxhj.jryf.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.jryf.dao.JryfMapper;
import com.cesgroup.prison.xxhj.jryf.entity.Jryf;
import com.cesgroup.prison.xxhj.jryf.service.JryfService;
import com.cesgroup.prison.xxhj.jryf.service.JryfServiceImpl;

/**
 * 今日押犯
 *
 */
@Controller
@RequestMapping(value = "/xxhj/jryf")
public class JryfController extends BaseEntityDaoServiceCRUDController<Jryf, String, JryfMapper, JryfServiceImpl> {
	
	@Resource
	private JryfService jryfService;
	
	/**
	 * 省局统计
	 */
	@RequestMapping("/pjryf")
	public ModelAndView pjryf() {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/pjryf");
		return mv;
	}
	
	/**
	 * 监狱统计
	 */
	@RequestMapping("/jryf")
	public ModelAndView jryf() {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/jryf");
		return mv;
	}
	
	/**
	 * 罪犯信息窗口
	 */
	@RequestMapping("/jryfPrisonerInfo")
	public ModelAndView jryfPrisonerinfo() {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/jryfPrisonerInfo");		
		return mv;
	}
	
	/**
	 * 罪犯信息一卡通
 	 */
	@RequestMapping("/criminalArchivesFile")
	public ModelAndView criminalArchivesFile(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/prisonerEcard/criminalArchivesFile");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯奖励惩罚窗口
 	 */
	@RequestMapping("/rewardsPunishment")
	public ModelAndView rewardsPunishment(String pprPrsnrIdnty, String pprCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/prisonerEcard/rewardsPunishment");
		mv.addObject("pprPrsnrIdnty", pprPrsnrIdnty);
		mv.addObject("pprCusNumber", pprCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯财务窗口
	 */
	@RequestMapping("/finanicalManagement")
	public ModelAndView finanicalManagement(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/prisonerEcard/finanicalManagement");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯行为掌控窗口
 	 */
	@RequestMapping("/behavioralControl")
	public ModelAndView behavioralControl(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/prisonerEcard/behavioralControl");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯健康档案窗口
 	 */
	@RequestMapping("/healthRecord")
	public ModelAndView healthRecord(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/prisonerEcard/healthRecord");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯亲情电话
 	 */
	@RequestMapping("/prisonerPhone")
	public ModelAndView prisonerPhone(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/prisonerEcard/prisonerPhone");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯亲情会见窗口
 	 */
	@RequestMapping("/prisonerMeeting")
	public ModelAndView prisonerMeeting(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/jryf/prisonerEcard/prisonerMeeting");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	@RequestMapping(value ="/queryPrisonCount")
   	@ResponseBody
   	public List<Map<String, Object>> queryPrisonCount(HttpServletRequest request) throws Exception {
		
    	List<Map<String, Object>> list = service.queryPrisonCount(request);
   		return list;
   	}

	@RequestMapping(value ="/queryDprtCount")
   	@ResponseBody
   	public List<Map<String, Object>> queryDprtCount(HttpServletRequest request) throws Exception {
	
    	List<Map<String, Object>> list = service.queryDprtCount(request);
   		return list;
   	}  

    @RequestMapping(value ="/queryInfoPrisonerarchives")
	@ResponseBody
	public List<Map<String, Object>> queryInfoPrisonerarchives(HttpServletRequest request) throws Exception {
    	
    	List<Map<String, Object>> list = service.queryInfoPrisonerarchives(request);
		return list;
	}
	
    @RequestMapping(value ="/queryPrisonerBriefInfo")
	@ResponseBody
	public Map<String, Object> queryPrisonerBriefInfo(HttpServletRequest request) throws Exception {	
    	
    	PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.queryPrisonerBriefInfo(request,pageRequest);
		return DataUtils.pageToMap(page);		 
	}   
  
    @RequestMapping(value ="/queryXJPrisonerCount")
   	@ResponseBody
   	public List<Map<String, Object>> queryXJPrisonerCount(HttpServletRequest request) throws Exception {
   
    	List<Map<String, Object>> list = service.queryXJPrisonerCount(request);
   		return list;
   	}
    
    @RequestMapping(value ="/queryXJPrsnrCountPrisonList")
   	@ResponseBody
   	public List<Map<String, Object>> queryXJPrsnrCountPrisonList(HttpServletRequest request) throws Exception {
   
    	List<Map<String, Object>> list = service.queryXJPrsnrCountPrisonList(request);   	
   		return list;
   	}
    
    @RequestMapping(value ="/queryXJPrsnrCountDrptmntList")
   	@ResponseBody
   	public List<Map<String, Object>> queryXJPrsnrCountDrptmntList(HttpServletRequest request) throws Exception {
   
    	List<Map<String, Object>> list = service.queryXJPrsnrCountDrptmntList(request);   	
   		return list;
   	}
    
    @RequestMapping(value ="/queryPrisonerInfo")
   	@ResponseBody
   	public Map<String, Object> queryPrisonerInfo(HttpServletRequest request) throws Exception {
    	
    	PageRequest pageRequest = buildPageRequest();
    	Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.queryPrisonerInfo(request,pageRequest);   	
		return DataUtils.pageToMap(page);	
   	}
    
    @RequestMapping(value ="/countPrisoner")
   	@ResponseBody
   	public List<Map<String, String>> countPrisoner(String cusNumber) {
   
    	List<Map<String, String>> list = service.countPrisoner(cusNumber);
   		return list;
   	}
	
    @RequestMapping(value ="/hospitalCount")
   	@ResponseBody
 	public List<Map<String, Object>> hospitalCount(String cusNumber) {
 
	   List<Map<String, Object>> list = service.hospitalCount(cusNumber);
	   return list;
 	}
	
    @RequestMapping("/listPrisonerFinanical")
    @ResponseBody
	public Map<String, Object> listPrisonerFinanical(HttpServletRequest request,Pageable page) throws Exception {
		
	   Page<Map<String, Object>> pages = service.listPrisonerFinanical(request,page);
		return DataUtils.pageToMap(pages);
	}
   
    @RequestMapping("/listPrisonerRewardPunish")
    @ResponseBody
	public Map<String, Object> listPrisonerRewardPunish(HttpServletRequest request,Pageable page) throws Exception {
		
	   Page<Map<String, Object>> pages = service.listPrisonerRewardPunish(request,page);
	   return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/listPrisonerHealthy")
	@ResponseBody
	public Map<String, Object> listPrisonerHealthy(String pheCusNumber,String phePrsnrIdnty,Pageable page) {
		
		Page<Map<String, String>> pages = service.listPrisonerHealthy(pheCusNumber,phePrsnrIdnty,page);
		return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/listPrisonerPhone")
	@ResponseBody
	public Map<String, Object> listPrisonerPhone(String pphCusNumber,String pphPrsnrIdnty,Pageable page) {
		
		Page<Map<String, String>> pages = service.listPrisonerPhone(pphCusNumber,pphPrsnrIdnty,page);
		return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/listPrisonerMeeting")
	@ResponseBody
	public Map<String, Object> listPrisonerMeeting(String pmeCusNumber,String pmePrsnrIdnty,Pageable page) {
		
		Page<Map<String, String>> pages = service.listPrisonerMeeting(pmeCusNumber,pmePrsnrIdnty,page);
		return DataUtils.pageToMap(pages);
	}
}