package com.cesgroup.prison.riskassess.fxzx.dao;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.riskassess.fxzx.entity.ChartFxzxGxzf;

/**
 * Description: 风险指向-各项总分数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ChartFxzxGxzfDao extends BaseDao<ChartFxzxGxzf, String> {
	/**
	 * Description: 根据种类查询风险分布数据findByCategory
	 * @param userRole
	 * @return
	 */
	public ChartFxzxGxzf findByNameAndCategory(@Param("name") String name, @Param("category") String category);
}
