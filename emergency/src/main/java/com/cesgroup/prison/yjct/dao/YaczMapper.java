package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.YaczEntity;

public interface YaczMapper extends BaseDao<YaczEntity, String> {

	public List<YaczEntity> findAllList(YaczEntity yaczEntity);
	
	public List<YaczEntity> findAllList1(YaczEntity yaczEntity);

	public void deleteByCondition(YaczEntity yaczEntity);
		
	public List<YaczEntity> findCountByPlanid(YaczEntity yaczEntity);
	
	public void deleteByPlanidAndMethodid(@Param("epaPlanFid") String epaPlanFid, @Param("idList") List<String> idList);

	public void batchDeleteByPlanid(List<String> idList);
}
