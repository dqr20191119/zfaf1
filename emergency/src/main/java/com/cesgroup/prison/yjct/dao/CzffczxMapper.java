package com.cesgroup.prison.yjct.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.CzffczxEntity;

public interface CzffczxMapper extends BaseDao<CzffczxEntity, String> {

	public List<CzffczxEntity> findAllList(CzffczxEntity czffczxEntity);

	public void deleteByCondition(CzffczxEntity czffczxEntity);
	
	public List<CzffczxEntity> findCountByPlanid(List<String> idList);
}
