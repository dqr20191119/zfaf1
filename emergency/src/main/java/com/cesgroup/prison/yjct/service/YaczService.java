package com.cesgroup.prison.yjct.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.YaczEntity;

public interface YaczService extends IBaseCRUDService<YaczEntity, String> {
	
	public List<YaczEntity> findAllList(YaczEntity yaczEntity);
}
