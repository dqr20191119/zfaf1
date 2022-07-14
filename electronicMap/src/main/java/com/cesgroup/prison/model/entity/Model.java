package com.cesgroup.prison.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * 模型
 * @author hurenjie
 *
 */
@Entity
@Table(name = "cds_model_info")
public class Model extends StringIDEntity{

	//监狱编号
    private String minCusNumber;

    //模型名称
    private String minModelName;

    //模型编号
    private String minModelNo;

    //区域编号
    private String minAreaId;

    private String minCrteUserId;

    private Date minCrteTime;

    private String minUpdtUserId;

    private Date minUpdtTime;

    //模型名称（来自建模）
    private String minModelFcname;

    //模型数据源（来自建模）
    private String minModelFdsname;
    
    //是否为楼顶
    private String minIsTop;
    
	public String getMinCusNumber() {
		return minCusNumber;
	}

	public void setMinCusNumber(String minCusNumber) {
		this.minCusNumber = minCusNumber;
	}

	public String getMinModelName() {
		return minModelName;
	}

	public void setMinModelName(String minModelName) {
		this.minModelName = minModelName;
	}

	public String getMinModelNo() {
		return minModelNo;
	}

	public void setMinModelNo(String minModelNo) {
		this.minModelNo = minModelNo;
	}

	public String getMinAreaId() {
		return minAreaId;
	}

	public void setMinAreaId(String minAreaId) {
		this.minAreaId = minAreaId;
	}

	public String getMinCrteUserId() {
		return minCrteUserId;
	}

	public void setMinCrteUserId(String minCrteUserId) {
		this.minCrteUserId = minCrteUserId;
	}

	public Date getMinCrteTime() {
		return minCrteTime;
	}

	public void setMinCrteTime(Date minCrteTime) {
		this.minCrteTime = minCrteTime;
	}

	public String getMinUpdtUserId() {
		return minUpdtUserId;
	}

	public void setMinUpdtUserId(String minUpdtUserId) {
		this.minUpdtUserId = minUpdtUserId;
	}

	public Date getMinUpdtTime() {
		return minUpdtTime;
	}

	public void setMinUpdtTime(Date minUpdtTime) {
		this.minUpdtTime = minUpdtTime;
	}

	public String getMinModelFcname() {
		return minModelFcname;
	}

	public void setMinModelFcname(String minModelFcname) {
		this.minModelFcname = minModelFcname;
	}

	public String getMinModelFdsname() {
		return minModelFdsname;
	}

	public void setMinModelFdsname(String minModelFdsname) {
		this.minModelFdsname = minModelFdsname;
	}

	public String getMinIsTop() {
		return minIsTop;
	}

	public void setMinIsTop(String minIsTop) {
		this.minIsTop = minIsTop;
	}
	
}