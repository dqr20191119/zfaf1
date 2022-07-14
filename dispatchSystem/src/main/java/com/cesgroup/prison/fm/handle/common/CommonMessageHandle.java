package com.cesgroup.prison.fm.handle.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;
/**
 * cesgroup
 * 接收前置机消息处理类
 * @author lihh
 */
@Service
public class CommonMessageHandle {
	final Logger logger = LoggerFactory.getLogger(CommonMessageHandle.class);
	private MsgDispatcher msgDispatcher;
	public CommonMessageHandle(){
		msgDispatcher=new MsgDispatcher();
	}
    /**
     * 报警
     * @param message
     */
	public void handle(String message,String cusNumber) {
		logger.info("收到前置机消息：cusNumber="+cusNumber+"msg="+message);
		msgDispatcher.dispatch(cusNumber,message);
	}
}
