package com.cesgroup.prison.callNames.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_CALL_NAMES_UNDONE_PRISONER")
public class CallNamesUndoneEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -8814964580984907238L;

	private String cnuRecordId;

	private String cnuCusNumber;

	private String cnuPrisonerIndc;

	private String cnuPrisonerName;

	private String cnuDprtmntId;

	private String cnuDprtmnt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnuCrteTime;

	private String cnuCrteUserId;

	private String cnuLch;

	private String cnuJsh;

	private String cnuLc;

	private String cnuJs;

	public String getCnuLc() {
		return cnuLc;
	}

	public void setCnuLc(String cnuLc) {
		this.cnuLc = cnuLc;
	}

	public String getCnuJs() {
		return cnuJs;
	}

	public void setCnuJs(String cnuJs) {
		this.cnuJs = cnuJs;
	}

	public String getCnuRecordId() {
		return cnuRecordId;
	}

	public void setCnuRecordId(String cnuRecordId) {
		this.cnuRecordId = cnuRecordId;
	}

	public String getCnuCusNumber() {
		return cnuCusNumber;
	}

	public String getCnuLch() {
		return cnuLch;
	}

	public void setCnuLch(String cnuLch) {
		this.cnuLch = cnuLch;
	}

	public String getCnuJsh() {
		return cnuJsh;
	}

	public void setCnuJsh(String cnuJsh) {
		this.cnuJsh = cnuJsh;
	}

	public void setCnuCusNumber(String cnuCusNumber) {
		this.cnuCusNumber = cnuCusNumber;
	}

	public String getCnuPrisonerIndc() {
		return cnuPrisonerIndc;
	}

	public void setCnuPrisonerIndc(String cnuPrisonerIndc) {
		this.cnuPrisonerIndc = cnuPrisonerIndc;
	}

	public String getCnuPrisonerName() {
		return cnuPrisonerName;
	}

	public void setCnuPrisonerName(String cnuPrisonerName) {
		this.cnuPrisonerName = cnuPrisonerName;
	}

	public String getCnuDprtmntId() {
		return cnuDprtmntId;
	}

	public void setCnuDprtmntId(String cnuDprtmntId) {
		this.cnuDprtmntId = cnuDprtmntId;
	}

	public String getCnuDprtmnt() {
		return cnuDprtmnt;
	}

	public void setCnuDprtmnt(String cnuDprtmnt) {
		this.cnuDprtmnt = cnuDprtmnt;
	}

	public Date getCnuCrteTime() {
		return cnuCrteTime;
	}

	public void setCnuCrteTime(Date cnuCrteTime) {
		this.cnuCrteTime = cnuCrteTime;
	}

	public String getCnuCrteUserId() {
		return cnuCrteUserId;
	}

	public void setCnuCrteUserId(String cnuCrteUserId) {
		this.cnuCrteUserId = cnuCrteUserId;
	}

}
