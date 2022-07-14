package com.cesgroup.prison.roamRecord.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 三维巡视记录
 * 
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "CDS_ROAM_RECORD")
public class RoamRecord extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 监狱编号
	 */
	private String cusNumber;
	/**
	 * 三维巡视路线ID
	 */
    private String roamPathId;
    /**
     * 三维巡视路线名称
     */
    private String roamPathName;
    /**
     * 大屏预案ID
     */
    private String screenPlanId;
    /**
     * 大屏预案名称
     */
    private String screenPlanName;
    /**
     * 巡视人ID
     */
    private String roamUserId;
    /**
     * 巡视人名称
     */
    private String roamUserName;
    /**
     * 巡视时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date roamTime;
    /**
     * 巡视部门ID
     */
    private String roamDeptId;
    /**
     * 巡视部门名称
     */
    private String roamDeptName;
    
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getRoamPathId() {
		return roamPathId;
	}
	public void setRoamPathId(String roamPathId) {
		this.roamPathId = roamPathId;
	}
	public String getRoamPathName() {
		return roamPathName;
	}
	public void setRoamPathName(String roamPathName) {
		this.roamPathName = roamPathName;
	}
	public String getScreenPlanId() {
		return screenPlanId;
	}
	public void setScreenPlanId(String screenPlanId) {
		this.screenPlanId = screenPlanId;
	}
	public String getScreenPlanName() {
		return screenPlanName;
	}
	public void setScreenPlanName(String screenPlanName) {
		this.screenPlanName = screenPlanName;
	}
	public String getRoamUserId() {
		return roamUserId;
	}
	public void setRoamUserId(String roamUserId) {
		this.roamUserId = roamUserId;
	}
	public String getRoamUserName() {
		return roamUserName;
	}
	public void setRoamUserName(String roamUserName) {
		this.roamUserName = roamUserName;
	}
	public Date getRoamTime() {
		return roamTime;
	}
	public void setRoamTime(Date roamTime) {
		this.roamTime = roamTime;
	}
	public String getRoamDeptId() {
		return roamDeptId;
	}
	public void setRoamDeptId(String roamDeptId) {
		this.roamDeptId = roamDeptId;
	}
	public String getRoamDeptName() {
		return roamDeptName;
	}
	public void setRoamDeptName(String roamDeptName) {
		this.roamDeptName = roamDeptName;
	}
}