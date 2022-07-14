package com.cesgroup.prison.sppz.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.mybatis.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
*    
* @projectName：prison   
* @ClassName：StreamServer   
* @Description：流媒体服务   
* @author：zhengke   
* @date：2017-12-01 09:50   
* @version        
*/
@Entity
@Table(name="DVC_STREAM_SERVER_INFO")
public class StreamServer extends StringIDEntity{
	/**
	* @Fields ssiCusNumber : 监所编号
	*/
	@NotNull
	private String ssiCusNumber;

	/**
	* @Fields ssiServerName : 流媒体服务名
	*/
	@NotNull
	private String ssiServerName;

	/**
	* @Fields ssiTypeIndc : 类型 1、DVR 2、编码器
	*/
	@NotNull
	private String ssiTypeIndc;

	/**
	* @Fields ssiIpAddrs : IP地址
	*/
	@NotNull
	private String ssiIpAddrs;

	/**
	* @Fields ssiPort : 端口
	*/
	@NotNull
	private String ssiPort;

	/**
	* @Fields ssiIp2Addrs : IP地址2
	*/
	private String ssiIp2Addrs;

	/**
	* @Fields ssiPort2 : 端口2
	*/
	private String ssiPort2;

	/**
	* @Fields ssiRemark : 描述
	*/
	private String ssiRemark;

	/**
	* @Fields ssiAreaId : 区域
	*/
	private String ssiAreaId;

	/**
	* @Fields ssiCrteTime : 创建时间
	*/
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date ssiCrteTime;

	/**
	* @Fields ssiCrteUserId : 创建人
	*/
	@NotNull
	private String ssiCrteUserId;

	public String getSsiServerName() {
		return ssiServerName;
	}

	public void setSsiServerName(String ssiServerName) {
		this.ssiServerName = ssiServerName;
	}

	public String getSsiIpAddrs() {
		return ssiIpAddrs;
	}

	public void setSsiIpAddrs(String ssiIpAddrs) {
		this.ssiIpAddrs = ssiIpAddrs;
	}

	public String getSsiIp2Addrs() {
		return ssiIp2Addrs;
	}

	public void setSsiIp2Addrs(String ssiIp2Addrs) {
		this.ssiIp2Addrs = ssiIp2Addrs;
	}

	public String getSsiRemark() {
		return ssiRemark;
	}

	public void setSsiRemark(String ssiRemark) {
		this.ssiRemark = ssiRemark;
	}


	public String getSsiCusNumber() {
		return ssiCusNumber;
	}

	public void setSsiCusNumber(String ssiCusNumber) {
		this.ssiCusNumber = ssiCusNumber;
	}

	public String getSsiTypeIndc() {
		return ssiTypeIndc;
	}

	public void setSsiTypeIndc(String ssiTypeIndc) {
		this.ssiTypeIndc = ssiTypeIndc;
	}

	public String getSsiPort() {
		return ssiPort;
	}

	public void setSsiPort(String ssiPort) {
		this.ssiPort = ssiPort;
	}

	public String getSsiPort2() {
		return ssiPort2;
	}

	public void setSsiPort2(String ssiPort2) {
		this.ssiPort2 = ssiPort2;
	}

	public String getSsiAreaId() {
		return ssiAreaId;
	}

	public void setSsiAreaId(String ssiAreaId) {
		this.ssiAreaId = ssiAreaId;
	}

	public Date getSsiCrteTime() {
		return ssiCrteTime;
	}

	public void setSsiCrteTime(Date ssiCrteTime) {
		this.ssiCrteTime = ssiCrteTime;
	}

	public String getSsiCrteUserId() {
		return ssiCrteUserId;
	}

	public void setSsiCrteUserId(String ssiCrteUserId) {
		this.ssiCrteUserId = ssiCrteUserId;
	}
	
	
}
