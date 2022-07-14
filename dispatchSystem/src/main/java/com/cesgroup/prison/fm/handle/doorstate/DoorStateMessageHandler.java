package com.cesgroup.prison.fm.handle.doorstate;

import com.cesgroup.fm.MsgDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 11:05 2019/5/22
 * @ Description：接收门禁状态处理类
 */
@Service
public class DoorStateMessageHandler {
    final Logger logger = LoggerFactory.getLogger(DoorStateMessageHandler.class);
    /**
     * 消息转发
     */
    private MsgDispatcher msgDispatcher;

    /**
     * 构造函数 消息转发初始化
     */
    public DoorStateMessageHandler() {
        msgDispatcher = new MsgDispatcher();
    }

    /**
     * 门禁状态
     *
     * @param message
     * @param cusNumber
     */
    public void handle(String message, String cusNumber) {

        msgDispatcher.dispatch(cusNumber, message);
    }


}
