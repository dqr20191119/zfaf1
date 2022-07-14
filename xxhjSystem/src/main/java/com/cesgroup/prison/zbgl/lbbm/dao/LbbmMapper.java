package com.cesgroup.prison.zbgl.lbbm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.lbbm.entity.LbbmEntity;

public interface LbbmMapper extends BaseDao<LbbmEntity, String> {

	public List<Map<String, Object>> findAllList(LbbmEntity LbbmEntity); 
	
	public void deleteByConditions(@Param("dcdCategoryId")String dcdCategoryId);
	
}

