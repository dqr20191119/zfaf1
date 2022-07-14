package com.cesgroup.prison.riskassess.fxpg.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * Description: 风险评估总分实体类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Entity
@Table(name = "TEST_AQFX_CHART_FXPG_ZF")
public class ChartFxpgZf extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * DESC: 种类
     */
    private String category;
    /**
     * DESC: 分值
     */
    private Integer score;
    
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