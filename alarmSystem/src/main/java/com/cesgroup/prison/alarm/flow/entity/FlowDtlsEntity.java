package com.cesgroup.prison.alarm.flow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "CDS_HANDLE_FLOW_DTLS")
public class FlowDtlsEntity extends StringIDEntity {

	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	// 监狱编号
	@Column(name = "HFD_CUS_NUMBER")
	private String hfdCusNumber;

	// MasterId
	@Column(name = "HFD_FLOW_ID")
	private String hfdFlowId;

	// 流程名称
	@Column(name = "HFD_FLOW_ITEM_NAME")
	private String hfdFlowItemName;

	@Column(name = "HFD_CRTE_TIME")
	private Date hfdCrteTime;

	@Column(name = "HFD_CRTE_USER_ID")
	private String hfdCrteUserId;

	@Column(name = "HFD_UPDT_TIME")
	private Date hfdUpdtTime;

	@Column(name = "HFD_UPDT_USER_ID")
	private String hfdUpdtUserId;

	public String getHfdCusNumber() {
		return hfdCusNumber;
	}

	public void setHfdCusNumber(String hfdCusNumber) {
		this.hfdCusNumber = hfdCusNumber == null ? null : hfdCusNumber.trim();
	}

	public String getHfdFlowId() {
		return hfdFlowId;
	}

	public void setHfdFlowId(String hfdFlowId) {
		this.hfdFlowId = hfdFlowId;
	}

	public String getHfdFlowItemName() {
		return hfdFlowItemName;
	}

	public void setHfdFlowItemName(String hfdFlowItemName) {
		this.hfdFlowItemName = hfdFlowItemName == null ? null : hfdFlowItemName.trim();
	}

	public Date getHfdCrteTime() {
		return hfdCrteTime;
	}

	public void setHfdCrteTime(Date hfdCrteTime) {
		this.hfdCrteTime = hfdCrteTime;
	}

	public String getHfdCrteUserId() {
		return hfdCrteUserId;
	}

	public void setHfdCrteUserId(String hfdCrteUserId) {
		this.hfdCrteUserId = hfdCrteUserId == null ? null : hfdCrteUserId.trim();
	}

	public Date getHfdUpdtTime() {
		return hfdUpdtTime;
	}

	public void setHfdUpdtTime(Date hfdUpdtTime) {
		this.hfdUpdtTime = hfdUpdtTime;
	}

	public String getHfdUpdtUserId() {
		return hfdUpdtUserId;
	}

	public void setHfdUpdtUserId(String hfdUpdtUserId) {
		this.hfdUpdtUserId = hfdUpdtUserId == null ? null : hfdUpdtUserId.trim();
	}
}