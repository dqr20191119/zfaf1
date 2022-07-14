package com.cesgroup.prison.rwgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "CDS_RWGL_RWXF")
public class RwxfEntity extends StringIDEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5431013232915160818L;
	private String jyId;
	private String jqId;
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date xfTime;//下发时间
	private String rwStatus;//任务状态
	private String rwTitle;//任务标题
	private String xfPolice;//下发干警
	private String rwContent;//任务内容
	private String jsDept;//接收单位
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clDeadline;//处理期限
	private String rwSituation;//任务情况
	
	@Column(updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rwxfCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String rwxfCrteUserId;
	@JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")// 创建人
	private Date rwxfUpdtTime;						// 更新时间
	private String rwxfUpdtUserId;					// 更新人
	
	private int xfdwzs;
	private int jsdwzs;
	private int wfks;
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
	public String getRwStatus() {
		return rwStatus;
	}
	public void setRwStatus(String rwStatus) {
		this.rwStatus = rwStatus;
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
	public String getJsDept() {
		return jsDept;
	}
	public void setJsDept(String jsDept) {
		this.jsDept = jsDept;
	}
	public Date getClDeadline() {
		return clDeadline;
	}
	public void setClDeadline(Date clDeadline) {
		this.clDeadline = clDeadline;
	}
	public String getRwSituation() {
		return rwSituation;
	}
	public void setRwSituation(String rwSituation) {
		this.rwSituation = rwSituation;
	}
	public Date getRwxfCrteTime() {
		return rwxfCrteTime;
	}
	public void setRwxfCrteTime(Date rwxfCrteTime) {
		this.rwxfCrteTime = rwxfCrteTime;
	}
	public String getRwxfCrteUserId() {
		return rwxfCrteUserId;
	}
	public void setRwxfCrteUserId(String rwxfCrteUserId) {
		this.rwxfCrteUserId = rwxfCrteUserId;
	}
	public Date getRwxfUpdtTime() {
		return rwxfUpdtTime;
	}
	public void setRwxfUpdtTime(Date rwxfUpdtTime) {
		this.rwxfUpdtTime = rwxfUpdtTime;
	}
	public String getRwxfUpdtUserId() {
		return rwxfUpdtUserId;
	}
	public void setRwxfUpdtUserId(String rwxfUpdtUserId) {
		this.rwxfUpdtUserId = rwxfUpdtUserId;
	}
	public int getXfdwzs() {
		return xfdwzs;
	}
	public void setXfdwzs(int xfdwzs) {
		this.xfdwzs = xfdwzs;
	}
	public int getJsdwzs() {
		return jsdwzs;
	}
	public void setJsdwzs(int jsdwzs) {
		this.jsdwzs = jsdwzs;
	}
	public int getWfks() {
		return wfks;
	}
	public void setWfks(int wfks) {
		this.wfks = wfks;
	}
	public String getFxcjId() {
		return fxcjId;
	}
	public void setFxcjId(String fxcjId) {
		this.fxcjId = fxcjId;
	}
  	
}
