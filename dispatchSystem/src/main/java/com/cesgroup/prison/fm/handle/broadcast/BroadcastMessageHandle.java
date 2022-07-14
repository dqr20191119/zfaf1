package com.cesgroup.prison.fm.handle.broadcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;
/**
 * Description: 接收前置机广播消息处理类
 * 
 * @author lincoln.cheng
 * 
 * 2019年2月22日
 */
@Service
public class BroadcastMessageHandle {
	final Logger logger = LoggerFactory.getLogger(BroadcastMessageHandle.class);
	private MsgDispatcher msgDispatcher;
	public BroadcastMessageHandle(){
		msgDispatcher=new MsgDispatcher();
	}
    /**
     * 广播消息
     * @param message
     */
	public void handle(String message,String cusNumber) {
		try {
			logger.info("收到广播消息：cusNumber = " + cusNumber + "，msg = " + message);
			msgDispatcher.dispatch(cusNumber, message);
		} catch (Exception e) {
			logger.error("转发广播消息发生异常" + e);
		}
	}
}
