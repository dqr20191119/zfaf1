package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_emergency_handle_process")
public class YjjlgcEntity extends StringIDEntity {
	
	private String ehpCusNumber;						// 监狱id
 	private String ehpHandleProcess;					// 处置过程内容
	private String ehpHandleFid;						// 记录id
	private String ehpMethodFid;						// 处置方法id

	@Column(updatable = false)
	private Date ehpCrteTime;							// 创建时间
	@Column(updatable = false)
	private String ehpCrteUserId;						// 创建人id
	private Date ehpUpdtTime;							// 更新时间
	private String ehpUpdtUserId;						// 更新人id
	
	@Transient
	private String dmiMethodName;
	@Transient
	private String sfUpdateStatus;
	@Transient
	private int czlcNum;
	
	
	public String getEhpCusNumber() {
		return ehpCusNumber;
	}
	public void setEhpCusNumber(String ehpCusNumber) {
		this.ehpCusNumber = ehpCusNumber;
	}
	public String getEhpHandleProcess() {
		return ehpHandleProcess;
	}
	public void setEhpHandleProcess(String ehpHandleProcess) {
		this.ehpHandleProcess = ehpHandleProcess;
	}
	public String getEhpHandleFid() {
		return ehpHandleFid;
	}
	public void setEhpHandleFid(String ehpHandleFid) {
		this.ehpHandleFid = ehpHandleFid;
	}
	public String getEhpMethodFid() {
		return ehpMethodFid;
	}
	public void setEhpMethodFid(String ehpMethodFid) {
		this.ehpMethodFid = ehpMethodFid;
	}
	public Date getEhpCrteTime() {
		return ehpCrteTime;
	}
	public void setEhpCrteTime(Date ehpCrteTime) {
		this.ehpCrteTime = ehpCrteTime;
	}
	public String getEhpCrteUserId() {
		return ehpCrteUserId;
	}
	public void setEhpCrteUserId(String ehpCrteUserId) {
		this.ehpCrteUserId = ehpCrteUserId;
	}
	public Date getEhpUpdtTime() {
		return ehpUpdtTime;
	}
	public void setEhpUpdtTime(Date ehpUpdtTime) {
		this.ehpUpdtTime = ehpUpdtTime;
	}
	public String getEhpUpdtUserId() {
		return ehpUpdtUserId;
	}
	public void setEhpUpdtUserId(String ehpUpdtUserId) {
		this.ehpUpdtUserId = ehpUpdtUserId;
	}
	public String getDmiMethodName() {
		return dmiMethodName;
	}
	public void setDmiMethodName(String dmiMethodName) {
		this.dmiMethodName = dmiMethodName;
	}
	public String getSfUpdateStatus() {
		return sfUpdateStatus;
	}
	public void setSfUpdateStatus(String sfUpdateStatus) {
		this.sfUpdateStatus = sfUpdateStatus;
	}
	public int getCzlcNum() {
		return czlcNum;
	}
	public void setCzlcNum(int czlcNum) {
		this.czlcNum = czlcNum;
	}
}
