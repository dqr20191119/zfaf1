package com.cesgroup.prison.zbgl.bcgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_duty_order")
public class BcglEntity extends StringIDEntity {

	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = -5319092071869191100L;
	private String dorCusNumber; // 监狱id
	private String dorDutyOrderName; // 岗位班次名称
	private String dorStartTime; // 开始时间
	private String dorEndTime; // 结束名称
	private String dorStatus;
	private Date dorUpdtTime; // 更新时间
	private String dorUpdtUserId; // 更新id
	private String dorStatisticsType;

	@Column(updatable = false)
	private Date dorCrteTime; // 创建时间
	@Column(updatable = false)
	private String dorCrteUserId; // 创建人

	public String getDorCusNumber() {
		return dorCusNumber;
	}

	public void setDorCusNumber(String dorCusNumber) {
		this.dorCusNumber = dorCusNumber;
	}

	public String getDorDutyOrderName() {
		return dorDutyOrderName;
	}

	public void setDorDutyOrderName(String dorDutyOrderName) {
		this.dorDutyOrderName = dorDutyOrderName;
	}

	public String getDorStartTime() {
		return dorStartTime;
	}

	public void setDorStartTime(String dorStartTime) {
		this.dorStartTime = dorStartTime;
	}

	public String getDorEndTime() {
		return dorEndTime;
	}

	public void setDorEndTime(String dorEndTime) {
		this.dorEndTime = dorEndTime;
	}

	public String getDorStatus() {
		return dorStatus;
	}

	public void setDorStatus(String dorStatus) {
		this.dorStatus = dorStatus;
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

	public String getDorStatisticsType() {
		return dorStatisticsType;
	}

	public void setDorStatisticsType(String dorStatisticsType) {
		this.dorStatisticsType = dorStatisticsType;
	}

}
