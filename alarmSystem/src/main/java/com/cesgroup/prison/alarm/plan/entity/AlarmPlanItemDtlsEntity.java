package com.cesgroup.prison.alarm.plan.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：AlarmPlanItemDtlsEntity   
* @Description：   报警预案关联项实体
* @author：Tao.xu   
* @date：2017年12月25日 下午6:20:38   
* @version        
*/
@Entity
@Table(name = "CDS_PLAN_ITEM_DTLS")
public class AlarmPlanItemDtlsEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String pidCusNumber;

	private String pidPlanId;

	private String pidItemId;

	@Transient
	private String itemName;

	private String pidSttsIndc;

	private String pidRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pidCrteTime;

	private String pidCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pidUpdtTime;

	private String pidUpdtUserId;

	@Transient
	private List<PlanDeviceRltnEntity> devices;

	public String getPidCusNumber() {
		return pidCusNumber;
	}

	public void setPidCusNumber(String pidCusNumber) {
		this.pidCusNumber = pidCusNumber;
	}

	public String getPidPlanId() {
		return pidPlanId;
	}

	public void setPidPlanId(String pidPlanId) {
		this.pidPlanId = pidPlanId;
	}

	public String getPidItemId() {
		return pidItemId;
	}

	public void setPidItemId(String pidItemId) {
		this.pidItemId = pidItemId;
	}

	public String getPidSttsIndc() {
		return pidSttsIndc;
	}

	public void setPidSttsIndc(String pidSttsIndc) {
		this.pidSttsIndc = pidSttsIndc;
	}

	public String getPidRemark() {
		return pidRemark;
	}

	public void setPidRemark(String pidRemark) {
		this.pidRemark = pidRemark;
	}

	public Date getPidCrteTime() {
		return pidCrteTime;
	}

	public void setPidCrteTime(Date pidCrteTime) {
		this.pidCrteTime = pidCrteTime;
	}

	public String getPidCrteUserId() {
		return pidCrteUserId;
	}

	public void setPidCrteUserId(String pidCrteUserId) {
		this.pidCrteUserId = pidCrteUserId;
	}

	public Date getPidUpdtTime() {
		return pidUpdtTime;
	}

	public void setPidUpdtTime(Date pidUpdtTime) {
		this.pidUpdtTime = pidUpdtTime;
	}

	public String getPidUpdtUserId() {
		return pidUpdtUserId;
	}

	public List<PlanDeviceRltnEntity> getDevices() {
		return devices;
	}

	public void setDevices(List<PlanDeviceRltnEntity> devices) {
		this.devices = devices;
	}

	public void setPidUpdtUserId(String pidUpdtUserId) {
		this.pidUpdtUserId = pidUpdtUserId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
