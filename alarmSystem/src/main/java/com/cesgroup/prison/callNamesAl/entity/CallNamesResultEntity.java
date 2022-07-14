package com.cesgroup.prison.callNamesAl.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_CALL_NAMES_RESULT")
public class CallNamesResultEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	private String cnrMasterId;

	private String cnrNadId;

	private String cnrPrisonerIndc;

	private String cnrIsCalled;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrCrteTime;

	private String cnrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrUpdtTime;

	private String cnrUpdtUserId;

	private String cnrCusNumber;

	public String getCnrMasterId() {
		return cnrMasterId;
	}

	public void setCnrMasterId(String cnrMasterId) {
		this.cnrMasterId = cnrMasterId;
	}

	public String getCnrNadId() {
		return cnrNadId;
	}

	public void setCnrNadId(String cnrNadId) {
		this.cnrNadId = cnrNadId;
	}

	public String getCnrPrisonerIndc() {
		return cnrPrisonerIndc;
	}

	public void setCnrPrisonerIndc(String cnrPrisonerIndc) {
		this.cnrPrisonerIndc = cnrPrisonerIndc;
	}

	public String getCnrIsCalled() {
		return cnrIsCalled;
	}

	public void setCnrIsCalled(String cnrIsCalled) {
		this.cnrIsCalled = cnrIsCalled;
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

	public void setCnrUpdtTime(Date cnrUpdtTime) {
		this.cnrUpdtTime = cnrUpdtTime;
	}

	public String getCnrUpdtUserId() {
		return cnrUpdtUserId;
	}

	public void setCnrUpdtUserId(String cnrUpdtUserId) {
		this.cnrUpdtUserId = cnrUpdtUserId;
	}

	public String getCnrCusNumber() {
		return cnrCusNumber;
	}

	public void setCnrCusNumber(String cnrCusNumber) {
		this.cnrCusNumber = cnrCusNumber;
	}

}
