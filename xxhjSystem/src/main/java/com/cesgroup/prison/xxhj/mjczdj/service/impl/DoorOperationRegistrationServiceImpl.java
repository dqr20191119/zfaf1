package com.cesgroup.prison.xxhj.mjczdj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.mjczdj.dao.DoorOperationRegistrationMapper;
import com.cesgroup.prison.xxhj.mjczdj.entity.DoorOperationRegistrationEntity;
import com.cesgroup.prison.xxhj.mjczdj.service.DoorOperationRegistrationService;

@Service
@Transactional
public class DoorOperationRegistrationServiceImpl
		extends BaseDaoService<DoorOperationRegistrationEntity, String, DoorOperationRegistrationMapper>
		implements DoorOperationRegistrationService {

	@Autowired
	private DoorOperationRegistrationMapper mapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public void addInfo(DoorOperationRegistrationEntity entity) throws Exception {
		mapper.insert(entity);
	}

	@Override
	public void updateInfo(DoorOperationRegistrationEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setDorUpdtTime(date);
			entity.setDorUpdtUserId(userId);
			entity.setDorUpdtUser(userBean.getRealName());
			map.put("doorOperationRegistrationEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(DoorOperationRegistrationEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("doorOperationRegistrationEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public DoorOperationRegistrationEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<Map<String, Object>> findTodayRegistrationByDprtmntAndCusNumber(String cusNumber, String dprtmntId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber) && StringUtils.isNotBlank(dprtmntId)) {
			map.put("dprtmntId", dprtmntId);
			map.put("cusNumber", cusNumber);
			return mapper.findTodayRegistrationByDprtmntAndCusNumber(map);
		}
		return null;
	}

}
