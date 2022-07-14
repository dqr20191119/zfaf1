package com.cesgroup.prison.prisonPath.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_PRISON_PATH_INFO")
public class PrisonPathEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -8611973424019486273L;

	private String ppiCusNumber;

	private String ppiDprtmntId;

	private String ppiDprtmnt;

	private String ppiPathName;

	private String ppiStartAreaId;

	private String ppiStartArea;

	private String ppiEndAreaId;

	private String ppiEndArea;

	private String ppiActionIndc;

	private String ppiRemark;

	private String ppiPathType;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ppiCrteTime;

	private String ppiCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ppiUpdtTime;

	private String ppiUpdtUserId;

	public String getPpiCusNumber() {
		return ppiCusNumber;
	}

	public void setPpiCusNumber(String ppiCusNumber) {
		this.ppiCusNumber = ppiCusNumber;
	}

	public String getPpiDprtmntId() {
		return ppiDprtmntId;
	}

	public void setPpiDprtmntId(String ppiDprtmntId) {
		this.ppiDprtmntId = ppiDprtmntId;
	}

	public String getPpiDprtmnt() {
		return ppiDprtmnt;
	}

	public void setPpiDprtmnt(String ppiDprtmnt) {
		this.ppiDprtmnt = ppiDprtmnt;
	}

	public String getPpiPathName() {
		return ppiPathName;
	}

	public void setPpiPathName(String ppiPathName) {
		this.ppiPathName = ppiPathName;
	}

	public String getPpiStartAreaId() {
		return ppiStartAreaId;
	}

	public void setPpiStartAreaId(String ppiStartAreaId) {
		this.ppiStartAreaId = ppiStartAreaId;
	}

	public String getPpiStartArea() {
		return ppiStartArea;
	}

	public void setPpiStartArea(String ppiStartArea) {
		this.ppiStartArea = ppiStartArea;
	}

	public String getPpiEndAreaId() {
		return ppiEndAreaId;
	}

	public void setPpiEndAreaId(String ppiEndAreaId) {
		this.ppiEndAreaId = ppiEndAreaId;
	}

	public String getPpiEndArea() {
		return ppiEndArea;
	}

	public void setPpiEndArea(String ppiEndArea) {
		this.ppiEndArea = ppiEndArea;
	}

	public String getPpiActionIndc() {
		return ppiActionIndc;
	}

	public void setPpiActionIndc(String ppiActionIndc) {
		this.ppiActionIndc = ppiActionIndc;
	}

	public String getPpiRemark() {
		return ppiRemark;
	}

	public void setPpiRemark(String ppiRemark) {
		this.ppiRemark = ppiRemark;
	}

	public Date getPpiCrteTime() {
		return ppiCrteTime;
	}

	public void setPpiCrteTime(Date ppiCrteTime) {
		this.ppiCrteTime = ppiCrteTime;
	}

	public String getPpiCrteUserId() {
		return ppiCrteUserId;
	}

	public void setPpiCrteUserId(String ppiCrteUserId) {
		this.ppiCrteUserId = ppiCrteUserId;
	}

	public Date getPpiUpdtTime() {
		return ppiUpdtTime;
	}

	public void setPpiUpdtTime(Date ppiUpdtTime) {
		this.ppiUpdtTime = ppiUpdtTime;
	}

	public String getPpiUpdtUserId() {
		return ppiUpdtUserId;
	}

	public void setPpiUpdtUserId(String ppiUpdtUserId) {
		this.ppiUpdtUserId = ppiUpdtUserId;
	}

	public String getPpiPathType() {
		return ppiPathType;
	}

	public void setPpiPathType(String ppiPathType) {
		this.ppiPathType = ppiPathType;
	}

}
