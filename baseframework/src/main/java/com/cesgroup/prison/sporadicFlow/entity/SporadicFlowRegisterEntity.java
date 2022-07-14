package com.cesgroup.prison.sporadicFlow.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：SporadicFlowRegisterEntity   
* @Description：   罪犯零星活动发起登记表
* @author：Tao.xu   
* @date：2017年12月7日 下午7:04:20   
* @version        
*/
@Entity
@Table(name = "CDS_SPORADIC_FLOW_REGISTER")
public class SporadicFlowRegisterEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	@NotNull
	private int sflCusNumber;

	@NotNull
	private int sflDprtmntId;

	@NotNull
	private String sflDprtmnt;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sflStartTime;

	@NotNull
	private String sflStartAddrs;

	private String sflReason;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sflCrteTime;

	@NotNull
	private String sflCrteUserId;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sflUpdtTime;

	@NotNull
	private String sflUpdtUserId;

	@NotNull
	private String sflFlowType;

	@NotNull
	private String sflEndAddrs;

	@NotNull
	private String sflFlowStts;

	/**
	* @Fields sflFlowCheckStts : 审核状态，默认null 未审核
	*/
	private String sflFlowCheckStts;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sflEndTime;

	@NotNull
	private String sflPrisonPathId;

	@NotNull
	private String sflCrteDprtmntId;

	@NotNull
	private String sflPoliceNum;

	private String sflCrteUser;

	private String sflUpdtUser;

	private String sflCheckUserId;

	private String sflCheckUser;

	private int sflOffenderNum;

	private  String sflFlowBackRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sflCheckTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sflBackTime;


	public int getSflCusNumber() {
		return sflCusNumber;
	}

	public void setSflCusNumber(int sflCusNumber) {
		this.sflCusNumber = sflCusNumber;
	}

	public int getSflDprtmntId() {
		return sflDprtmntId;
	}

	public void setSflDprtmntId(int sflDprtmntId) {
		this.sflDprtmntId = sflDprtmntId;
	}

	public String getSflDprtmnt() {
		return sflDprtmnt;
	}

	public void setSflDprtmnt(String sflDprtmnt) {
		this.sflDprtmnt = sflDprtmnt;
	}

	public Date getSflStartTime() {
		return sflStartTime;
	}

	public void setSflStartTime(Date sflStartTime) {
		this.sflStartTime = sflStartTime;
	}

	public String getSflStartAddrs() {
		return sflStartAddrs;
	}

	public void setSflStartAddrs(String sflStartAddrs) {
		this.sflStartAddrs = sflStartAddrs;
	}

	public String getSflReason() {
		return sflReason;
	}

	public void setSflReason(String sflReason) {
		this.sflReason = sflReason;
	}

	public Date getSflCrteTime() {
		return sflCrteTime;
	}

	public void setSflCrteTime(Date sflCrteTime) {
		this.sflCrteTime = sflCrteTime;
	}

	public String getSflCrteUserId() {
		return sflCrteUserId;
	}

	public void setSflCrteUserId(String sflCrteUserId) {
		this.sflCrteUserId = sflCrteUserId;
	}

	public Date getSflUpdtTime() {
		return sflUpdtTime;
	}

	public void setSflUpdtTime(Date sflUpdtTime) {
		this.sflUpdtTime = sflUpdtTime;
	}

	public String getSflUpdtUserId() {
		return sflUpdtUserId;
	}

	public void setSflUpdtUserId(String sflUpdtUserId) {
		this.sflUpdtUserId = sflUpdtUserId;
	}

	public String getSflFlowType() {
		return sflFlowType;
	}

	public void setSflFlowType(String sflFlowType) {
		this.sflFlowType = sflFlowType;
	}

	public String getSflEndAddrs() {
		return sflEndAddrs;
	}

	public void setSflEndAddrs(String sflEndAddrs) {
		this.sflEndAddrs = sflEndAddrs;
	}

	public String getSflFlowStts() {
		return sflFlowStts;
	}

	public void setSflFlowStts(String sflFlowStts) {
		this.sflFlowStts = sflFlowStts;
	}

	public String getSflFlowCheckStts() {
		return sflFlowCheckStts;
	}

	public void setSflFlowCheckStts(String sflFlowCheckStts) {
		this.sflFlowCheckStts = sflFlowCheckStts;
	}

	public Date getSflEndTime() {
		return sflEndTime;
	}

	public void setSflEndTime(Date sflEndTime) {
		this.sflEndTime = sflEndTime;
	}

	public String getSflPrisonPathId() {
		return sflPrisonPathId;
	}

	public void setSflPrisonPathId(String sflPrisonPathId) {
		this.sflPrisonPathId = sflPrisonPathId;
	}

	public String getSflCrteDprtmntId() {
		return sflCrteDprtmntId;
	}

	public void setSflCrteDprtmntId(String sflCrteDprtmntId) {
		this.sflCrteDprtmntId = sflCrteDprtmntId;
	}

	public String getSflPoliceNum() {
		return sflPoliceNum;
	}

	public void setSflPoliceNum(String sflPoliceNum) {
		this.sflPoliceNum = sflPoliceNum;
	}

	public String getSflCrteUser() {
		return sflCrteUser;
	}

	public void setSflCrteUser(String sflCrteUser) {
		this.sflCrteUser = sflCrteUser;
	}


	public String getSflUpdtUser() {
		return sflUpdtUser;
	}

	public void setSflUpdtUser(String sflUpdtUser) {
		this.sflUpdtUser = sflUpdtUser;
	}


	public String getSflCheckUserId() {
		return sflCheckUserId;
	}

	public void setSflCheckUserId(String sflCheckUserId) {
		this.sflCheckUserId = sflCheckUserId;
	}


	public String getSflCheckUser() {
		return sflCheckUser;
	}

	public void setSflCheckUser(String sflCheckUser) {
		this.sflCheckUser = sflCheckUser;
	}

	public int getSflOffenderNum() {
		return sflOffenderNum;
	}

	public void setSflOffenderNum(int sflOffenderNum) {
		this.sflOffenderNum = sflOffenderNum;
	}

	public String getSflFlowBackRemark() {
		return sflFlowBackRemark;
	}

	public void setSflFlowBackRemark(String sflFlowBackRemark) {
		this.sflFlowBackRemark = sflFlowBackRemark;
	}

	public Date getSflCheckTime() {
		return sflCheckTime;
	}

	public void setSflCheckTime(Date sflCheckTime) {
		this.sflCheckTime = sflCheckTime;
	}

	public Date getSflBackTime() {
		return sflBackTime;
	}

	public void setSflBackTime(Date sflBackTime) {
		this.sflBackTime = sflBackTime;
	}

}
