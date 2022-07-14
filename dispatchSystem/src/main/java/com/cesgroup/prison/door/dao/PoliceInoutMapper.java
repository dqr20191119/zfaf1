package com.cesgroup.prison.door.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.door.entity.PoliceInoutEntity;

public interface PoliceInoutMapper extends BaseDao<PoliceInoutEntity, String> {
	
	public List<PoliceInoutEntity> findAllList(PoliceInoutEntity policeInoutEntity);
	
	public void updateByCondition(PoliceInoutEntity policeInoutEntity);
	
	public void deleteByCondition(PoliceInoutEntity policeInoutEntity);

	public List<Map<String, Object>> findUserIdByPoliceIdnty(Map<String, Object> paramMap);
}
