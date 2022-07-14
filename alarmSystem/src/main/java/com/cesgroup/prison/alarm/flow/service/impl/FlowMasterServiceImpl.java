package com.cesgroup.prison.alarm.flow.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.alarm.flow.dao.FlowDtlsMapper;
import com.cesgroup.prison.alarm.flow.dao.FlowMasterMapper;
import com.cesgroup.prison.alarm.flow.entity.FlowDtlsEntity;
import com.cesgroup.prison.alarm.flow.entity.FlowMasterEntity;
import com.cesgroup.prison.alarm.flow.service.FlowMasterService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

@Service
public class FlowMasterServiceImpl extends BaseService<FlowMasterEntity, String> implements FlowMasterService {

	@Autowired
	FlowMasterMapper flowMasterMapper;

	@Autowired
	FlowDtlsMapper flowDtlsMapper;

	@Override
	public FlowMasterMapper getDao() {
		return flowMasterMapper;
	}

	@Override
	@Transactional
	public FlowMasterEntity saveFlow(FlowMasterEntity flowMaster) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		flowMaster.setHfmUpdtUserId(userId);
		flowMaster.setHfmUpdtTime(new Date());
		flowMaster.setHfmCusNumber(userBean.getCusNumber());
		String flowDtlsString = flowMaster.getHfmFlowDtls();
		if (EUserLevel.PROV == userBean.getUserLevel()) {
			Short indc = 0;
			flowMaster.setHfmTypeIndc(indc);
		} else {
			Short indc = 1;
			flowMaster.setHfmTypeIndc(indc);
		}

		String cusNumber = flowMaster.getHfmCusNumber();
		int order = 1;
		if (flowMaster.getId() != null && !"".equals(flowMaster.getId())) {
			// 修改
			flowMasterMapper.updateSelective(flowMaster);
		} else {
			// 新增
			flowMaster.setHfmCrteUserId(userId);
			flowMaster.setHfmCrteTime(new Date());
			flowMasterMapper.insert(flowMaster);
		}
		flowMaster.setHfmUpdtUserId(userId);
		flowMaster.setHfmUpdtTime(new Date());


		String[] dtlsGroup = flowDtlsString.split(";");

		// 删除原先全部的
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masterId", flowMaster.getId());
		flowDtlsMapper.deleteByMasterId(map);
		// 遍历流程步骤
		for (String dtlsDetail : dtlsGroup) {
			String itemName = dtlsDetail;
			FlowDtlsEntity flowDtls = new FlowDtlsEntity();
			flowDtls.setHfdCusNumber(cusNumber);
			flowDtls.setHfdUpdtUserId(userId);
			flowDtls.setHfdUpdtTime(new Date());
			flowDtls.setHfdFlowItemName(itemName);
			order++;
			flowDtls.setHfdCrteUserId(userId);
			flowDtls.setHfdCrteTime(new Date());
			flowDtls.setHfdFlowId(flowMaster.getId());
			flowDtlsMapper.insertSelective(flowDtls);
		}
		return flowMaster;
	}

	@Override
	@Transactional
	public int deleteByIds(List<String> ids) {
		int num = flowMasterMapper.deleteByIds(ids);
		flowDtlsMapper.deleteByMasterIds(ids);
		return num;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FlowMasterEntity findById(String id) {
		FlowMasterEntity flowMaster = flowMasterMapper.selectOne(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List list = flowDtlsMapper.findByMasterId(map);
		flowMaster.setHfmFlowDtlsList(list);
		return flowMaster;
	}

	@Override
	public Page<Map<String, String>> findByPage(FlowMasterEntity flowMaster, PageRequest pageRequest) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		if (EUserLevel.PRIS == userBean.getUserLevel() && flowMaster.getHfmTypeIndc() != 0) {
			flowMaster.setHfmCusNumber(userBean.getCusNumber());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("entity", flowMaster);
		return flowMasterMapper.findByPage(map, pageRequest);
	}

	@Override
	public List<Map<String, Object>> findMaster(String cusNumber, String cusNum, String id) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(cusNum)) {
			map.put("cusNum", cusNum);
		}
		if (StringUtils.isNotBlank(id)) {
			map.put("id", id);
		}
		return flowMasterMapper.findMaster(map);
	}

	@Override
	public List<Map<String, Object>> findFlowDtls(String cusNumber, String cusNum, String flowId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(cusNum)) {
			map.put("cusNum", cusNum);
		}
		if (StringUtils.isNotBlank(flowId)) {
			map.put("flowId", flowId);
		}
		return flowDtlsMapper.findFlowDtls(map);
	}

	@Override
	public List<Map<String, Object>> searchCombData(String cusNumber) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		return flowMasterMapper.searchCombData(map);
	}

}
