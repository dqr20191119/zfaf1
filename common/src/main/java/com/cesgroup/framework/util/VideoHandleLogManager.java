package com.cesgroup.framework.util;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;



/**
 * cesgroup
 * 视频操作日志管理
 * @author lihh
 */
@Service
public class VideoHandleLogManager implements Runnable{
	private static final Logger log = LoggerFactory.getLogger(VideoHandleLogManager.class);
	private static int queueSize = 100;// 列队最大值
	private static BlockingQueue<List<Object>> queue;

	//@Autowired
	//private ISysLogService sysLogService;

	//public void setSysLogService(ISysLogService sysLogService) {
	//	this.sysLogService = sysLogService;
	//}

	private VideoHandleLogManager(){
		queueSize = Tools.toInt( ConfigUtil.get( "SYS_LOG_QUEUE_MAX_SIZE" ) );
		queue = new ArrayBlockingQueue<List<Object>>( queueSize );
		new Thread(this).start();
	}

	@Override
	public void run() {
		List<Object> params = null;
		while (true) {
			try {
				params = queue.take();
				//sysLogService.addVideoHandleLog(params);
			} catch (Exception e) {
				log.error("保存系统操作日志异常：参数<" + JSON.toJSONString( params ) + "> | " + e.getMessage());
			}
		}
	}

	/**
	 * 插入日志
	 * @param args	插入参数
	 */
	public static void insert(List<Object> args) {
		try{
			queue.put( args );
		} catch (Exception ex) {
			VideoHandleLogManager.log.error("保存系统操作日志异常：" + ex.getMessage());
		}
	}
}
