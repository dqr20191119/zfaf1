package com.cesgroup.prison.fm.handle.rcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cesgroup.fm.MsgDispatcher;

/**
 * Description: 接收融合通讯消息处理类
 * 
 * @author lincoln.cheng
 *
 */
@Service
public class RcsMessageHandle {
	final Logger logger = LoggerFactory.getLogger(RcsMessageHandle.class);
    /**
     * 消息转发器
     */
    private MsgDispatcher msgDispatcher;
    /**
     * 构造函数，消息转发器初始化
     */
    public RcsMessageHandle() {
        msgDispatcher = new MsgDispatcher();
    }

    /**
     * 接收处理融合通讯设备发来的消息
     * @param message
     * @param cusNumber
     */
    public void handle(String message, String cusNumber) {
		logger.info("收到融合通讯消息： cusNumber= " + cusNumber + " msg= " + message);
        msgDispatcher.dispatch(cusNumber, message);
    }
}
