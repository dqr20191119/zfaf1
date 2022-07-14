package com.cesgroup.prison.zbgl.jjb.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @author lihong
* @date 创建时间：2020年5月28日 下午6:11:37
* 类说明:
*/
public class DutyDataDto {
	
	private String cusNumber;
	private String deptNumber;
	private String zbrId;//值班人id
	private String zbrName;//值班人
	private String orderName;//班次
	private String JobName;//岗位
	private String sartTime;//开始时间
	private String endTime;//结束时间
	private Date dutyDate;//值班日期
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getDeptNumber() {
		return deptNumber;
	}
	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}
	public String getZbrId() {
		return zbrId;
	}
	public void setZbrId(String zbrId) {
		this.zbrId = zbrId;
	}
	public String getZbrName() {
		return zbrName;
	}
	public void setZbrName(String zbrName) {
		this.zbrName = zbrName;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getJobName() {
		return JobName;
	}
	public void setJobName(String jobName) {
		JobName = jobName;
	}
	public String getSartTime() {
		return sartTime;
	}
	public void setSartTime(String sartTime) {
		this.sartTime = sartTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Date getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}
	
	
	
}
