package com.cesgroup.prison.common.web;

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
import com.cesgroup.prison.common.dao.MsgsubscribeMapper;
import com.cesgroup.prison.common.entity.MsgsubscribeEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.MsgsubscribeService;

/**
 * 消息订阅管理
 * 
 */
@Controller
@RequestMapping(value = "/common/msgsubscribe")
public class MsgsubscribeController extends BaseEntityDaoServiceCRUDController<MsgsubscribeEntity, String, MsgsubscribeMapper, MsgsubscribeService> {
	
	private final Logger logger = LoggerFactory.getLogger(MsgsubscribeController.class);  
	
	@Resource
	private MsgsubscribeService msgsubscribeService;	

	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<MsgsubscribeEntity> searchAllData(MsgsubscribeEntity msgsubscribeEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
					
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		 
		msgsubscribeEntity.setMsdCusNumber(user.getOrgCode());
		List<MsgsubscribeEntity> list = msgsubscribeService.findAllList(msgsubscribeEntity);

		return list;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(MsgsubscribeEntity msgsubscribeEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = msgsubscribeEntity.getId();
			
			if(id != null && !"".equals(id)) {
				msgsubscribeEntity.setMsdUpdtUserId(user.getUserId());
				msgsubscribeEntity.setMsdUpdtTime(new Date());
			} else {
				msgsubscribeEntity.setMsdCusNumber(user.getOrgCode());
				msgsubscribeEntity.setMsdCrteUserId(user.getUserId());
				msgsubscribeEntity.setMsdCrteTime(new Date());
				msgsubscribeEntity.setMsdUpdtUserId(user.getUserId());
				msgsubscribeEntity.setMsdUpdtTime(new Date());
			}
			
			msgsubscribeService.saveOrUpdate(msgsubscribeEntity);
			resultMap.put("code", "1");
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", "0");
		}
		
		return resultMap;
	}
}