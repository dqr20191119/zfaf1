package com.cesgroup.prison.zfxx.zfdm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 湖南罪犯点名详情
 *
 */
@Entity
@Table(name = "T_ZFDM_DTLS")
public class ZfdmEntity extends StringIDEntity {
	private static final long serialVersionUID = 1L;
	private String cusNumber;
	/**
	 * 创建时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date crteTime;
	/**
	 * 人员编号
	 */
    private String prisonerBH;
    /**
     * 人员名称
     */
    private String prisonerName;
    /**
     * 房间编号
     */
    private String cellNo;
    /**
     * 房间名称
     */
    private String cellName;
    /**
     * 点名状态(0 为未点名或点名失败, 1 为点名成功)
     */
    private String rollcallResult;
    /**
     * 所在分组
     */
    private String adminGroup;
    /**
     * 监区 Id
     */
    private String deptId;
    /**
     * 监区名称
     */
    private String deptName;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date callTime;
	
    
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public Date getCrteTime() {
		return crteTime;
	}
	public void setCrteTime(Date crteTime) {
		this.crteTime = crteTime;
	}
	public String getPrisonerBH() {
		return prisonerBH;
	}
	public void setPrisonerBH(String prisonerBH) {
		this.prisonerBH = prisonerBH;
	}
	public String getPrisonerName() {
		return prisonerName;
	}
	public void setPrisonerName(String prisonerName) {
		this.prisonerName = prisonerName;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getRollcallResult() {
		return rollcallResult;
	}
	public void setRollcallResult(String rollcallResult) {
		this.rollcallResult = rollcallResult;
	}
	public String getAdminGroup() {
		return adminGroup;
	}
	public void setAdminGroup(String adminGroup) {
		this.adminGroup = adminGroup;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
}