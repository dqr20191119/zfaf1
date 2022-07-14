package com.cesgroup.prison.yjct.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.YjspEntity;

public interface YjspMapper extends BaseDao<YjspEntity, String> {

	public List<YjspEntity> findAllList(YjspEntity yjspEntity);
}
