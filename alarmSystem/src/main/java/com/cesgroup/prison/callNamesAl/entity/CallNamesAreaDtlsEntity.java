package com.cesgroup.prison.callNamesAl.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_CALL_NAMES_AREA_DTLS")
public class CallNamesAreaDtlsEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String nadCusNumber;

	private String nadMasterId;

	private String nadLch;

	private String nadJsh;

	private String nadPrisonerTotal;

	private String nadPrisonerCalledTotal;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date nadCrteTime;

	private String nadCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date nadUpdtTime;

	private String nadUpdtUserId;

	public String getNadCusNumber() {
		return nadCusNumber;
	}

	public void setNadCusNumber(String nadCusNumber) {
		this.nadCusNumber = nadCusNumber;
	}

	public String getNadMasterId() {
		return nadMasterId;
	}

	public void setNadMasterId(String nadMasterId) {
		this.nadMasterId = nadMasterId;
	}

	public String getNadLch() {
		return nadLch;
	}

	public void setNadLch(String nadLch) {
		this.nadLch = nadLch;
	}

	public String getNadJsh() {
		return nadJsh;
	}

	public void setNadJsh(String nadJsh) {
		this.nadJsh = nadJsh;
	}

	public String getNadPrisonerTotal() {
		return nadPrisonerTotal;
	}

	public void setNadPrisonerTotal(String nadPrisonerTotal) {
		this.nadPrisonerTotal = nadPrisonerTotal;
	}

	public String getNadPrisonerCalledTotal() {
		return nadPrisonerCalledTotal;
	}

	public void setNadPrisonerCalledTotal(String nadPrisonerCalledTotal) {
		this.nadPrisonerCalledTotal = nadPrisonerCalledTotal;
	}

	public Date getNadCrteTime() {
		return nadCrteTime;
	}

	public void setNadCrteTime(Date nadCrteTime) {
		this.nadCrteTime = nadCrteTime;
	}

	public String getNadCrteUserId() {
		return nadCrteUserId;
	}

	public void setNadCrteUserId(String nadCrteUserId) {
		this.nadCrteUserId = nadCrteUserId;
	}

	public Date getNadUpdtTime() {
		return nadUpdtTime;
	}

	public void setNadUpdtTime(Date nadUpdtTime) {
		this.nadUpdtTime = nadUpdtTime;
	}

	public String getNadUpdtUserId() {
		return nadUpdtUserId;
	}

	public void setNadUpdtUserId(String nadUpdtUserId) {
		this.nadUpdtUserId = nadUpdtUserId;
	}

}
