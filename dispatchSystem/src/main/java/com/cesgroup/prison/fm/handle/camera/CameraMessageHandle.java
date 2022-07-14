package com.cesgroup.prison.fm.handle.camera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;
/**
 * cesgroup
 * 接收前置机摄像机消息处理类
 * @author zxh
 */
@Service
public class CameraMessageHandle {
	final Logger logger = LoggerFactory.getLogger(CameraMessageHandle.class);
	private MsgDispatcher msgDispatcher;
	public CameraMessageHandle(){
		msgDispatcher=new MsgDispatcher();
	}

	
	public void handle(String message,String cusNumber) {
		// logger.info("收到前置机门禁消息：cusNumber+"+cusNumber+"msg="+message);
		msgDispatcher.dispatch(cusNumber,message);
	}
}
