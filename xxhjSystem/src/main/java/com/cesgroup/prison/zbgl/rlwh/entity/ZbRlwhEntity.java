package com.cesgroup.prison.zbgl.rlwh.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *@ClassName ZbRlwhEntity
 *@Description 值班日历维护表
 *@Author lh
 *@Date 2020/8/18 14:46
 *
 **/
@Table(name = "CDS_DUTY_CALENDAR")
public class ZbRlwhEntity extends StringIDEntity {

    private static final long serialVersionUID = -5992951485706358326L;
    /**
     * 值班日期
     */
    private String dutyDate;
    /**
     * 节假日与工作日标识  0.工作日 1.节假日
     */
    private String flag;

    private String createTime;
    private String createId;
    private String createName;
    /**
     * 周几
     */
    private String weekDay;
    @Transient
    private String startDate;
    @Transient
    private String endDate;

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getDutyDate() {
        return dutyDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ZbRlwhEntity{" +
                "dutyDate='" + dutyDate + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
