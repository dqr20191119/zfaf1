package com.cesgroup.prison.door.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "cds_door_record_dtls")
public class DoorCardEntity extends StringIDEntity {
	
	private String drdCusNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date drdBsnsDate;
 	private String drdDoorCtrlIdnty;
	private String drdDoorIdnty;
 	private String drdCardIdnty;
	private String drdInOutIndc;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date drdBrushCardTime;
	private String drdStatusIndc;
	/**
	 * 人员类型(1-民警，2-犯人，3-外来人员，4-职工)
	 */
	private String drdPeopleTypeIndc;
	private String drdRemark;
	
	@Column(updatable = false)
	private Date drdCrteTime;
	private String drdCrteUserId;
	//add by zk
	private String drdPeopleIdnty;
	private String drdPeopleName;
	//add by lincoln.cheng
	/**
	 * 门禁名称
	 */
	private String drdDoorName;
	/**
	 * 门禁所属监舍ID
	 */
	private String drdDoorRoomId;
	/**
	 * 门禁所属监区编号
	 */
	private String drdDoorDeptId;
	/**
	 * 门禁所属监区名称
	 */
	private String drdDoorDeptName;
	/**
	 * 罪犯所属监区名称
	 */
	private String drdPeopleDeptName;
	
	public String getDrdCusNumber() {
		return drdCusNumber;
	}
	public void setDrdCusNumber(String drdCusNumber) {
		this.drdCusNumber = drdCusNumber;
	}
	public Date getDrdBsnsDate() {
		return drdBsnsDate;
	}
	public void setDrdBsnsDate(Date drdBsnsDate) {
		this.drdBsnsDate = drdBsnsDate;
	}
	public String getDrdDoorCtrlIdnty() {
		return drdDoorCtrlIdnty;
	}
	public void setDrdDoorCtrlIdnty(String drdDoorCtrlIdnty) {
		this.drdDoorCtrlIdnty = drdDoorCtrlIdnty;
	}
	public String getDrdDoorIdnty() {
		return drdDoorIdnty;
	}
	public void setDrdDoorIdnty(String drdDoorIdnty) {
		this.drdDoorIdnty = drdDoorIdnty;
	}
	public String getDrdCardIdnty() {
		return drdCardIdnty;
	}
	public void setDrdCardIdnty(String drdCardIdnty) {
		this.drdCardIdnty = drdCardIdnty;
	}
	public String getDrdInOutIndc() {
		return drdInOutIndc;
	}
	public void setDrdInOutIndc(String drdInOutIndc) {
		this.drdInOutIndc = drdInOutIndc;
	}
	public Date getDrdBrushCardTime() {
		return drdBrushCardTime;
	}
	public void setDrdBrushCardTime(Date drdBrushCardTime) {
		this.drdBrushCardTime = drdBrushCardTime;
	}
	public String getDrdStatusIndc() {
		return drdStatusIndc;
	}
	public void setDrdStatusIndc(String drdStatusIndc) {
		this.drdStatusIndc = drdStatusIndc;
	}
	public String getDrdPeopleTypeIndc() {
		return drdPeopleTypeIndc;
	}
	public void setDrdPeopleTypeIndc(String drdPeopleTypeIndc) {
		this.drdPeopleTypeIndc = drdPeopleTypeIndc;
	}
	public String getDrdRemark() {
		return drdRemark;
	}
	public void setDrdRemark(String drdRemark) {
		this.drdRemark = drdRemark;
	}
	public Date getDrdCrteTime() {
		return drdCrteTime;
	}
	public void setDrdCrteTime(Date drdCrteTime) {
		this.drdCrteTime = drdCrteTime;
	}
	public String getDrdCrteUserId() {
		return drdCrteUserId;
	}
	public void setDrdCrteUserId(String drdCrteUserId) {
		this.drdCrteUserId = drdCrteUserId;
	}
	public String getDrdPeopleIdnty() {
		return drdPeopleIdnty;
	}
	public void setDrdPeopleIdnty(String drdPeopleIdnty) {
		this.drdPeopleIdnty = drdPeopleIdnty;
	}
	public String getDrdPeopleName() {
		return drdPeopleName;
	}
	public void setDrdPeopleName(String drdPeopleName) {
		this.drdPeopleName = drdPeopleName;
	}
	public String getDrdDoorName() {
		return drdDoorName;
	}
	public void setDrdDoorName(String drdDoorName) {
		this.drdDoorName = drdDoorName;
	}
	public String getDrdDoorRoomId() {
		return drdDoorRoomId;
	}
	public void setDrdDoorRoomId(String drdDoorRoomId) {
		this.drdDoorRoomId = drdDoorRoomId;
	}
	public String getDrdDoorDeptId() {
		return drdDoorDeptId;
	}
	public void setDrdDoorDeptId(String drdDoorDeptId) {
		this.drdDoorDeptId = drdDoorDeptId;
	}
	public String getDrdDoorDeptName() {
		return drdDoorDeptName;
	}
	public void setDrdDoorDeptName(String drdDoorDeptName) {
		this.drdDoorDeptName = drdDoorDeptName;
	}
	public String getDrdPeopleDeptName() {
		return drdPeopleDeptName;
	}
	public void setDrdPeopleDeptName(String drdPeopleDeptName) {
		this.drdPeopleDeptName = drdPeopleDeptName;
	}
}