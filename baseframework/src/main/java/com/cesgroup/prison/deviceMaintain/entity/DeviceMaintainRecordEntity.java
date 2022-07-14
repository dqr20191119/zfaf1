package com.cesgroup.prison.deviceMaintain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：alarmSystem   
* @ClassName：DeviceMaintainRecordEntity   
* @Description：   设备维修记录
* @author：Tao.xu   
* @date：2018年2月28日 上午9:46:08   
* @version        
*/
@Entity
@Table(name = "CDS_DEVICE_MAINTAIN_RECORD")
public class DeviceMaintainRecordEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = -5017370728129940468L;

	private String dmrCusNumber;

	private String dmrDeviceType;

	private String dmrDeviceIdnty;

	private String dmrDeviceName;

	private String dmrFaultMaintainer;

	private String dmrFaultContent;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String dmrFaultMaintainTime;

	private String dmrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmrCrteTime;

	private String dmrUpdtUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmrUpdtTime;

	public String getDmrCusNumber() {
		return dmrCusNumber;
	}

	public void setDmrCusNumber(String dmrCusNumber) {
		this.dmrCusNumber = dmrCusNumber;
	}

	public String getDmrDeviceType() {
		return dmrDeviceType;
	}

	public void setDmrDeviceType(String dmrDeviceType) {
		this.dmrDeviceType = dmrDeviceType;
	}

	public String getDmrDeviceIdnty() {
		return dmrDeviceIdnty;
	}

	public void setDmrDeviceIdnty(String dmrDeviceIdnty) {
		this.dmrDeviceIdnty = dmrDeviceIdnty;
	}

	public String getDmrDeviceName() {
		return dmrDeviceName;
	}

	public void setDmrDeviceName(String dmrDeviceName) {
		this.dmrDeviceName = dmrDeviceName;
	}

	public String getDmrFaultMaintainer() {
		return dmrFaultMaintainer;
	}

	public void setDmrFaultMaintainer(String dmrFaultMaintainer) {
		this.dmrFaultMaintainer = dmrFaultMaintainer;
	}

	public String getDmrFaultContent() {
		return dmrFaultContent;
	}

	public void setDmrFaultContent(String dmrFaultContent) {
		this.dmrFaultContent = dmrFaultContent;
	}

	public String getDmrFaultMaintainTime() {
		return dmrFaultMaintainTime;
	}

	public void setDmrFaultMaintainTime(String dmrFaultMaintainTime) {
		this.dmrFaultMaintainTime = dmrFaultMaintainTime;
	}

	public String getDmrCrteUserId() {
		return dmrCrteUserId;
	}

	public void setDmrCrteUserId(String dmrCrteUserId) {
		this.dmrCrteUserId = dmrCrteUserId;
	}

	public Date getDmrCrteTime() {
		return dmrCrteTime;
	}

	public void setDmrCrteTime(Date dmrCrteTime) {
		this.dmrCrteTime = dmrCrteTime;
	}

	public String getDmrUpdtUserId() {
		return dmrUpdtUserId;
	}

	public void setDmrUpdtUserId(String dmrUpdtUserId) {
		this.dmrUpdtUserId = dmrUpdtUserId;
	}

	public Date getDmrUpdtTime() {
		return dmrUpdtTime;
	}

	public void setDmrUpdtTime(Date dmrUpdtTime) {
		this.dmrUpdtTime = dmrUpdtTime;
	}

}
