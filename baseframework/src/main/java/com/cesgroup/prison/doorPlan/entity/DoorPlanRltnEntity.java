package com.cesgroup.prison.doorPlan.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_PLAN_DOOR_RLTN")
public class DoorPlanRltnEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -3861529009981653098L;

	private String dorCusNumber;

	private String dorPlanId;

	private String dorDoorId;

	private String dorDoorName;

	private String dorExecOrder;

	private String dorRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dorCrteTime;

	private String dorCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dorUpdtTime;

	private String dorUpdtUserId;

	public String getDorCusNumber() {
		return dorCusNumber;
	}

	public void setDorCusNumber(String dorCusNumber) {
		this.dorCusNumber = dorCusNumber;
	}

	public String getDorPlanId() {
		return dorPlanId;
	}

	public void setDorPlanId(String dorPlanId) {
		this.dorPlanId = dorPlanId;
	}

	public String getDorDoorId() {
		return dorDoorId;
	}

	public void setDorDoorId(String dorDoorId) {
		this.dorDoorId = dorDoorId;
	}

	public String getDorDoorName() {
		return dorDoorName;
	}

	public void setDorDoorName(String dorDoorName) {
		this.dorDoorName = dorDoorName;
	}

	public String getDorExecOrder() {
		return dorExecOrder;
	}

	public void setDorExecOrder(String dorExecOrder) {
		this.dorExecOrder = dorExecOrder;
	}

	public String getDorRemark() {
		return dorRemark;
	}

	public void setDorRemark(String dorRemark) {
		this.dorRemark = dorRemark;
	}

	public Date getDorCrteTime() {
		return dorCrteTime;
	}

	public void setDorCrteTime(Date dorCrteTime) {
		this.dorCrteTime = dorCrteTime;
	}

	public String getDorCrteUserId() {
		return dorCrteUserId;
	}

	public void setDorCrteUserId(String dorCrteUserId) {
		this.dorCrteUserId = dorCrteUserId;
	}

	public Date getDorUpdtTime() {
		return dorUpdtTime;
	}

	public void setDorUpdtTime(Date dorUpdtTime) {
		this.dorUpdtTime = dorUpdtTime;
	}

	public String getDorUpdtUserId() {
		return dorUpdtUserId;
	}

	public void setDorUpdtUserId(String dorUpdtUserId) {
		this.dorUpdtUserId = dorUpdtUserId;
	}

}
