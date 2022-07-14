package com.cesgroup.prison.securityCheck.bean;

import java.io.Serializable;

/**
 * 民警所在位置Bean
 * 
 * @author lincoln.cheng
 *
 */
public class SecurityCheckBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 设备名称
	 */
	private String DeviceName;
	/**
	 * 安检时间
	 */
	private String Time;
	/**
	 * 安检地点
	 */
	private String Location;
	/**
	 * 案件结果
	 */
	private String Result;
	
	public String getDeviceName() {
		return DeviceName;
	}
	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
}