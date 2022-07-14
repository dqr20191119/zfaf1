package com.cesgroup.prison.xxhj.cgsgxx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/*罪犯出工收工信息表*/
@Table(name = "cds_prisoner_work_record")
public class CgsgxxEntity extends StringIDEntity {

	private String pwrCusNumber; 
	private String pwrDprtmntId;             //出工监区
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pwrStartTime;              //出工时间默认填报时时间
	private String pwrPrisonerCount;		 //罪犯人数
	private String pwrLeadPoliceId;          //车间带班领导ID
	private String pwrLeadPoliceName;        //车间带班领导
	private String pwrLocalPoliceId;          //车间现场民警领导ID
	private String pwrLocalPoliceName;        //车间现场民警
	private String pwrFactoryName;           //车间名称
	private String pwrRemark;
	private String pwrWafCount;		         //危安犯人数
	private String pwrXsfCount;		         //刑事犯人数
	private String pwrStatus;
	private String pwrPoliceCount;         //警力
	private String pwrDprtmntName;			//用于首页统计字段

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pwrStartCrteTime;           //记录创建时间 出工时间默认填报时时间
	@Column(updatable = false)
	private String pwrStartCrteUserId;       //出发创建记录民警ID
	@Column(updatable = false)
	private String pwrStartCrteUserName;     //出发创建记录民警名称
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pwrBackTime;               //收工时间
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date  pwrUpdtTime;               //修改时间
	private String pwrUpdtUserId;            //修改民警ID
	private String pwrUpdtUserName;          //(返回）更新记录民警名称
	public String getPwrCusNumber() {
		return pwrCusNumber;
	}
	public void setPwrCusNumber(String pwrCusNumber) {
		this.pwrCusNumber = pwrCusNumber;
	}
	public String getPwrDprtmntId() {
		return pwrDprtmntId;
	}
	public void setPwrDprtmntId(String pwrDprtmntId) {
		this.pwrDprtmntId = pwrDprtmntId;
	}
	public Date getPwrStartTime() {
		return pwrStartTime;
	}
	public void setPwrStartTime(Date pwrStartTime) {
		this.pwrStartTime = pwrStartTime;
	}
	public String getPwrPrisonerCount() {
		return pwrPrisonerCount;
	}
	public void setPwrPrisonerCount(String pwrPrisonerCount) {
		this.pwrPrisonerCount = pwrPrisonerCount;
	}
	public String getPwrLeadPoliceId() {
		return pwrLeadPoliceId;
	}
	public void setPwrLeadPoliceId(String pwrLeadPoliceId) {
		this.pwrLeadPoliceId = pwrLeadPoliceId;
	}
	public String getPwrLeadPoliceName() {
		return pwrLeadPoliceName;
	}
	public void setPwrLeadPoliceName(String pwrLeadPoliceName) {
		this.pwrLeadPoliceName = pwrLeadPoliceName;
	}
	public String getPwrLocalPoliceId() {
		return pwrLocalPoliceId;
	}
	public void setPwrLocalPoliceId(String pwrLocalPoliceId) {
		this.pwrLocalPoliceId = pwrLocalPoliceId;
	}
	public String getPwrLocalPoliceName() {
		return pwrLocalPoliceName;
	}
	public void setPwrLocalPoliceName(String pwrLocalPoliceName) {
		this.pwrLocalPoliceName = pwrLocalPoliceName;
	}
	public String getPwrFactoryName() {
		return pwrFactoryName;
	}
	public void setPwrFactoryName(String pwrFactoryName) {
		this.pwrFactoryName = pwrFactoryName;
	}
	public String getPwrRemark() {
		return pwrRemark;
	}
	public void setPwrRemark(String pwrRemark) {
		this.pwrRemark = pwrRemark;
	}
	public String getPwrWafCount() {
		return pwrWafCount;
	}
	public void setPwrWafCount(String pwrWafCount) {
		this.pwrWafCount = pwrWafCount;
	}
	public String getPwrXsfCount() {
		return pwrXsfCount;
	}
	public void setPwrXsfCount(String pwrXsfCount) {
		this.pwrXsfCount = pwrXsfCount;
	}
	public String getPwrStatus() {
		return pwrStatus;
	}
	public void setPwrStatus(String pwrStatus) {
		this.pwrStatus = pwrStatus;
	}
	public Date getPwrStartCrteTime() {
		return pwrStartCrteTime;
	}
	public void setPwrStartCrteTime(Date pwrStartCrteTime) {
		this.pwrStartCrteTime = pwrStartCrteTime;
	}
	public String getPwrStartCrteUserId() {
		return pwrStartCrteUserId;
	}
	public void setPwrStartCrteUserId(String pwrStartCrteUserId) {
		this.pwrStartCrteUserId = pwrStartCrteUserId;
	}
	public String getPwrStartCrteUserName() {
		return pwrStartCrteUserName;
	}
	public void setPwrStartCrteUserName(String pwrStartCrteUserName) {
		this.pwrStartCrteUserName = pwrStartCrteUserName;
	}
	public Date getPwrBackTime() {
		return pwrBackTime;
	}
	public void setPwrBackTime(Date pwrBackTime) {
		this.pwrBackTime = pwrBackTime;
	}
	public Date getPwrUpdtTime() {
		return pwrUpdtTime;
	}
	public void setPwrUpdtTime(Date pwrUpdtTime) {
		this.pwrUpdtTime = pwrUpdtTime;
	}
	public String getPwrUpdtUserId() {
		return pwrUpdtUserId;
	}
	public void setPwrUpdtUserId(String pwrUpdtUserId) {
		this.pwrUpdtUserId = pwrUpdtUserId;
	}
	public String getPwrUpdtUserName() {
		return pwrUpdtUserName;
	}
	public void setPwrUpdtUserName(String pwrUpdtUserName) {
		this.pwrUpdtUserName = pwrUpdtUserName;
	}
	public String getPwrPoliceCount() {
		return pwrPoliceCount;
	}
	public void setPwrPoliceCount(String pwrPoliceCount) {
		this.pwrPoliceCount = pwrPoliceCount;
	}
	public String getPwrDprtmntName() {
		return pwrDprtmntName;
	}
	public void setPwrDprtmntName(String pwrDprtmntName) {
		this.pwrDprtmntName = pwrDprtmntName;
	}
}