package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_expert_info")
public class ZjglEntity extends StringIDEntity {
	
	private String epiCusNumber;					// 监狱id
 	private String epiExpertName;					// 专家名称
	private String epiCompany;						// 所属单位
	private String epiPost;							// 职位
	private String epiSex;							// 性别
	private String epiAge;							// 年龄
	private String epiFunction;						// 职能
	private String epiType;							// 类别
	private String epiSpecialty;					// 应急特长
	private String epiPhone;						// 电话
 	private String epiSttsIndc;						// 状态
	
	@Column(updatable = false)
	private Date epiCrteTime;
	@Column(updatable = false)
	private String epiCrteUserId;
	private Date epiUpdtTime;
	private String epiUpdtUserId;
	private String epiRemark;						// 备注
	
	@Transient
	private String epaPlanFid;
	@Transient
	private String epaMethodFid;
	@Transient
	private String actionExist;
	
	
	public String getEpiCusNumber() {
		return epiCusNumber;
	}
	public void setEpiCusNumber(String epiCusNumber) {
		this.epiCusNumber = epiCusNumber;
	}
	public String getEpiExpertName() {
		return epiExpertName;
	}
	public void setEpiExpertName(String epiExpertName) {
		this.epiExpertName = epiExpertName;
	}
	public String getEpiCompany() {
		return epiCompany;
	}
	public void setEpiCompany(String epiCompany) {
		this.epiCompany = epiCompany;
	}
	public String getEpiPost() {
		return epiPost;
	}
	public void setEpiPost(String epiPost) {
		this.epiPost = epiPost;
	}
	public String getEpiSex() {
		return epiSex;
	}
	public void setEpiSex(String epiSex) {
		this.epiSex = epiSex;
	}
	public String getEpiAge() {
		return epiAge;
	}
	public void setEpiAge(String epiAge) {
		this.epiAge = epiAge;
	}
	public String getEpiFunction() {
		return epiFunction;
	}
	public void setEpiFunction(String epiFunction) {
		this.epiFunction = epiFunction;
	}
	public String getEpiType() {
		return epiType;
	}
	public void setEpiType(String epiType) {
		this.epiType = epiType;
	}
	public String getEpiSpecialty() {
		return epiSpecialty;
	}
	public void setEpiSpecialty(String epiSpecialty) {
		this.epiSpecialty = epiSpecialty;
	}
	public String getEpiPhone() {
		return epiPhone;
	}
	public void setEpiPhone(String epiPhone) {
		this.epiPhone = epiPhone;
	}
	public String getEpiSttsIndc() {
		return epiSttsIndc;
	}
	public void setEpiSttsIndc(String epiSttsIndc) {
		this.epiSttsIndc = epiSttsIndc;
	}
	public Date getEpiCrteTime() {
		return epiCrteTime;
	}
	public void setEpiCrteTime(Date epiCrteTime) {
		this.epiCrteTime = epiCrteTime;
	}
	public String getEpiCrteUserId() {
		return epiCrteUserId;
	}
	public void setEpiCrteUserId(String epiCrteUserId) {
		this.epiCrteUserId = epiCrteUserId;
	}
	public Date getEpiUpdtTime() {
		return epiUpdtTime;
	}
	public void setEpiUpdtTime(Date epiUpdtTime) {
		this.epiUpdtTime = epiUpdtTime;
	}
	public String getEpiUpdtUserId() {
		return epiUpdtUserId;
	}
	public void setEpiUpdtUserId(String epiUpdtUserId) {
		this.epiUpdtUserId = epiUpdtUserId;
	}
	public String getEpiRemark() {
		return epiRemark;
	}
	public void setEpiRemark(String epiRemark) {
		this.epiRemark = epiRemark;
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
