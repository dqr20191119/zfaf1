package com.cesgroup.prison.common.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.entity.MsgsubscribeEntity;

public interface MsgsubscribeService extends IBaseCRUDService<MsgsubscribeEntity, String> {
	
	public List<MsgsubscribeEntity> findAllList(MsgsubscribeEntity msgsubscribeEntity);
	
	public void saveOrUpdate(MsgsubscribeEntity msgsubscribeEntity) throws Exception;
}
