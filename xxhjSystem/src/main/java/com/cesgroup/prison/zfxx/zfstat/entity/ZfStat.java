package com.cesgroup.prison.zfxx.zfstat.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 统计表实体类
 * @author zhou.jian
 *
 * 2019年2月20日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_STAT")
public class ZfStat extends StringIDEntity{

	private static final long serialVersionUID = 1L;
	
	private String jyId;
	
	private String jqId;
	
	/**
	 * 统计总数
	 */
	private Integer count;

	/**
	 * 查询标识
	 */
	private String itemsId;
	
	/**
	 * 统计项名称
	 */
	private String itemsName;

	/**
	 * 查询结果数量
	 */
	private Integer itemsValue;
	
	/**
	 * 查询结果占count的百分比
	 */
	private String itemsPercent;
	
	/**
	 * 统计项类型
	 */
	private String type;
	
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date DUrlTime;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public Integer getItemsValue() {
		return itemsValue;
	}

	public void setItemsValue(Integer itemsValue) {
		this.itemsValue = itemsValue;
	}

	public String getItemsPercent() {
		return itemsPercent;
	}

	public void setItemsPercent(String itemsPercent) {
		this.itemsPercent = itemsPercent;
	}

	public Date getDUrlTime() {
		return DUrlTime;
	}

	public void setDUrlTime(Date dUrlTime) {
		DUrlTime = dUrlTime;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJyId() {
		return jyId;
	}

	public void setJyId(String jyId) {
		this.jyId = jyId;
	}

	public String getJqId() {
		return jqId;
	}

	public void setJqId(String jqId) {
		this.jqId = jqId;
	}

	
	
}
