package com.cesgroup.prison.common.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_c_message")
public class MessageEntity extends StringIDEntity {

	/**
	 * 被提醒人
	 */
	private String noticeUserId;
	/**
	 * 被提醒人姓名
	 */
	private String noticeUserName;
	/**
	 * 提醒开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	/**
	 * 提醒结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	/**
	 * 提醒内容
	 */
	private String content;
	/**
	 * 是否阅读
	 */
	private String isRead;

	/**
	 * 阅读时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date readDate;

	/**
	 * 跳转地址
	 */
	private String url;
	/**
	 * 记录生成时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 监狱编号
	 */
	private String jyId;
	/**
	 * 业务ID
	 */
	private String ywId;

	public String getNoticeUserId() {
		return noticeUserId;
	}
	public void setNoticeUserId(String noticeUserId) {
		this.noticeUserId = noticeUserId;
	}
	public String getNoticeUserName() {
		return noticeUserName;
	}
	public void setNoticeUserName(String noticeUserName) {
		this.noticeUserName = noticeUserName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getJyId() {
		return jyId;
	}
	public void setJyId(String jyId) {
		this.jyId = jyId;
	}
	public String getYwId() {
		return ywId;
	}
	public void setYwId(String ywId) {
		this.ywId = ywId;
	}
}
