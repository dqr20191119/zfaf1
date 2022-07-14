package com.cesgroup.prison.inspect.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.inspect.entity.InspectPolice;

public interface InspectPoliceMapper extends BaseDao<InspectPolice, String>{
	public int deleteByIprInspectId(String iprInspectId);
}