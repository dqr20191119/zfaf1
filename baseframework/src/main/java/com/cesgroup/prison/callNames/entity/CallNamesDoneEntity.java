package com.cesgroup.prison.callNames.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_CALL_NAMES_DONE_PRISONER")
public class CallNamesDoneEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -4693050419025010966L;

	private String cndRecordId;

	private String cndCusNumber;

	private String cndPrisonerIndc;

	private String cndPrisonerName;

	private String cndDprtmntId;

	private String cndDprtmnt;

	private String cndRollTime;

	private String cndRollMark;

	private String cndLch;

	private String cndLc;

	private String cndJsh;

	private String cndJs;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cndCrteTime;

	private String cndCrteUserId;

	public String getCndRecordId() {
		return cndRecordId;
	}

	public void setCndRecordId(String cndRecordId) {
		this.cndRecordId = cndRecordId;
	}

	public String getCndCusNumber() {
		return cndCusNumber;
	}

	public String getCndLch() {
		return cndLch;
	}

	public void setCndLch(String cndLch) {
		this.cndLch = cndLch;
	}

	public String getCndJsh() {
		return cndJsh;
	}

	public void setCndJsh(String cndJsh) {
		this.cndJsh = cndJsh;
	}

	public void setCndCusNumber(String cndCusNumber) {
		this.cndCusNumber = cndCusNumber;
	}

	public String getCndPrisonerIndc() {
		return cndPrisonerIndc;
	}

	public void setCndPrisonerIndc(String cndPrisonerIndc) {
		this.cndPrisonerIndc = cndPrisonerIndc;
	}

	public String getCndPrisonerName() {
		return cndPrisonerName;
	}

	public void setCndPrisonerName(String cndPrisonerName) {
		this.cndPrisonerName = cndPrisonerName;
	}

	public String getCndDprtmntId() {
		return cndDprtmntId;
	}

	public void setCndDprtmntId(String cndDprtmntId) {
		this.cndDprtmntId = cndDprtmntId;
	}

	public String getCndDprtmnt() {
		return cndDprtmnt;
	}

	public void setCndDprtmnt(String cndDprtmnt) {
		this.cndDprtmnt = cndDprtmnt;
	}

	public String getCndRollTime() {
		return cndRollTime;
	}

	public void setCndRollTime(String cndRollTime) {
		this.cndRollTime = cndRollTime;
	}

	public String getCndRollMark() {
		return cndRollMark;
	}

	public void setCndRollMark(String cndRollMark) {
		this.cndRollMark = cndRollMark;
	}

	public Date getCndCrteTime() {
		return cndCrteTime;
	}

	public void setCndCrteTime(Date cndCrteTime) {
		this.cndCrteTime = cndCrteTime;
	}

	public String getCndCrteUserId() {
		return cndCrteUserId;
	}

	public void setCndCrteUserId(String cndCrteUserId) {
		this.cndCrteUserId = cndCrteUserId;
	}

	public String getCndLc() {
		return cndLc;
	}

	public void setCndLc(String cndLc) {
		this.cndLc = cndLc;
	}

	public String getCndJs() {
		return cndJs;
	}

	public void setCndJs(String cndJs) {
		this.cndJs = cndJs;
	}

}
