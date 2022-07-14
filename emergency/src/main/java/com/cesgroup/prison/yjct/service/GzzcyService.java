package com.cesgroup.prison.yjct.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.GzzcyEntity;

public interface GzzcyService extends IBaseCRUDService<GzzcyEntity, String> {
	
	public List<GzzcyEntity> findAllList(GzzcyEntity gzzcyEntity);
}
