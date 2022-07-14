package com.cesgroup.framework.bean;

public class WebSocketMessage {
	
	private String sendType = null; // 发送类型：1-按监狱订阅、2-按用户、3-按组织部门
	private String sendTo = null;   // 发送目标对象: 对应上面的具体值-多个值逗号分隔
    private String msgType = null;	// 消息类型
    private String content = null;	// 消息内容（字符串）
    private Object message = null;	// 消息内容（对象）

    
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
}
