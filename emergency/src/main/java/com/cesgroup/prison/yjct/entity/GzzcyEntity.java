package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_workgroup_member")
public class GzzcyEntity extends StringIDEntity {
	
	private String wgmCusNumber;				// 监狱id	
    private String wgmUserRole;					// 成员角色
    private String wgmPoliceId;					// 警员id
    private String wgmPoliceName;				// 警员名称
    private String wgmRoleTask;					// 角色任务
    private String wgmWorkgroupFid;				// 所属工作组id
    
    @Column(updatable = false)
    private Date wgmCrteTime;					// 创建时间
    @Column(updatable = false)
    private String wgmCrteUserId;				// 创建人id
    private Date wgmUpdtTime;					// 更新时间
    private String wgmUpdtUserId;				// 更新人id
    
    @Transient
    private String pbdPhone;					// 警员手机号码
    
    
	public String getWgmCusNumber() {
		return wgmCusNumber;
	}
	public void setWgmCusNumber(String wgmCusNumber) {
		this.wgmCusNumber = wgmCusNumber;
	}
	public String getWgmUserRole() {
		return wgmUserRole;
	}
	public void setWgmUserRole(String wgmUserRole) {
		this.wgmUserRole = wgmUserRole;
	}
	public String getWgmPoliceId() {
		return wgmPoliceId;
	}
	public void setWgmPoliceId(String wgmPoliceId) {
		this.wgmPoliceId = wgmPoliceId;
	}
	public String getWgmPoliceName() {
		return wgmPoliceName;
	}
	public void setWgmPoliceName(String wgmPoliceName) {
		this.wgmPoliceName = wgmPoliceName;
	}
	public String getWgmRoleTask() {
		return wgmRoleTask;
	}
	public void setWgmRoleTask(String wgmRoleTask) {
		this.wgmRoleTask = wgmRoleTask;
	}
	public Date getWgmCrteTime() {
		return wgmCrteTime;
	}
	public void setWgmCrteTime(Date wgmCrteTime) {
		this.wgmCrteTime = wgmCrteTime;
	}
	public String getWgmCrteUserId() {
		return wgmCrteUserId;
	}
	public void setWgmCrteUserId(String wgmCrteUserId) {
		this.wgmCrteUserId = wgmCrteUserId;
	}
	public Date getWgmUpdtTime() {
		return wgmUpdtTime;
	}
	public void setWgmUpdtTime(Date wgmUpdtTime) {
		this.wgmUpdtTime = wgmUpdtTime;
	}
	public String getWgmUpdtUserId() {
		return wgmUpdtUserId;
	}
	public void setWgmUpdtUserId(String wgmUpdtUserId) {
		this.wgmUpdtUserId = wgmUpdtUserId;
	}
	public String getPbdPhone() {
		return pbdPhone;
	}
	public void setPbdPhone(String pbdPhone) {
		this.pbdPhone = pbdPhone;
	}
	public String getWgmWorkgroupFid() {
		return wgmWorkgroupFid;
	}
	public void setWgmWorkgroupFid(String wgmWorkgroupFid) {
		this.wgmWorkgroupFid = wgmWorkgroupFid;
	}
}
