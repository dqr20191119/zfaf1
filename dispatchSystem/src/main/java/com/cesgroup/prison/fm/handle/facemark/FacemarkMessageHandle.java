package com.cesgroup.prison.fm.handle.facemark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cesgroup.fm.MsgDispatcher;

public class FacemarkMessageHandle {
	final Logger logger = LoggerFactory.getLogger(FacemarkMessageHandle.class);
    /**
     * 消息转发器
     */
    private MsgDispatcher msgDispatcher;
    /**
     * 构造函数，消息转发器初始化
     */
    public FacemarkMessageHandle() {
        msgDispatcher = new MsgDispatcher();
    }

    /**
     * 接收处人脸识别发来的黑名单消息
     * @param message
     * @param cusNumber
     */
    public void handle(String message, String cusNumber) {
        msgDispatcher.dispatch(cusNumber, message);
    }
}
