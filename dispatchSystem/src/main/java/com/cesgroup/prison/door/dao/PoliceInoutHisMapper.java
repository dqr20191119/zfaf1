package com.cesgroup.prison.door.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.door.entity.PoliceInoutEntity;
import com.cesgroup.prison.door.entity.PoliceInoutHisEntity;

public interface PoliceInoutHisMapper extends BaseDao<PoliceInoutHisEntity, String> {
	
	public List<PoliceInoutHisEntity> findAllList(PoliceInoutHisEntity policeInoutHisEntity);
	
	public void updateByCondition(PoliceInoutHisEntity policeInoutHisEntity);
	
	public void saveBySelect(PoliceInoutEntity policeInoutEntity);
}
