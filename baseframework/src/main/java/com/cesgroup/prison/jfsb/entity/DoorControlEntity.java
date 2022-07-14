package com.cesgroup.prison.jfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：DoorControlEntity   
* @Description：   门禁控制器
* @author：Tao.xu   
* @date：2017年12月13日 下午5:41:52   
* @version        
*/
@Entity
@Table(name = "DVC_DOOR_CONTROL_DEVICE")
public class DoorControlEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :
	*/
	private static final long serialVersionUID = 1L;

	private String dcdCusNumber;

	private String dcdIdnty;

	private String dcdName;

	private String dcdSn;

	private String dcdIpAddrs;

	private String dcdPort;

	private String dcdUserName;

	private String dcdUserPassword;

	private String dcdActionIndc;

	private String dcdRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dcdCrteTime;

	private String dcdCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dcdUpdtTime;

	private String dcdUpdtUserId;

	public String getDcdCusNumber() {
		return dcdCusNumber;
	}

	public void setDcdCusNumber(String dcdCusNumber) {
		this.dcdCusNumber = dcdCusNumber;
	}

	public String getDcdIdnty() {
		return dcdIdnty;
	}

	public void setDcdIdnty(String dcdIdnty) {
		this.dcdIdnty = dcdIdnty;
	}

	public String getDcdName() {
		return dcdName;
	}

	public void setDcdName(String dcdName) {
		this.dcdName = dcdName;
	}

	public String getDcdSn() {
		return dcdSn;
	}

	public void setDcdSn(String dcdSn) {
		this.dcdSn = dcdSn;
	}

	public String getDcdIpAddrs() {
		return dcdIpAddrs;
	}

	public void setDcdIpAddrs(String dcdIpAddrs) {
		this.dcdIpAddrs = dcdIpAddrs;
	}

	public String getDcdPort() {
		return dcdPort;
	}

	public void setDcdPort(String dcdPort) {
		this.dcdPort = dcdPort;
	}

	public String getDcdUserName() {
		return dcdUserName;
	}

	public void setDcdUserName(String dcdUserName) {
		this.dcdUserName = dcdUserName;
	}

	public String getDcdUserPassword() {
		return dcdUserPassword;
	}

	public void setDcdUserPassword(String dcdUserPassword) {
		this.dcdUserPassword = dcdUserPassword;
	}

	public String getDcdActionIndc() {
		return dcdActionIndc;
	}

	public void setDcdActionIndc(String dcdActionIndc) {
		this.dcdActionIndc = dcdActionIndc;
	}

	public String getDcdRemark() {
		return dcdRemark;
	}

	public void setDcdRemark(String dcdRemark) {
		this.dcdRemark = dcdRemark;
	}

	public Date getDcdCrteTime() {
		return dcdCrteTime;
	}

	public void setDcdCrteTime(Date dcdCrteTime) {
		this.dcdCrteTime = dcdCrteTime;
	}

	public String getDcdCrteUserId() {
		return dcdCrteUserId;
	}

	public void setDcdCrteUserId(String dcdCrteUserId) {
		this.dcdCrteUserId = dcdCrteUserId;
	}

	public Date getDcdUpdtTime() {
		return dcdUpdtTime;
	}

	public void setDcdUpdtTime(Date dcdUpdtTime) {
		this.dcdUpdtTime = dcdUpdtTime;
	}

	public String getDcdUpdtUserId() {
		return dcdUpdtUserId;
	}

	public void setDcdUpdtUserId(String dcdUpdtUserId) {
		this.dcdUpdtUserId = dcdUpdtUserId;
	}

}
