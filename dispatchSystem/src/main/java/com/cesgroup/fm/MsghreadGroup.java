package com.cesgroup.fm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cesgroup.framework.util.ConfigUtil;


public class MsghreadGroup {
    private ExecutorService executorService;
    public MsghreadGroup(){
    	int threadNum = 10;
    	try {
    		threadNum = Integer.valueOf(ConfigUtil.get("frontMachineMsgThreadNum")).intValue();
		} catch (NumberFormatException e) {
		}
        //创建线程池
    	executorService = Executors.newFixedThreadPool(threadNum);
    }
    public MsghreadGroup(int nThreads) {
        //创建线程池
    	executorService = Executors.newFixedThreadPool(nThreads);
    }

    public void execute(Runnable runnable) {
    	executorService.execute(runnable);
    }

    public void shutdown() {
    	executorService.shutdown();
    }

}
