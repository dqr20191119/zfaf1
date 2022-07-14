package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_laws_info")
public class FgglEntity extends StringIDEntity {
	
	private String lriCusNumber;					// 监狱id
	private String lriLawsType;						// 法规类别
	private String lriLawsName;						// 法规名称
	private String lpiSttsIndc;						// 法规状态
	private String lriLawsContent;					// 法规内容
	
	@Column(updatable = false)
	private Date lriCrteTime;						// 创建时间
	@Column(updatable = false)
	private String lriCrteUserId;					// 创建人id
	private Date lriUpdtTime;						// 更新时间
	private String lriUpdtUserId;					// 更新人id
	
	@Transient
	private String epaPlanFid;
	@Transient
	private String epaMethodFid;
	@Transient
	private String actionExist;
	
	
	public String getLriCusNumber() {
		return lriCusNumber;
	}
	public void setLriCusNumber(String lriCusNumber) {
		this.lriCusNumber = lriCusNumber;
	}
	public String getLriLawsType() {
		return lriLawsType;
	}
	public void setLriLawsType(String lriLawsType) {
		this.lriLawsType = lriLawsType;
	}
	public String getLriLawsName() {
		return lriLawsName;
	}
	public void setLriLawsName(String lriLawsName) {
		this.lriLawsName = lriLawsName;
	}
	public String getLpiSttsIndc() {
		return lpiSttsIndc;
	}
	public void setLpiSttsIndc(String lpiSttsIndc) {
		this.lpiSttsIndc = lpiSttsIndc;
	}
	public String getLriLawsContent() {
		return lriLawsContent;
	}
	public void setLriLawsContent(String lriLawsContent) {
		this.lriLawsContent = lriLawsContent;
	}
	public Date getLriCrteTime() {
		return lriCrteTime;
	}
	public void setLriCrteTime(Date lriCrteTime) {
		this.lriCrteTime = lriCrteTime;
	}
	public String getLriCrteUserId() {
		return lriCrteUserId;
	}
	public void setLriCrteUserId(String lriCrteUserId) {
		this.lriCrteUserId = lriCrteUserId;
	}
	public Date getLriUpdtTime() {
		return lriUpdtTime;
	}
	public void setLriUpdtTime(Date lriUpdtTime) {
		this.lriUpdtTime = lriUpdtTime;
	}
	public String getLriUpdtUserId() {
		return lriUpdtUserId;
	}
	public void setLriUpdtUserId(String lriUpdtUserId) {
		this.lriUpdtUserId = lriUpdtUserId;
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
	public String getActionExist() {
		return actionExist;
	}
	public void setActionExist(String actionExist) {
		this.actionExist = actionExist;
	}
}
