package com.cesgroup.prison.zbgl.zbbp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "cds_duty_base_dtls")
public class ZbbpEntity extends StringIDEntity {

	private String dbdCusNumber;					// 监狱id
	private String dbdDutyModeDepartmentId;        //模板部门ID
	private String dbdDutyModeOrderJobId;          //模板详情ID
	private String dbdStaffId;                      //值班警员ID
	private String dbdStaffName;                      //值班警员名称
	private String dbdRemark;						// 备注	
	private Date   dbdUpdtTime;					    // 更新时间
	private String dbdUpdtUserId;					// 更新id
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dbdDutyDate;						// 值班日期
	
	@Column(updatable = false)
	private Date dbdCrteTime;						// 创建时间
	
	@Column(updatable = false)						
	private String dbdCrteUserId;					// 创建人
	
	@Transient
	private List<ZbbpEntity> zbbpEntityList = new ArrayList<ZbbpEntity>();
	
	@Transient
	private String dutyPoliceData ;              //值班人员信息
	
	@Transient
	private String policeName ;              //值班人员信息
	
	@Transient
	private String mojOrderId;               //模板详情表中班次
	
	@Transient 
	private String para ;              //
	
	public String getMojOrderId() {
		return mojOrderId;
	}

	public void setMojOrderId(String mojOrderId) {
		this.mojOrderId = mojOrderId;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getDutyPoliceData() {
		return dutyPoliceData;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getDbdDutyModeDepartmentId() {
		return dbdDutyModeDepartmentId;
	}

	public void setDbdDutyModeDepartmentId(String dbdDutyModeDepartmentId) {
		this.dbdDutyModeDepartmentId = dbdDutyModeDepartmentId;
	}

	public String getDbdDutyModeOrderJobId() {
		return dbdDutyModeOrderJobId;
	}

	public void setDbdDutyModeOrderJobId(String dbdDutyModeOrderJobId) {
		this.dbdDutyModeOrderJobId = dbdDutyModeOrderJobId;
	}

	public void setDutyPoliceData(String dutyPoliceData) {
		this.dutyPoliceData = dutyPoliceData;
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

	public String getDbdRemark() {
		return dbdRemark;
	}

	public void setDbdRemark(String dbdRemark) {
		this.dbdRemark = dbdRemark;
	}

	public Date getDbdUpdtTime() {
		return dbdUpdtTime;
	}

	public void setDbdUpdtTime(Date dbdUpdtTime) {
		this.dbdUpdtTime = dbdUpdtTime;
	}

	public String getDbdUpdtUserId() {
		return dbdUpdtUserId;
	}

	public void setDbdUpdtUserId(String dbdUpdtUserId) {
		this.dbdUpdtUserId = dbdUpdtUserId;
	}

	public Date getDbdDutyDate() {
		return dbdDutyDate;
	}

	public void setDbdDutyDate(Date dbdDutyDate) {
		this.dbdDutyDate = dbdDutyDate;
	}

	public Date getDbdCrteTime() {
		return dbdCrteTime;
	}

	public void setDbdCrteTime(Date dbdCrteTime) {
		this.dbdCrteTime = dbdCrteTime;
	}

	public String getDbdCrteUserId() {
		return dbdCrteUserId;
	}

	public void setDbdCrteUserId(String dbdCrteUserId) {
		this.dbdCrteUserId = dbdCrteUserId;
	}
	public String getDbdStaffName() {
		return dbdStaffName;
	}
	public void setDbdStaffName(String dbdStaffName) {
		this.dbdStaffName = dbdStaffName;
	}
	public List<ZbbpEntity> getZbbpEntityList() {
		return zbbpEntityList;
	}

	public void setZbbpEntityList(List<ZbbpEntity> zbbpEntityList) {
		this.zbbpEntityList = zbbpEntityList;
	}
}
