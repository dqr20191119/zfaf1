package com.cesgroup.prison.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "com_msg_subscribe")
public class MsgsubscribeEntity extends StringIDEntity {
	
	private String msdCusNumber;					// 监狱id
	private String msdUserId;						// 警号
	private String msdMsgIdnty;						// 消息编码
	
	@Column(updatable = false)
	private Date msdCrteTime;						// 创建时间
	@Column(updatable = false)
	private String msdCrteUserId;					// 创建人id
	private Date msdUpdtTime;						// 更新时间
	private String msdUpdtUserId;					// 更新人id
	
	@Transient
	private List<MsgsubscribeEntity> msgsubscribeList = new ArrayList<MsgsubscribeEntity>();
	@Transient
	private String msdUserName;
	
	
	public String getMsdCusNumber() {
		return msdCusNumber;
	}
	public void setMsdCusNumber(String msdCusNumber) {
		this.msdCusNumber = msdCusNumber;
	}
	public String getMsdUserId() {
		return msdUserId;
	}
	public void setMsdUserId(String msdUserId) {
		this.msdUserId = msdUserId;
	}
	public String getMsdMsgIdnty() {
		return msdMsgIdnty;
	}
	public void setMsdMsgIdnty(String msdMsgIdnty) {
		this.msdMsgIdnty = msdMsgIdnty;
	}
	public Date getMsdCrteTime() {
		return msdCrteTime;
	}
	public void setMsdCrteTime(Date msdCrteTime) {
		this.msdCrteTime = msdCrteTime;
	}
	public String getMsdCrteUserId() {
		return msdCrteUserId;
	}
	public void setMsdCrteUserId(String msdCrteUserId) {
		this.msdCrteUserId = msdCrteUserId;
	}
	public Date getMsdUpdtTime() {
		return msdUpdtTime;
	}
	public void setMsdUpdtTime(Date msdUpdtTime) {
		this.msdUpdtTime = msdUpdtTime;
	}
	public String getMsdUpdtUserId() {
		return msdUpdtUserId;
	}
	public void setMsdUpdtUserId(String msdUpdtUserId) {
		this.msdUpdtUserId = msdUpdtUserId;
	}
	public List<MsgsubscribeEntity> getMsgsubscribeList() {
		return msgsubscribeList;
	}
	public void setMsgsubscribeList(List<MsgsubscribeEntity> msgsubscribeList) {
		this.msgsubscribeList = msgsubscribeList;
	}
	public String getMsdUserName() {
		return msdUserName;
	}
	public void setMsdUserName(String msdUserName) {
		this.msdUserName = msdUserName;
	}
}
