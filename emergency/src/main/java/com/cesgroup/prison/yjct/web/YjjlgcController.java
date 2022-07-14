package com.cesgroup.prison.yjct.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.yjct.dao.YjjlgcMapper;
import com.cesgroup.prison.yjct.entity.YjjlgcEntity;
import com.cesgroup.prison.yjct.service.YjjlgcService;

/**
 * 处置、演练记录过程管理
 * 
 */
@Controller
@RequestMapping(value = "/yjct/yjjlgc")
public class YjjlgcController extends BaseEntityDaoServiceCRUDController<YjjlgcEntity, String, YjjlgcMapper, YjjlgcService> {
	
	private final Logger logger = LoggerFactory.getLogger(YjjlgcController.class);  
	
	@Resource
	private YjjlgcService yjjlgcService;
	
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<YjjlgcEntity> searchAllData(YjjlgcEntity yjjlgcEntity, HttpServletRequest request,
			HttpServletResponse response) {
						 
		return yjjlgcService.findAllList(yjjlgcEntity);
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(YjjlgcEntity yjjlgcEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		String alarmRecordId = request.getParameter("alarmRecordId");
		String type = request.getParameter("type");
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = yjjlgcEntity.getId();
			
			if(id != null && !"".equals(id)) {
				yjjlgcEntity.setEhpUpdtUserId(user.getUserId());
				yjjlgcEntity.setEhpUpdtTime(new Date());
			} else {
				yjjlgcEntity.setEhpCusNumber(user.getOrgCode());
				yjjlgcEntity.setEhpCrteUserId(user.getUserId());
				yjjlgcEntity.setEhpCrteTime(new Date());
				yjjlgcEntity.setEhpUpdtUserId(user.getUserId());
				yjjlgcEntity.setEhpUpdtTime(new Date());
			}
			
			String msg = yjjlgcService.saveOrUpdate(yjjlgcEntity);
			if("1".equals(type)){
				yjjlgcService.updateAlarmRecord(alarmRecordId, user.getCusNumber(), user.getUserName(), user.getUserId());
			}
			if(!"".equals(msg)) {
				resultMap.put("code", CommonConstant.VALID_FAILURE_CODE);
				resultMap.put("msg", msg);
			} else {
				resultMap.put("code", CommonConstant.SUCCESS_CODE);
			}		
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
}
