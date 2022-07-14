package com.cesgroup.prison.yjct.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.GzzcyEntity;

public interface GzzcyMapper extends BaseDao<GzzcyEntity, String> {

	public List<GzzcyEntity> findAllList(GzzcyEntity gzzcyEntity);

	public void deleteByCondition(GzzcyEntity gzzcyEntity);
}
