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
* @ClassName：VideoDevice   
* @Description：视频设备   
* @author：zhengke   
* @date：2017-11-29 12:53   
* @version        
*/
@Entity
@Table(name="DVC_VIDEO_DEVICE_INFO")
public class VideoDevice extends StringIDEntity{
	/**
	* @Fields vdiCusNumber : 监所编码
	*/
	private String vdiCusNumber;

	/**
	* @Fields vdiDeviceName : 视频设备名称
	*/
	@NotNull(message="视频设备名称不能为空")
	private String vdiDeviceName;

	/**
	* @Fields vdiTypeIndc : 视频设备类型 1：DVR 2：编码器 3:NVR
	*/
	@NotNull
	private String vdiTypeIndc;

	/**
	* @Fields vdiBrand : 品牌
	*/
	private String vdiBrand;

	/**
	* @Fields vdiMode : 型号
	*/
	@NotNull
	private String vdiMode;

	/**
	* @Fields vdiIpAddrs : IP地址
	*/
	@NotNull
	private String vdiIpAddrs;

	/**
	* @Fields vdiIp2Addrs : IP地址2
	*/
	private String vdiIp2Addrs;

	/**
	* @Fields vdiPort : 端口
	*/
	@NotNull
	private String vdiPort;

	/**
	* @Fields vdiUserName : 用户名
	*/
	private String vdiUserName;

	/**
	* @Fields vdiUserPassword : 密码
	*/
	private String vdiUserPassword;

	/**
	* @Fields vdiCrteTime : 创建时间
	*/
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date vdiCrteTime;

	/**
	* @Fields vdiCrteUserId : 创建人
	*/
	@NotNull
	private String vdiCrteUserId;


	public String getVdiCusNumber() {
		return vdiCusNumber;
	}

	public void setVdiCusNumber(String vdiCusNumber) {
		this.vdiCusNumber = vdiCusNumber;
	}

	public String getVdiDeviceName() {
		return vdiDeviceName;
	}

	public void setVdiDeviceName(String vdiDeviceName) {
		this.vdiDeviceName = vdiDeviceName;
	}

	public String getVdiTypeIndc() {
		return vdiTypeIndc;
	}

	public void setVdiTypeIndc(String vdiTypeIndc) {
		this.vdiTypeIndc = vdiTypeIndc;
	}

	public String getVdiBrand() {
		return vdiBrand;
	}

	public void setVdiBrand(String vdiBrand) {
		this.vdiBrand = vdiBrand;
	}

	public String getVdiMode() {
		return vdiMode;
	}

	public void setVdiMode(String vdiMode) {
		this.vdiMode = vdiMode;
	}

	public String getVdiIpAddrs() {
		return vdiIpAddrs;
	}

	public void setVdiIpAddrs(String vdiIpAddrs) {
		this.vdiIpAddrs = vdiIpAddrs;
	}

	public String getVdiIp2Addrs() {
		return vdiIp2Addrs;
	}

	public void setVdiIp2Addrs(String vdiIp2Addrs) {
		this.vdiIp2Addrs = vdiIp2Addrs;
	}

	public String getVdiPort() {
		return vdiPort;
	}

	public void setVdiPort(String vdiPort) {
		this.vdiPort = vdiPort;
	}

	public String getVdiUserName() {
		return vdiUserName;
	}

	public void setVdiUserName(String vdiUserName) {
		this.vdiUserName = vdiUserName;
	}

	public String getVdiUserPassword() {
		return vdiUserPassword;
	}

	public void setVdiUserPassword(String vdiUserPassword) {
		this.vdiUserPassword = vdiUserPassword;
	}

	public Date getVdiCrteTime() {
		return vdiCrteTime;
	}

	public void setVdiCrteTime(Date vdiCrteTime) {
		this.vdiCrteTime = vdiCrteTime;
	}

	public String getVdiCrteUserId() {
		return vdiCrteUserId;
	}

	public void setVdiCrteUserId(String vdiCrteUserId) {
		this.vdiCrteUserId = vdiCrteUserId;
	}
	
	

}
