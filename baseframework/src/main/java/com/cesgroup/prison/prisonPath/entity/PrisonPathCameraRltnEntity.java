package com.cesgroup.prison.prisonPath.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_PRISON_PATH_CAMERA_RLTN")
public class PrisonPathCameraRltnEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = -2179635106137755306L;

	private String pcrCusNumber;

	private String pcrCameraId;

	private String pcrCamera;

	private String pcrPathId;

	private String pcrRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pcrCrteTime;

	private String pcrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pcrUpdtTime;

	private String pcrUpdtUserId;

	public String getPcrCusNumber() {
		return pcrCusNumber;
	}

	public void setPcrCusNumber(String pcrCusNumber) {
		this.pcrCusNumber = pcrCusNumber;
	}

	public String getPcrCameraId() {
		return pcrCameraId;
	}

	public void setPcrCameraId(String pcrCameraId) {
		this.pcrCameraId = pcrCameraId;
	}

	public String getPcrCamera() {
		return pcrCamera;
	}

	public void setPcrCamera(String pcrCamera) {
		this.pcrCamera = pcrCamera;
	}

	public String getPcrPathId() {
		return pcrPathId;
	}

	public void setPcrPathId(String pcrPathId) {
		this.pcrPathId = pcrPathId;
	}

	public String getPcrRemark() {
		return pcrRemark;
	}

	public void setPcrRemark(String pcrRemark) {
		this.pcrRemark = pcrRemark;
	}

	public Date getPcrCrteTime() {
		return pcrCrteTime;
	}

	public void setPcrCrteTime(Date pcrCrteTime) {
		this.pcrCrteTime = pcrCrteTime;
	}

	public String getPcrCrteUserId() {
		return pcrCrteUserId;
	}

	public void setPcrCrteUserId(String pcrCrteUserId) {
		this.pcrCrteUserId = pcrCrteUserId;
	}

	public Date getPcrUpdtTime() {
		return pcrUpdtTime;
	}

	public void setPcrUpdtTime(Date pcrUpdtTime) {
		this.pcrUpdtTime = pcrUpdtTime;
	}

	public String getPcrUpdtUserId() {
		return pcrUpdtUserId;
	}

	public void setPcrUpdtUserId(String pcrUpdtUserId) {
		this.pcrUpdtUserId = pcrUpdtUserId;
	}

}
