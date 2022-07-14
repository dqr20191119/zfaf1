package com.cesgroup.fm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgDispatcher{
	private MsghreadGroup msghreadGroup = null;
	private final Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);
	public MsgDispatcher(){
		init();
	}
	private void init() {
		this.msghreadGroup = new MsghreadGroup();
	}
	public void dispatch(String cusNumber,String msg) {
		try {
			Runnable runnable = createMsgRunnable(cusNumber,msg);
			if(runnable!=null){
				msghreadGroup.execute(runnable);
			}
        } catch (Throwable ex) {
            logger.error("消息分派发生异常",ex);
            logger.error("异常的消息内容："+msg);
        }

	}

	/**
	 * 创建消息处理Runnabl
	 * @param msg
	 * @return
	 */
	private Runnable createMsgRunnable(String cusNumber,String msg) {
		try {
            MsgHandlerRunnable msgHandlerRunnable;
            msgHandlerRunnable = new MsgHandlerRunnable(cusNumber,msg);
			return msgHandlerRunnable;
		} catch (Throwable ex){
            throw new RuntimeException("createMsgRunnable异常",ex);
        }
	}
}
