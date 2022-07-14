package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_disposal_method_info")
public class CzffglEntity extends StringIDEntity {
	
	private String dmiCusNumber;					// 监狱id
	private String dmiMethodName;					// 方法名称
	private String dmiMethodDesc;					// 方法描述
	private String dmiPlanType;						// 预案类别			
	private String dmiStatus;						// 状态
	
	@Column(updatable = false)
	private Date dmiCrteTime;						// 创建时间
	@Column(updatable = false)
	private String dmiCrteUserId;					// 创建人id
	private Date dmiUpdtTime;						// 更新时间
	private String dmiUpdtUserId;					// 更新人id
	
	@Transient
	private String methodRelAction;
	@Transient
	private String epcPlanFid;		
	@Transient
	private String configExist;
  	
	
	public String getDmiCusNumber() {
		return dmiCusNumber;
	}
	public void setDmiCusNumber(String dmiCusNumber) {
		this.dmiCusNumber = dmiCusNumber;
	}
	public String getDmiMethodName() {
		return dmiMethodName;
	}
	public void setDmiMethodName(String dmiMethodName) {
		this.dmiMethodName = dmiMethodName;
	}
	public String getDmiMethodDesc() {
		return dmiMethodDesc;
	}
	public void setDmiMethodDesc(String dmiMethodDesc) {
		this.dmiMethodDesc = dmiMethodDesc;
	}
	public String getDmiPlanType() {
		return dmiPlanType;
	}
	public void setDmiPlanType(String dmiPlanType) {
		this.dmiPlanType = dmiPlanType;
	}
	public String getDmiStatus() {
		return dmiStatus;
	}
	public void setDmiStatus(String dmiStatus) {
		this.dmiStatus = dmiStatus;
	}
	public Date getDmiCrteTime() {
		return dmiCrteTime;
	}
	public void setDmiCrteTime(Date dmiCrteTime) {
		this.dmiCrteTime = dmiCrteTime;
	}
	public String getDmiCrteUserId() {
		return dmiCrteUserId;
	}
	public void setDmiCrteUserId(String dmiCrteUserId) {
		this.dmiCrteUserId = dmiCrteUserId;
	}
	public Date getDmiUpdtTime() {
		return dmiUpdtTime;
	}
	public void setDmiUpdtTime(Date dmiUpdtTime) {
		this.dmiUpdtTime = dmiUpdtTime;
	}
	public String getDmiUpdtUserId() {
		return dmiUpdtUserId;
	}
	public void setDmiUpdtUserId(String dmiUpdtUserId) {
		this.dmiUpdtUserId = dmiUpdtUserId;
	}
	public String getEpcPlanFid() {
		return epcPlanFid;
	}
	public void setEpcPlanFid(String epcPlanFid) {
		this.epcPlanFid = epcPlanFid;
	}
	public String getConfigExist() {
		return configExist;
	}
	public void setConfigExist(String configExist) {
		this.configExist = configExist;
	}
	public String getMethodRelAction() {
		return methodRelAction;
	}
	public void setMethodRelAction(String methodRelAction) {
		this.methodRelAction = methodRelAction;
	}
}
