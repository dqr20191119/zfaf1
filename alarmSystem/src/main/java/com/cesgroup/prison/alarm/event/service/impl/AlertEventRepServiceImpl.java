package com.cesgroup.prison.alarm.event.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.alarm.event.dao.AlertEventRepMapper;
import com.cesgroup.prison.alarm.event.dao.AlertEvidenceRelationMapper;
import com.cesgroup.prison.alarm.event.entity.AlertEventRepEntity;
import com.cesgroup.prison.alarm.event.entity.AlertEvidenceRelationEntity;
import com.cesgroup.prison.alarm.event.service.AlertEventRepService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

/**      
* @projectName：alarmSystem   
* @ClassName：AlertEventRepServiceImpl   
* @Description：   添加相关报警证据信息
* @author：Tao.xu   
* @date：2018年1月19日 上午10:25:49   
* @version        
*/
@Service
@Transactional
public class AlertEventRepServiceImpl extends BaseDaoService<AlertEventRepEntity, String, AlertEventRepMapper>
		implements AlertEventRepService {

	/**
	* @Fields mapper : 报警事件
	*/
	@Autowired
	private AlertEventRepMapper mapper;

	/**
	* @Fields relationMapper : 报警证据信息
	*/
	@Autowired
	private AlertEvidenceRelationMapper relationMapper;

	@Override
	public void addEventRep(AlertEventRepEntity entity) throws Exception {
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setAevCusNumber(userBean.getCusNumber());
			entity.setAevEventTime(date);
			entity.setAevEventUserId(userId);
			mapper.insert(entity);
		}

	}

	@Override
	public void addEvidenceRelation(AlertEvidenceRelationEntity entity) throws Exception {
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setAerCrteUserId(userId);
			entity.setAerCrteTime(date);
			entity.setAerCusNumber(userBean.getCusNumber());
			relationMapper.insert(entity);
		}

	}

	@Override
	public AlertEventRepEntity findEventRepByEntity(AlertEventRepEntity entity) {
		return mapper.selectByEntity(entity).get(0);
	}

	@Override
	public AlertEvidenceRelationEntity findEvidenceRelationByEntity(AlertEvidenceRelationEntity entity) {
		return relationMapper.selectByEntity(entity).get(0);
	}

}
