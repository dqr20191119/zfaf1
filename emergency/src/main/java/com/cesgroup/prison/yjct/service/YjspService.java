package com.cesgroup.prison.yjct.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.YjspEntity;

public interface YjspService extends IBaseCRUDService<YjspEntity, String> {
	
	public List<YjspEntity> findAllList(YjspEntity yjspEntity);

	public void saveOrUpdate(YjspEntity yjspEntity);
}
