package com.cesgroup.prison.alarm.plan.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：AlertPlanRltnEntity   
* @Description：  报警器关联报警预案实体 
* @author：Tao.xu   
* @date：2017年12月25日 下午6:35:23   
* @version        
*/
@Entity
@Table(name = "CDS_ALERT_PLAN_RLTN")
public class AlertPlanRltnEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String aprCusNumber;

	private String aprDvcIdnty;

	private String aprDvcName;

	private String aprDvcTypeIndc;

	private String aprBrandIndc;

	private String aprPlanId;

	private String aprRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date aprCrteTime;

	private String aprCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date aprUpdtTime;

	private String aprUpdtUserId;

	public String getAprCusNumber() {
		return aprCusNumber;
	}

	public void setAprCusNumber(String aprCusNumber) {
		this.aprCusNumber = aprCusNumber;
	}

	public String getAprDvcIdnty() {
		return aprDvcIdnty;
	}

	public void setAprDvcIdnty(String aprDvcIdnty) {
		this.aprDvcIdnty = aprDvcIdnty;
	}

	public String getAprDvcName() {
		return aprDvcName;
	}

	public void setAprDvcName(String aprDvcName) {
		this.aprDvcName = aprDvcName;
	}

	public String getAprDvcTypeIndc() {
		return aprDvcTypeIndc;
	}

	public void setAprDvcTypeIndc(String aprDvcTypeIndc) {
		this.aprDvcTypeIndc = aprDvcTypeIndc;
	}

	public String getAprPlanId() {
		return aprPlanId;
	}

	public void setAprPlanId(String aprPlanId) {
		this.aprPlanId = aprPlanId;
	}

	public String getAprRemark() {
		return aprRemark;
	}

	public void setAprRemark(String aprRemark) {
		this.aprRemark = aprRemark;
	}

	public Date getAprCrteTime() {
		return aprCrteTime;
	}

	public void setAprCrteTime(Date aprCrteTime) {
		this.aprCrteTime = aprCrteTime;
	}

	public String getAprCrteUserId() {
		return aprCrteUserId;
	}

	public void setAprCrteUserId(String aprCrteUserId) {
		this.aprCrteUserId = aprCrteUserId;
	}

	public Date getAprUpdtTime() {
		return aprUpdtTime;
	}

	public void setAprUpdtTime(Date aprUpdtTime) {
		this.aprUpdtTime = aprUpdtTime;
	}

	public String getAprUpdtUserId() {
		return aprUpdtUserId;
	}

	public void setAprUpdtUserId(String aprUpdtUserId) {
		this.aprUpdtUserId = aprUpdtUserId;
	}

	public String getAprBrandIndc() {
		return aprBrandIndc;
	}

	public void setAprBrandIndc(String aprBrandIndc) {
		this.aprBrandIndc = aprBrandIndc;
	}

}
