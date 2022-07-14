package com.cesgroup.prison.httpclient.zfxx.dto;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Description: 罪犯统计信息DTO
 * 
 * @author zhou.jian
 *
 * @Data  2019年2月20日
 */
public class ZfStatDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	/**
	 * 统计总数
	 */
	private Integer Count;

	/**
	 * 查询标识
	 */
	private String ItemsId;

	/**
	 * 查询结果数量
	 */
	private Integer value;
	
	private String name;
	
	
	/**
	 * 查询结果占count的百分比
	 */
	private String percent;

	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	private String DUrlTime;

	public Integer getCount() {
		return Count;
	}

	public void setCount(Integer count) {
		Count = count;
	}

	public String getItemsId() {
		return ItemsId;
	}

	public void setItemsId(String itemsId) {
		ItemsId = itemsId;
	}

	public String getDUrlTime() {
		return DUrlTime;
	}

	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
