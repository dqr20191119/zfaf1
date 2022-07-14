package com.cesgroup.prison.yjct.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.YjjlgcEntity;

public interface YjjlgcMapper extends BaseDao<YjjlgcEntity, String> {

	public List<YjjlgcEntity> findAllList(YjjlgcEntity yjjlgcEntity); 	
	
	public void updateAlarmRecord(Map<String, Object> map);
}
