package com.cesgroup.prison.xtgl.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xtgl.entity.UserConfig;

public interface UserConfigMapper  extends BaseDao<UserConfig,String>{
	//局部修改
  	public int updatePart(UserConfig model);
  	
  	public UserConfig findByUcUserId(String ucUserId);
}