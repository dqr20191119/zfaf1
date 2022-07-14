package com.cesgroup.prison.riskassess.chart.dao;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.riskassess.chart.entity.ChartData;

/**
 * Description: 图表展示数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ChartDataDao extends BaseDao<ChartData, String> {
	/**
	 * Description: 根据用户角色查询风险分布数据findFxfbByUserRole
	 * @param userRole
	 * @return
	 */
	public ChartData findFxfbByUserRole(@Param("userRole") String userRole);
	
	/**
	 * Description: 根据用户角色查询风险趋势数据findFxqsByUserRole
	 * @param userRole
	 * @return
	 */
	public ChartData findFxqsByUserRole(@Param("userRole") String userRole);
	
	/**
	 * Description: 根据风险评估种类与用户角色查询风险评估数据findFxpgByCategoryAndUserRole
	 * @param category
	 * @param userRole
	 * @return
	 */
	public ChartData findFxpgByCategoryAndUserRole(@Param("category") String category, @Param("userRole") String userRole);
	
	/**
	 * Description: 根据风险评估种类与用户角色查询风险指向数据findFxzxByCategoryAndUserRole
	 * @param category
	 * @param userRole
	 * @return
	 */
	public ChartData findFxzxByCategoryAndUserRole(@Param("category") String category, @Param("userRole") String userRole);

	/**
	 * Description: 根据用户角色查询风险概况数据findFxgkByUserRole
	 * @param userRole
	 * @return
	 */
	public ChartData findFxgkByUserRole(@Param("userRole") String userRole);
}
