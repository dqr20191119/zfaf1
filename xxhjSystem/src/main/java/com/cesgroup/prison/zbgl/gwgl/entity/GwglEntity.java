package com.cesgroup.prison.zbgl.gwgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "cds_duty_job")
public class GwglEntity extends StringIDEntity {


	private String cdjCusNumber;					// 监狱id
	private String cdjJobName;					    // 岗位名称
	private String cdjCategoryId;					// 类别id
	private String cdjJobCode;                      // 岗位编码
	private String cdjRemark;						// 备注
	private String cdjStatus;                       // 状态0 无效 1 有效
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date   cdjUpdtTime;						// 更新时间	
	
	private String cdjUpdtUserId;					// 更新id
	private String cdjUpdtUser;					    // 更新人姓名

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date cdjCrteTime;						// 创建时间
	
	@Column(updatable = false)						
	private String cdjCrteUserId;					// 创建人
	@Column(updatable = false)				
	private String cdjCrteUser;					    // 创建人姓名
	
	@Transient
	private String dcaCategoryName;

	public String getCdjCusNumber() {
		return cdjCusNumber;
	}

	public void setCdjCusNumber(String cdjCusNumber) {
		this.cdjCusNumber = cdjCusNumber;
	}

	public String getCdjJobName() {
		return cdjJobName;
	}

	public void setCdjJobName(String cdjJobName) {
		this.cdjJobName = cdjJobName;
	}

	public String getCdjCategoryId() {
		return cdjCategoryId;
	}

	public void setCdjCategoryId(String cdjCategoryId) {
		this.cdjCategoryId = cdjCategoryId;
	}

	public String getCdjRemark() {
		return cdjRemark;
	}

	public void setCdjRemark(String cdjRemark) {
		this.cdjRemark = cdjRemark;
	}

	public String getCdjStatus() {
		return cdjStatus;
	}

	public void setCdjStatus(String cdjStatus) {
		this.cdjStatus = cdjStatus;
	}

	public Date getCdjUpdtTime() {
		return cdjUpdtTime;
	}

	public void setCdjUpdtTime(Date cdjUpdtTime) {
		this.cdjUpdtTime = cdjUpdtTime;
	}

	public String getCdjUpdtUserId() {
		return cdjUpdtUserId;
	}

	public void setCdjUpdtUserId(String cdjUpdtUserId) {
		this.cdjUpdtUserId = cdjUpdtUserId;
	}

	public Date getCdjCrteTime() {
		return cdjCrteTime;
	}

	public void setCdjCrteTime(Date cdjCrteTime) {
		this.cdjCrteTime = cdjCrteTime;
	}

	public String getCdjCrteUserId() {
		return cdjCrteUserId;
	}

	public void setCdjCrteUserId(String cdjCrteUserId) {
		this.cdjCrteUserId = cdjCrteUserId;
	}

	public String getDcaCategoryName() {
		return dcaCategoryName;
	}

	public void setDcaCategoryName(String dcaCategoryName) {
		this.dcaCategoryName = dcaCategoryName;
	}

	public String getCdjJobCode() {
		return cdjJobCode;
	}

	public void setCdjJobCode(String cdjJobCode) {
		this.cdjJobCode = cdjJobCode;
	}
	
	public String getCdjCrteUser() {
		return cdjCrteUser;
	}

	public void setCdjCrteUser(String cdjCrteUser) {
		this.cdjCrteUser = cdjCrteUser;
	}

	public String getCdjUpdtUser() {
		return cdjUpdtUser;
	}

	public void setCdjUpdtUser(String cdjUpdtUser) {
		this.cdjUpdtUser = cdjUpdtUser;
	}
}
