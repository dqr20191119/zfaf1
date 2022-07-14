package com.cesgroup.prison.callNames.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_CALL_NAMES_RECORD")
public class CallNamesRecordEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :
	*/
	private static final long serialVersionUID = 7150009939323230443L;

	private String cnrCusNumber;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrStartTime;

	private String cnrTimeLag;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrEndTime;

	private String cnrPrisonerSum;

	private String cnrCallSum;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrCrteTime;

	private String cnrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrUpdtTime;

	private String cnrUpdtUserId;

	/**
	* @Fields cnrIsDone : 点名是否结束 0否 1 是
	*/
	private String cnrIsDone;

	public String getCnrCusNumber() {
		return cnrCusNumber;
	}

	public void setCnrCusNumber(String cnrCusNumber) {
		this.cnrCusNumber = cnrCusNumber;
	}

	public Date getCnrStartTime() {
		return cnrStartTime;
	}

	public void setCnrStartTime(Date cnrStartTime) {
		this.cnrStartTime = cnrStartTime;
	}

	public String getCnrTimeLag() {
		return cnrTimeLag;
	}

	public void setCnrTimeLag(String cnrTimeLag) {
		this.cnrTimeLag = cnrTimeLag;
	}

	public Date getCnrEndTime() {
		return cnrEndTime;
	}

	public void setCnrEndTime(Date cnrEndTime) {
		this.cnrEndTime = cnrEndTime;
	}

	public String getCnrPrisonerSum() {
		return cnrPrisonerSum;
	}

	public void setCnrPrisonerSum(String cnrPrisonerSum) {
		this.cnrPrisonerSum = cnrPrisonerSum;
	}

	public String getCnrCallSum() {
		return cnrCallSum;
	}

	public void setCnrCallSum(String cnrCallSum) {
		this.cnrCallSum = cnrCallSum;
	}

	public Date getCnrCrteTime() {
		return cnrCrteTime;
	}

	public void setCnrCrteTime(Date cnrCrteTime) {
		this.cnrCrteTime = cnrCrteTime;
	}

	public String getCnrCrteUserId() {
		return cnrCrteUserId;
	}

	public void setCnrCrteUserId(String cnrCrteUserId) {
		this.cnrCrteUserId = cnrCrteUserId;
	}

	public Date getCnrUpdtTime() {
		return cnrUpdtTime;
	}

	public String getCnrIsDone() {
		return cnrIsDone;
	}

	public void setCnrIsDone(String cnrIsDone) {
		this.cnrIsDone = cnrIsDone;
	}

	public void setCnrUpdtTime(Date cnrUpdtTime) {
		this.cnrUpdtTime = cnrUpdtTime;
	}

	public String getCnrUpdtUserId() {
		return cnrUpdtUserId;
	}

	public void setCnrUpdtUserId(String cnrUpdtUserId) {
		this.cnrUpdtUserId = cnrUpdtUserId;
	}

}
