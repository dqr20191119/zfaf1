package com.cesgroup.prison.deviceMaintain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：FaultDepmtReltEntity   
* @Description：   故障关联部门
* @author：Tao.xu   
* @date：2017年12月18日 下午12:39:20   
* @version        
*/
@Entity
@Table(name = "CDS_FAULT_DEPARTMENT_RELATION")
public class FaultDepmtReltEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = -7459602535089159269L;

	private String fdrCusNumber;

	private String fdrFaultId;

	private String fdrMaintainDprtmntId;

	private String fdrMaintainDprtmnt;

	private String fdrHelpDprtmnt;

	private String fdrHelpDprtmntId;

	private String fdrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fdrCrteTime;

	private String fdrUpdtUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fdrUpdtTime;

	public String getFdrCusNumber() {
		return fdrCusNumber;
	}

	public void setFdrCusNumber(String fdrCusNumber) {
		this.fdrCusNumber = fdrCusNumber;
	}

	public String getFdrFaultId() {
		return fdrFaultId;
	}

	public void setFdrFaultId(String fdrFaultId) {
		this.fdrFaultId = fdrFaultId;
	}

	public String getFdrMaintainDprtmntId() {
		return fdrMaintainDprtmntId;
	}

	public void setFdrMaintainDprtmntId(String fdrMaintainDprtmntId) {
		this.fdrMaintainDprtmntId = fdrMaintainDprtmntId;
	}

	public String getFdrMaintainDprtmnt() {
		return fdrMaintainDprtmnt;
	}

	public void setFdrMaintainDprtmnt(String fdrMaintainDprtmnt) {
		this.fdrMaintainDprtmnt = fdrMaintainDprtmnt;
	}

	public String getFdrHelpDprtmnt() {
		return fdrHelpDprtmnt;
	}

	public void setFdrHelpDprtmnt(String fdrHelpDprtmnt) {
		this.fdrHelpDprtmnt = fdrHelpDprtmnt;
	}

	public String getFdrHelpDprtmntId() {
		return fdrHelpDprtmntId;
	}

	public void setFdrHelpDprtmntId(String fdrHelpDprtmntId) {
		this.fdrHelpDprtmntId = fdrHelpDprtmntId;
	}

	public String getFdrCrteUserId() {
		return fdrCrteUserId;
	}

	public void setFdrCrteUserId(String fdrCrteUserId) {
		this.fdrCrteUserId = fdrCrteUserId;
	}

	public Date getFdrCrteTime() {
		return fdrCrteTime;
	}

	public void setFdrCrteTime(Date fdrCrteTime) {
		this.fdrCrteTime = fdrCrteTime;
	}

	public String getFdrUpdtUserId() {
		return fdrUpdtUserId;
	}

	public void setFdrUpdtUserId(String fdrUpdtUserId) {
		this.fdrUpdtUserId = fdrUpdtUserId;
	}

	public Date getFdrUpdtTime() {
		return fdrUpdtTime;
	}

	public void setFdrUpdtTime(Date fdrUpdtTime) {
		this.fdrUpdtTime = fdrUpdtTime;
	}

	 
}
