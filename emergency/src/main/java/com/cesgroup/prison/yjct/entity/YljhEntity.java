package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "cds_emergency_drill_plan")
public class YljhEntity extends StringIDEntity {
	
	private String edpCusNumber;					// 监狱id
	private String edpTitle;						// 演练标题
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date edpTime;							// 演练时间
	private String edpAddress;						// 演练地点
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date edpReleaseTime;					// 演练结束时间
	private String edpStatus;						// 状态	1-待审批，3-已审批，4-已发布
	private String edpEmPlanFid;					// 演练预案id
	
	@Column(updatable = false)
	private Date edpCrteTime;						// 创建时间
	@Column(updatable = false)
	private String edpCrteUserId;					// 创建人id
	private Date edpUpdtTime;						// 更新时间
	private String edpUpdtUserId;					// 更新人id
	
	@Transient
	private String epiPlanName;
	@Transient
	private String isHaveSp;
	@Transient
	private String ehaIsAgree;
	@Transient
	private String ehaAgreeContent;	
	
	
	public String getEdpTitle() {
		return edpTitle;
	}
	public void setEdpTitle(String edpTitle) {
		this.edpTitle = edpTitle;
	}
	public String getEdpCusNumber() {
		return edpCusNumber;
	}
	public void setEdpCusNumber(String edpCusNumber) {
		this.edpCusNumber = edpCusNumber;
	}
	public Date getEdpTime() {
		return edpTime;
	}
	public void setEdpTime(Date edpTime) {
		this.edpTime = edpTime;
	}
	public String getEdpAddress() {
		return edpAddress;
	}
	public void setEdpAddress(String edpAddress) {
		this.edpAddress = edpAddress;
	}
	public Date getEdpReleaseTime() {
		return edpReleaseTime;
	}
	public void setEdpReleaseTime(Date edpReleaseTime) {
		this.edpReleaseTime = edpReleaseTime;
	}
	public String getEdpStatus() {
		return edpStatus;
	}
	public void setEdpStatus(String edpStatus) {
		this.edpStatus = edpStatus;
	}
	public Date getEdpCrteTime() {
		return edpCrteTime;
	}
	public void setEdpCrteTime(Date edpCrteTime) {
		this.edpCrteTime = edpCrteTime;
	}
	public String getEdpCrteUserId() {
		return edpCrteUserId;
	}
	public void setEdpCrteUserId(String edpCrteUserId) {
		this.edpCrteUserId = edpCrteUserId;
	}
	public Date getEdpUpdtTime() {
		return edpUpdtTime;
	}
	public void setEdpUpdtTime(Date edpUpdtTime) {
		this.edpUpdtTime = edpUpdtTime;
	}
	public String getEdpUpdtUserId() {
		return edpUpdtUserId;
	}
	public void setEdpUpdtUserId(String edpUpdtUserId) {
		this.edpUpdtUserId = edpUpdtUserId;
	}
	public String getEdpEmPlanFid() {
		return edpEmPlanFid;
	}
	public void setEdpEmPlanFid(String edpEmPlanFid) {
		this.edpEmPlanFid = edpEmPlanFid;
	}
	public String getEpiPlanName() {
		return epiPlanName;
	}
	public void setEpiPlanName(String epiPlanName) {
		this.epiPlanName = epiPlanName;
	}
	public String getIsHaveSp() {
		return isHaveSp;
	}
	public void setIsHaveSp(String isHaveSp) {
		this.isHaveSp = isHaveSp;
	}
	public String getEhaIsAgree() {
		return ehaIsAgree;
	}
	public void setEhaIsAgree(String ehaIsAgree) {
		this.ehaIsAgree = ehaIsAgree;
	}
	public String getEhaAgreeContent() {
		return ehaAgreeContent;
	}
	public void setEhaAgreeContent(String ehaAgreeContent) {
		this.ehaAgreeContent = ehaAgreeContent;
	}
}
