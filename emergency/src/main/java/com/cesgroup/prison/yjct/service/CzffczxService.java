package com.cesgroup.prison.yjct.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.CzffczxEntity;

public interface CzffczxService extends IBaseCRUDService<CzffczxEntity, String> {
	
	public List<CzffczxEntity> findAllList(CzffczxEntity czffczxEntity);
}
