package com.cesgroup.prison.yjct.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.YapzEntity;

public interface YapzService extends IBaseCRUDService<YapzEntity, String> {
	
	public List<YapzEntity> findAllList(YapzEntity yapzEntity);
}
