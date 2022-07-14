package com.cesgroup.prison.rwgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "CDS_RWGL_RWJS")
public class RwjsEntity extends StringIDEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6861175710466965558L;
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date xfTime;//下发时间
	private String rwTitle;//任务标题
	private String xfPolice;//下发干警
	private String rwContent;//任务内容
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clDeadline;//处理期限
	@Column(updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rwjsCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String rwjsCrteUserId;
	private String rwjsCrteUserName;
	@JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")// 创建人
	private Date rwjsUpdtTime;						// 更新时间
	private String rwjsUpdtUserId;					// 更新人
	private String jsStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clTime; 
	private String clContent;
	private String xfId;
	private String jsDept;
	
	private String jyId;
	private String jqId;
	private String fxcjId;
	public String getJyId() {
		return jyId;
	}
	public void setJyId(String jyId) {
		this.jyId = jyId;
	}
	public String getJqId() {
		return jqId;
	}
	public void setJqId(String jqId) {
		this.jqId = jqId;
	}
	public Date getXfTime() {
		return xfTime;
	}
	public void setXfTime(Date xfTime) {
		this.xfTime = xfTime;
	}
	public String getRwTitle() {
		return rwTitle;
	}
	public void setRwTitle(String rwTitle) {
		this.rwTitle = rwTitle;
	}
	public String getXfPolice() {
		return xfPolice;
	}
	public void setXfPolice(String xfPolice) {
		this.xfPolice = xfPolice;
	}
	public String getRwContent() {
		return rwContent;
	}
	public void setRwContent(String rwContent) {
		this.rwContent = rwContent;
	}
	public Date getClDeadline() {
		return clDeadline;
	}
	public void setClDeadline(Date clDeadline) {
		this.clDeadline = clDeadline;
	}
	public Date getRwjsCrteTime() {
		return rwjsCrteTime;
	}
	public void setRwjsCrteTime(Date rwjsCrteTime) {
		this.rwjsCrteTime = rwjsCrteTime;
	}
	public String getRwjsCrteUserId() {
		return rwjsCrteUserId;
	}
	public void setRwjsCrteUserId(String rwjsCrteUserId) {
		this.rwjsCrteUserId = rwjsCrteUserId;
	}
	public String getRwjsCrteUserName() {
		return rwjsCrteUserName;
	}
	public void setRwjsCrteUserName(String rwjsCrteUserName) {
		this.rwjsCrteUserName = rwjsCrteUserName;
	}
	public Date getRwjsUpdtTime() {
		return rwjsUpdtTime;
	}
	public void setRwjsUpdtTime(Date rwjsUpdtTime) {
		this.rwjsUpdtTime = rwjsUpdtTime;
	}
	public String getRwjsUpdtUserId() {
		return rwjsUpdtUserId;
	}
	public void setRwjsUpdtUserId(String rwjsUpdtUserId) {
		this.rwjsUpdtUserId = rwjsUpdtUserId;
	}
	public String getJsStatus() {
		return jsStatus;
	}
	public void setJsStatus(String jsStatus) {
		this.jsStatus = jsStatus;
	}
	public Date getClTime() {
		return clTime;
	}
	public void setClTime(Date clTime) {
		this.clTime = clTime;
	}
	public String getClContent() {
		return clContent;
	}
	public void setClContent(String clContent) {
		this.clContent = clContent;
	}
	public String getXfId() {
		return xfId;
	}
	public void setXfId(String xfId) {
		this.xfId = xfId;
	}
	public String getJsDept() {
		return jsDept;
	}
	public void setJsDept(String jsDept) {
		this.jsDept = jsDept;
	}
	public String getFxcjId() {
		return fxcjId;
	}
	public void setFxcjId(String fxcjId) {
		this.fxcjId = fxcjId;
	}
  	
}
