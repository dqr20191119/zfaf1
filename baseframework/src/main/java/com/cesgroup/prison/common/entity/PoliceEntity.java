package com.cesgroup.prison.common.entity;

import java.util.Date;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PoliceEntity extends StringIDEntity {
	
	private String pbdCusNumber;
	private String pbdPoliceIdnty;
	private String pbdPoliceName;
	private String pbdUserId;
	private String pbdLoginName;
	private String pbdLoginPwd;
	private String pbdUserSttsIndc;
	private String pbdDoorCardIdnty;
	private String pbdDrptmntId;
	private String pbdDrptmnt;
	private String pbdPstnName;
	private String pbdSex;
	private String pbdAge;
	private String pbdPoliticalStatus;
	private String pbdOfficialTitle;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date pbdBirthDate;
	private String pbdFixedPhone;
	private String pbdIpPhone;
	private String pbdPhone;
	private String pbdShortPhone;
	private String pbdFax;
	private String pbdTalkNum;
	private String pbdEmail;
	private String pbdOaSystem;
	private String pbdPoliceCmmnct;
	private String pbdFamilyPhone;
	private String pbdImgDir;
	private String pbdImg;
	private String pbdActionIndc;
	private String pbdStationName;
	private String pbdRemark;
	private String pbdCrteTime;
	private String pbdCrteUserId;
	private String pbdUpdtTime;
	private String pbdUpdtUserId;
	private String pbdDegreeIndc;
	private String pbdProfession;
	private String pbdJobDate;
	private String pbdPoliceTypeIndc;
	private String pbdJobTypeIndc;
	private String pbdLeaderIdnty;
	private String pbdIsLeaderIndc;

	
	public String getPbdCusNumber() {
		return pbdCusNumber;
	}
	public void setPbdCusNumber(String pbdCusNumber) {
		this.pbdCusNumber = pbdCusNumber;
	}
	public String getPbdPoliceIdnty() {
		return pbdPoliceIdnty;
	}
	public void setPbdPoliceIdnty(String pbdPoliceIdnty) {
		this.pbdPoliceIdnty = pbdPoliceIdnty;
	}
	public String getPbdPoliceName() {
		return pbdPoliceName;
	}
	public void setPbdPoliceName(String pbdPoliceName) {
		this.pbdPoliceName = pbdPoliceName;
	}
	public String getPbdPhone() {
		return pbdPhone;
	}
	public void setPbdPhone(String pbdPhone) {
		this.pbdPhone = pbdPhone;
	}
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
	public String getPbdUserId() {
		return pbdUserId;
	}
	public void setPbdUserId(String pbdUserId) {
		this.pbdUserId = pbdUserId;
	}
	public String getPbdLoginPwd() {
		return pbdLoginPwd;
	}
	public void setPbdLoginPwd(String pbdLoginPwd) {
		this.pbdLoginPwd = pbdLoginPwd;
	}
	public String getPbdUserSttsIndc() {
		return pbdUserSttsIndc;
	}
	public void setPbdUserSttsIndc(String pbdUserSttsIndc) {
		this.pbdUserSttsIndc = pbdUserSttsIndc;
	}
	public String getPbdDoorCardIdnty() {
		return pbdDoorCardIdnty;
	}
	public void setPbdDoorCardIdnty(String pbdDoorCardIdnty) {
		this.pbdDoorCardIdnty = pbdDoorCardIdnty;
	}
	public String getPbdDrptmnt() {
		return pbdDrptmnt;
	}
	public void setPbdDrptmnt(String pbdDrptmnt) {
		this.pbdDrptmnt = pbdDrptmnt;
	}
	public String getPbdPstnName() {
		return pbdPstnName;
	}
	public void setPbdPstnName(String pbdPstnName) {
		this.pbdPstnName = pbdPstnName;
	}
	public String getPbdSex() {
		return pbdSex;
	}
	public void setPbdSex(String pbdSex) {
		this.pbdSex = pbdSex;
	}
	public String getPbdAge() {
		return pbdAge;
	}
	public void setPbdAge(String pbdAge) {
		this.pbdAge = pbdAge;
	}
	public String getPbdPoliticalStatus() {
		return pbdPoliticalStatus;
	}
	public void setPbdPoliticalStatus(String pbdPoliticalStatus) {
		this.pbdPoliticalStatus = pbdPoliticalStatus;
	}
	public String getPbdOfficialTitle() {
		return pbdOfficialTitle;
	}
	public void setPbdOfficialTitle(String pbdOfficialTitle) {
		this.pbdOfficialTitle = pbdOfficialTitle;
	}
	public Date getPbdBirthDate() {
		return pbdBirthDate;
	}
	public void setPbdBirthDate(Date pbdBirthDate) {
		this.pbdBirthDate = pbdBirthDate;
	}
	public String getPbdFixedPhone() {
		return pbdFixedPhone;
	}
	public void setPbdFixedPhone(String pbdFixedPhone) {
		this.pbdFixedPhone = pbdFixedPhone;
	}
	public String getPbdIpPhone() {
		return pbdIpPhone;
	}
	public void setPbdIpPhone(String pbdIpPhone) {
		this.pbdIpPhone = pbdIpPhone;
	}
	public String getPbdShortPhone() {
		return pbdShortPhone;
	}
	public void setPbdShortPhone(String pbdShortPhone) {
		this.pbdShortPhone = pbdShortPhone;
	}
	public String getPbdFax() {
		return pbdFax;
	}
	public void setPbdFax(String pbdFax) {
		this.pbdFax = pbdFax;
	}
	public String getPbdTalkNum() {
		return pbdTalkNum;
	}
	public void setPbdTalkNum(String pbdTalkNum) {
		this.pbdTalkNum = pbdTalkNum;
	}
	public String getPbdEmail() {
		return pbdEmail;
	}
	public void setPbdEmail(String pbdEmail) {
		this.pbdEmail = pbdEmail;
	}
	public String getPbdOaSystem() {
		return pbdOaSystem;
	}
	public void setPbdOaSystem(String pbdOaSystem) {
		this.pbdOaSystem = pbdOaSystem;
	}
	public String getPbdPoliceCmmnct() {
		return pbdPoliceCmmnct;
	}
	public void setPbdPoliceCmmnct(String pbdPoliceCmmnct) {
		this.pbdPoliceCmmnct = pbdPoliceCmmnct;
	}
	public String getPbdFamilyPhone() {
		return pbdFamilyPhone;
	}
	public void setPbdFamilyPhone(String pbdFamilyPhone) {
		this.pbdFamilyPhone = pbdFamilyPhone;
	}
	public String getPbdImgDir() {
		return pbdImgDir;
	}
	public void setPbdImgDir(String pbdImgDir) {
		this.pbdImgDir = pbdImgDir;
	}
	public String getPbdImg() {
		return pbdImg;
	}
	public void setPbdImg(String pbdImg) {
		this.pbdImg = pbdImg;
	}
	public String getPbdActionIndc() {
		return pbdActionIndc;
	}
	public void setPbdActionIndc(String pbdActionIndc) {
		this.pbdActionIndc = pbdActionIndc;
	}
	public String getPbdStationName() {
		return pbdStationName;
	}
	public void setPbdStationName(String pbdStationName) {
		this.pbdStationName = pbdStationName;
	}
	public String getPbdRemark() {
		return pbdRemark;
	}
	public void setPbdRemark(String pbdRemark) {
		this.pbdRemark = pbdRemark;
	}
	public String getPbdCrteTime() {
		return pbdCrteTime;
	}
	public void setPbdCrteTime(String pbdCrteTime) {
		this.pbdCrteTime = pbdCrteTime;
	}
	public String getPbdCrteUserId() {
		return pbdCrteUserId;
	}
	public void setPbdCrteUserId(String pbdCrteUserId) {
		this.pbdCrteUserId = pbdCrteUserId;
	}
	public String getPbdUpdtTime() {
		return pbdUpdtTime;
	}
	public void setPbdUpdtTime(String pbdUpdtTime) {
		this.pbdUpdtTime = pbdUpdtTime;
	}
	public String getPbdUpdtUserId() {
		return pbdUpdtUserId;
	}
	public void setPbdUpdtUserId(String pbdUpdtUserId) {
		this.pbdUpdtUserId = pbdUpdtUserId;
	}
	public String getPbdDegreeIndc() {
		return pbdDegreeIndc;
	}
	public void setPbdDegreeIndc(String pbdDegreeIndc) {
		this.pbdDegreeIndc = pbdDegreeIndc;
	}
	public String getPbdProfession() {
		return pbdProfession;
	}
	public void setPbdProfession(String pbdProfession) {
		this.pbdProfession = pbdProfession;
	}
	public String getPbdJobDate() {
		return pbdJobDate;
	}
	public void setPbdJobDate(String pbdJobDate) {
		this.pbdJobDate = pbdJobDate;
	}
	public String getPbdPoliceTypeIndc() {
		return pbdPoliceTypeIndc;
	}
	public void setPbdPoliceTypeIndc(String pbdPoliceTypeIndc) {
		this.pbdPoliceTypeIndc = pbdPoliceTypeIndc;
	}
	public String getPbdJobTypeIndc() {
		return pbdJobTypeIndc;
	}
	public void setPbdJobTypeIndc(String pbdJobTypeIndc) {
		this.pbdJobTypeIndc = pbdJobTypeIndc;
	}
	public String getPbdLeaderIdnty() {
		return pbdLeaderIdnty;
	}
	public void setPbdLeaderIdnty(String pbdLeaderIdnty) {
		this.pbdLeaderIdnty = pbdLeaderIdnty;
	}
	public String getPbdIsLeaderIndc() {
		return pbdIsLeaderIndc;
	}
	public void setPbdIsLeaderIndc(String pbdIsLeaderIndc) {
		this.pbdIsLeaderIndc = pbdIsLeaderIndc;
	}
}
