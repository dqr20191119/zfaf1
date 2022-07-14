package com.cesgroup.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cesgroup.frm.net.netty.util.DateUtil;

public class MessageLog {
	private static final Logger logger = LoggerFactory.getLogger(MessageLog.class);
    public static void printLog(String msgType,String msg){
    	logger.debug(DateUtil.getTodayString()+" receive msg:{}", msg);
    }
}
