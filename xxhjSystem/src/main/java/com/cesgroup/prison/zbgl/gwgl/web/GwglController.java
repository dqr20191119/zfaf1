package com.cesgroup.prison.zbgl.gwgl.web;

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
import com.cesgroup.prison.zbgl.gwgl.dao.GwglMapper;
import com.cesgroup.prison.zbgl.gwgl.entity.GwglEntity;
import com.cesgroup.prison.zbgl.gwgl.service.GwglService;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;
import com.cesgroup.prison.zbgl.mbxq.service.MbxqService;

/**
 * 岗位管理
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/gwgl")
public class GwglController extends BaseEntityDaoServiceCRUDController<GwglEntity, String, GwglMapper, GwglService> {
	
	private final Logger logger = LoggerFactory.getLogger(GwglController.class);  
	
	@Resource
	private GwglService gwglService;
	@Resource
	private MbxqService mbxqService ;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/gwgl/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/gwgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public GwglEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return gwglService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(GwglEntity gwglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		gwglEntity.setCdjCusNumber(user.getOrgCode());
		Page<GwglEntity> pageInfo = (Page<GwglEntity>) gwglService.findList(gwglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(GwglEntity gwglEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		gwglEntity.setCdjCusNumber(user.getOrgCode());
		gwglEntity.setCdjStatus(CommonConstant.STATUS_VALID_CODE);
		List<GwglEntity> list = gwglService.findAllList(gwglEntity);
		
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(int i = 0; i<list.size(); i++){
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getId());
			map.put("text", list.get(i).getCdjJobName());	
			maps.add(map);
		}
		
		return maps;
	}
		
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(GwglEntity gwglEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = gwglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				gwglEntity.setCdjUpdtUserId(user.getUserId());
				gwglEntity.setCdjUpdtUser(user.getRealName());
				gwglEntity.setCdjUpdtTime(new Date());
			} else {
				gwglEntity.setCdjCusNumber(user.getOrgCode());
				gwglEntity.setCdjStatus(CommonConstant.STATUS_VALID_CODE);
				gwglEntity.setCdjCrteUserId(user.getUserId());
				gwglEntity.setCdjCrteUser(user.getRealName());
				gwglEntity.setCdjCrteTime(new Date());
				gwglEntity.setCdjUpdtUserId(user.getUserId());
				gwglEntity.setCdjUpdtUser(user.getRealName());
				gwglEntity.setCdjUpdtTime(new Date());
			}
			
			gwglService.saveOrUpdate(gwglEntity);
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
		MbxqEntity mbxqEntity = new MbxqEntity();
		String[] id = ids.split(",");
		
		for(int i = 0; i<id.length; i++) {
			mbxqEntity.setMojJobId(id[i]);
			List<MbxqEntity> list = mbxqService.findAllList(mbxqEntity);
			if (list != null && list.size()> 0) {
				resultMap.put("code","3");
			}
		}
		
		if (resultMap.isEmpty()) {
			try {
				gwglService.deleteById(ids);
				resultMap.put("code", CommonConstant.SUCCESS_CODE);
			} catch(Exception e) {
				
				logger.error(e.toString(), e.fillInStackTrace());
				resultMap.put("code", CommonConstant.FAILURE_CODE);
			}
		} else {
			
		}
		return resultMap;
	}
}
