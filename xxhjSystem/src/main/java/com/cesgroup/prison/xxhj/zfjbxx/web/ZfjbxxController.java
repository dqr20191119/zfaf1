package com.cesgroup.prison.xxhj.zfjbxx.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.zfjbxx.dao.ZfjbxxMapper;
import com.cesgroup.prison.xxhj.zfjbxx.entity.Zfjbxx;
import com.cesgroup.prison.xxhj.zfjbxx.service.ZfjbxxService;
import com.cesgroup.prison.xxhj.zfjbxx.service.ZfjbxxServiceImpl;

/**
 * Description: 信息汇聚-罪犯基本信息控制器
 * @author lincoln.cheng
 *
 */
@Controller
@RequestMapping(value = "/xxhj/zfjbxx")
public class ZfjbxxController extends BaseEntityDaoServiceCRUDController <Zfjbxx, String, ZfjbxxMapper, ZfjbxxServiceImpl>{
	
	@Resource
	private ZfjbxxService zfjbxxService;
	
    @RequestMapping("/pzfjbxx")
    public ModelAndView zfjbxx() {
    	
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/pzfjbxx");
		return mv;
    }
    
    @RequestMapping("/jqzfjbxx")
    public ModelAndView jqzfjbxx() {
    	
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/jq_zfjbxx");
		return mv;
    }
    
    @RequestMapping("/zfjbxx")
    public ModelAndView zfjbxx(HttpServletRequest request) throws UnsupportedEncodingException {
    	
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/zfjbxx");
		
	    if(request.getParameter("prisonName") != null && request.getParameter("prisonId") != null) {
			mv.addObject("prisonName", URLDecoder.decode(request.getParameter("prisonName"), "utf-8") );
			mv.addObject("prisonId", request.getParameter("prisonId"));
	    }
		return mv;
    }
    
    /**
	 * 罪犯信息窗口
	 */
	@RequestMapping("/prisonerInfo")
	public ModelAndView prisonerinfo(@RequestParam(value = "query", defaultValue = "", required = false) String query, 
			@RequestParam(value = "type", defaultValue = "", required = false) String type, 
			@RequestParam(value = "cusNumber", defaultValue = "", required = false) String cusNumber, 
			@RequestParam(value = "drpmntId", defaultValue = "", required = false) String drpmntId, 
			@RequestParam(value = "prsnrIdntyOrName", defaultValue = "", required = false) String prsnrIdntyOrName,
			@RequestParam(value = "drpmntName", defaultValue = "", required = false) String drpmntName,
			@RequestParam(value = "zyzf", defaultValue = "", required = false) String zyzf) {
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerInfo");
		mv.addObject("query", query);
		mv.addObject("type", type);
		mv.addObject("cusNumber", cusNumber);
		mv.addObject("drpmntId", drpmntId);
		mv.addObject("zyzf", zyzf);
		try {
			mv.addObject("drpmntName", URLDecoder.decode(drpmntName,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("prsnrIdntyOrName", Base64Util.decodeString(prsnrIdntyOrName, 2));
		return mv;
	}

	/**
	 * 罪犯信息一卡通
 	 */
	@RequestMapping("/criminalArchivesFile")
	public ModelAndView criminalArchivesFile(String prisonerId, String cusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerEcard/criminalArchivesFile");
		mv.addObject("pbdPrsnrIdnty", prisonerId);
		mv.addObject("pbdCusNumber", cusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯奖励惩罚窗口
 	 */
	@RequestMapping("/rewardsPunishment")
	public ModelAndView rewardsPunishment(String pprPrsnrIdnty, String pprCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerEcard/rewardsPunishment");
		mv.addObject("pprPrsnrIdnty", pprPrsnrIdnty);
		mv.addObject("pprCusNumber", pprCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯财务窗口
	 */
	@RequestMapping("/finanicalManagement")
	public ModelAndView finanicalManagement(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerEcard/finanicalManagement");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯行为掌控窗口
 	 */
	@RequestMapping("/behavioralControl")
	public ModelAndView behavioralControl(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerEcard/behavioralControl");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯健康档案窗口
 	 */
	@RequestMapping("/healthRecord")
	public ModelAndView healthRecord(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerEcard/healthRecord");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 罪犯亲情电话
 	 */
	@RequestMapping("/prisonerPhone")
	public ModelAndView prisonerPhone(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerEcard/prisonerPhone");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	
	/**
 	 * 罪犯亲情会见窗口
 	 */
	@RequestMapping("/prisonerMeeting")
	public ModelAndView prisonerMeeting(String pbdPrsnrIdnty, String pbdCusNumber) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/prisonerEcard/prisonerMeeting");
		mv.addObject("pbdPrsnrIdnty", pbdPrsnrIdnty);
		mv.addObject("pbdCusNumber", pbdCusNumber);
		return mv;
	}
	
	/**
 	 * 监舍小组罪犯
 	 */
	@RequestMapping("/prisonerBedInfo")
	public ModelAndView prisonerBedInfo(String jsId,String qyId) {
		
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/jsxzzf/jsxzzf");
		
		mv.addObject("jsId", jsId);
		mv.addObject("qyId", qyId);
		return mv;
	}
	
	/**
 	 * 监舍罪犯信息
 	 */
	@RequestMapping("/jsPrisonerInfo")
	public ModelAndView jsPrisonerInfo(String cusNumber,String lch,String jsh) {
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/jszf/jszf");
		mv.addObject("cusNumber",cusNumber);
		mv.addObject("lch", lch);
		mv.addObject("jsh", jsh);
		return mv;
	}
	
	
	/*public ModelAndView jsPrisonerInfo(String jsId) {
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/jszf/jszf");
		String[] tmpString=jsId.split("_");
		mv.addObject("cusNumber",tmpString[0]);
		mv.addObject("lch", tmpString[1]);
		mv.addObject("jsh", tmpString[2]);
		return mv;
	}*/
	
	@RequestMapping(value ="/queryPrisonCount")
   	@ResponseBody
   	public List<Map<String, Object>> queryPrisonCount(HttpServletRequest request) throws Exception {
		
    	List<Map<String, Object>> list = zfjbxxService.queryPrisonCount(request);
   		return list;
   	}

	@RequestMapping(value ="/queryDprtCount")
   	@ResponseBody
   	public List<Map<String, Object>> queryDprtCount(HttpServletRequest request) throws Exception {
	
    	List<Map<String, Object>> list = zfjbxxService.queryDprtCount(request);
   		return list;
   	}  

    @RequestMapping(value ="/queryInfoPrisonerArchives")
	@ResponseBody
	public List<Map<String, Object>> queryInfoPrisonerArchives(HttpServletRequest request) throws Exception {
    	
    	List<Map<String, Object>> list = zfjbxxService.queryInfoPrisonerArchives(request);
		return list;
	}
    
    @RequestMapping(value ="/queryPrisonerBriefInfo")
	@ResponseBody
	public Map<String, Object> queryPrisonerBriefInfo(HttpServletRequest request) throws Exception {	
    	
    	PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) zfjbxxService.queryPrisonerBriefInfo(request,pageRequest);
		return DataUtils.pageToMap(page);		 
	}   
  
    @RequestMapping(value ="/queryXJPrisonerCount")
   	@ResponseBody
   	public List<Map<String, Object>> queryXJPrisonerCount(HttpServletRequest request) throws Exception {
   
    	List<Map<String, Object>> list = zfjbxxService.queryXJPrisonerCount(request);
   		return list;
   	}
    
    @RequestMapping(value ="/queryXJPrsnrCountPrisonList")
   	@ResponseBody
   	public List<Map<String, Object>> queryXJPrsnrCountPrisonList(HttpServletRequest request) throws Exception {
   
    	List<Map<String, Object>> list = zfjbxxService.queryXJPrsnrCountPrisonList(request);   	
   		return list;
   	}
    
    @RequestMapping(value ="/queryXJPrsnrCountDrptmntList")
   	@ResponseBody
   	public List<Map<String, Object>> queryXJPrsnrCountDrptmntList(HttpServletRequest request) throws Exception {
   
    	List<Map<String, Object>> list = zfjbxxService.queryXJPrsnrCountDrptmntList(request);   	
   		return list;
   	}
    
    /**
     * Description: 查询罪犯信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/queryPrisonerInfo")
   	@ResponseBody
   	public Map<String, Object> queryPrisonerInfo(HttpServletRequest request) throws Exception {
    	PageRequest pageRequest = buildPageRequest();
    	Page<Map<String, Object>> page = (Page<Map<String, Object>>) zfjbxxService.queryPrisonerInfo(request,pageRequest);   	
		return DataUtils.pageToMap(page);	
   	}
    
    @RequestMapping(value ="/countPrisoner")
   	@ResponseBody
   	public List<Map<String, String>> countPrisoner(String cusNumber) {
   
    	List<Map<String, String>> list = zfjbxxService.countPrisoner(cusNumber);
   		return list;
   	}
	
    @RequestMapping(value ="/hospitalCount")
   	@ResponseBody
 	public List<Map<String, Object>> hospitalCount(String cusNumber) {
 
	   List<Map<String, Object>> list = zfjbxxService.hospitalCount(cusNumber);
	   return list;
 	}
	
    @RequestMapping("/listPrisonerFinanical")
    @ResponseBody
	public Map<String, Object> listPrisonerFinanical(HttpServletRequest request,Pageable page) throws Exception {
		
	   Page<Map<String, Object>> pages = zfjbxxService.listPrisonerFinanical(request,page);
		return DataUtils.pageToMap(pages);
	}
   
    @RequestMapping("/listPrisonerRewardPunish")
    @ResponseBody
	public Map<String, Object> listPrisonerRewardPunish(HttpServletRequest request,Pageable page) throws Exception {
		
	   Page<Map<String, Object>> pages = zfjbxxService.listPrisonerRewardPunish(request,page);
	   return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/listPrisonerHealthy")
	@ResponseBody
	public Map<String, Object> listPrisonerHealthy(String pheCusNumber,String phePrsnrIdnty,Pageable page) {
		
		Page<Map<String, String>> pages = zfjbxxService.listPrisonerHealthy(pheCusNumber,phePrsnrIdnty,page);
		return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/listPrisonerPhone")
	@ResponseBody
	public Map<String, Object> listPrisonerPhone(String pphCusNumber,String pphPrsnrIdnty,Pageable page) {
		
		Page<Map<String, String>> pages = zfjbxxService.listPrisonerPhone(pphCusNumber,pphPrsnrIdnty,page);
		return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/listPrisonerMeeting")
	@ResponseBody
	public Map<String, Object> listPrisonerMeeting(String pmeCusNumber,String pmePrsnrIdnty,Pageable page) {
		
		Page<Map<String, String>> pages = zfjbxxService.listPrisonerMeeting(pmeCusNumber,pmePrsnrIdnty,page);
		return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/queryPrisonerBedInfo")
	@ResponseBody
	public Map<String, Object> queryPrisonerBedInfo(HttpServletRequest request,Pageable page) throws Exception {
		
		Page<Map<String, Object>> pages = zfjbxxService.queryPrisonerBedInfo(request,page);
		return DataUtils.pageToMap(pages);
	}
	
	@RequestMapping("/queryJSPrisonerInfo")
	@ResponseBody
	public Map<String, Object> queryJSPrisonerInfo(HttpServletRequest request,Pageable page) throws Exception {
		
		Page<Map<String, Object>> pages = zfjbxxService.queryJSPrisonerInfo(request,page);
		return DataUtils.pageToMap(pages);
	}
	@RequestMapping("/prisonJcjl")
	public ModelAndView prisonJcjl(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/jcjl/index");
		mv.addObject("zfbh", request.getParameter("zfbh"));
		return mv;
	}
	@RequestMapping(value = "/searchDataJcjl")
	@ResponseBody
	public Map<String, Object> searchDataJcjl(HttpServletRequest request,Pageable page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zfbh", request.getParameter("zfbh"));
		Page<Map<String, Object> > pageInfo = zfjbxxService.findList(map, page);
		return DataUtils.pageToMap(pageInfo);		
	}
	
	@RequestMapping(value = "/queryCaramer")
	@ResponseBody
	public List<Map<String, Object>> queryCaramer(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			 list= zfjbxxService.queryCaramer(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping("/rygl")
	public ModelAndView rygl(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("xxhj/zfjbxx/rygl/rygl");
		mv.addObject("zfbh", request.getParameter("zfbh"));
		return mv;
	}
	@RequestMapping(value = "/queryRylb")
	@ResponseBody
	public List<Map<String, Object>> queryRylb(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			 list= zfjbxxService.queryRylb(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 今日动态
	 * 
	 * */
	@RequestMapping("/jrdt")
	@ResponseBody
	public Object jrdt(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> map = zfjbxxService.jrdt(request);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
