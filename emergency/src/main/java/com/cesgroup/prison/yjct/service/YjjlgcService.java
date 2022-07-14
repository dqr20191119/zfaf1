package com.cesgroup.prison.yjct.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.YjjlgcEntity;

public interface YjjlgcService extends IBaseCRUDService<YjjlgcEntity, String> {
	
	public List<YjjlgcEntity> findAllList(YjjlgcEntity yjjlgcEntity);

	public String saveOrUpdate(YjjlgcEntity yjjlgcEntity);
	
	public void updateAlarmRecord(String id, String cusNumber, String userName, String userId);
}
