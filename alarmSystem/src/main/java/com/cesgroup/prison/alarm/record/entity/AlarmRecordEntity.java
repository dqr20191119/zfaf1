package com.cesgroup.prison.alarm.record.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：alarmSystem   
* @ClassName：AlarmRecordEntity   
* @Description：   报警记录
* @author：Tao.xu   
* @date：2018年1月9日 下午1:02:06   
* @version        
*/
@Entity
@Table(name = "CDS_ALERT_RECORD_DTLS")
public class AlarmRecordEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String ardCusNumber;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardBsnsDate;

	private String ardAlertorIdnty;

	private String ardAlarmPoliceId;

	private String ardAlarmPolice;

	private String ardAlertLevelIndc;

	private String ardTypeIndc;

	private String ardAlarmTypeIndc;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardAlertTime;

	private String ardEventId;

	private String ardEventType;

	private String ardAlertSttsIndc;

	private String ardReceiveAlarmPoliceId;

	private String ardReceiveAlarmPolice;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardReceiveTime;

	private String ardLocalCase;

	private String ardOprtrId;

	private String ardOprtr;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardOprtnTime;

	private String ardOprtnDesc;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardOprtnFinishTime;

	private String ardFinishSurePoliceId;

	private String ardFinishSurePolice;

	private String ardOprtnSttsIndc;

	/**
	* @Fields ardOprtnResult : 处置结果 
	*/
	private String ardOprtnResult;

	/**
	* @Fields ardReceiveStts : 接警状态  0 未接警 1接警 
	*/
	private String ardReceiveStts;

	private String ardRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardCrteTime;

	private String ardCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardUpdtTime;

	private String ardUpdtUserId;
	
	private String ardFileStts;

	/**
	* @Fields alertFiles : 报警证据信息
	*/
	@Transient
	private List<AffixEntity> alertFiles = new ArrayList<AffixEntity>();

	
	public String getArdFileStts() {
		return ardFileStts;
	}

	public void setArdFileStts(String ardFileStts) {
		this.ardFileStts = ardFileStts;
	}

	public String getArdCusNumber() {
		return ardCusNumber;
	}

	public void setArdCusNumber(String ardCusNumber) {
		this.ardCusNumber = ardCusNumber;
	}

	public Date getArdBsnsDate() {
		return ardBsnsDate;
	}

	public void setArdBsnsDate(Date ardBsnsDate) {
		this.ardBsnsDate = ardBsnsDate;
	}

	public String getArdAlertorIdnty() {
		return ardAlertorIdnty;
	}

	public void setArdAlertorIdnty(String ardAlertorIdnty) {
		this.ardAlertorIdnty = ardAlertorIdnty;
	}

	public String getArdAlarmPoliceId() {
		return ardAlarmPoliceId;
	}

	public void setArdAlarmPoliceId(String ardAlarmPoliceId) {
		this.ardAlarmPoliceId = ardAlarmPoliceId;
	}

	public String getArdAlarmPolice() {
		return ardAlarmPolice;
	}

	public void setArdAlarmPolice(String ardAlarmPolice) {
		this.ardAlarmPolice = ardAlarmPolice;
	}

	public String getArdAlertLevelIndc() {
		return ardAlertLevelIndc;
	}

	public void setArdAlertLevelIndc(String ardAlertLevelIndc) {
		this.ardAlertLevelIndc = ardAlertLevelIndc;
	}

	public String getArdTypeIndc() {
		return ardTypeIndc;
	}

	public void setArdTypeIndc(String ardTypeIndc) {
		this.ardTypeIndc = ardTypeIndc;
	}

	public String getArdAlarmTypeIndc() {
		return ardAlarmTypeIndc;
	}

	public void setArdAlarmTypeIndc(String ardAlarmTypeIndc) {
		this.ardAlarmTypeIndc = ardAlarmTypeIndc;
	}

	public Date getArdAlertTime() {
		return ardAlertTime;
	}

	public void setArdAlertTime(Date ardAlertTime) {
		this.ardAlertTime = ardAlertTime;
	}

	public String getArdEventId() {
		return ardEventId;
	}

	public void setArdEventId(String ardEventId) {
		this.ardEventId = ardEventId;
	}

	public String getArdEventType() {
		return ardEventType;
	}

	public void setArdEventType(String ardEventType) {
		this.ardEventType = ardEventType;
	}

	public String getArdAlertSttsIndc() {
		return ardAlertSttsIndc;
	}

	public void setArdAlertSttsIndc(String ardAlertSttsIndc) {
		this.ardAlertSttsIndc = ardAlertSttsIndc;
	}

	public String getArdReceiveAlarmPoliceId() {
		return ardReceiveAlarmPoliceId;
	}

	public void setArdReceiveAlarmPoliceId(String ardReceiveAlarmPoliceId) {
		this.ardReceiveAlarmPoliceId = ardReceiveAlarmPoliceId;
	}

	public String getArdReceiveAlarmPolice() {
		return ardReceiveAlarmPolice;
	}

	public void setArdReceiveAlarmPolice(String ardReceiveAlarmPolice) {
		this.ardReceiveAlarmPolice = ardReceiveAlarmPolice;
	}

	public Date getArdReceiveTime() {
		return ardReceiveTime;
	}

	public void setArdReceiveTime(Date ardReceiveTime) {
		this.ardReceiveTime = ardReceiveTime;
	}

	public String getArdLocalCase() {
		return ardLocalCase;
	}

	public void setArdLocalCase(String ardLocalCase) {
		this.ardLocalCase = ardLocalCase;
	}

	public String getArdOprtrId() {
		return ardOprtrId;
	}

	public void setArdOprtrId(String ardOprtrId) {
		this.ardOprtrId = ardOprtrId;
	}

	public String getArdOprtr() {
		return ardOprtr;
	}

	public void setArdOprtr(String ardOprtr) {
		this.ardOprtr = ardOprtr;
	}

	public Date getArdOprtnTime() {
		return ardOprtnTime;
	}

	public void setArdOprtnTime(Date ardOprtnTime) {
		this.ardOprtnTime = ardOprtnTime;
	}

	public String getArdOprtnDesc() {
		return ardOprtnDesc;
	}

	public void setArdOprtnDesc(String ardOprtnDesc) {
		this.ardOprtnDesc = ardOprtnDesc;
	}

	public Date getArdOprtnFinishTime() {
		return ardOprtnFinishTime;
	}

	public void setArdOprtnFinishTime(Date ardOprtnFinishTime) {
		this.ardOprtnFinishTime = ardOprtnFinishTime;
	}

	public String getArdFinishSurePoliceId() {
		return ardFinishSurePoliceId;
	}

	public void setArdFinishSurePoliceId(String ardFinishSurePoliceId) {
		this.ardFinishSurePoliceId = ardFinishSurePoliceId;
	}

	public String getArdFinishSurePolice() {
		return ardFinishSurePolice;
	}

	public void setArdFinishSurePolice(String ardFinishSurePolice) {
		this.ardFinishSurePolice = ardFinishSurePolice;
	}

	public String getArdOprtnSttsIndc() {
		return ardOprtnSttsIndc;
	}

	public void setArdOprtnSttsIndc(String ardOprtnSttsIndc) {
		this.ardOprtnSttsIndc = ardOprtnSttsIndc;
	}

	public String getArdOprtnResult() {
		return ardOprtnResult;
	}

	public void setArdOprtnResult(String ardOprtnResult) {
		this.ardOprtnResult = ardOprtnResult;
	}

	public String getArdReceiveStts() {
		return ardReceiveStts;
	}

	public void setArdReceiveStts(String ardReceiveStts) {
		this.ardReceiveStts = ardReceiveStts;
	}

	public String getArdRemark() {
		return ardRemark;
	}

	public void setArdRemark(String ardRemark) {
		this.ardRemark = ardRemark;
	}

	public Date getArdCrteTime() {
		return ardCrteTime;
	}

	public void setArdCrteTime(Date ardCrteTime) {
		this.ardCrteTime = ardCrteTime;
	}

	public String getArdCrteUserId() {
		return ardCrteUserId;
	}

	public void setArdCrteUserId(String ardCrteUserId) {
		this.ardCrteUserId = ardCrteUserId;
	}

	public Date getArdUpdtTime() {
		return ardUpdtTime;
	}

	public void setArdUpdtTime(Date ardUpdtTime) {
		this.ardUpdtTime = ardUpdtTime;
	}

	public String getArdUpdtUserId() {
		return ardUpdtUserId;
	}

	public void setArdUpdtUserId(String ardUpdtUserId) {
		this.ardUpdtUserId = ardUpdtUserId;
	}

	public List<AffixEntity> getAlertFiles() {
		return alertFiles;
	}

	public void setAlertFiles(List<AffixEntity> alertFiles) {
		this.alertFiles = alertFiles;
	}

}
