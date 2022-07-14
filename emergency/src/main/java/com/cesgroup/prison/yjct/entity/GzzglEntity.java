package com.cesgroup.prison.yjct.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_workgroup_info")
public class GzzglEntity extends StringIDEntity {
	
	private String wgiCusNumber;				// 监狱id
	private String wgiWorkgroupName;			// 工作组名称
	private String wgiWorkgroupTask;			// 工作组任务
	private String wgiStatus;					// 状态	
	
	@Column(updatable = false)
	private Date wgiCrteTime;					// 创建时间
	@Column(updatable = false)
	private String wgiCrteUserId;				// 创建人id
	private Date wgiUpdtTime;					// 更新时间
	private String wgiUpdtUserId;				// 更新人id
	
	@Transient
	private List<GzzcyEntity> gzzcyEntityList = new ArrayList<GzzcyEntity>();	// 工作组成员
	@Transient
	private String configExist;
	@Transient
	private String epcPlanFid;
  	
	
	public String getWgiCusNumber() {
		return wgiCusNumber;
	}
	public void setWgiCusNumber(String wgiCusNumber) {
		this.wgiCusNumber = wgiCusNumber;
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
	public String getWgiStatus() {
		return wgiStatus;
	}
	public void setWgiStatus(String wgiStatus) {
		this.wgiStatus = wgiStatus;
	}
	public Date getWgiCrteTime() {
		return wgiCrteTime;
	}
	public void setWgiCrteTime(Date wgiCrteTime) {
		this.wgiCrteTime = wgiCrteTime;
	}
	public String getWgiCrteUserId() {
		return wgiCrteUserId;
	}
	public void setWgiCrteUserId(String wgiCrteUserId) {
		this.wgiCrteUserId = wgiCrteUserId;
	}
	public Date getWgiUpdtTime() {
		return wgiUpdtTime;
	}
	public void setWgiUpdtTime(Date wgiUpdtTime) {
		this.wgiUpdtTime = wgiUpdtTime;
	}
	public String getWgiUpdtUserId() {
		return wgiUpdtUserId;
	}
	public void setWgiUpdtUserId(String wgiUpdtUserId) {
		this.wgiUpdtUserId = wgiUpdtUserId;
	} 
	public List<GzzcyEntity> getGzzcyEntityList() {
		return gzzcyEntityList;
	}
	public void setGzzcyEntityList(List<GzzcyEntity> gzzcyEntityList) {
		this.gzzcyEntityList = gzzcyEntityList;
	}
	public String getConfigExist() {
		return configExist;
	}
	public void setConfigExist(String configExist) {
		this.configExist = configExist;
	}
	public String getEpcPlanFid() {
		return epcPlanFid;
	}
	public void setEpcPlanFid(String epcPlanFid) {
		this.epcPlanFid = epcPlanFid;
	}
}
