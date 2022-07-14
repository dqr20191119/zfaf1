package com.cesgroup.prison.alarm.plan.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 报警预案与应急预案关联关系
 *
 * AlarmEmerPlanRltn
 *
 * @author cheng.jie
 * @created Create Time: Wed May 27 16:34:26 CST 2020
 */
@Entity
@Table(name = "CDS_ALARM_EMER_PLAN_RLTN", schema = "PRISON")
public class AlarmEmerPlanRltn extends StringIDEntity {
    /**
     * 单位编号
     */
    private String cusNumber;

    /**
     * 报警预案ID
     */
    private String alarmPlanId;

    /**
     * 应急预案ID
     */
    private String emerPlanId;

    /**
     * 数据状态(0:有效; 1:删除)
     */
    private String status;

    /**
     * 显示排序
     */
    private Long showOrder;

    /**
     * 修改人
     */
    private String updateUserId;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getAlarmPlanId() {
        return alarmPlanId;
    }

    public void setAlarmPlanId(String alarmPlanId) {
        this.alarmPlanId = alarmPlanId;
    }

    public String getEmerPlanId() {
        return emerPlanId;
    }

    public void setEmerPlanId(String emerPlanId) {
        this.emerPlanId = emerPlanId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}