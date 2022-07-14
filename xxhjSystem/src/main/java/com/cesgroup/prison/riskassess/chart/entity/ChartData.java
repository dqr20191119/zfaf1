package com.cesgroup.prison.riskassess.chart.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * Description: 图图表展示数据实体类
 * 
 * @author lincoln.cheng
 *
 * 2018年11月12日
 */
public class ChartData extends StringIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6128208655319015533L;
	/**
	 * Desc: 显示名称
	 */
	private String names;
	/**
	 * Desc: 显示数量
	 */
	private String counts;
	
	
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "names = " + this.names + " "
				+ "counts = " + this.counts
				+ "}";
	}
}