package com.cesgroup.prison.inspect.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.inspect.entity.InspectCheckPolice;

public interface InspectCheckPoliceMapper extends BaseDao<InspectCheckPolice, String>{

	void updateByIcpInspectId(InspectCheckPolice cp);
	
	public int deleteByIcpInspectId(String icpInspectId);
}