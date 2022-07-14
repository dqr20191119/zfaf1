package com.cesgroup.prison.jswh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：JswhEntity   
* @Description：   监舍维护
* @author：Wang.xin 
* @date：2018年08月08日 
* @version        
*/
@Entity
@Table(name = "CDS_PRISON_JS")
public class JswhEntity extends StringIDEntity {

	private String cpjCusNumber;       

	private String cpjLch;

	private String cpjLc;

	private String cpjJs;

	
	@Column(updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cpjCrteTime;
	
	@Column(updatable = false)
	private String cpjCrteUserId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cpjUpdtTime;
	
	private String cpjUpdtUserId;
	private String cpjCameraId;
	private String cpjCameraName;
	private String cpjFCameraId;
	private String cpjFCameraName;
	
	public String getCpjCameraId() {
		return cpjCameraId;
	}

	public void setCpjCameraId(String cpjCameraId) {
		this.cpjCameraId = cpjCameraId;
	}

	public String getCpjCameraName() {
		return cpjCameraName;
	}

	public void setCpjCameraName(String cpjCameraName) {
		this.cpjCameraName = cpjCameraName;
	}

	public String getCpjFCameraId() {
		return cpjFCameraId;
	}

	public void setCpjFCameraId(String cpjFCameraId) {
		this.cpjFCameraId = cpjFCameraId;
	}

	public String getCpjFCameraName() {
		return cpjFCameraName;
	}

	public void setCpjFCameraName(String cpjFCameraName) {
		this.cpjFCameraName = cpjFCameraName;
	}

	

	public String getCpjCusNumber() {
		return cpjCusNumber;
	}

	public void setCpjCusNumber(String cpjCusNumber) {
		this.cpjCusNumber = cpjCusNumber;
	}

	public String getCpjLch() {
		return cpjLch;
	}

	public void setCpjLch(String cpjLch) {
		this.cpjLch = cpjLch;
	}

	public String getCpjLc() {
		return cpjLc;
	}

	public void setCpjLc(String cpjLc) {
		this.cpjLc = cpjLc;
	}

	public String getCpjJs() {
		return cpjJs;
	}

	public void setCpjJs(String cpjJs) {
		this.cpjJs = cpjJs;
	}

	public Date getCpjCrteTime() {
		return cpjCrteTime;
	}

	public void setCpjCrteTime(Date cpjCrteTime) {
		this.cpjCrteTime = cpjCrteTime;
	}

	public String getCpjCrteUserId() {
		return cpjCrteUserId;
	}

	public void setCpjCrteUserId(String cpjCrteUserId) {
		this.cpjCrteUserId = cpjCrteUserId;
	}

	public Date getCpjUpdtTime() {
		return cpjUpdtTime;
	}

	public void setCpjUpdtTime(Date cpjUpdtTime) {
		this.cpjUpdtTime = cpjUpdtTime;
	}

	public String getCpjUpdtUserId() {
		return cpjUpdtUserId;
	}

	public void setCpjUpdtUserId(String cpjUpdtUserId) {
		this.cpjUpdtUserId = cpjUpdtUserId;
	}



}
