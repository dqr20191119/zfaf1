package com.cesgroup.prison.xxhj.jndt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "cds_prison_activity_record")
public class JndtEntity extends StringIDEntity {
	
	private String parCusNumber; 
	private String parDprtmntId;             //出工监区
	private String parPoliceId;              //复制民警
	private String parPoliceName;            //负责民警名称
	private String parWafCount;		         //危安犯人数
	private String parXsfCount;		         //刑事犯人数
	private String parPrisonerCount;		 //罪犯人数
	private String parOutCategory;           //外出类别
	private String parOutReason;             //外出细则
	private String parPoliceCount;            //警力
	private String parDprtmntName;			  //首页展示用
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date parStartTime;
	private String parStartReporterId;          //出发报备民警名称
	private String parStartReporterName;     //出发报备民警名称
	private String parRemark;
	private String parStatus;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date parStartCrteTime;           //记录创建时间
	@Column(updatable = false)
	private String parStartCrteUserId;       //出发创建记录民警ID
	@Column(updatable = false)
	private String parStartCrteUserName;     //出发创建记录民警名称
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date parBackTime;               //返回时间
	private String parBackReporterId;         //返回报备民警ID
	private String parBackReporterName;      //返回报备民警名称
	private String parBackRemark;           //返回备注
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date  parUpdtTime;               //修改时间
	private String parUpdtUserId;            //修改民警ID
	private String parUpdtUserName;          //(返回）更新记录民警名称
	private String parLx;					//临时添加演示用  wq 2018-09-26
	
	@Transient
	private String relationId;                 //历史表中外键
	@Transient
	private String pbdLoginName;            
	@Transient
	private String ardOprtnSttsIndc;
	@Transient
	private String pbdTalkNum;
	@Transient
	private String pbdPoliceIdnty;
	@Transient
	private String pbdDrptmntId;
	
	public String getPbdDrptmntId() {
		return pbdDrptmntId;
	}
	public void setPbdDrptmntId(String pbdDrptmntId) {
		this.pbdDrptmntId = pbdDrptmntId;
	}
	public String getPbdLoginName() {
		return pbdLoginName;
	}
	public void setPbdLoginName(String pbdLoginName) {
		this.pbdLoginName = pbdLoginName;
	}
	@Transient
	private String pbdStartCrteName;             //出发记录人
	
	@Transient
	private String pbdBackCrteName;              //返回记录人
	
	@Transient
	private String pbdStartReporter;               //出发报备人  
	
	public String getPbdStartReporter() {
		return pbdStartReporter;
	}
	public void setPbdStartReporter(String pbdStartReporter) {
		this.pbdStartReporter = pbdStartReporter;
	}
	
	public String getParCusNumber() {
		return parCusNumber;
	}
	public void setParCusNumber(String parCusNumber) {
		this.parCusNumber = parCusNumber;
	}
	public String getParDprtmntId() {
		return parDprtmntId;
	}
	public void setParDprtmntId(String parDprtmntId) {
		this.parDprtmntId = parDprtmntId;
	}
	public String getParPoliceId() {
		return parPoliceId;
	}
	public void setParPoliceId(String parPoliceId) {
		this.parPoliceId = parPoliceId;
	}
	public String getParPrisonerCount() {
		return parPrisonerCount;
	}
	public void setParPrisonerCount(String parPrisonerCount) {
		this.parPrisonerCount = parPrisonerCount;
	}
	public String getParOutCategory() {
		return parOutCategory;
	}
	public void setParOutCategory(String parOutCategory) {
		this.parOutCategory = parOutCategory;
	}
	public String getParOutReason() {
		return parOutReason;
	}
	public void setParOutReason(String parOutReason) {
		this.parOutReason = parOutReason;
	}
	public String getParStartReporterId() {
		return parStartReporterId;
	}
	public void setParStartReporterId(String parStartReporterId) {
		this.parStartReporterId = parStartReporterId;
	}
	public Date getParStartCrteTime() {
		return parStartCrteTime;
	}
	public void setParStartCrteTime(Date parStartCrteTime) {
		this.parStartCrteTime = parStartCrteTime;
	}
	public String getParStartCrteUserId() {
		return parStartCrteUserId;
	}
	public void setParStartCrteUserId(String parStartCrteUserId) {
		this.parStartCrteUserId = parStartCrteUserId;
	}
	public String getParBackReporterId() {
		return parBackReporterId;
	}
	public void setParBackReporterId(String parBackReporterId) {
		this.parBackReporterId = parBackReporterId;
	}
	public String getParBackRemark() {
		return parBackRemark;
	}
	public void setParBackRemark(String parBackRemark) {
		this.parBackRemark = parBackRemark;
	}
	public String getParRemark() {
		return parRemark;
	}
	public void setParRemark(String parRemark) {
		this.parRemark = parRemark;
	}
	public Date getParUpdtTime() {
		return parUpdtTime;
	}
	public void setParUpdtTime(Date parUpdtTime) {
		this.parUpdtTime = parUpdtTime;
	}
	public String getParUpdtUserId() {
		return parUpdtUserId;
	}
	public void setParUpdtUserId(String parUpdtUserId) {
		this.parUpdtUserId = parUpdtUserId;
	}
	public Date getParStartTime() {
		return parStartTime;
	}
	public void setParStartTime(Date parStartTime) {
		this.parStartTime = parStartTime;
	}
	public Date getParBackTime() {
		return parBackTime;
	}
	public void setParBackTime(Date parBackTime) {
		this.parBackTime = parBackTime;
	}
	public String getPbdStartCrteName() {
		return pbdStartCrteName;
	}
	public void setPbdStartCrteName(String pbdStartCrteName) {
		this.pbdStartCrteName = pbdStartCrteName;
	}
	public String getPbdBackCrteName() {
		return pbdBackCrteName;
	}
	public void setPbdBackCrteName(String pbdBackCrteName) {
		this.pbdBackCrteName = pbdBackCrteName;
	}
	public String getParStartReporterName() {
		return parStartReporterName;
	}
	public void setParStartReporterName(String parStartReporterName) {
		this.parStartReporterName = parStartReporterName;
	}
	public String getParPoliceName() {
		return parPoliceName;
	}
	public void setParPoliceName(String parPoliceName) {
		this.parPoliceName = parPoliceName;
	}
	public String getParStartCrteUserName() {
		return parStartCrteUserName;
	}
	public void setParStartCrteUserName(String parStartCrteUserName) {
		this.parStartCrteUserName = parStartCrteUserName;
	}
	public String getParBackReporterName() {
		return parBackReporterName;
	}
	public void setParBackReporterName(String parBackReporterName) {
		this.parBackReporterName = parBackReporterName;
	}
	public String getParUpdtUserName() {
		return parUpdtUserName;
	}
	public void setParUpdtUserName(String parUpdtUserName) {
		this.parUpdtUserName = parUpdtUserName;
	}
	public String getArdOprtnSttsIndc() {
		return ardOprtnSttsIndc;
	}
	public void setArdOprtnSttsIndc(String ardOprtnSttsIndc) {
		this.ardOprtnSttsIndc = ardOprtnSttsIndc;
	}
	public String getPbdTalkNum() {
		return pbdTalkNum;
	}
	public void setPbdTalkNum(String pbdTalkNum) {
		this.pbdTalkNum = pbdTalkNum;
	}
	public String getPbdPoliceIdnty() {
		return pbdPoliceIdnty;
	}
	public void setPbdPoliceIdnty(String pbdPoliceIdnty) {
		this.pbdPoliceIdnty = pbdPoliceIdnty;
	}
	public String getParStatus() {
		return parStatus;
	}
	public void setParStatus(String parStatus) {
		this.parStatus = parStatus;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getParPoliceCount() {
		return parPoliceCount;
	}
	public void setParPoliceCount(String parPoliceCount) {
		this.parPoliceCount = parPoliceCount;
	}
	public String getParWafCount() {
		return parWafCount;
	}
	public void setParWafCount(String parWafCount) {
		this.parWafCount = parWafCount;
	}
	public String getParXsfCount() {
		return parXsfCount;
	}
	public void setParXsfCount(String parXsfCount) {
		this.parXsfCount = parXsfCount;
	}
	public String getParDprtmntName() {
		return parDprtmntName;
	}
	public void setParDprtmntName(String parDprtmntName) {
		this.parDprtmntName = parDprtmntName;
	}

	//临时添加演示用  wq 2018-09-26
	public String getParLx() {
		return parLx;
	}

	public void setParLx(String parLx) {
		this.parLx = parLx;
	}
}