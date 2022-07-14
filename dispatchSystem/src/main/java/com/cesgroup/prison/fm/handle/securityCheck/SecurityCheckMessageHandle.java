package com.cesgroup.prison.fm.handle.securityCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;
/**
 * Description: 接收前置机安检消息处理类
 * 
 * @author lincoln.cheng
 * 
 * 2019年2月22日
 */
@Service
public class SecurityCheckMessageHandle {
	final Logger logger = LoggerFactory.getLogger(SecurityCheckMessageHandle.class);
	private MsgDispatcher msgDispatcher;
	public SecurityCheckMessageHandle(){
		msgDispatcher=new MsgDispatcher();
	}
    /**
     * 安检消息
     * @param message
     */
	public void handle(String message,String cusNumber) {
		logger.info("收到安检消息：cusNumber + " + cusNumber + "msg = " + message);
		msgDispatcher.dispatch(cusNumber,message);
	}
}
