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

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.djwg.dao.DjwgrywhMapper;
import com.cesgroup.prison.djwg.entity.DjwgrywhEntity;
import com.cesgroup.prison.djwg.entity.DjwgzzwhEntity;
import com.cesgroup.prison.djwg.service.DjwgrywhService;
import com.cesgroup.prison.djwg.service.DjwgzzwhService;
import com.cesgroup.prison.utils.DataUtils;

import ces.sdk.util.StringUtil;

@Controller
@RequestMapping(value = "/djwg/rywh")
public class DjwgrywhController extends BaseEntityDaoServiceCRUDController<DjwgrywhEntity, String, DjwgrywhMapper, DjwgrywhService> {

	@Resource
	private DjwgrywhService djwgrywhService;

	
	@Resource
	private DjwgzzwhService djwgzzwhService;
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("djwg/rywh/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("djwg/rywh/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public DjwgrywhEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return djwgrywhService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(DjwgrywhEntity djwgrywhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PageRequest pageRequest = buildPageRequest();
		Page<DjwgrywhEntity> pageInfo = (Page<DjwgrywhEntity>) djwgrywhService.findList(djwgrywhEntity, pageRequest);
		List list = pageInfo.getContent();
		DjwgrywhEntity djwgrywhEntity2 = new DjwgrywhEntity();
		List<DjwgrywhEntity> list2 = djwgrywhService.findAllList(djwgrywhEntity2);
		
		for(int i =0;i<list.size();i++){
			DjwgrywhEntity d = (DjwgrywhEntity) list.get(i);
			String parentCode = String.valueOf(d.getParentCode());
			if(!StringUtil.isBlank(parentCode)){
				for(int t =0;t<list2.size();t++){
					String code= list2.get(t).getCode();
					String name = list2.get(t).getUserName();
					String zw = list2.get(t).getZw();
					 if(code.equals(parentCode)){
						 d.setParentCode(name+"("+zw+")");
					 }
				 }
			}
			 
		}
		
		
		return DataUtils.pageToMap(pageInfo);		
		
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(DjwgrywhEntity djwgrywhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		List<DjwgrywhEntity> list = djwgrywhService.findAllList(djwgrywhEntity);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(int i = 0; i<list.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getCode());
			map.put("text", list.get(i).getUserName()+"("+list.get(i).getZw()+")");	
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(DjwgrywhEntity djwgrywhEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = djwgrywhEntity.getId();
			
			if(id != null && !"".equals(id)) {
				djwgrywhEntity.setcGxr(user.getUserName());
				djwgrywhEntity.setcGxrId(user.getUserId());
				djwgrywhEntity.setcGxRq(new Date());
			} else {
				djwgrywhEntity.setcCjr(user.getUserName());
				djwgrywhEntity.setcCjrId(user.getUserId());
				djwgrywhEntity.setcCjRq(new Date());
				djwgrywhEntity.setScflg(CommonConstant.STATUS_VALID_CODE);
			}
			String code = djwgrywhEntity.getZzCode();
			String jyId = user.getOrgCode();
			if(code.equals(jyId+"_FGBMJCDJLXD")){
				//如果为分管部门
				djwgrywhEntity.setUserId("");
				djwgrywhEntity.setUserName(djwgrywhEntity.getDeptName());
			} 
			
			djwgrywhService.saveOrUpdate(djwgrywhEntity);
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
			djwgrywhService.deleteByIds(ids);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			resultMap.put("code", CommonConstant.FAILURE_CODE);
	 
		}
		return resultMap;
	}
	
	
	@RequestMapping(value = "/getDjwg")
	@ResponseBody
	public AjaxResult getDjwg(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = djwgrywhService.getDjwg();
		return AjaxResult.success(resultMap);
	}
	
}
