package com.cesgroup.prison.zbgl.mbxq.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;

public interface MbxqService extends IBaseCRUDService<MbxqEntity, String> {
	
	public List<MbxqEntity> findAllList(MbxqEntity MbxqEntity);
	
}
