package com.cesgroup.prison.yjct.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.YapzEntity;

public interface YapzMapper extends BaseDao<YapzEntity, String> {

	public List<YapzEntity> findAllList(YapzEntity yapzEntity);
	
	public List<YapzEntity> findAllList1(YapzEntity yapzEntity);

	public void deleteByCondition(YapzEntity yapzEntity);

	public void batchDeleteByPlanid(List<String> idList);
}
