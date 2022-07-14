package com.cesgroup.prison.doorPlan.service.impl;

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

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.doorPlan.dao.DoorPlanMapper;
import com.cesgroup.prison.doorPlan.dao.DoorPlanRltnMapper;
import com.cesgroup.prison.doorPlan.entity.DoorPlanEntity;
import com.cesgroup.prison.doorPlan.entity.DoorPlanRltnEntity;
import com.cesgroup.prison.doorPlan.service.DoorPlanService;

@Service
@Transactional
public class DoorPlanServiceImpl extends BaseDaoService<DoorPlanEntity, String, DoorPlanMapper>
		implements DoorPlanService {

	@Autowired
	private DoorPlanMapper mapper;

	@Autowired
	private DoorPlanRltnMapper doorPlanRltnMapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public void addInfo(DoorPlanEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		entity.setPdoCusNumber(userBean.getCusNumber());
		entity.setPdoSttsIndc("0");// 状态，未生效
		entity.setPdoCrteTime(date);
		entity.setPdoCrteUserId(userId);
		entity.setPdoUpdtTime(date);
		entity.setPdoUpdtUserId(userId);
		mapper.insert(entity);
	}

	@Override
	public void updateInfo(DoorPlanEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setPdoUpdtTime(date);
			entity.setPdoUpdtUserId(userId);
			map.put("doorPlanEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(DoorPlanEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("doorPlanEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public DoorPlanEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<Map<String, Object>> listAllForMj(String cusNumber, String areaId, String planId, String doorName) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(areaId)) {
			map.put("areaId", areaId);
		}
		if (StringUtils.isNotBlank(planId)) {
			map.put("planId", planId);
		}
		if (StringUtils.isNotBlank(doorName)) {
			map.put("doorName", doorName);
		}
		return doorPlanRltnMapper.listAllForMj(map);
	}

	@Override
	public Page<Map<String, Object>> listAllForDoor(DoorPlanRltnEntity doorPlanRltnEntity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (doorPlanRltnEntity != null) {
			map.put("doorPlanRltnEntity", doorPlanRltnEntity);
		}
		return doorPlanRltnMapper.listAll(map, pageable);
	}

	@Override
	public void deleteByIdsForDoor(List<String> list) {
		doorPlanRltnMapper.deleteByIds(list);
	}

	@Override
	public AjaxMessage saveDoorRltn(Object doorDate) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			if (doorDate != null) {
				@SuppressWarnings("unchecked")
				List<Map<String, String>> doorList = (List<Map<String, String>>) doorDate;
				if (!doorList.isEmpty()) {
					UserBean userBean = AuthSystemFacade.getLoginUserInfo();
					String userId = userBean.getUserId();
					Date date = new Date();
					for (Map<String, String> map : doorList) {
						DoorPlanRltnEntity doorPlanRltnEntity = new DoorPlanRltnEntity();
						String id = map.get("ID");
						doorPlanRltnEntity.setDorPlanId(map.get("DOR_PLAN_ID"));
						doorPlanRltnEntity.setDorDoorId(map.get("DOR_DOOR_ID"));
						doorPlanRltnEntity.setDorDoorName(map.get("DOR_DOOR_NAME"));
						doorPlanRltnEntity.setDorExecOrder(map.get("DOR_EXEC_ORDER"));
						doorPlanRltnEntity.setDorRemark(map.get("DOR_REMARK"));
						if (StringUtils.isNotBlank(id)) {
							doorPlanRltnEntity.setId(id);
							doorPlanRltnEntity.setDorUpdtTime(date);
							doorPlanRltnEntity.setDorUpdtUserId(userId);
							Map<String, Object> map_ = new HashMap<>();
							map_.put("doorPlanRltnEntity", doorPlanRltnEntity);
							doorPlanRltnMapper.updateInfo(map_);
						} else {
							doorPlanRltnEntity.setDorUpdtTime(date);
							doorPlanRltnEntity.setDorUpdtUserId(userId);
							doorPlanRltnEntity.setDorCrteTime(date);
							doorPlanRltnEntity.setDorCrteUserId(userId);
							doorPlanRltnEntity.setDorCusNumber(userBean.getCusNumber());
							doorPlanRltnMapper.insert(doorPlanRltnEntity);
						}
					}
					flag = true;
					obj = "关联成功";
				}
			}
		} catch (Exception e) {
			flag = false;
			obj = "发生异常，关联失败";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@Override
	public List<Map<String, Object>> findForCombotree(Map<String, Object> paramMap) {

		List<Map<String, Object>> planList = mapper.findAllPlan(paramMap);
		if (paramMap.get("id") != null && !"".equals(paramMap.get("id"))) {
			List<Map<String, Object>> doorRltnList = mapper.findAllDoorRltn(paramMap);
			if (paramMap.get("stts").equals("0")) {
				for (Map<String, Object> map : doorRltnList) {
					map.put("nocheck", true);
				}
			}
			return doorRltnList;
		}

		if (planList != null && !planList.isEmpty()) {
			for (Map<String, Object> map : planList) {
				map.put("isParent", map.get("childrenNum").equals("0") ? false : true);
				map.put("open", false);
			}
		}
		return planList;
	}

	@Override
	public void updateSttsByIds(List<String> list, String stts) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(stts) && list != null && !list.isEmpty()) {
			map.put("stts", stts);
			map.put("ids", list);
			mapper.updateSttsByIds(map);
		}

	}

}
