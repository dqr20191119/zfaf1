package com.cesgroup.prison.xxhj.mjczdj.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_DOOR_OPERATION_REGISTRATION")
public class DoorOperationRegistrationEntity extends StringIDEntity {

	private static final long serialVersionUID = 8482600293271364038L;

	private String dorCusNumber;

	private String dorDprtmntId;

	private String dorDprtmnt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dorCrteTime;

	private String dorCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dorUpdtTime;

	private String dorUpdtUserId;

	private String dorCrteUser;

	private String dorUpdtUser;

	@Transient
	private String startTime;

	@Transient
	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDorCusNumber() {
		return dorCusNumber;
	}

	public void setDorCusNumber(String dorCusNumber) {
		this.dorCusNumber = dorCusNumber;
	}

	public String getDorDprtmntId() {
		return dorDprtmntId;
	}

	public void setDorDprtmntId(String dorDprtmntId) {
		this.dorDprtmntId = dorDprtmntId;
	}

	public String getDorDprtmnt() {
		return dorDprtmnt;
	}

	public void setDorDprtmnt(String dorDprtmnt) {
		this.dorDprtmnt = dorDprtmnt;
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

	public String getDorCrteUser() {
		return dorCrteUser;
	}

	public void setDorCrteUser(String dorCrteUser) {
		this.dorCrteUser = dorCrteUser;
	}

	public String getDorUpdtUser() {
		return dorUpdtUser;
	}

	public void setDorUpdtUser(String dorUpdtUser) {
		this.dorUpdtUser = dorUpdtUser;
	}

}
