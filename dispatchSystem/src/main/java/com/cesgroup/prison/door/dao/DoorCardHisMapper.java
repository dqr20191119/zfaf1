package com.cesgroup.prison.door.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.door.entity.DoorCardEntity;
import com.cesgroup.prison.door.entity.DoorCardHisEntity;

public interface DoorCardHisMapper extends BaseDao<DoorCardHisEntity, String> {
			
	public void saveBySelect(DoorCardEntity doorCardEntity);
}
