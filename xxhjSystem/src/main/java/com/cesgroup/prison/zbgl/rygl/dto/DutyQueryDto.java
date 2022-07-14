package com.cesgroup.prison.zbgl.rygl.dto;

import java.io.Serializable;

/**
 *@ClassName DutyQueryDto
 *@Description TODO
 *@Author lh
 *@Date 2020/8/24 14:53
 *
 **/
public class DutyQueryDto implements Serializable {


    private static final long serialVersionUID = 7549684144662392849L;

    private String cusNumber;
    private String dutyOrderName;
    private String jobName;
    private String job;
    private String dutyDate;

    public String getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate;
    }



    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getDutyOrderName() {
        return dutyOrderName;
    }

    public void setDutyOrderName(String dutyOrderName) {
        this.dutyOrderName = dutyOrderName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
