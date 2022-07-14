package com.cesgroup.prison.yjct.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.BjyaEntity;

public interface BjyaService extends IBaseCRUDService<BjyaEntity, String>{
	
	public List<Map<String, Object>> findAllListForCombobox(BjyaEntity bjyaEntity);
}
