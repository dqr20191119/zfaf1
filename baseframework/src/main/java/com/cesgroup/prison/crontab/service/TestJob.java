package com.cesgroup.prison.crontab.service;

import org.springframework.stereotype.Component;

/**
 * Created by wxl on 2017/8/8.
 */
@Component("testJob")
public class TestJob {
    public void doJob() {
    	System.out.println("***********************************************************");
        System.out.println("*I am the job template, must can be found in the container*");
        System.out.println("***********************************************************");
    }
}
