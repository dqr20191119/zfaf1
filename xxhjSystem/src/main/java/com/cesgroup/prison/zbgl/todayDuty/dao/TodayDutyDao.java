package com.cesgroup.prison.zbgl.todayDuty.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.todayDuty.dto.TodayDutyDto;
import com.cesgroup.prison.zbgl.todayDuty.entity.TodayDuty;

/**
 * 今日值班DAO
 * @author lincoln.cheng
 *
 */
public interface TodayDutyDao extends BaseDao<TodayDuty, String> {

    /**
     * 分页查询今日值班数据
     * 
     * @param map
     * @param pageable
     * @return
     */
	Page<Map<String, Object>> findWithPage(Map<String, Object> map, Pageable pageable);

	/**
	 * 根据查询条件查询值班信息
	 * @param paramMap
	 * @return
	 */
	public List<TodayDutyDto> findByCategoryNameAndModeNameAndOrderNameLike(Map<String, Object> paramMap);
	/**
	 * 根据查询条件查询值班信息
	 * @param paramMap
	 * @return
	 */
	public List<TodayDutyDto> findByCategoryNameLikeAndModeNameLikeAndDeptCode(Map<String, Object> paramMap);
	
	/**
	 * 根据部门编码、部门值班岗位编号，查询部门某个岗位的今日值班人员信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<TodayDutyDto> findByDeptCodeAndDutyJobId(Map<String, Object> paramMap);
	/**
	 * 查询今日值班数据  新增
	 * @param cusNumber
	 * @param orderName
	 * @return
	 */
	List<TodayDutyDto> getTodayDuty(@Param("cusNumber") String cusNumber, @Param("orderName") String orderName,@Param("dutyDate") String dutyDate);
	
	/**
	 * 分页查询今日值班数据
	 * @param map
	 * @param pageable
	 * @return
	 */
	Page<Map<String, Object>> getTodayDutyListWithPage(Map<String, Object> map, Pageable pageable);
}
