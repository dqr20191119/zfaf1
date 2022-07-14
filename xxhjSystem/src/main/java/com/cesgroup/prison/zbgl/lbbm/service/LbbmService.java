package com.cesgroup.prison.zbgl.lbbm.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.lbbm.entity.LbbmEntity;

public interface LbbmService extends IBaseCRUDService<LbbmEntity, String> {

	public List<Map<String, Object>> findAllList(LbbmEntity lbbmEntity);
	
	public void deleteByConditions(String dcdCategoryId);
	
}