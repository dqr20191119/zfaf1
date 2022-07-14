package com.cesgroup.prison.ext.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ext.entity.OptLogEntity;

public interface OptLogMapper extends BaseDao<OptLogEntity, String> {

	public void insertLog(OptLogEntity log);
	
}


