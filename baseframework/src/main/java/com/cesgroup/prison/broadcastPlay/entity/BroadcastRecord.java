package com.cesgroup.prison.broadcastPlay.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CDS_BROADCAST_RECORD_DTLS")
public class BroadcastRecord extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String cusNumber;
	/**
	 * 会话ID(停止播放广播时用到)
	 */
	private String sessionId;
	/**
	 * 播放类型（1：媒体库；2：文字转语音）
	 */
    private String contentType;
    /**
     * 播放内容
     */
    private String content;
    /**
     * 广播设备主键ID
     */
    private String broadcastId;
    /**
     * 广播设备名称
     */
    private String broadcastName;
    /**
     * 开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
	/**
	 * 开始广播返回结果
	 */
    private String startResult;
	/**
	 * 开始广播返回结果描述
	 */
    private String startResultDesc;
    /**
     * 结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
	/**
	 * 停止广播返回结果
	 */
    private String endResult;
    /**
     * 停止广播返回结果描述
     */
    private String endResultDesc;
    /**
     * 创建人ID
     */
    private String createUserId;
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 最近更新人
     */
    private String updateUserId;
    /**
     * 最近更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
	/**
	 * 播放内容的值（CONTENT_TYPE为0时，为播放曲目ID对应的名称；CONTENT_TYPE为1时与CONTENT一致）
	 */
	private String contentValue;
	
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBroadcastId() {
		return broadcastId;
	}
	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}
	public String getBroadcastName() {
		return broadcastName;
	}
	public void setBroadcastName(String broadcastName) {
		this.broadcastName = broadcastName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getStartResult() {
		return startResult;
	}
	public void setStartResult(String startResult) {
		this.startResult = startResult;
	}
	public String getStartResultDesc() {
		return startResultDesc;
	}
	public void setStartResultDesc(String startResultDesc) {
		this.startResultDesc = startResultDesc;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getEndResult() {
		return endResult;
	}
	public void setEndResult(String endResult) {
		this.endResult = endResult;
	}
	public String getEndResultDesc() {
		return endResultDesc;
	}
	public void setEndResultDesc(String endResultDesc) {
		this.endResultDesc = endResultDesc;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContentValue() {
		return contentValue;
	}

	public void setContentValue(String contentValue) {
		this.contentValue = contentValue;
	}
}