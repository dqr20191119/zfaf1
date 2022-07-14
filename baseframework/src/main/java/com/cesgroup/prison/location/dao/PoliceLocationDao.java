package com.cesgroup.prison.location.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.location.entity.PoliceLocation;

/**
 * 民警所在位置数据库访问类
 * 
 * @author lincoln.cheng
 *
 */
public interface PoliceLocationDao extends BaseDao<PoliceLocation, String> {
	/**
	 * 根据数据源查询数据列表
	 * 
	 * @param dataSource
	 * @return
	 */
	List<PoliceLocation> findByDataSource(@Param("dataSource") String dataSource);
	
	/**
	 * 根据数据源删除数据
	 * 
	 * @param dataSource
	 * @return
	 */
	void deleteByDataSource(@Param("dataSource") String dataSource);

    /**
     * 分页查询在监民警信息
     * @param map
     * @param pageable
     * @return
     */
	Page<Map<String, Object>> findWithPage(Map<String, Object> map, Pageable pageable);

    /**
     * 分页查询各监区生物识别信息
     * @param map
     * @param pageable
     * @return
     */
	Page<Map<String, Object>> findSwsbWithPage(Map<String, Object> map, Pageable pageable);

}