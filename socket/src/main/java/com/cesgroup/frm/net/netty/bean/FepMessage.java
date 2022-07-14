package com.cesgroup.frm.net.netty.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FepMessage {
	private MsgHeader header;
	private String msg;
	private JSONObject body;
	private JSONArray bodyArray;
	
	
	public JSONArray getBodyArray() {
		return bodyArray;
	}
	public void setBodyArray(JSONArray bodyArray) {
		this.bodyArray = bodyArray;
	}
	public JSONObject getBody() {
		return body;
	}
	public void setBody(JSONObject body) {
		this.body = body;
	}
	public FepMessage() {
		header=new MsgHeader();
	}
	public FepMessage(JSONObject jsonMsg) {
		header = (MsgHeader)JSONObject.parseObject(jsonMsg.get("header").toString(), MsgHeader.class);
		msg = jsonMsg.toString();
		String msgType = header.getMsgType();
		if(msgType.equals("POLICE003")){
			bodyArray = JSONObject.parseArray(jsonMsg.getString("body"));
		}else{
			body = JSONObject.parseObject(jsonMsg.getString("body"));
		}
	}
	
	public FepMessage(String msg, String recevier) {
		header=new MsgHeader();
		header.setRecevier(recevier);
		this.msg = msg;
	}
	public MsgHeader getHeader() {
		return header;
	}
	public void setHeader(MsgHeader header) {
		this.header = header;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
