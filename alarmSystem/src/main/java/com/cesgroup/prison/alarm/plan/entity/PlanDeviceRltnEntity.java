package com.cesgroup.prison.alarm.plan.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：PlanDeviceRltnEntity   
* @Description：   设备关联报警预案实体类
* @author：Tao.xu   
* @date：2017年12月25日 下午6:27:55   
* @version        
*/
@Entity
@Table(name = "CDS_PLAN_DEVICE_RLTN")
public class PlanDeviceRltnEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	private String pdrCusNumber;

	private String pdrPlanId;

	private String pdrItemId;

	private String pdrDvcIdnty;

	private String pdrDvcName;

	private String pdrOutoIndc;
	/**
	* @Fields pdrExecOrder : 执行顺序
	*/
	private String pdrExecOrder;

	private String pdrRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pdrCrteTime;

	private String pdrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pdrUpdtTime;

	private String pdrUpdtUserId;

	public String getPdrCusNumber() {
		return pdrCusNumber;
	}

	public void setPdrCusNumber(String pdrCusNumber) {
		this.pdrCusNumber = pdrCusNumber;
	}

	public String getPdrPlanId() {
		return pdrPlanId;
	}

	public void setPdrPlanId(String pdrPlanId) {
		this.pdrPlanId = pdrPlanId;
	}

	public String getPdrItemId() {
		return pdrItemId;
	}

	public void setPdrItemId(String pdrItemId) {
		this.pdrItemId = pdrItemId;
	}

	public String getPdrDvcIdnty() {
		return pdrDvcIdnty;
	}

	public void setPdrDvcIdnty(String pdrDvcIdnty) {
		this.pdrDvcIdnty = pdrDvcIdnty;
	}

	public String getPdrDvcName() {
		return pdrDvcName;
	}

	public void setPdrDvcName(String pdrDvcName) {
		this.pdrDvcName = pdrDvcName;
	}

	public String getPdrExecOrder() {
		return pdrExecOrder;
	}

	public void setPdrExecOrder(String pdrExecOrder) {
		this.pdrExecOrder = pdrExecOrder;
	}

	public String getPdrRemark() {
		return pdrRemark;
	}

	public void setPdrRemark(String pdrRemark) {
		this.pdrRemark = pdrRemark;
	}

	public Date getPdrCrteTime() {
		return pdrCrteTime;
	}

	public void setPdrCrteTime(Date pdrCrteTime) {
		this.pdrCrteTime = pdrCrteTime;
	}

	public String getPdrCrteUserId() {
		return pdrCrteUserId;
	}

	public void setPdrCrteUserId(String pdrCrteUserId) {
		this.pdrCrteUserId = pdrCrteUserId;
	}

	public Date getPdrUpdtTime() {
		return pdrUpdtTime;
	}

	public void setPdrUpdtTime(Date pdrUpdtTime) {
		this.pdrUpdtTime = pdrUpdtTime;
	}

	public String getPdrUpdtUserId() {
		return pdrUpdtUserId;
	}

	public void setPdrUpdtUserId(String pdrUpdtUserId) {
		this.pdrUpdtUserId = pdrUpdtUserId;
	}

	public String getPdrOutoIndc() {
		return pdrOutoIndc;
	}

	public void setPdrOutoIndc(String pdrOutoIndc) {
		this.pdrOutoIndc = pdrOutoIndc;
	}

}
