package com.cesgroup.prison.riskassess.fxzx.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * Description: 风险指向-各项总分实体类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Entity
@Table(name = "TEST_AQFX_CHART_FXZX_GXZF")
public class ChartFxzxGxzf extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * DESC: 名称
	 */
	private String name;
    /**
     * DESC: 种类
     */
    private String category;
	/**
	 * DESC: 分值
	 */
    private Integer score;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}