package com.cesgroup.prison.xxhj.jhrc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @projectName：prison
 * @ClassName：JhrcEntity @Description： 计划日程
 * @author：Wang.xin
 * @date：2018年09月05日 @version
 */
@Entity
@Table(name = "CDS_PLAN_SCHEDULE")
public class JhrcEntity extends StringIDEntity {

	private static final long serialVersionUID = 1L;

	private String cpsCusNumber;

	private String cpsDrpmntId;

	private String cpsDrpmntName;

	private String cpsPlanDetail;

	private String cpsScheduleTime;
	
	private String cpsScheduleEndTime;

	private String cpsStartDate;

	private String cpsEndDate;
	
	private String tzsb;

	@Column(updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cpsCrteTime;

	@Column(updatable = false)
	private String cpsCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cpsUpdtTime;

	private String cpsUpdtUserId;

	private String cpsLx;
	
	private String tzsj; //巡更通知时间
	
	private String tznr; //巡更通知内容

	public String getCpsCusNumber() {
		return cpsCusNumber;
	}

	public void setCpsCusNumber(String cpsCusNumber) {
		this.cpsCusNumber = cpsCusNumber;
	}

	public String getCpsDrpmntId() {
		return cpsDrpmntId;
	}

	public void setCpsDrpmntId(String cpsDrpmntId) {
		this.cpsDrpmntId = cpsDrpmntId;
	}

	public String getCpsDrpmntName() {
		return cpsDrpmntName;
	}

	public void setCpsDrpmntName(String cpsDrpmntName) {
		this.cpsDrpmntName = cpsDrpmntName;
	}

	public String getCpsPlanDetail() {
		return cpsPlanDetail;
	}

	public void setCpsPlanDetail(String cpsPlanDetail) {
		this.cpsPlanDetail = cpsPlanDetail;
	}

	public String getCpsScheduleTime() {
		return cpsScheduleTime;
	}

	public void setCpsScheduleTime(String cpsScheduleTime) {
		this.cpsScheduleTime = cpsScheduleTime;
	}

	public String getCpsScheduleEndTime() {
		return cpsScheduleEndTime;
	}

	public void setCpsScheduleEndTime(String cpsScheduleEndTime) {
		this.cpsScheduleEndTime = cpsScheduleEndTime;
	}

	public String getCpsStartDate() {
		return cpsStartDate;
	}

	public void setCpsStartDate(String cpsStartDate) {
		this.cpsStartDate = cpsStartDate;
	}

	public String getCpsEndDate() {
		return cpsEndDate;
	}

	public void setCpsEndDate(String cpsEndDate) {
		this.cpsEndDate = cpsEndDate;
	}

	public Date getCpsCrteTime() {
		return cpsCrteTime;
	}

	public void setCpsCrteTime(Date cpsCrteTime) {
		this.cpsCrteTime = cpsCrteTime;
	}

	public String getCpsCrteUserId() {
		return cpsCrteUserId;
	}

	public void setCpsCrteUserId(String cpsCrteUserId) {
		this.cpsCrteUserId = cpsCrteUserId;
	}

	public Date getCpsUpdtTime() {
		return cpsUpdtTime;
	}

	public void setCpsUpdtTime(Date cpsUpdtTime) {
		this.cpsUpdtTime = cpsUpdtTime;
	}

	public String getCpsUpdtUserId() {
		return cpsUpdtUserId;
	}

	public void setCpsUpdtUserId(String cpsUpdtUserId) {
		this.cpsUpdtUserId = cpsUpdtUserId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCpsLx() {
		return cpsLx;
	}

	public void setCpsLx(String cpsLx) {
		this.cpsLx = cpsLx;
	}

	public String getTzsj() {
		return tzsj;
	}

	public void setTzsj(String tzsj) {
		this.tzsj = tzsj;
	}

	public String getTznr() {
		return tznr;
	}

	public void setTznr(String tznr) {
		this.tznr = tznr;
	}

	public String getTzsb() {
		return tzsb;
	}

	public void setTzsb(String tzsb) {
		this.tzsb = tzsb;
	}
	
	
}
