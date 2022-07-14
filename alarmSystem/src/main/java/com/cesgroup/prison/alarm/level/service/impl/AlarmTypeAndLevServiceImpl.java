package com.cesgroup.prison.alarm.level.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.alarm.level.dao.AlarmTypeAndLevMapper;
import com.cesgroup.prison.alarm.level.entity.AlarmTypeAndLevEntity;
import com.cesgroup.prison.alarm.level.service.AlarmTypeAndLevService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
 

/**      
* @projectName：prison   
* @ClassName：AlarmTypeAndLevServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月25日 下午12:16:47   
* @version        
*/
@Service
@Transactional
public class AlarmTypeAndLevServiceImpl extends BaseDaoService<AlarmTypeAndLevEntity, String, AlarmTypeAndLevMapper>
		implements AlarmTypeAndLevService {

	@Autowired
	private AlarmTypeAndLevMapper mapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public void addInfo(AlarmTypeAndLevEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		entity.setAltCusNumber(userBean.getCusNumber());
		entity.setAltCrteTime(date);
		entity.setAltCrteUserId(userId);
		entity.setAltUpdtTime(date);
		entity.setAltUpdtUserId(userId);
		mapper.insert(entity);
	}

	@Override
	public void updateInfo(AlarmTypeAndLevEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setAltUpdtTime(date);
			entity.setAltUpdtUserId(userId);
			map.put("alarmTypeAndLevEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(AlarmTypeAndLevEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("alarmTypeAndLevEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public AlarmTypeAndLevEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<AlarmTypeAndLevEntity> findByCusNumberAndType(AlarmTypeAndLevEntity entity) {
		return mapper.selectByEntity(entity);
	}

}
