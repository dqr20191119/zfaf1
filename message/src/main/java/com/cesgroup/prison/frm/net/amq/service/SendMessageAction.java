package com.cesgroup.prison.frm.net.amq.service;

public interface SendMessageAction<T> {
	
	public void send(T message, String cusNumber,String msgID);
}
