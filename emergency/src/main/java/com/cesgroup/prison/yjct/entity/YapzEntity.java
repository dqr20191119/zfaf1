package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_emergency_plan_config")
public class YapzEntity extends StringIDEntity {
	
	private String epcCusNumber;					// 监狱id
	private String epcConfigType;					// 配置类别 	1-处置方法，2-工作组
	private String epcOrderSeq;			 			// 排序
	private String epcPlanFid;						// 预案id
	private String epcConfigItemFid;				// 配置项id	处置方法id、工作组id
	
	@Column(updatable = false)
	private Date epcCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String epcCrteUserId;					// 创建人
	private Date epcUpdtTime;						// 更新时间
	private String epcUpdtUserId;					// 更新人
	
	@Transient
	private String wgiWorkgroupName;							 
	@Transient
	private String wgiWorkgroupTask;							 
	@Transient
	private String dmiMethodName;							 
	@Transient
	private String dmiMethodDesc;						 
	@Transient
	private String dmiPlanType;		
	@Transient
	private String actionExist;
	@Transient
	private String epaRelActionType;
	@Transient
	private String epaMethodFid;
	
	
	public String getEpcCusNumber() {
		return epcCusNumber;
	}
	public void setEpcCusNumber(String epcCusNumber) {
		this.epcCusNumber = epcCusNumber;
	}
	public String getEpcConfigType() {
		return epcConfigType;
	}
	public void setEpcConfigType(String epcConfigType) {
		this.epcConfigType = epcConfigType;
	}
	public String getEpcOrderSeq() {
		return epcOrderSeq;
	}
	public void setEpcOrderSeq(String epcOrderSeq) {
		this.epcOrderSeq = epcOrderSeq;
	}
	public Date getEpcCrteTime() {
		return epcCrteTime;
	}
	public void setEpcCrteTime(Date epcCrteTime) {
		this.epcCrteTime = epcCrteTime;
	}
	public String getEpcCrteUserId() {
		return epcCrteUserId;
	}
	public void setEpcCrteUserId(String epcCrteUserId) {
		this.epcCrteUserId = epcCrteUserId;
	}
	public Date getEpcUpdtTime() {
		return epcUpdtTime;
	}
	public void setEpcUpdtTime(Date epcUpdtTime) {
		this.epcUpdtTime = epcUpdtTime;
	}
	public String getEpcUpdtUserId() {
		return epcUpdtUserId;
	}
	public void setEpcUpdtUserId(String epcUpdtUserId) {
		this.epcUpdtUserId = epcUpdtUserId;
	}
	public String getEpcPlanFid() {
		return epcPlanFid;
	}
	public void setEpcPlanFid(String epcPlanFid) {
		this.epcPlanFid = epcPlanFid;
	}
	public String getEpcConfigItemFid() {
		return epcConfigItemFid;
	}
	public void setEpcConfigItemFid(String epcConfigItemFid) {
		this.epcConfigItemFid = epcConfigItemFid;
	}
	public String getWgiWorkgroupName() {
		return wgiWorkgroupName;
	}
	public void setWgiWorkgroupName(String wgiWorkgroupName) {
		this.wgiWorkgroupName = wgiWorkgroupName;
	}
	public String getWgiWorkgroupTask() {
		return wgiWorkgroupTask;
	}
	public void setWgiWorkgroupTask(String wgiWorkgroupTask) {
		this.wgiWorkgroupTask = wgiWorkgroupTask;
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
	public String getActionExist() {
		return actionExist;
	}
	public void setActionExist(String actionExist) {
		this.actionExist = actionExist;
	}
	public String getEpaRelActionType() {
		return epaRelActionType;
	}
	public void setEpaRelActionType(String epaRelActionType) {
		this.epaRelActionType = epaRelActionType;
	}
	public String getEpaMethodFid() {
		return epaMethodFid;
	}
	public void setEpaMethodFid(String epaMethodFid) {
		this.epaMethodFid = epaMethodFid;
	}
}
