package com.cesgroup.prison.screen.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_SCREEN_AREA_CAMERA_RLTN")
public class ScreenAreaCameraEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 9135428991022353115L;

	private String scrCusNumber;

	private String scrScreenPlanId;

	private String scrScreenAreaId;

	private String scrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date scrCrteTime;

	private String scrUpdtUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date scrUpdtTime;

	private String scrSeqNum;

	private String scrCameraId;

	private String scrCameraName;

	public String getScrCusNumber() {
		return scrCusNumber;
	}

	public void setScrCusNumber(String scrCusNumber) {
		this.scrCusNumber = scrCusNumber;
	}

	public String getScrScreenPlanId() {
		return scrScreenPlanId;
	}

	public void setScrScreenPlanId(String scrScreenPlanId) {
		this.scrScreenPlanId = scrScreenPlanId;
	}

	public String getScrScreenAreaId() {
		return scrScreenAreaId;
	}

	public void setScrScreenAreaId(String scrScreenAreaId) {
		this.scrScreenAreaId = scrScreenAreaId;
	}

	public String getScrCrteUserId() {
		return scrCrteUserId;
	}

	public void setScrCrteUserId(String scrCrteUserId) {
		this.scrCrteUserId = scrCrteUserId;
	}

	public Date getScrCrteTime() {
		return scrCrteTime;
	}

	public void setScrCrteTime(Date scrCrteTime) {
		this.scrCrteTime = scrCrteTime;
	}

	public String getScrUpdtUserId() {
		return scrUpdtUserId;
	}

	public void setScrUpdtUserId(String scrUpdtUserId) {
		this.scrUpdtUserId = scrUpdtUserId;
	}

	public Date getScrUpdtTime() {
		return scrUpdtTime;
	}

	public void setScrUpdtTime(Date scrUpdtTime) {
		this.scrUpdtTime = scrUpdtTime;
	}

	public String getScrSeqNum() {
		return scrSeqNum;
	}

	public void setScrSeqNum(String scrSeqNum) {
		this.scrSeqNum = scrSeqNum;
	}

	public String getScrCameraId() {
		return scrCameraId;
	}

	public void setScrCameraId(String scrCameraId) {
		this.scrCameraId = scrCameraId;
	}

	public String getScrCameraName() {
		return scrCameraName;
	}

	public void setScrCameraName(String scrCameraName) {
		this.scrCameraName = scrCameraName;
	}

}
