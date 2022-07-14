package com.cesgroup.prison.riskassess.chart.dto;

import java.io.Serializable;

/**
 * desc: Map键值对类型数据Dto
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public class MapDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * desc: 名称
	 */
	private String name;
	/**
	 * desc: 值
	 */
	private Integer value;
	/**
	 * desc: 总分值
	 */
	private Integer score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
