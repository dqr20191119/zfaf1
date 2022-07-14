package com.cesgroup.prison.deviceMaintain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_DEVICE_MAINTAIN")
public class DeviceMaintainEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 4300172781856638061L;

	private String dmaCusNumber;

	private String dmaDprmntIdnty;

	private String dmaDprmntName;

	private String dmaFaultSubmitterId;

	private String dmaFaultSubmitter;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmaFaultSubmitTime;

	private String dmaSubmitType;

	private String dmaDeviceType;

	private String dmaDeviceIdnty;

	private String dmaDeviceName;

	private String dmaFaultType;

	private String dmaFaultContent;

	private String dmaFaultAddrs;

	private String dmaFaultDesc;

	private String dmaMaintainTerm;

	private String dmaMaintainDesc;

	private String dmaMaintainPersonId;

	private String dmaMaintainPerson;

	private String dmaMaintainResult;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmaMaintainStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmaMaintainEndTime;

	private String dmaMaintainTimeoutIndc;

	private String dmaDprtmntLeaderId;

	private String dmaDprtmntLeader;

	private String dmaDprtmntIdea;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmaSureTime;

	private String dmaRemindPeopleId;

	private String dmaRemindPeople;

	private String dmaRemindStts;

	private String dmaRemindType;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmaRemindTime;

	private String dmaSttsIndc;

	private String dmaRemark;

	private String dmaCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmaCrteTime;

	private String dmaUpdtUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmaUpdtTime;

	/**
	* @Fields actionType : 事件类型 ： handle 处理、  feedBack 反馈、  oversee 监督
	*/
	@Transient
	private String actionType;

	/**
	* @Fields startTime : 开始填报时间，用于查询
	*/
	@Transient
	private String startTime;

	/**
	* @Fields endTime : 结束填报时间 ，用于查询
	*/
	@Transient
	private String endTime;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDmaCusNumber() {
		return dmaCusNumber;
	}

	public void setDmaCusNumber(String dmaCusNumber) {
		this.dmaCusNumber = dmaCusNumber;
	}

	public String getDmaDprmntIdnty() {
		return dmaDprmntIdnty;
	}

	public void setDmaDprmntIdnty(String dmaDprmntIdnty) {
		this.dmaDprmntIdnty = dmaDprmntIdnty;
	}

	public String getDmaDprmntName() {
		return dmaDprmntName;
	}

	public void setDmaDprmntName(String dmaDprmntName) {
		this.dmaDprmntName = dmaDprmntName;
	}

	public String getDmaFaultSubmitterId() {
		return dmaFaultSubmitterId;
	}

	public void setDmaFaultSubmitterId(String dmaFaultSubmitterId) {
		this.dmaFaultSubmitterId = dmaFaultSubmitterId;
	}

	public String getDmaFaultSubmitter() {
		return dmaFaultSubmitter;
	}

	public void setDmaFaultSubmitter(String dmaFaultSubmitter) {
		this.dmaFaultSubmitter = dmaFaultSubmitter;
	}

	public Date getDmaFaultSubmitTime() {
		return dmaFaultSubmitTime;
	}

	public void setDmaFaultSubmitTime(Date dmaFaultSubmitTime) {
		this.dmaFaultSubmitTime = dmaFaultSubmitTime;
	}

	public String getDmaSubmitType() {
		return dmaSubmitType;
	}

	public void setDmaSubmitType(String dmaSubmitType) {
		this.dmaSubmitType = dmaSubmitType;
	}

	public String getDmaDeviceType() {
		return dmaDeviceType;
	}

	public void setDmaDeviceType(String dmaDeviceType) {
		this.dmaDeviceType = dmaDeviceType;
	}

	public String getDmaDeviceIdnty() {
		return dmaDeviceIdnty;
	}

	public void setDmaDeviceIdnty(String dmaDeviceIdnty) {
		this.dmaDeviceIdnty = dmaDeviceIdnty;
	}

	public String getDmaDeviceName() {
		return dmaDeviceName;
	}

	public void setDmaDeviceName(String dmaDeviceName) {
		this.dmaDeviceName = dmaDeviceName;
	}

	public String getDmaFaultType() {
		return dmaFaultType;
	}

	public void setDmaFaultType(String dmaFaultType) {
		this.dmaFaultType = dmaFaultType;
	}

	public String getDmaFaultContent() {
		return dmaFaultContent;
	}

	public void setDmaFaultContent(String dmaFaultContent) {
		this.dmaFaultContent = dmaFaultContent;
	}

	public String getDmaFaultAddrs() {
		return dmaFaultAddrs;
	}

	public void setDmaFaultAddrs(String dmaFaultAddrs) {
		this.dmaFaultAddrs = dmaFaultAddrs;
	}

	public String getDmaFaultDesc() {
		return dmaFaultDesc;
	}

	public void setDmaFaultDesc(String dmaFaultDesc) {
		this.dmaFaultDesc = dmaFaultDesc;
	}

	public String getDmaMaintainTerm() {
		return dmaMaintainTerm;
	}

	public void setDmaMaintainTerm(String dmaMaintainTerm) {
		this.dmaMaintainTerm = dmaMaintainTerm;
	}

	public String getDmaMaintainDesc() {
		return dmaMaintainDesc;
	}

	public void setDmaMaintainDesc(String dmaMaintainDesc) {
		this.dmaMaintainDesc = dmaMaintainDesc;
	}

	public String getDmaMaintainPersonId() {
		return dmaMaintainPersonId;
	}

	public void setDmaMaintainPersonId(String dmaMaintainPersonId) {
		this.dmaMaintainPersonId = dmaMaintainPersonId;
	}

	public String getDmaMaintainPerson() {
		return dmaMaintainPerson;
	}

	public void setDmaMaintainPerson(String dmaMaintainPerson) {
		this.dmaMaintainPerson = dmaMaintainPerson;
	}

	public String getDmaMaintainResult() {
		return dmaMaintainResult;
	}

	public void setDmaMaintainResult(String dmaMaintainResult) {
		this.dmaMaintainResult = dmaMaintainResult;
	}

	public Date getDmaMaintainStartTime() {
		return dmaMaintainStartTime;
	}

	public void setDmaMaintainStartTime(Date dmaMaintainStartTime) {
		this.dmaMaintainStartTime = dmaMaintainStartTime;
	}

	public Date getDmaMaintainEndTime() {
		return dmaMaintainEndTime;
	}

	public void setDmaMaintainEndTime(Date dmaMaintainEndTime) {
		this.dmaMaintainEndTime = dmaMaintainEndTime;
	}

	public String getDmaMaintainTimeoutIndc() {
		return dmaMaintainTimeoutIndc;
	}

	public void setDmaMaintainTimeoutIndc(String dmaMaintainTimeoutIndc) {
		this.dmaMaintainTimeoutIndc = dmaMaintainTimeoutIndc;
	}

	public String getDmaDprtmntLeaderId() {
		return dmaDprtmntLeaderId;
	}

	public void setDmaDprtmntLeaderId(String dmaDprtmntLeaderId) {
		this.dmaDprtmntLeaderId = dmaDprtmntLeaderId;
	}

	public String getDmaDprtmntLeader() {
		return dmaDprtmntLeader;
	}

	public void setDmaDprtmntLeader(String dmaDprtmntLeader) {
		this.dmaDprtmntLeader = dmaDprtmntLeader;
	}

	public String getDmaDprtmntIdea() {
		return dmaDprtmntIdea;
	}

	public void setDmaDprtmntIdea(String dmaDprtmntIdea) {
		this.dmaDprtmntIdea = dmaDprtmntIdea;
	}

	public Date getDmaSureTime() {
		return dmaSureTime;
	}

	public void setDmaSureTime(Date dmaSureTime) {
		this.dmaSureTime = dmaSureTime;
	}

	public String getDmaRemindPeopleId() {
		return dmaRemindPeopleId;
	}

	public void setDmaRemindPeopleId(String dmaRemindPeopleId) {
		this.dmaRemindPeopleId = dmaRemindPeopleId;
	}

	public String getDmaRemindPeople() {
		return dmaRemindPeople;
	}

	public void setDmaRemindPeople(String dmaRemindPeople) {
		this.dmaRemindPeople = dmaRemindPeople;
	}

	public String getDmaRemindStts() {
		return dmaRemindStts;
	}

	public void setDmaRemindStts(String dmaRemindStts) {
		this.dmaRemindStts = dmaRemindStts;
	}

	public String getDmaRemindType() {
		return dmaRemindType;
	}

	public void setDmaRemindType(String dmaRemindType) {
		this.dmaRemindType = dmaRemindType;
	}

	public Date getDmaRemindTime() {
		return dmaRemindTime;
	}

	public void setDmaRemindTime(Date dmaRemindTime) {
		this.dmaRemindTime = dmaRemindTime;
	}

	public String getDmaSttsIndc() {
		return dmaSttsIndc;
	}

	public void setDmaSttsIndc(String dmaSttsIndc) {
		this.dmaSttsIndc = dmaSttsIndc;
	}

	public String getDmaRemark() {
		return dmaRemark;
	}

	public void setDmaRemark(String dmaRemark) {
		this.dmaRemark = dmaRemark;
	}

	public String getDmaCrteUserId() {
		return dmaCrteUserId;
	}

	public void setDmaCrteUserId(String dmaCrteUserId) {
		this.dmaCrteUserId = dmaCrteUserId;
	}

	public Date getDmaCrteTime() {
		return dmaCrteTime;
	}

	public void setDmaCrteTime(Date dmaCrteTime) {
		this.dmaCrteTime = dmaCrteTime;
	}

	public String getDmaUpdtUserId() {
		return dmaUpdtUserId;
	}

	public void setDmaUpdtUserId(String dmaUpdtUserId) {
		this.dmaUpdtUserId = dmaUpdtUserId;
	}

	public Date getDmaUpdtTime() {
		return dmaUpdtTime;
	}

	public void setDmaUpdtTime(Date dmaUpdtTime) {
		this.dmaUpdtTime = dmaUpdtTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
