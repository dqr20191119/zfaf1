package com.cesgroup.prison.fm.handle.callName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;

 
@Service
public class CallNameMessageHandle {
	final Logger logger = LoggerFactory.getLogger(CallNameMessageHandle.class);
	private MsgDispatcher msgDispatcher;

	public CallNameMessageHandle() {
		msgDispatcher = new MsgDispatcher();
	}

	public void handle(String message, String cusNumber) {
		logger.info("收到前置机点名消息： cusNumber= "+cusNumber+" msg= "+message);
		msgDispatcher.dispatch(cusNumber, message);
	}
}
