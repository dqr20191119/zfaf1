package com.cesgroup.prison.jfsb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.dao.AreadeviceMapper;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.DoorMapper;
import com.cesgroup.prison.jfsb.entity.DoorEntity;
import com.cesgroup.prison.jfsb.service.DoorService;

@Service
@Transactional
public class DoorServiceImpl extends BaseDaoService<DoorEntity, String, DoorMapper> implements DoorService {

	@Autowired
	private DoorMapper mapper;

	@Resource
	private AreadeviceMapper areadeviceMapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public AjaxMessage addInfo(DoorEntity entity) {

		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();

			DoorEntity doorEntity = new DoorEntity();
			doorEntity.setDcbCusNumber(userBean.getCusNumber());
			doorEntity.setDcbIdnty(entity.getDcbIdnty());

			if (StringUtils.isBlank(entity.getDcbCtrlIdnty())) {
				Map<String, Object> map = this.findDoorSum(doorEntity);
				if (Integer.parseInt((String) map.get("SUM")) > 0) {
					flag = false;
					obj = "编号【" + entity.getDcbIdnty() + "】重复，保存失败";
				}else {
					entity.setDcbCusNumber(userBean.getCusNumber());
					entity.setDcbActionIndc("1");// 增加
					entity.setDcbCrteTime(date);
					entity.setDcbCrteUserId(userId);
					entity.setDcbUpdtTime(date);
					entity.setDcbUpdtUserId(userId);
					mapper.insert(entity);
					flag = true;
					obj = "保存成功";

				}
			} else {
				entity.setDcbCusNumber(userBean.getCusNumber());
				entity.setDcbActionIndc("1");// 增加
				entity.setDcbCrteTime(date);
				entity.setDcbCrteUserId(userId);
				entity.setDcbUpdtTime(date);
				entity.setDcbUpdtUserId(userId);
				mapper.insert(entity);
				flag = true;
				obj = "保存成功";

			}

		} catch (Exception e) {
			flag = false;
			obj = "保存异常： " + e.getMessage();
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@Override
	public void updateInfo(DoorEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setDcbActionIndc("2");// 更新
			entity.setDcbUpdtTime(date);
			entity.setDcbUpdtUserId(userId);
			map.put("doorEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(DoorEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("doorEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public DoorEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<DoorEntity> findByIds(List<String> ids) {
		return mapper.findByIds(ids);
	}

	@Override
	public List<Map<String, Object>> findForJqTree(Map<String, Object> paramMap) {
		if (paramMap.get("id") != null) {
			List<Map<String, Object>> doorList = mapper.findAllDoor(paramMap);
			return doorList;
		} else {
			List<Map<String, Object>> areaList = mapper.findAreaDepartment(paramMap);
			for (Map<String, Object> areaMap : areaList) {
				areaMap.put("isParent", true);
				areaMap.put("open", false);
			}
			return areaList;
		}
	}

	@Override
	public List<Map<String, Object>> findForTree(Map<String, Object> paramMap) {

		List<Map<String, Object>> areaList = areadeviceMapper.findAllArea(paramMap);
		if (paramMap.get("id") != null && (areaList == null || areaList.isEmpty())) {
			List<Map<String, Object>> doorList = mapper.findAllDoor(paramMap);
			return doorList;
		}
		for (Map<String, Object> areaMap : areaList) {
			areaMap.put("isParent", true);
			areaMap.put("open", false);
		}
		return areaList;
	}

	@Override
	public Map<String, Object> findDoorSum(DoorEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("doorEntity", entity);
		}
		return mapper.findDoorSum(map);
	}
}
