package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_method_rel_action")
public class CzffczxEntity extends StringIDEntity {
	
	private String mraCusNumber;						// 监狱id
	private String mraRelActionType;					// 关联操作类型
	private String mraMethodFid;						// 处置方法id
	
	@Column(updatable = false)
	private Date mraCrteTime;							// 创建时间
	@Column(updatable = false)
	private String mraCrteUserId;						// 创建人id
	private Date mraUpdtTime;							// 更新时间
	private String mraUpdtUserId;						// 更新人id
	
	@Transient
	private String epcPlanFid;
	@Transient
	private String actionCount;
	
	
	public String getMraCusNumber() {
		return mraCusNumber;
	}
	public void setMraCusNumber(String mraCusNumber) {
		this.mraCusNumber = mraCusNumber;
	}
	public String getMraRelActionType() {
		return mraRelActionType;
	}
	public void setMraRelActionType(String mraRelActionType) {
		this.mraRelActionType = mraRelActionType;
	}
	public Date getMraCrteTime() {
		return mraCrteTime;
	}
	public void setMraCrteTime(Date mraCrteTime) {
		this.mraCrteTime = mraCrteTime;
	}
	public String getMraCrteUserId() {
		return mraCrteUserId;
	}
	public void setMraCrteUserId(String mraCrteUserId) {
		this.mraCrteUserId = mraCrteUserId;
	}
	public Date getMraUpdtTime() {
		return mraUpdtTime;
	}
	public void setMraUpdtTime(Date mraUpdtTime) {
		this.mraUpdtTime = mraUpdtTime;
	}
	public String getMraUpdtUserId() {
		return mraUpdtUserId;
	}
	public void setMraUpdtUserId(String mraUpdtUserId) {
		this.mraUpdtUserId = mraUpdtUserId;
	}
	public String getMraMethodFid() {
		return mraMethodFid;
	}
	public void setMraMethodFid(String mraMethodFid) {
		this.mraMethodFid = mraMethodFid;
	}
	public String getEpcPlanFid() {
		return epcPlanFid;
	}
	public void setEpcPlanFid(String epcPlanFid) {
		this.epcPlanFid = epcPlanFid;
	}
	public String getActionCount() {
		return actionCount;
	}
	public void setActionCount(String actionCount) {
		this.actionCount = actionCount;
	}
}
