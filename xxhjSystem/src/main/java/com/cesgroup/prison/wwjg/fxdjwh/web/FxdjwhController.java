package com.cesgroup.prison.wwjg.fxdjwh.web;

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
import com.cesgroup.prison.wwjg.fxdjwh.dao.FxdjwhMapper;
import com.cesgroup.prison.wwjg.fxdjwh.entity.FxdjwhEntity;
import com.cesgroup.prison.wwjg.fxdjwh.service.FxdjwhService;

 
@Controller
@RequestMapping(value = "/wwjg/fxdjwh")
public class FxdjwhController extends BaseEntityDaoServiceCRUDController<FxdjwhEntity, 
	String, FxdjwhMapper, FxdjwhService> {
	
	private final Logger logger = LoggerFactory.getLogger(FxdjwhController.class);  
	
	@Resource
	private FxdjwhService fxdjwhService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/fxdjwh/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("wwjg/fxdjwh/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public FxdjwhEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return fxdjwhService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(FxdjwhEntity fxdjwhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<FxdjwhEntity> pageInfo = (Page<FxdjwhEntity>) fxdjwhService.findList(fxdjwhEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(FxdjwhEntity fxdjwhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		/*wwjgwhEntity.setDcaCusNumber(user.getOrgCode());
		wwjgwhEntity.setDcaStatus(CommonConstant.STATUS_VALID_CODE);*/
		List<FxdjwhEntity> list = fxdjwhService.findAllList(fxdjwhEntity);
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
	public Map<String, Object> saveOrUpdate(FxdjwhEntity fxdjwhEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = fxdjwhEntity.getId();
			
			if(id != null && !"".equals(id)) {
				fxdjwhEntity.setcGxr(user.getUserName());
				fxdjwhEntity.setcGxrId(user.getUserId());
				fxdjwhEntity.setcGxRq(new Date());
			} else {
				fxdjwhEntity.setcCjr(user.getUserName());
				fxdjwhEntity.setcCjrId(user.getUserId());
				fxdjwhEntity.setcCjRq(new Date());
				fxdjwhEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
				
			}
			
			fxdjwhService.saveOrUpdate(fxdjwhEntity);
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
			fxdjwhService.deleteByIds(ids);
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
	 		
		FxdjwhEntity fxdjwhEntity = fxdjwhService.getByCode(code); 
		if(fxdjwhEntity==null){
			return "0";    //不存在 可以新增
		}else{
			return "1";   //存在 不可新增
		}
	}
}
