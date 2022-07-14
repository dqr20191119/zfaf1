package com.cesgroup.prison.ysjg.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_YSJG_YSJG")
public class YsjgEntity extends StringIDEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jyId;
	
	private String jqId;
	 
	private String hqTime;
	
	//获取类型，3m为3个月，6m为6个月，1y为一年
	private String hqType;
	
	//1民警，2罪犯，本次获取只是罪犯的
	private String type;

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

	public String getHqTime() {
		return hqTime;
	}

	public void setHqTime(String hqTime) {
		this.hqTime = hqTime;
	}

	public String getHqType() {
		return hqType;
	}

	public void setHqType(String hqType) {
		this.hqType = hqType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
