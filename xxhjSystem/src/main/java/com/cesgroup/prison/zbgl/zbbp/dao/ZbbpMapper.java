package com.cesgroup.prison.zbgl.zbbp.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.zbbp.entity.ZbbpEntity;
import org.apache.ibatis.annotations.Param;

public interface ZbbpMapper extends BaseDao<ZbbpEntity, String> {
	
	public List<ZbbpEntity> findAllList(ZbbpEntity zbbpEntity); 
	
	public void deleteByConditions(ZbbpEntity zbbpEntity);

    List<Map<String,Object>> getUser(@Param("cusNumber") String cusNumber, @Param("dprtmntCode") String dprtmntCode, @Param("userName")String userName);
}
