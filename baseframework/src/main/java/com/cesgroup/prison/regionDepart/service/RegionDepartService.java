package com.cesgroup.prison.regionDepart.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cesgroup.prison.regionDepart.entity.RegionDepart;

@Service
public interface RegionDepartService {
	/**
	 * 根据监狱编号和区域编号获取部门信息
	 * @param cusNumber	监狱编号
	 * @param areaId	区域编号
	 * @return	部门信息列表
	 */
	public List<RegionDepart> findByCusNumberAndAreaId(String cusNumber, String areaId);
	/**
	 * 批量添加
	 * @param list
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public int insertByBatch(String objs) throws ParseException, Exception;
	
}
