package com.cesgroup.prison.zbgl.lbgl.web;

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
import com.cesgroup.prison.zbgl.gwgl.entity.GwglEntity;
import com.cesgroup.prison.zbgl.gwgl.service.GwglService;
import com.cesgroup.prison.zbgl.lbbm.service.LbbmService;
import com.cesgroup.prison.zbgl.lbgl.dao.LbglMapper;
import com.cesgroup.prison.zbgl.lbgl.entity.LbglEntity;
import com.cesgroup.prison.zbgl.lbgl.service.LbglService;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;

/**
 * 类别管理
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/lbgl")
public class LbglController extends BaseEntityDaoServiceCRUDController<LbglEntity, String, LbglMapper, LbglService> {
	
	private final Logger logger = LoggerFactory.getLogger(LbglController.class);  
	
	@Resource
	private LbglService lbglService;
	@Resource
	private LbbmService lbbmService;
	@Resource
	private GwglService gwglService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/lbgl/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/lbgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public LbglEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return lbglService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(LbglEntity lbglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		lbglEntity.setDcaCusNumber(user.getOrgCode());
		Page<LbglEntity> pageInfo = (Page<LbglEntity>) lbglService.findList(lbglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(LbglEntity lbglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		lbglEntity.setDcaCusNumber(user.getOrgCode());
		lbglEntity.setDcaStatus(CommonConstant.STATUS_VALID_CODE);
		List<LbglEntity> list = lbglService.findAllList(lbglEntity);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i<list.size(); i++){
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getId());
			map.put("text", list.get(i).getDcaCategoryName());	
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(LbglEntity lbglEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = lbglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				lbglEntity.setDcaUpdtUserId(user.getUserId());
				lbglEntity.setDcaUpdtTime(new Date());
			} else {
				lbglEntity.setDcaCusNumber(user.getOrgCode());
				lbglEntity.setDcaStatus(CommonConstant.STATUS_VALID_CODE);
				lbglEntity.setDcaCrteUserId(user.getUserId());
				lbglEntity.setDcaCrteTime(new Date());
				lbglEntity.setDcaUpdtUserId(user.getUserId());
				lbglEntity.setDcaUpdtTime(new Date());
			}
			
			lbglService.saveOrUpdate(lbglEntity);
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
		GwglEntity gwglEntity = new GwglEntity();
		String[] id = ids.split(",");
		
		for(int i = 0; i<id.length; i++) {
			
			gwglEntity.setCdjCategoryId(id[i]);
			gwglEntity.setCdjStatus(CommonConstant.STATUS_VALID_CODE);
			List<GwglEntity> list = gwglService.findAllList(gwglEntity);
			
			if (list != null && list.size()> 0) {
				resultMap.put("code","3");
			}
		}
		if (resultMap.isEmpty()) {
			try {
				lbglService.deleteByIds(ids);
				resultMap.put("code", CommonConstant.SUCCESS_CODE);
			} catch(Exception e) {
				
				logger.error(e.toString(), e.fillInStackTrace());
				resultMap.put("code", CommonConstant.FAILURE_CODE);
			}
		}
		return resultMap;
	}
}
