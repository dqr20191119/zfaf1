package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_material_info")
public class WzglEntity extends StringIDEntity {
	
	private String mriCusNumber;					// 监狱id
	private String mriMaterialName;					// 物资名称
	private String mriMaterialType;					// 物资类型
	private String mriMaterialCompany;			 	// 物资所属单位
	private String mriStatus;						// 物资状态
	private String mriAddress;						// 物资存放位置
	private String mriCharger;						// 物资负责人
	private String mriCount;						// 物资数量	
	private String mriChargePhone;					// 物资负责人电话
	private String mriUse;							// 物资用途
	private String mriSignCompany;					// 物资签约单位
	private String mriSpec;							// 规格说明
	
	@Column(updatable = false)
	private Date mriCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String mriCrteUserId;					// 创建人
	private Date mriUpdtTime;						// 更新时间
	private String mriUpdtUserId;					// 更新人
	
	@Transient
	private String epaPlanFid;
	@Transient
	private String epaMethodFid;
	@Transient
	private String actionExist;
	
	
	public String getMriCusNumber() {
		return mriCusNumber;
	}
	public void setMriCusNumber(String mriCusNumber) {
		this.mriCusNumber = mriCusNumber;
	}
	public String getMriMaterialName() {
		return mriMaterialName;
	}
	public void setMriMaterialName(String mriMaterialName) {
		this.mriMaterialName = mriMaterialName;
	}
	public String getMriMaterialType() {
		return mriMaterialType;
	}
	public void setMriMaterialType(String mriMaterialType) {
		this.mriMaterialType = mriMaterialType;
	}
	public String getMriMaterialCompany() {
		return mriMaterialCompany;
	}
	public void setMriMaterialCompany(String mriMaterialCompany) {
		this.mriMaterialCompany = mriMaterialCompany;
	}
	public String getMriStatus() {
		return mriStatus;
	}
	public void setMriStatus(String mriStatus) {
		this.mriStatus = mriStatus;
	}
	public String getMriAddress() {
		return mriAddress;
	}
	public void setMriAddress(String mriAddress) {
		this.mriAddress = mriAddress;
	}
	public String getMriCharger() {
		return mriCharger;
	}
	public void setMriCharger(String mriCharger) {
		this.mriCharger = mriCharger;
	}
	public String getMriCount() {
		return mriCount;
	}
	public void setMriCount(String mriCount) {
		this.mriCount = mriCount;
	}
	public String getMriChargePhone() {
		return mriChargePhone;
	}
	public void setMriChargePhone(String mriChargePhone) {
		this.mriChargePhone = mriChargePhone;
	}
	public String getMriUse() {
		return mriUse;
	}
	public void setMriUse(String mriUse) {
		this.mriUse = mriUse;
	}
	public String getMriSignCompany() {
		return mriSignCompany;
	}
	public void setMriSignCompany(String mriSignCompany) {
		this.mriSignCompany = mriSignCompany;
	}
	public String getMriSpec() {
		return mriSpec;
	}
	public void setMriSpec(String mriSpec) {
		this.mriSpec = mriSpec;
	}
	public Date getMriCrteTime() {
		return mriCrteTime;
	}
	public void setMriCrteTime(Date mriCrteTime) {
		this.mriCrteTime = mriCrteTime;
	}
	public String getMriCrteUserId() {
		return mriCrteUserId;
	}
	public void setMriCrteUserId(String mriCrteUserId) {
		this.mriCrteUserId = mriCrteUserId;
	}
	public Date getMriUpdtTime() {
		return mriUpdtTime;
	}
	public void setMriUpdtTime(Date mriUpdtTime) {
		this.mriUpdtTime = mriUpdtTime;
	}
	public String getMriUpdtUserId() {
		return mriUpdtUserId;
	}
	public void setMriUpdtUserId(String mriUpdtUserId) {
		this.mriUpdtUserId = mriUpdtUserId;
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
