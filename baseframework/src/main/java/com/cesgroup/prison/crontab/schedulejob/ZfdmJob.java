package com.cesgroup.prison.crontab.schedulejob;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *@ClassName ZfdmJob
 *@Description 定时同步罪犯点名信息
 *@Author lh
 *@Date 2020/8/17 17:02
 *
 **/

public class ZfdmJob  {


    public void task(){

        try {
            System.out.println("开始执行任务lh...........................");
           /* Db.use().executeBatch("delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4304",
                    "delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4336",
                    "delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4312",
                    "delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4303");

            Db.use().executeBatch("insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS",
                    "insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS_HH",
                    "insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS_YN",
                    "insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS_YY");*/
            /*session.beginTransaction();
            session.executeBatch("delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4304",
                    "delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4336",
                    "delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4312",
                    "delete from PRISON.T_ZFDM_DTLS_HZ_NOW where CUS_NUMBER =4303");
            session.commit();

            session.beginTransaction();
            session.executeBatch("insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS",
                    "insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS_HH",
                    "insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS_YN",
                    "insert into PRISON.T_ZFDM_DTLS_HZ_NOW  select * from GJDM.T_ZFDM_DTLS_YY");
            session.commit();*/
            System.out.println("执行任务结束lh...........................");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
