package com.cesgroup.prison.rcs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 融合通讯实体类
 * 
 * @author lincoln
 *
 */
@Table(name = "rcs_log")
public class RcsEntity extends StringIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * cusNumber 监狱id
	 */
	private String cusNumber;
	/**
	 * 指令代码
	 * 1:启动预案
	 * 2:语音通知
	 * 3:发送短信
	 * 4:取消呼叫
	 * 5:修改位置信息
	 */
	private String cmd;
	
	/**
	 * 用户号码
	 */
	private String cellvalue;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 警号
	 */
	private String jobNo;
	/**
	 * 民警姓名
	 */
	private String userName;
	/**
	 * 发送时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
	private Date sendTime;
	/**
	 * 发送人姓名
	 */
	private String sendUserName;
	/**
	 * 发送人id
	 */
	private String sendUserId;
	/**
	 * 状态
	 */
	private String type;
	/**
	 * 位置
	 */
//	private String position;
	
	/**
	 * 区域
	 */
//	private String organizeCode;
	
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public String getCellvalue() {
		return cellvalue;
	}
	public void setCellvalue(String cellvalue) {
		this.cellvalue = cellvalue;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
//	public String getPosition() {
//		return position;
//	}
//	public void setPosition(String position) {
//		this.position = position;
//	}
//	public String getOrganizeCode() {
//		return organizeCode;
//	}
//	public void setOrganizeCode(String organizeCode) {
//		this.organizeCode = organizeCode;
//	}
	
}
