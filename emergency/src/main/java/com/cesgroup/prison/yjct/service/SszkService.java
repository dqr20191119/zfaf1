package com.cesgroup.prison.yjct.service;

import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.SszkEntity;

public interface SszkService extends IBaseCRUDService<SszkEntity, String> {
	
	public Map<String, Object> findConfig(Map<String, Object> paramMap);
}
