package com.cesgroup.prison.xxhj.cgsgxx.vo;

import com.cesgroup.prison.xxhj.cgsgxx.entity.CgsgxxEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CgsgxxVo extends CgsgxxEntity {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pwrTimeStart;           //查询LIST时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pwrTimeEnd;           //查询LIST时间

    public Date getPwrTimeStart() {
        return pwrTimeStart;
    }

    public void setPwrTimeStart(Date pwrTimeStart) {
        this.pwrTimeStart = pwrTimeStart;
    }

    public Date getPwrTimeEnd() {
        return pwrTimeEnd;
    }

    public void setPwrTimeEnd(Date pwrTimeEnd) {
        this.pwrTimeEnd = pwrTimeEnd;
    }
}
