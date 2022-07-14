package com.cesgroup.prison.fm.handle.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;
/**
 * Description: 接收前置机民警在监位置消息处理类
 * 
 * @author lincoln.cheng
 * 
 * 2019年2月22日
 */
@Service
public class PoliceLocationMessageHandle {
	final Logger logger = LoggerFactory.getLogger(PoliceLocationMessageHandle.class);
	private MsgDispatcher msgDispatcher;
	public PoliceLocationMessageHandle(){
		msgDispatcher=new MsgDispatcher();
	}
    /**
     * 民警在监位置消息
     * @param message
     */
	public void handle(String message,String cusNumber) {
		logger.info("收到前置机民警在监位置消息：cusNumber + " + cusNumber + "msg = " + message);
		msgDispatcher.dispatch(cusNumber,message);
	}
}
