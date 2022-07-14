package com.cesgroup.prison.alarm.plan.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
@Entity
@Table(name = "CDS_BROADCAST_PLAN_RLTN")
public class AlarmPlanBroadcastPlan extends StringIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bprCusNumber;
	private String bprPlanId;
	private String bprBroadcastPlanId;
	private String bprBroadcastPlanName;
	private String bprRemark;
	private String bprCrteTime;
	private String bprCrteUserId;
	private String bprUpdtTime;
	private String bprUpdtUserId;
	public String getBprCusNumber() {
		return bprCusNumber;
	}
	public void setBprCusNumber(String bprCusNumber) {
		this.bprCusNumber = bprCusNumber;
	}
	public String getBprPlanId() {
		return bprPlanId;
	}
	public void setBprPlanId(String bprPlanId) {
		this.bprPlanId = bprPlanId;
	}
	public String getBprBroadcastPlanId() {
		return bprBroadcastPlanId;
	}
	public void setBprBroadcastPlanId(String bprBroadcastPlanId) {
		this.bprBroadcastPlanId = bprBroadcastPlanId;
	}
	public String getBprBroadcastPlanName() {
		return bprBroadcastPlanName;
	}
	public void setBprBroadcastPlanName(String bprBroadcastPlanName) {
		this.bprBroadcastPlanName = bprBroadcastPlanName;
	}
	public String getBprRemark() {
		return bprRemark;
	}
	public void setBprRemark(String bprRemark) {
		this.bprRemark = bprRemark;
	}
	public String getBprCrteTime() {
		return bprCrteTime;
	}
	public void setBprCrteTime(String bprCrteTime) {
		this.bprCrteTime = bprCrteTime;
	}
	public String getBprCrteUserId() {
		return bprCrteUserId;
	}
	public void setBprCrteUserId(String bprCrteUserId) {
		this.bprCrteUserId = bprCrteUserId;
	}
	public String getBprUpdtTime() {
		return bprUpdtTime;
	}
	public void setBprUpdtTime(String bprUpdtTime) {
		this.bprUpdtTime = bprUpdtTime;
	}
	public String getBprUpdtUserId() {
		return bprUpdtUserId;
	}
	public void setBprUpdtUserId(String bprUpdtUserId) {
		this.bprUpdtUserId = bprUpdtUserId;
	}
	@Override
	public String toString() {
		return "AlarmPlanBroadcastPlan [bprCusNumber=" + bprCusNumber + ", bprPlanId=" + bprPlanId
				+ ", bprBroadcastPlanId=" + bprBroadcastPlanId + ", bprBroadcastPlanName=" + bprBroadcastPlanName
				+ ", bprRemark=" + bprRemark + ", bprCrteTime=" + bprCrteTime + ", bprCrteUserId=" + bprCrteUserId
				+ ", bprUpdtTime=" + bprUpdtTime + ", bprUpdtUserId=" + bprUpdtUserId + "]";
	}
	

}
