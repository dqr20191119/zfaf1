package com.cesgroup.prison.fm.handle.outsiders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cesgroup.fm.MsgDispatcher;
import com.cesgroup.prison.fm.handle.facemark.FacemarkMessageHandle;

public class OutsidersMessageHandle {
	final Logger logger = LoggerFactory.getLogger(FacemarkMessageHandle.class);
    /**
     * 消息转发器
     */
    private MsgDispatcher msgDispatcher;
    /**
     * 构造函数，消息转发器初始化
     */
    public OutsidersMessageHandle() {
        msgDispatcher = new MsgDispatcher();
    }

    /**
     * 外来人员
     * @param message
     * @param cusNumber
     */
    public void handle(String message, String cusNumber) {
        msgDispatcher.dispatch(cusNumber, message);
    }
}
