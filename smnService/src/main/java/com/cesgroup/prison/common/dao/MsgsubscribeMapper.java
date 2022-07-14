package com.cesgroup.prison.common.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.MsgsubscribeEntity;

public interface MsgsubscribeMapper extends BaseDao<MsgsubscribeEntity, String> {
	
	public List<MsgsubscribeEntity> findAllList(MsgsubscribeEntity msgsubscribeEntity);
	
	public List<MsgsubscribeEntity> findAllData(MsgsubscribeEntity msgsubscribeEntity);
}
