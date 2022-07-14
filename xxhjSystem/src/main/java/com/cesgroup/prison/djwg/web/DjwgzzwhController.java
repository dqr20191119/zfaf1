package com.cesgroup.prison.djwg.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cesgroup.prison.djwg.dao.DjwgzzwhMapper;
import com.cesgroup.prison.djwg.entity.DjwgzzwhEntity;
import com.cesgroup.prison.djwg.service.DjwgzzwhService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/djwg/zzwh")
public class DjwgzzwhController  extends BaseEntityDaoServiceCRUDController<DjwgzzwhEntity, String,DjwgzzwhMapper,DjwgzzwhService> {

	@Resource
	private DjwgzzwhService djwgzzwhService;
	
	

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("djwg/zzwh/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("djwg/zzwh/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public DjwgzzwhEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return djwgzzwhService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(DjwgzzwhEntity djwgzzwhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<DjwgzzwhEntity> pageInfo = (Page<DjwgzzwhEntity>) djwgzzwhService.findList(djwgzzwhEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(DjwgzzwhEntity djwgzzwhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		List<DjwgzzwhEntity> list = djwgzzwhService.findAllList(djwgzzwhEntity);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i<list.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getZzCode());
			map.put("text", list.get(i).getZzName());	
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(DjwgzzwhEntity djwgzzwhEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = djwgzzwhEntity.getId();
			
			if(id != null && !"".equals(id)) {
				djwgzzwhEntity.setcGxr(user.getUserName());
				djwgzzwhEntity.setcGxrId(user.getUserId());
				djwgzzwhEntity.setcGxRq(new Date());
			} else {
				djwgzzwhEntity.setcCjr(user.getUserName());
				djwgzzwhEntity.setcCjrId(user.getUserId());
				djwgzzwhEntity.setcCjRq(new Date());
				djwgzzwhEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
				
			}
			
			djwgzzwhService.saveOrUpdate(djwgzzwhEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	public Map<String, Object> deleteByIds(String ids, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
	
		try {
			djwgzzwhService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			resultMap.put("code", CommonConstant.FAILURE_CODE);
	 
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/getByCode")
	@ResponseBody
	public String getByCode(String zzCode, HttpServletRequest request, HttpServletResponse response) {
	 		
		DjwgzzwhEntity djwgzzwhEntity = djwgzzwhService.getByCode(zzCode); 
		if(djwgzzwhEntity==null){
			return "0";    //不存在 可以新增
		}else{
			return "1";   //存在 不可新增
		}
	}
	
}