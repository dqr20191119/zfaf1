package com.cesgroup.prison.zbgl.zbbp.dto;

import java.util.List;
import java.util.Map;

/**
 *@ClassName OrderDto
 *@Description TODO
 *@Author lh
 *@Date 2020/12/2 15:39
 *
 **/
public class OrderDto {
    private String orderIdName;
    private String startDate;
    private String endDate;
    private List<String> job;

    public String getOrderIdName() {
        return orderIdName;
    }

    public void setOrderIdName(String orderIdName) {
        this.orderIdName = orderIdName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getJob() {
        return job;
    }

    public void setJob(List<String> job) {
        this.job = job;
    }
}
