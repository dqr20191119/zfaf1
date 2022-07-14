package com.cesgroup.prison.wwjg.fxdgl.web;

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
import com.cesgroup.prison.wwjg.fxdgl.dao.FxdglMapper;
import com.cesgroup.prison.wwjg.fxdgl.entity.FxdglEntity;
import com.cesgroup.prison.wwjg.fxdgl.service.FxdglService;

 
@Controller
@RequestMapping(value = "/wwjg/fxdgl")
public class FxdglController extends BaseEntityDaoServiceCRUDController<FxdglEntity, 
	String, FxdglMapper, FxdglService> {
	
	private final Logger logger = LoggerFactory.getLogger(FxdglController.class);  
	
	@Resource
	private FxdglService fxdglService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/fxdgl/index");
		mv.addObject("sjfwName", request.getParameter("sjfwName"));
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/fxdgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public FxdglEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return fxdglService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(FxdglEntity fxdglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<FxdglEntity> pageInfo = (Page<FxdglEntity>) fxdglService.findList(fxdglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(FxdglEntity fxdglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		/*wwjgwhEntity.setDcaCusNumber(user.getOrgCode());
		wwjgwhEntity.setDcaStatus(CommonConstant.STATUS_VALID_CODE);*/
		List<FxdglEntity> list = fxdglService.findAllList(fxdglEntity);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i<list.size(); i++){
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getId());
			map.put("text", list.get(i).getName());
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(FxdglEntity fxdglEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = fxdglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				fxdglEntity.setcGxr(user.getUserName());
				fxdglEntity.setcGxrId(user.getUserId());
				fxdglEntity.setcGxRq(new Date());
			} else {
				fxdglEntity.setcCjr(user.getUserName());
				fxdglEntity.setcCjrId(user.getUserId());
				fxdglEntity.setcCjRq(new Date());
				fxdglEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
				
			}
			
			fxdglService.saveOrUpdate(fxdglEntity);
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
			fxdglService.deleteByIds(ids);
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
	 		
		FxdglEntity fxdglEntity = fxdglService.getByCode(code); 
		if(fxdglEntity==null){
			return "0";    //不存在 可以新增
		}else{
			return "1";   //存在 不可新增
		}
	}
}
