package com.cesgroup.prison.yjct.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_emergency_plan_info")
public class YabzEntity extends StringIDEntity {
	
	private String epiCusNumber;					// 监狱id
	private String epiPlanName;						// 预案名称
	private String epiPlanType;						// 预案类别
	private String epiPlanDesc;			 			// 预案描述
	private String epiPlanStatus;					// 状态	0-新建中，1-待审批，2-审批中，3-已审批，4-已发布，6-修订中
	private String epiAppFlow;						// 审批流程	1-监狱领导，2-省局领导
	
	@Column(updatable = false)
	private Date epiCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String epiCrteUserId;					// 创建人
	private Date epiUpdtTime;						// 更新时间
	private String epiUpdtUserId;					// 更新人
	
	@Transient
	private List<YapzEntity> yapzList = new ArrayList<YapzEntity>();		// 预案配置信息
	@Transient
	private List<YaczEntity> yaczList = new ArrayList<YaczEntity>();		// 预案操作信息
	@Transient
	private String ehaIsAgree;
  	
	
	public String getEpiCusNumber() {
		return epiCusNumber;
	}
	public void setEpiCusNumber(String epiCusNumber) {
		this.epiCusNumber = epiCusNumber;
	}
	public String getEpiPlanName() {
		return epiPlanName;
	}
	public void setEpiPlanName(String epiPlanName) {
		this.epiPlanName = epiPlanName;
	}
	public String getEpiPlanType() {
		return epiPlanType;
	}
	public void setEpiPlanType(String epiPlanType) {
		this.epiPlanType = epiPlanType;
	}
	public String getEpiPlanDesc() {
		return epiPlanDesc;
	}
	public void setEpiPlanDesc(String epiPlanDesc) {
		this.epiPlanDesc = epiPlanDesc;
	}
	public String getEpiPlanStatus() {
		return epiPlanStatus;
	}
	public void setEpiPlanStatus(String epiPlanStatus) {
		this.epiPlanStatus = epiPlanStatus;
	}
	public String getEpiAppFlow() {
		return epiAppFlow;
	}
	public void setEpiAppFlow(String epiAppFlow) {
		this.epiAppFlow = epiAppFlow;
	}
	public Date getEpiCrteTime() {
		return epiCrteTime;
	}
	public void setEpiCrteTime(Date epiCrteTime) {
		this.epiCrteTime = epiCrteTime;
	}
	public String getEpiCrteUserId() {
		return epiCrteUserId;
	}
	public void setEpiCrteUserId(String epiCrteUserId) {
		this.epiCrteUserId = epiCrteUserId;
	}
	public Date getEpiUpdtTime() {
		return epiUpdtTime;
	}
	public void setEpiUpdtTime(Date epiUpdtTime) {
		this.epiUpdtTime = epiUpdtTime;
	}
	public String getEpiUpdtUserId() {
		return epiUpdtUserId;
	}
	public void setEpiUpdtUserId(String epiUpdtUserId) {
		this.epiUpdtUserId = epiUpdtUserId;
	}
	public List<YapzEntity> getYapzList() {
		return yapzList;
	}
	public void setYapzList(List<YapzEntity> yapzList) {
		this.yapzList = yapzList;
	}
	public List<YaczEntity> getYaczList() {
		return yaczList;
	}
	public void setYaczList(List<YaczEntity> yaczList) {
		this.yaczList = yaczList;
	}
	public String getEhaIsAgree() {
		return ehaIsAgree;
	}
	public void setEhaIsAgree(String ehaIsAgree) {
		this.ehaIsAgree = ehaIsAgree;
	}
}
