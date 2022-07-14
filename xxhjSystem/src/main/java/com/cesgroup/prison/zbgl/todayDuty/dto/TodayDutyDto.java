package com.cesgroup.prison.zbgl.todayDuty.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 今日值班DTO
 * 
 * @author lincoln.cheng
 *
 */
public class TodayDutyDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 值班表编号
	 */
	private String id;
	/**
	 * 监狱编号
	 */
	private String dbdCusNumber;
	/**
	 * 值班人员编号
	 */
	private String dbdStaffId;
	/**
	 * 值班人员姓名
	 */
	private String dbdStaffName;
	/**
	 * 值班类别名称
	 */
	private String dcaCategoryName;
	/**
	 * 值班模板名称
	 */
	private String cdmModeName;
	/**
	 * 班次名称
	 */
	private String dorDutyOrderName;
	/**
	 * 岗位名称
	 */
	private String cdjJobName;
	/**
	 * 值班日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date dbdDutyDate;
	/**
	 * 开始时间
	 */
	private String dorStartTime;
	/**
	 * 结束时间
	 */
	private String dorEndTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDbdCusNumber() {
		return dbdCusNumber;
	}
	public void setDbdCusNumber(String dbdCusNumber) {
		this.dbdCusNumber = dbdCusNumber;
	}
	public String getDbdStaffId() {
		return dbdStaffId;
	}
	public void setDbdStaffId(String dbdStaffId) {
		this.dbdStaffId = dbdStaffId;
	}
	public String getDbdStaffName() {
		return dbdStaffName;
	}
	public void setDbdStaffName(String dbdStaffName) {
		this.dbdStaffName = dbdStaffName;
	}
	public String getDcaCategoryName() {
		return dcaCategoryName;
	}
	public void setDcaCategoryName(String dcaCategoryName) {
		this.dcaCategoryName = dcaCategoryName;
	}
	public String getCdmModeName() {
		return cdmModeName;
	}
	public void setCdmModeName(String cdmModeName) {
		this.cdmModeName = cdmModeName;
	}
	public String getDorDutyOrderName() {
		return dorDutyOrderName;
	}
	public void setDorDutyOrderName(String dorDutyOrderName) {
		this.dorDutyOrderName = dorDutyOrderName;
	}
	public String getCdjJobName() {
		return cdjJobName;
	}
	public void setCdjJobName(String cdjJobName) {
		this.cdjJobName = cdjJobName;
	}
	public Date getDbdDutyDate() {
		return dbdDutyDate;
	}
	public void setDbdDutyDate(Date dbdDutyDate) {
		this.dbdDutyDate = dbdDutyDate;
	}
	public String getDorStartTime() {
		return dorStartTime;
	}
	public void setDorStartTime(String dorStartTime) {
		this.dorStartTime = dorStartTime;
	}
	public String getDorEndTime() {
		return dorEndTime;
	}
	public void setDorEndTime(String dorEndTime) {
		this.dorEndTime = dorEndTime;
	}
}
