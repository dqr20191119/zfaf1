package com.cesgroup.prison.fm.handle.bfcf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;
/**
 * Description: 接收前置机布防撤防消息处理类
 * 
 * @author lincoln.cheng
 * 
 * 
 */
@Service
public class BfCfMessageHandle {
	final Logger logger = LoggerFactory.getLogger(BfCfMessageHandle.class);
	private MsgDispatcher msgDispatcher;
	public BfCfMessageHandle(){
		msgDispatcher=new MsgDispatcher();
	}
    /**
     * 布防撤防消息
     * @param message
     */
	public void handle(String message,String cusNumber) {
		try {
			logger.info("收到布防撤防消息：cusNumber = " + cusNumber + "，msg = " + message);
			msgDispatcher.dispatch(cusNumber, message);
		} catch (Exception e) {
			logger.error("转发布防撤防消息发生异常" + e);
		}
	}
	
}
