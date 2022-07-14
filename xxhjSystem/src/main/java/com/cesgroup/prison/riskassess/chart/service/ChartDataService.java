package com.cesgroup.prison.riskassess.chart.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.riskassess.chart.dto.MapDto;
import com.cesgroup.prison.riskassess.chart.entity.ChartData;

/**
 * Description: 图表展示业务访问接口
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public interface ChartDataService extends IBaseCRUDService<ChartData, String> {
	/**
	 * Description: 根据用户角色，查询该角色用户可看到的风险分布数据
	 * @param userRole
	 * @return
	 */
	public List<MapDto> queryFxfbByUserRole(String userRole);
	/**
	 * Description: 根据用户角色，查询该角色用户可看到的风险趋势数据
	 * @param userRole
	 * @return
	 */
	public List<MapDto> queryFxqsByUserRole(String userRole);
	/**
	 * Description: 根据风险评估种类与用户角色，查询该角色用户可看到的风险趋势数据
	 * @param category
	 * @param userRole
	 * @return
	 */
	public Map<String, Object> queryFxpgByCategoryAndUserRole(String category, String userRole);
	/**
	 * Description: 根据风险评估种类与用户角色，查询该角色用户可看到的风险指向数据
	 * @param category
	 * @param userRole
	 * @return
	 */
	public List<MapDto> queryFxzxByCategoryAndUserRole(String category, String userRole);
	/**
	 * Description: 根据用户角色，查询该角色用户可看到的风险概况数据
	 * @param userRole
	 * @return
	 */
	public List<MapDto> queryFxgkByUserRole(String userRole);
}
