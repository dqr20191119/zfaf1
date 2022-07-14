package com.cesgroup.prison.jfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：TalkBackServer   
* @Description：   对讲主机
* @author：Tao.xu   
* @date：2017年12月20日 下午6:49:29   
* @version        
*/
@Entity
@Table(name = "DVC_TALKBACK_SERVER")
public class TalkBackServerEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = -8483988935120807106L;

	private String tseCusNumber;

	private String tseIdnty;

	private String tseName;

	private String tseBrand;

	private String tseSn;

	private String tseIp;

	private String tsePcIp;

	private String tseUser;

	private String tsePwd;

	private String tseAreaId;

	private String tseArea;

	private String tseDprtmntId;

	private String tseDprtmnt;

	private String tseSeqNum;

	private String tseActionIndc;

	private String tseSttsIndc;

	private String tseRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date tseCrteTime;

	private String tseCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date tseUpdtTime;

	private String tseUpdtUserId;

	/**
	* @Fields tseParentId : 上级主机
	*/
	private String tseParentId;

	public String getTseCusNumber() {
		return tseCusNumber;
	}

	public void setTseCusNumber(String tseCusNumber) {
		this.tseCusNumber = tseCusNumber;
	}

	public String getTseIdnty() {
		return tseIdnty;
	}

	public void setTseIdnty(String tseIdnty) {
		this.tseIdnty = tseIdnty;
	}

	public String getTseName() {
		return tseName;
	}

	public void setTseName(String tseName) {
		this.tseName = tseName;
	}

	public String getTseBrand() {
		return tseBrand;
	}

	public void setTseBrand(String tseBrand) {
		this.tseBrand = tseBrand;
	}

	public String getTseSn() {
		return tseSn;
	}

	public void setTseSn(String tseSn) {
		this.tseSn = tseSn;
	}

	public String getTseIp() {
		return tseIp;
	}

	public void setTseIp(String tseIp) {
		this.tseIp = tseIp;
	}

	public String getTsePcIp() {
		return tsePcIp;
	}

	public void setTsePcIp(String tsePcIp) {
		this.tsePcIp = tsePcIp;
	}

	public String getTseUser() {
		return tseUser;
	}

	public void setTseUser(String tseUser) {
		this.tseUser = tseUser;
	}

	public String getTsePwd() {
		return tsePwd;
	}

	public void setTsePwd(String tsePwd) {
		this.tsePwd = tsePwd;
	}

	public String getTseAreaId() {
		return tseAreaId;
	}

	public void setTseAreaId(String tseAreaId) {
		this.tseAreaId = tseAreaId;
	}

	public String getTseArea() {
		return tseArea;
	}

	public void setTseArea(String tseArea) {
		this.tseArea = tseArea;
	}

	public String getTseDprtmntId() {
		return tseDprtmntId;
	}

	public void setTseDprtmntId(String tseDprtmntId) {
		this.tseDprtmntId = tseDprtmntId;
	}

	public String getTseDprtmnt() {
		return tseDprtmnt;
	}

	public void setTseDprtmnt(String tseDprtmnt) {
		this.tseDprtmnt = tseDprtmnt;
	}

	public String getTseSeqNum() {
		return tseSeqNum;
	}

	public void setTseSeqNum(String tseSeqNum) {
		this.tseSeqNum = tseSeqNum;
	}

	public String getTseActionIndc() {
		return tseActionIndc;
	}

	public void setTseActionIndc(String tseActionIndc) {
		this.tseActionIndc = tseActionIndc;
	}

	public String getTseSttsIndc() {
		return tseSttsIndc;
	}

	public void setTseSttsIndc(String tseSttsIndc) {
		this.tseSttsIndc = tseSttsIndc;
	}

	public String getTseRemark() {
		return tseRemark;
	}

	public void setTseRemark(String tseRemark) {
		this.tseRemark = tseRemark;
	}

	public Date getTseCrteTime() {
		return tseCrteTime;
	}

	public void setTseCrteTime(Date tseCrteTime) {
		this.tseCrteTime = tseCrteTime;
	}

	public String getTseCrteUserId() {
		return tseCrteUserId;
	}

	public void setTseCrteUserId(String tseCrteUserId) {
		this.tseCrteUserId = tseCrteUserId;
	}

	public Date getTseUpdtTime() {
		return tseUpdtTime;
	}

	public void setTseUpdtTime(Date tseUpdtTime) {
		this.tseUpdtTime = tseUpdtTime;
	}

	public String getTseUpdtUserId() {
		return tseUpdtUserId;
	}

	public void setTseUpdtUserId(String tseUpdtUserId) {
		this.tseUpdtUserId = tseUpdtUserId;
	}

	public String getTseParentId() {
		return tseParentId;
	}

	public void setTseParentId(String tseParentId) {
		this.tseParentId = tseParentId;
	}

}
