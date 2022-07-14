package com.cesgroup.prison.xtgl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xtgl.dao.UserConfigMapper;
import com.cesgroup.prison.xtgl.entity.UserConfig;
import com.cesgroup.prison.xtgl.service.UserConfigService;


@Service
public class UserConfigServiceImpl extends BaseDaoService<UserConfig,String,UserConfigMapper>  implements UserConfigService{
	
	@Override
	@Transactional
	public int updatePart(UserConfig model) {
		return this.getDao().updatePart(model);
	}

	@Override
	public UserConfig findByUcUserId(String ucUserId) {
		return this.getDao().findByUcUserId(ucUserId);
	}
}
