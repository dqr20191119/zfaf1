package com.cesgroup.prison.alarm.record.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AlarmRecordParam {
    //告警人ID
    private String uId;
    //warnDefId 1:一键告警 2 访客滞留 3:押运脱离 4重点区域告警
    private String warnDefId;
    //告警描述
    private String warnRemark;
    //告警时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date warnTime;
    //监狱编号
    private String pOrgId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getWarnDefId() {
        return warnDefId;
    }

    public void setWarnDefId(String warnDefId) {
        this.warnDefId = warnDefId;
    }

    public String getWarnRemark() {
        return warnRemark;
    }

    public void setWarnRemark(String warnRemark) {
        this.warnRemark = warnRemark;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public String getpOrgId() {
        return pOrgId;
    }

    public void setpOrgId(String pOrgId) {
        this.pOrgId = pOrgId;
    }
}
