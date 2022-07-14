package com.cesgroup.prison.door.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.door.entity.DoorCardEntity;

public interface DoorCardMapper extends BaseDao<DoorCardEntity, String> {
			
	public void deleteByCondition(DoorCardEntity doorCardEntity);
}
