package com.cesgroup.prison.deviceMaintain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：DeviceFaultTypeEntity   
* @Description： 设备维修类别  
* @author：Tao.xu   
* @date：2017年12月17日 下午10:06:39   
* @version        
*/
@Entity
@Table(name = "CDS_DEVICE_FAULT_TYPE")
public class DeviceFaultTypeEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	private String dftCusNumber;

	private String dftFaultName;

	/**
	* @Fields dftTypeClassify : 故障区分类别和内容的字段 1、类别 2、内容
	*/
	private String dftTypeClassify;

	private String dftParentId;

	/**
	* @Fields dftSttsIndc :故障类型状态:  1、启用  2、停用 3、删除		
	*/
	private String dftSttsIndc;

	private String dftActionIndc;

	private String dftCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dftCrteTime;

	private String dftUpdtUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dftUpdtTime;

	public String getDftCusNumber() {
		return dftCusNumber;
	}

	public void setDftCusNumber(String dftCusNumber) {
		this.dftCusNumber = dftCusNumber;
	}

	public String getDftFaultName() {
		return dftFaultName;
	}

	public void setDftFaultName(String dftFaultName) {
		this.dftFaultName = dftFaultName;
	}

	public String getDftTypeClassify() {
		return dftTypeClassify;
	}

	public void setDftTypeClassify(String dftTypeClassify) {
		this.dftTypeClassify = dftTypeClassify;
	}

	public String getDftParentId() {
		return dftParentId;
	}

	public void setDftParentId(String dftParentId) {
		this.dftParentId = dftParentId;
	}

	public String getDftSttsIndc() {
		return dftSttsIndc;
	}

	public void setDftSttsIndc(String dftSttsIndc) {
		this.dftSttsIndc = dftSttsIndc;
	}

	public String getDftActionIndc() {
		return dftActionIndc;
	}

	public void setDftActionIndc(String dftActionIndc) {
		this.dftActionIndc = dftActionIndc;
	}

	public String getDftCrteUserId() {
		return dftCrteUserId;
	}

	public void setDftCrteUserId(String dftCrteUserId) {
		this.dftCrteUserId = dftCrteUserId;
	}

	public Date getDftCrteTime() {
		return dftCrteTime;
	}

	public void setDftCrteTime(Date dftCrteTime) {
		this.dftCrteTime = dftCrteTime;
	}

	public String getDftUpdtUserId() {
		return dftUpdtUserId;
	}

	public void setDftUpdtUserId(String dftUpdtUserId) {
		this.dftUpdtUserId = dftUpdtUserId;
	}

	public Date getDftUpdtTime() {
		return dftUpdtTime;
	}

	public void setDftUpdtTime(Date dftUpdtTime) {
		this.dftUpdtTime = dftUpdtTime;
	}

}
