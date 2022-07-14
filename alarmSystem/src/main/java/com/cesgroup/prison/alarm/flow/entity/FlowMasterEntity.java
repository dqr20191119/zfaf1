package com.cesgroup.prison.alarm.flow.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "CDS_HANDLE_FLOW_MASTER")
public class FlowMasterEntity extends StringIDEntity {

	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	// 监狱编号
	@Column(name = "HFM_CUS_NUMBER")
	private String hfmCusNumber;

	// 流程名称
	@Column(name = "HFM_FLOW_NAME")
	private String hfmFlowName;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "HFM_CRTE_TIME")
	private Date hfmCrteTime;

	@Column(name = "HFM_CRTE_USER_ID")
	private String hfmCrteUserId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "HFM_UPDT_TIME")
	private Date hfmUpdtTime;

	@Column(name = "HFM_UPDT_USER_ID")
	private String hfmUpdtUserId;

	// 流程类型，0标准流程 1、定制流程
	@Column(name = "HFM_TYPE_INDC")
	private Short hfmTypeIndc;

	// 显示序号
	@Column(name = "HFM_SHOW_SEQ")
	private Long hfmShowSeq;

	// 级别，1，2，3
	@Column(name = "HFM_ALARM_LEVEL")
	private BigDecimal hfmAlarmLevel;

	// 保存传入
	@Transient
	private String hfmFlowDtls;

	@Transient
	private String updtUserName;

	@Transient
	private String crteUserName;

	// 详情取出
	@Transient
	private List hfmFlowDtlsList;

	public String getHfmCusNumber() {
		return hfmCusNumber;
	}

	public void setHfmCusNumber(String hfmCusNumber) {
		this.hfmCusNumber = hfmCusNumber;
	}

	public String getHfmFlowName() {
		return hfmFlowName;
	}

	public void setHfmFlowName(String hfmFlowName) {
		this.hfmFlowName = hfmFlowName == null ? null : hfmFlowName.trim();
	}

	public Date getHfmCrteTime() {
		return hfmCrteTime;
	}

	public void setHfmCrteTime(Date hfmCrteTime) {
		this.hfmCrteTime = hfmCrteTime;
	}

	public String getHfmCrteUserId() {
		return hfmCrteUserId;
	}

	public void setHfmCrteUserId(String hfmCrteUserId) {
		this.hfmCrteUserId = hfmCrteUserId == null ? null : hfmCrteUserId.trim();
	}

	public Date getHfmUpdtTime() {
		return hfmUpdtTime;
	}

	public void setHfmUpdtTime(Date hfmUpdtTime) {
		this.hfmUpdtTime = hfmUpdtTime;
	}

	public String getHfmUpdtUserId() {
		return hfmUpdtUserId;
	}

	public void setHfmUpdtUserId(String hfmUpdtUserId) {
		this.hfmUpdtUserId = hfmUpdtUserId == null ? null : hfmUpdtUserId.trim();
	}

	public Short getHfmTypeIndc() {
		return hfmTypeIndc;
	}

	public void setHfmTypeIndc(Short hfmTypeIndc) {
		this.hfmTypeIndc = hfmTypeIndc;
	}

	public Long getHfmShowSeq() {
		return hfmShowSeq;
	}

	public void setHfmShowSeq(Long hfmShowSeq) {
		this.hfmShowSeq = hfmShowSeq;
	}

	public BigDecimal getHfmAlarmLevel() {
		return hfmAlarmLevel;
	}

	public void setHfmAlarmLevel(BigDecimal hfmAlarmLevel) {
		this.hfmAlarmLevel = hfmAlarmLevel;
	}

	public String getHfmFlowDtls() {
		return hfmFlowDtls;
	}

	public void setHfmFlowDtls(String hfmFlowDtls) {
		this.hfmFlowDtls = hfmFlowDtls;
	}

	public List getHfmFlowDtlsList() {
		return hfmFlowDtlsList;
	}

	public void setHfmFlowDtlsList(List hfmFlowDtlsList) {
		this.hfmFlowDtlsList = hfmFlowDtlsList;
	}

	public String getUpdtUserName() {
		return updtUserName;
	}

	public void setUpdtUserName(String updtUserName) {
		this.updtUserName = updtUserName;
	}

	public String getCrteUserName() {
		return crteUserName;
	}

	public void setCrteUserName(String crteUserName) {
		this.crteUserName = crteUserName;
	}

}