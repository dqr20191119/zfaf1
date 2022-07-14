package com.cesgroup.prison.xxhj.sy.service;

import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.sy.entity.Sy;

public interface SyService extends IBaseCRUDService<Sy, String> {

	public Map<String, Object> countHomeMeunCount(String cusNumber,String cdjJobCode);
}	
