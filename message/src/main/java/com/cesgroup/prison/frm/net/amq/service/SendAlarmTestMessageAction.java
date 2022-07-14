package com.cesgroup.prison.frm.net.amq.service;

public interface SendAlarmTestMessageAction<T> {
	
	public void send(T message, String cusNumber,String msgType);
}
