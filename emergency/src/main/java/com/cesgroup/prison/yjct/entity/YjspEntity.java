package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_emergency_handle_approve")
public class YjspEntity extends StringIDEntity {
	
	private String ehaCusNumber;					// 监狱id
	private String ehaAppType;						// 审批类型			1-应急预案审批   2-演练方案审批.
 	private String ehaAppRole;						// 审批角色			1-监狱领导   2-省局领导.
	private String ehaPoliceId;						// 警员编号
	private String ehaPoliceName;					// 警员名称
	private String ehaIsAgree;						// 是否同意			1-同意   0-不同意
	private String ehaAgreeContent;					// 意见内容
	private String ehaPhFid;						// 关联对象id		预案id、演练id	
	
	@Column(updatable = false)
	private Date ehaCrteTime;						// 创建时间
	@Column(updatable = false)
	private String ehaCrteUserId;					// 创建人id
	private Date ehaUpdtTime;						// 更新时间
	private String ehaUpdtUserId;					// 更新人id
	
	
	public String getEhaCusNumber() {
		return ehaCusNumber;
	}
	public void setEhaCusNumber(String ehaCusNumber) {
		this.ehaCusNumber = ehaCusNumber;
	}
	public String getEhaAppType() {
		return ehaAppType;
	}
	public void setEhaAppType(String ehaAppType) {
		this.ehaAppType = ehaAppType;
	}
	public String getEhaAppRole() {
		return ehaAppRole;
	}
	public void setEhaAppRole(String ehaAppRole) {
		this.ehaAppRole = ehaAppRole;
	}
	public String getEhaPoliceId() {
		return ehaPoliceId;
	}
	public void setEhaPoliceId(String ehaPoliceId) {
		this.ehaPoliceId = ehaPoliceId;
	}
	public String getEhaPoliceName() {
		return ehaPoliceName;
	}
	public void setEhaPoliceName(String ehaPoliceName) {
		this.ehaPoliceName = ehaPoliceName;
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
	public Date getEhaCrteTime() {
		return ehaCrteTime;
	}
	public void setEhaCrteTime(Date ehaCrteTime) {
		this.ehaCrteTime = ehaCrteTime;
	}
	public String getEhaCrteUserId() {
		return ehaCrteUserId;
	}
	public void setEhaCrteUserId(String ehaCrteUserId) {
		this.ehaCrteUserId = ehaCrteUserId;
	}
	public Date getEhaUpdtTime() {
		return ehaUpdtTime;
	}
	public void setEhaUpdtTime(Date ehaUpdtTime) {
		this.ehaUpdtTime = ehaUpdtTime;
	}
	public String getEhaUpdtUserId() {
		return ehaUpdtUserId;
	}
	public void setEhaUpdtUserId(String ehaUpdtUserId) {
		this.ehaUpdtUserId = ehaUpdtUserId;
	}
	public String getEhaPhFid() {
		return ehaPhFid;
	}
	public void setEhaPhFid(String ehaPhFid) {
		this.ehaPhFid = ehaPhFid;
	} 
}
