package com.cesgroup.prison.doorPlan.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_PLAN_DOOR")
public class DoorPlanEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = -1722172145505322207L;

	private String pdoCusNumber;

	private String pdoPlanName;

	private String pdoRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pdoCrteTime;

	private String pdoCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pdoUpdtTime;

	private String pdoUpdtUserId;

	private String pdoSttsIndc;

	private String pdoTimeLimit;

	private String pdoOffTime;

	public String getPdoCusNumber() {
		return pdoCusNumber;
	}

	public void setPdoCusNumber(String pdoCusNumber) {
		this.pdoCusNumber = pdoCusNumber;
	}

	public String getPdoPlanName() {
		return pdoPlanName;
	}

	public void setPdoPlanName(String pdoPlanName) {
		this.pdoPlanName = pdoPlanName;
	}

	public String getPdoRemark() {
		return pdoRemark;
	}

	public void setPdoRemark(String pdoRemark) {
		this.pdoRemark = pdoRemark;
	}

	public Date getPdoCrteTime() {
		return pdoCrteTime;
	}

	public void setPdoCrteTime(Date pdoCrteTime) {
		this.pdoCrteTime = pdoCrteTime;
	}

	public String getPdoCrteUserId() {
		return pdoCrteUserId;
	}

	public void setPdoCrteUserId(String pdoCrteUserId) {
		this.pdoCrteUserId = pdoCrteUserId;
	}

	public Date getPdoUpdtTime() {
		return pdoUpdtTime;
	}

	public void setPdoUpdtTime(Date pdoUpdtTime) {
		this.pdoUpdtTime = pdoUpdtTime;
	}

	public String getPdoUpdtUserId() {
		return pdoUpdtUserId;
	}

	public void setPdoUpdtUserId(String pdoUpdtUserId) {
		this.pdoUpdtUserId = pdoUpdtUserId;
	}

	public String getPdoSttsIndc() {
		return pdoSttsIndc;
	}

	public void setPdoSttsIndc(String pdoSttsIndc) {
		this.pdoSttsIndc = pdoSttsIndc;
	}

	public String getpdoTimeLimit() {
		return pdoTimeLimit;
	}

	public void setpdoTimeLimit(String pdoTimeLimit) {
		this.pdoTimeLimit = pdoTimeLimit;
	}

	public String getPdoOffTime() {
		return pdoOffTime;
	}

	public void setPdoOffTime(String pdoOffTime) {
		this.pdoOffTime = pdoOffTime;
	}

}
