package com.cesgroup.prison.door.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.door.entity.PoliceInprsnEntity;

/**
 * 民警在监数据操作类
 * 
 * @author lincoln.cheng
 *
 */
public interface PoliceInprsnMapper extends BaseDao<PoliceInprsnEntity, String> {

	public List<PoliceInprsnEntity> findAllList(PoliceInprsnEntity policeInprsnEntity);
	
	public List<Map<String,Object>> countPoliceInprsn(PoliceInprsnEntity policeInprsnEntity);
	
	public void deleteByCondition(PoliceInprsnEntity policeInprsnEntity);
	
	/**
	 * 根据监狱编号、民警编号，查询在监民警列表
	 * 
	 * @param cipCusNumber
	 * @param cipPoliceIdnty
	 * @return
	 */
	public List<PoliceInprsnEntity> findByCipCusNumberAndCipPoliceIdnty(@Param("cipCusNumber") String cipCusNumber, @Param("cipPoliceIdnty") String cipPoliceIdnty);
}
