package com.cesgroup.prison.zbgl.pbgz.web;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;
import com.cesgroup.prison.zbgl.mbxq.service.MbxqService;
import com.cesgroup.prison.zbgl.pbgz.dao.PbgzMapper;
import com.cesgroup.prison.zbgl.pbgz.entity.PbgzEntity;
import com.cesgroup.prison.zbgl.pbgz.service.PbgzService;
import com.cesgroup.prison.zbgl.rygl.web.RyglController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 排班规则
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/pbgz")
public class PbgzlController extends BaseEntityDaoServiceCRUDController<PbgzEntity, String, PbgzMapper, PbgzService> {
	
	private final Logger logger = LoggerFactory.getLogger(RyglController.class);
	
	@Resource
	private PbgzService pbgzService;
	@Resource
	private MbxqService mbxqService ;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/bcgl/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/bcgl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public PbgzEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return pbgzService.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(PbgzEntity bcglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		bcglEntity.setDorCusNumber(user.getOrgCode());
		Page<PbgzEntity> pageInfo = (Page<PbgzEntity>) pbgzService.findList(bcglEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(PbgzEntity bcglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		bcglEntity.setDorCusNumber(user.getOrgCode());
		bcglEntity.setDorStatus(CommonConstant.STATUS_VALID_CODE);
		List<PbgzEntity> list = pbgzService.findAllList(bcglEntity);
		
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < list.size(); i++) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getId());
			map.put("text", list.get(i).getDorDutyOrderName());	
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(PbgzEntity bcglEntity, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = bcglEntity.getId();
			
			if(id != null && !"".equals(id)) {
				bcglEntity.setDorUpdtUserId(user.getUserId());
				bcglEntity.setDorUpdtTime(new Date());
			} else {
				bcglEntity.setDorCusNumber(user.getOrgCode());
				bcglEntity.setDorStatus(CommonConstant.STATUS_VALID_CODE);
				bcglEntity.setDorCrteUserId(user.getUserId());
				bcglEntity.setDorCrteTime(new Date());
				bcglEntity.setDorUpdtUserId(user.getUserId());
				bcglEntity.setDorUpdtTime(new Date());
			}
			
			pbgzService.saveOrUpdate(bcglEntity);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
		
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public Map<String, Object> deleteByIds(String id, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		MbxqEntity mbxqEntity = new MbxqEntity();
		mbxqEntity.setMojOrderId(id);
		List<MbxqEntity> list =  mbxqService.findAllList(mbxqEntity);
		if (list != null && list.size()> 0) {
			resultMap.put("code","3");
		}else {
			try {
				pbgzService.deleteById(id);
				resultMap.put("code", CommonConstant.SUCCESS_CODE);
			} catch(Exception e) {
				
				logger.error(e.toString(), e.fillInStackTrace());
				resultMap.put("code", CommonConstant.FAILURE_CODE);
			}
		}
		return resultMap;
	}
}
