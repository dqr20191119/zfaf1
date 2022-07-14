package com.cesgroup.prison.deviceMaintain.service.impl;

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
import com.cesgroup.prison.deviceMaintain.dao.DeviceFaultTypeMapper;
import com.cesgroup.prison.deviceMaintain.dao.FaultDepmtReltMapper;
import com.cesgroup.prison.deviceMaintain.entity.DeviceFaultTypeEntity;
import com.cesgroup.prison.deviceMaintain.entity.FaultDepmtReltEntity;
import com.cesgroup.prison.deviceMaintain.service.DeviceFaultTypeService;

/**      
* @projectName：prison   
* @ClassName：DeviceFaultTypeServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月17日 下午9:58:46   
* @version        
*/
@Service
@Transactional
public class DeviceFaultTypeServiceImpl extends BaseDaoService<DeviceFaultTypeEntity, String, DeviceFaultTypeMapper>
		implements DeviceFaultTypeService {

	@Autowired
	private DeviceFaultTypeMapper mapper;

	@Autowired
	private FaultDepmtReltMapper faultDepmtReltMapper;

	@Override
	public void deleteById(DeviceFaultTypeEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			entity.setDftActionIndc("3");
			entity.setDftUpdtTime(new Date());
			map.put("deviceFaultTypeEntity", entity);
		}
		mapper.deleteById(map);
	}

	@Override
	public void addDeviceFaultTypeInfo(DeviceFaultTypeEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		entity.setDftCusNumber(userBean.getCusNumber());
		entity.setDftActionIndc("1");
		entity.setDftUpdtTime(date);
		entity.setDftUpdtUserId(userId);
		entity.setDftCrteTime(date);
		entity.setDftCrteUserId(userId);
		mapper.insert(entity);
	}

	@Override
	public void updateDeviceFaultTypeInfo(DeviceFaultTypeEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			entity.setDftActionIndc("2");
			entity.setDftUpdtTime(new Date());
			entity.setDftUpdtUserId(userBean.getUserId());
			map.put("deviceFaultTypeEntity", entity);
		}
		mapper.updateDeviceFaultTypeInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(DeviceFaultTypeEntity entity, String type, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("deviceFaultTypeEntity", entity);
		}
		if (type.trim().equals("1")) {
			return mapper.listAllForType(map, pageable);
		} else {
			return mapper.listAllForContent(map, pageable);
		}
	}

	@Override
	public DeviceFaultTypeEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public void addRelationInfo(FaultDepmtReltEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		entity.setFdrCusNumber(userBean.getCusNumber());
		entity.setFdrCrteTime(date);
		entity.setFdrCrteUserId(userId);
		entity.setFdrUpdtTime(date);
		entity.setFdrUpdtUserId(userId);
		faultDepmtReltMapper.insert(entity);
	}

	@Override
	public void updateRelationInfo(FaultDepmtReltEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			entity.setFdrUpdtUserId(userId);
			entity.setFdrUpdtTime(new Date());
			map.put("faultDepmtReltEntity", entity);
		}
		mapper.updateRelationInfo(map);
	}

	@Override
	public List<Map<String, Object>> searchCombData(String cusNumber, String typeClassify, String sttsIndc,
			String faultId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		if (StringUtils.isNotBlank(typeClassify)) {
			map.put("typeClassify", typeClassify.trim());
		}
		if (StringUtils.isNotBlank(sttsIndc)) {
			map.put("sttsIndc", sttsIndc.trim());
		}
		if (StringUtils.isNotBlank(faultId)) {
			map.put("faultId", faultId.trim());
		}
		return mapper.searchCombData(map);
	}

	@Override
	public void deleRelationDepartment(String id) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(id)) {
			map.put("ID", id.trim());
		}
		mapper.deleRelationDepartment(map);
	}

	@Override
	public Map<String, Object> findContentById(String id) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(id)) {
			map.put("ID", id.trim());
		}
		return mapper.findContentById(map);
	}

	@Override
	public Map<String, Object> findDprtmntByCusNumberAndFaultType(String cusNumber, String faultId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		if (StringUtils.isNotBlank(faultId)) {
			map.put("faultId", faultId.trim());
		}
		return mapper.findDprtmntByCusNumberAndFaultType(map);
	}
}
