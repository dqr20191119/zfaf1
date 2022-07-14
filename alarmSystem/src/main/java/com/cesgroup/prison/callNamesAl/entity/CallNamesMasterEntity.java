package com.cesgroup.prison.callNamesAl.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_CALL_NAMES_MASTER")
public class CallNamesMasterEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String cnmCusNumber;

	private String cnmLch;

	private String cnmDprtmntId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnmStartTime;

	private String cnmTimeLag;

	private String cnmIsEnd;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnmCrteTime;

	private String cnmCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnmUpdtTime;

	private String cnmUpdtUserId;

	/**
	* @Fields startTime : 查询使用
	*/
	@Transient
	private String startTime;

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCnmCusNumber() {
		return cnmCusNumber;
	}

	public void setCnmCusNumber(String cnmCusNumber) {
		this.cnmCusNumber = cnmCusNumber;
	}

	public String getCnmLch() {
		return cnmLch;
	}

	public void setCnmLch(String cnmLch) {
		this.cnmLch = cnmLch;
	}

	public String getCnmDprtmntId() {
		return cnmDprtmntId;
	}

	public void setCnmDprtmntId(String cnmDprtmntId) {
		this.cnmDprtmntId = cnmDprtmntId;
	}

	public Date getCnmStartTime() {
		return cnmStartTime;
	}

	public void setCnmStartTime(Date cnmStartTime) {
		this.cnmStartTime = cnmStartTime;
	}

	public String getCnmTimeLag() {
		return cnmTimeLag;
	}

	public void setCnmTimeLag(String cnmTimeLag) {
		this.cnmTimeLag = cnmTimeLag;
	}

	public String getCnmIsEnd() {
		return cnmIsEnd;
	}

	public void setCnmIsEnd(String cnmIsEnd) {
		this.cnmIsEnd = cnmIsEnd;
	}

	public Date getCnmCrteTime() {
		return cnmCrteTime;
	}

	public void setCnmCrteTime(Date cnmCrteTime) {
		this.cnmCrteTime = cnmCrteTime;
	}

	public String getCnmCrteUserId() {
		return cnmCrteUserId;
	}

	public void setCnmCrteUserId(String cnmCrteUserId) {
		this.cnmCrteUserId = cnmCrteUserId;
	}

	public Date getCnmUpdtTime() {
		return cnmUpdtTime;
	}

	public void setCnmUpdtTime(Date cnmUpdtTime) {
		this.cnmUpdtTime = cnmUpdtTime;
	}

	public String getCnmUpdtUserId() {
		return cnmUpdtUserId;
	}

	public void setCnmUpdtUserId(String cnmUpdtUserId) {
		this.cnmUpdtUserId = cnmUpdtUserId;
	}

}
