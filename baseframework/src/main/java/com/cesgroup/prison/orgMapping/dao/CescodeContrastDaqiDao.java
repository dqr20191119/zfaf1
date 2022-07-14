package com.cesgroup.prison.orgMapping.dao;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.orgMapping.entity.CescodeContrastDaqi;

/**
 * 中信与大旗机构代码映射数据库访问类
 * 
 * @author lincoln.cheng
 *
 */
public interface CescodeContrastDaqiDao extends BaseDao<CescodeContrastDaqi, String> {
	/**
	 * 根据中信编码查询中信与大旗机构编码映射数据
	 * 
	 * @param cesKey
	 * @return
	 */
	CescodeContrastDaqi findByCesKey(@Param("cesKey") String cesKey);
	
	/**
	 * 根据大旗编码查询中信与大旗机构代码映射数据
	 * 
	 * @param daqiKey
	 * @return
	 */
	CescodeContrastDaqi findByDaqiKey(@Param("daqiKey") String daqiKey);
}