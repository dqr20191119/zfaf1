package com.cesgroup.prison.regionDepart.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.regionDepart.entity.RegionDepart;

public interface RegionDepartMapper extends BaseDao<RegionDepart,String> {
    /**
     * 根据监狱编号和区域编号获取部门信息集合
     * @param cusNumber
     * @param areaId
     * @return
     */
	public List<RegionDepart> findByCusNumberAndAreaId(String cusNumber, String areaId);
	/**
	 * 批量添加
	 * @param list
	 */
	public int insertByBatch(List<RegionDepart> list);
	/**
	 * 根据监狱编号和区域编号批量删除
	 * @param cusNumber
	 * @param areaId
	 */
	public int deleteByCusNumberAndAreaId(String cusNumber, String areaId);
	/**
	 * 根据监狱编号和部门编号获取区域编号集合
	 * @param cusNumber
	 * @param departId
	 * @return
	 */
	public List<Map<String, Object>> findByCusNumberAndDprtmntId(String cusNumber, String departId);
	
	
}