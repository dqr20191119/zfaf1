package com.cesgroup.prison.riskassess.fxpg.dao;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.riskassess.fxpg.entity.ChartFxpgZf;

/**
 * Description: 风险评估-总分数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ChartFxpgZfDao extends BaseDao<ChartFxpgZf, String> {
	/**
	 * Description: 根据用户角色查询风险分布数据findFxfbByUserRole
	 * @param userRole
	 * @return
	 */
	public ChartFxpgZf findByCategory(@Param("category") String category);
}
