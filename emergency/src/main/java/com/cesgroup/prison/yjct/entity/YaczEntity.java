package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_emergency_plan_action")
public class YaczEntity extends StringIDEntity {
	
	private String epaCusNumber;						// 监狱id
 	private String epaRelActionType;					// 关联操作项类别	1-事件记录，2-工作组，3-专家协助，4-应急物资，5-法律法规
	private String epaRemark;							// 备注
	
	@Column(updatable = false)
	private Date epaCrteTime;							// 创建时间
	@Column(updatable = false)
	private String epaCrteUserId;						// 创建人id	
	private Date epaUpdtTime;							// 更新时间
	private String epaUpdtUserId;						// 更新人id
	
 	private String epaPlanFid;
 	private String epaMethodFid;
 	private String epaRelActionFid;
 		
 	@Transient
	private String epiExpertName;
 	@Transient
 	private String mriMaterialName;
 	@Transient
 	private String mriCount;
 	@Transient
 	private String lriLawsName;
 	@Transient
 	private String lriLawsContent;
 	@Transient
 	private String actionCount;
 	
	
	public String getEpaCusNumber() {
		return epaCusNumber;
	}
	public void setEpaCusNumber(String epaCusNumber) {
		this.epaCusNumber = epaCusNumber;
	}
	public String getEpaRelActionType() {
		return epaRelActionType;
	}
	public void setEpaRelActionType(String epaRelActionType) {
		this.epaRelActionType = epaRelActionType;
	}
	public String getEpaRemark() {
		return epaRemark;
	}
	public void setEpaRemark(String epaRemark) {
		this.epaRemark = epaRemark;
	}
	public Date getEpaCrteTime() {
		return epaCrteTime;
	}
	public void setEpaCrteTime(Date epaCrteTime) {
		this.epaCrteTime = epaCrteTime;
	}
	public String getEpaCrteUserId() {
		return epaCrteUserId;
	}
	public void setEpaCrteUserId(String epaCrteUserId) {
		this.epaCrteUserId = epaCrteUserId;
	}
	public Date getEpaUpdtTime() {
		return epaUpdtTime;
	}
	public void setEpaUpdtTime(Date epaUpdtTime) {
		this.epaUpdtTime = epaUpdtTime;
	}
	public String getEpaUpdtUserId() {
		return epaUpdtUserId;
	}
	public void setEpaUpdtUserId(String epaUpdtUserId) {
		this.epaUpdtUserId = epaUpdtUserId;
	}
	public String getEpaPlanFid() {
		return epaPlanFid;
	}
	public void setEpaPlanFid(String epaPlanFid) {
		this.epaPlanFid = epaPlanFid;
	}
	public String getEpaMethodFid() {
		return epaMethodFid;
	}
	public void setEpaMethodFid(String epaMethodFid) {
		this.epaMethodFid = epaMethodFid;
	}
	public String getEpaRelActionFid() {
		return epaRelActionFid;
	}
	public void setEpaRelActionFid(String epaRelActionFid) {
		this.epaRelActionFid = epaRelActionFid;
	}
	public String getEpiExpertName() {
		return epiExpertName;
	}
	public void setEpiExpertName(String epiExpertName) {
		this.epiExpertName = epiExpertName;
	}
	public String getMriMaterialName() {
		return mriMaterialName;
	}
	public void setMriMaterialName(String mriMaterialName) {
		this.mriMaterialName = mriMaterialName;
	}
	public String getMriCount() {
		return mriCount;
	}
	public void setMriCount(String mriCount) {
		this.mriCount = mriCount;
	}
	public String getLriLawsName() {
		return lriLawsName;
	}
	public void setLriLawsName(String lriLawsName) {
		this.lriLawsName = lriLawsName;
	}
	public String getLriLawsContent() {
		return lriLawsContent;
	}
	public void setLriLawsContent(String lriLawsContent) {
		this.lriLawsContent = lriLawsContent;
	}
	public String getActionCount() {
		return actionCount;
	}
	public void setActionCount(String actionCount) {
		this.actionCount = actionCount;
	}	
}
