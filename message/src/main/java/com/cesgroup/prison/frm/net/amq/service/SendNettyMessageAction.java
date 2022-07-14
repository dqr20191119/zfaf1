package com.cesgroup.prison.frm.net.amq.service;

public interface SendNettyMessageAction<T> {
	
	public void send(T message, String cusNumber);
}
