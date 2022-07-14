package com.cesgroup.prison.callNamesAl.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：baseframework   
* @ClassName：CallNamesRegisterEntity   
* @Description：   人脸注册
* @author：Tao   
* @date：2018年8月8日 上午10:30:38   
* @version        
*/
@Entity
@Table(name = "CDS_CALL_NAMES_REGISTER_PRISONER")
public class CallNamesRegisterEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	private String cnrCusNumber;

	private String cnrPrisonerIndc;

	@Transient
	private String prisonerName;

	private String cnrImgId;

	private String cnrImgUrl;

	private String cnrImgName;

	private String cnrImgSize;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrCrteTime;

	private String cnrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cnrUpdtTime;

	private String cnrUpdtUserId;

	@Transient
	private String lch;

	@Transient
	private String jsh;

	@Transient
	private String demptId;

	public String getCnrCusNumber() {
		return cnrCusNumber;
	}

	public void setCnrCusNumber(String cnrCusNumber) {
		this.cnrCusNumber = cnrCusNumber;
	}

	public String getCnrPrisonerIndc() {
		return cnrPrisonerIndc;
	}

	public void setCnrPrisonerIndc(String cnrPrisonerIndc) {
		this.cnrPrisonerIndc = cnrPrisonerIndc;
	}

	public String getPrisonerName() {
		return prisonerName;
	}

	public void setPrisonerName(String prisonerName) {
		this.prisonerName = prisonerName;
	}

	public String getCnrImgId() {
		return cnrImgId;
	}

	public void setCnrImgId(String cnrImgId) {
		this.cnrImgId = cnrImgId;
	}

	public String getCnrImgUrl() {
		return cnrImgUrl;
	}

	public void setCnrImgUrl(String cnrImgUrl) {
		this.cnrImgUrl = cnrImgUrl;
	}

	public Date getCnrCrteTime() {
		return cnrCrteTime;
	}

	public void setCnrCrteTime(Date cnrCrteTime) {
		this.cnrCrteTime = cnrCrteTime;
	}

	public String getCnrCrteUserId() {
		return cnrCrteUserId;
	}

	public void setCnrCrteUserId(String cnrCrteUserId) {
		this.cnrCrteUserId = cnrCrteUserId;
	}

	public Date getCnrUpdtTime() {
		return cnrUpdtTime;
	}

	public void setCnrUpdtTime(Date cnrUpdtTime) {
		this.cnrUpdtTime = cnrUpdtTime;
	}

	public String getCnrUpdtUserId() {
		return cnrUpdtUserId;
	}

	public void setCnrUpdtUserId(String cnrUpdtUserId) {
		this.cnrUpdtUserId = cnrUpdtUserId;
	}

	public String getLch() {
		return lch;
	}

	public void setLch(String lch) {
		this.lch = lch;
	}

	public String getJsh() {
		return jsh;
	}

	public void setJsh(String jsh) {
		this.jsh = jsh;
	}

	public String getDemptId() {
		return demptId;
	}

	public void setDemptId(String demptId) {
		this.demptId = demptId;
	}

	public String getCnrImgName() {
		return cnrImgName;
	}

	public void setCnrImgName(String cnrImgName) {
		this.cnrImgName = cnrImgName;
	}

	public String getCnrImgSize() {
		return cnrImgSize;
	}
 
	public void setCnrImgSize(String cnrImgSize) {
		this.cnrImgSize = cnrImgSize;
	}

}
