package com.cesgroup.prison.xtgl.service;

import com.cesgroup.prison.xtgl.entity.UserConfig;

public interface UserConfigService {
	//局部修改
	public int updatePart(UserConfig model);
	public UserConfig findByUcUserId(String ucUserId);
}
