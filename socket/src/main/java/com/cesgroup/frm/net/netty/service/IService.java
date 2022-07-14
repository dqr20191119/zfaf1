package com.cesgroup.frm.net.netty.service;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.frm.net.netty.bean.WebSocketMsg;

public interface IService {
	String handleMsg(WebSocketMsg msg);
	
	String handleMsg(String msg);
	
	String handleMsg(JSONObject msg);
}
