package com.cesgroup.prison.wwjg.fxgkgl.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.wwjg.fxgkgl.dao.FxgkglMapper;
import com.cesgroup.prison.wwjg.fxgkgl.entity.FxgkglEntity;
import com.cesgroup.prison.wwjg.fxgkgl.service.FxgkglService;

 
@Controller
@RequestMapping(value = "/wwjg/fxgkgl")
public class FxgkglController extends BaseEntityDaoServiceCRUDController<FxgkglEntity, 
	String, FxgkglMapper, FxgkglService> {
	
	private final Logger logger = LoggerFactory.getLogger(FxgkglController.class);  
	
	@Resource
	private FxgkglService FxgkglService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/fxgkgl/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/fxgkgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public FxgkglEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return FxgkglService.getById(id); 
	}
	@RequestMapping(value = "/getByfxcjId")
	@ResponseBody
	public FxgkglEntity getByfxcjId(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return FxgkglService.getByFxcjId(id); 
	}
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(FxgkglEntity fxgkglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<FxgkglEntity> pageInfo = (Page<FxgkglEntity>) FxgkglService.findList(fxgkglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(FxgkglEntity fxgkglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		/*wwjgwhEntity.setDcaCusNumber(user.getOrgCode());
		wwjgwhEntity.setDcaStatus(CommonConstant.STATUS_VALID_CODE);*/
		List<FxgkglEntity> list = FxgkglService.findAllList(fxgkglEntity);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i<list.size(); i++){
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getId());
			/*map.put("text", list.get(i).getDcaCategoryName());	*/
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(FxgkglEntity fxgkglEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = fxgkglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				fxgkglEntity.setcGxr(user.getUserName());
				fxgkglEntity.setcGxrId(user.getUserId());
				fxgkglEntity.setcGxRq(new Date());
			} else {
				fxgkglEntity.setcCjr(user.getUserName());
				fxgkglEntity.setcCjrId(user.getUserId());
				fxgkglEntity.setcCjRq(new Date());
				fxgkglEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
				
			}
			
			FxgkglService.saveOrUpdate(fxgkglEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	public Map<String, Object> deleteByIds(String ids, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
	
		try {
			FxgkglService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
	 
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/getByCode")
	@ResponseBody
	public String getByCode(String code, HttpServletRequest request, HttpServletResponse response) {
	 		
		FxgkglEntity fxgkglEntity = FxgkglService.getByCode(code); 
		if(fxgkglEntity==null){
			return "0";    //不存在 可以新增
		}else{
			return "1";   //存在 不可新增
		}
	}
	
	/**
	 * 假的五大改造嵌入
	 */
	@RequestMapping(value = "/toIndexWdgz")
	public ModelAndView toIndexWdgz(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/fxgkgl/indexWdgz");
		return mv;
	}
	@RequestMapping(value = "/searchDataWdgz")
	@ResponseBody
	public Map<String, Object> searchDataWdgz( HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,Object>> pageInfo = (Page<Map<String,Object>>) FxgkglService.findListWdgz(pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
}
