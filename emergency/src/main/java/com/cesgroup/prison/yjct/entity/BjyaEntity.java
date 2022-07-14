package com.cesgroup.prison.yjct.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_plan_master")
public class BjyaEntity extends StringIDEntity {
	
	private String pmaCusNumber;						// 监狱id
	private String pmaPlanName;							// 名称
	private String pmaRemark;							// 备注
	
	@Column(updatable = false)
	private Date pmaCrteTime;							// 创建时间
	@Column(updatable = false)
	private String pmaCrteUserId;						// 创建人id
	private Date pmaUpdtTime;							// 更新时间
	private String pmaUpdtUserId;						// 更新人id
	
	
	public String getPmaCusNumber() {
		return pmaCusNumber;
	}
	public void setPmaCusNumber(String pmaCusNumber) {
		this.pmaCusNumber = pmaCusNumber;
	}
	public String getPmaPlanName() {
		return pmaPlanName;
	}
	public void setPmaPlanName(String pmaPlanName) {
		this.pmaPlanName = pmaPlanName;
	}
	public String getPmaRemark() {
		return pmaRemark;
	}
	public void setPmaRemark(String pmaRemark) {
		this.pmaRemark = pmaRemark;
	}
	public Date getPmaCrteTime() {
		return pmaCrteTime;
	}
	public void setPmaCrteTime(Date pmaCrteTime) {
		this.pmaCrteTime = pmaCrteTime;
	}
	public String getPmaCrteUserId() {
		return pmaCrteUserId;
	}
	public void setPmaCrteUserId(String pmaCrteUserId) {
		this.pmaCrteUserId = pmaCrteUserId;
	}
	public Date getPmaUpdtTime() {
		return pmaUpdtTime;
	}
	public void setPmaUpdtTime(Date pmaUpdtTime) {
		this.pmaUpdtTime = pmaUpdtTime;
	}
	public String getPmaUpdtUserId() {
		return pmaUpdtUserId;
	}
	public void setPmaUpdtUserId(String pmaUpdtUserId) {
		this.pmaUpdtUserId = pmaUpdtUserId;
	}
}
