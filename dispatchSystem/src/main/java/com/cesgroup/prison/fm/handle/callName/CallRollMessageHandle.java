package com.cesgroup.prison.fm.handle.callName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;

/**
 * 湖南前置机点名
 * **/ 
@Service
public class CallRollMessageHandle {
	final Logger logger = LoggerFactory.getLogger(CallRollMessageHandle.class);
	private MsgDispatcher msgDispatcher;

	public CallRollMessageHandle() {
		msgDispatcher = new MsgDispatcher();
	}

	public void handle(String message, String cusNumber) {
		logger.info("收到前置机点名消息： cusNumber= "+cusNumber+" msg= "+message);
		msgDispatcher.dispatch(cusNumber, message);
	}
}
