package com.cesgroup.prison.fm.handle.patrolrecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cesgroup.fm.MsgDispatcher;
import com.cesgroup.prison.fm.handle.facemark.FacemarkMessageHandle;

public class PatrolrecordMessageHandle {
	final Logger logger = LoggerFactory.getLogger(FacemarkMessageHandle.class);
    /**
     * 消息转发器
     */
    private MsgDispatcher msgDispatcher;
    /**
     * 构造函数，消息转发器初始化
     */
    public PatrolrecordMessageHandle() {
        msgDispatcher = new MsgDispatcher();
    }

    /**
     * 接收巡更记录
     * @param message
     * @param cusNumber
     */
    public void handle(String message, String cusNumber) {
        msgDispatcher.dispatch(cusNumber, message);
    }
}
