package com.cesgroup.prison.jfsb.service.impl;

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
import com.cesgroup.prison.jfsb.dao.DoorControlMapper;
import com.cesgroup.prison.jfsb.entity.DoorControlEntity;
import com.cesgroup.prison.jfsb.service.DoorControlService;

/**      
* @projectName：prison   
* @ClassName：DoorControlServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月12日 下午3:51:06   
* @version        
*/
@Service
@Transactional
public class DoorControlServiceImpl extends BaseDaoService<DoorControlEntity, String, DoorControlMapper>
		implements DoorControlService {

	@Autowired
	private DoorControlMapper mapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public void addInfo(DoorControlEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		entity.setDcdCusNumber(userBean.getCusNumber());
		entity.setDcdActionIndc("1");// 增加
		entity.setDcdCrteTime(date);
		entity.setDcdCrteUserId(userId);
		entity.setDcdUpdtTime(date);
		entity.setDcdUpdtUserId(userId);
		mapper.insert(entity);
	}

	@Override
	public void updateInfo(DoorControlEntity entity) throws Exception {

		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setDcdActionIndc("2");// 更新
			entity.setDcdUpdtTime(date);
			entity.setDcdUpdtUserId(userId);
			map.put("doorControlEntity", entity);
		}

		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(DoorControlEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("doorControlEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public DoorControlEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<Map<String, Object>> searchCombData(String cusNumber) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		return mapper.searchCombData(map);
	}

}
