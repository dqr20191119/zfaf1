package com.cesgroup.prison.zbgl.zbcx.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.zbcx.entity.ZbcxEntity;

public interface ZbcxMapper extends BaseDao<ZbcxEntity, String> {

	public Page<Map<String, Object>> findList(Map<String, Object> map, Pageable pageable);

	public List<Map<String, Object>> findAllList(Map<String, Object> map);

	public List<Map<String, Object>> findInoutRecord(Map<String, Object> map);

	public List<Map<String, Object>> findTodayDutyPolice(Map<String, Object> map);

	/**
	* @methodName: queryDutyCountByDeapmntAndDate
	* @Description: 根据部门和日期查询值班人数
	* @param map
	* @return List<Map<String,Object>>
	*/
	public List<Map<String, String>> queryDutyCountByDeapmntAndDate(Map<String, Object> map);

	/**
	* @methodName: queryStaffByDeapmntAndDate
	* @Description: 根据部门和日期查询值班人详情
	* @param map
	* @param pageable
	* @return Page<Map<String,Object>>
	* @throws  
	*/
	public Page<Map<String, Object>> queryStaffByDeapmntAndDate(Map<String, Object> map, Pageable pageable);

}
