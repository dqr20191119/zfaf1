package com.cesgroup.prison.zbgl.rygl.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *@ClassName RyztHistoryEntity
 *@Description 人员状态历史表
 *@Author lh
 *@Date 2020/9/21 14:10
 *
 **/
@Table(name = "CDS_DUTY_ZBRYGL_RYZT_H")
public class RyztHistoryEntity extends StringIDEntity {
    /**
     * 值班人员id
     */
    private String zbryId;
    /**
     * 人员状态  1.在编2.借调 3.退休4.培训5.病假 6.其他
     */
    private String ryzt;
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 备注
     */
    private String remark;
    private String updateTime;
    private String updateId;
    private String updateName;
    @Transient
    private String year;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getZbryId() {
        return zbryId;
    }

    public void setZbryId(String zbryId) {
        this.zbryId = zbryId;
    }

    public String getRyzt() {
        return ryzt;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
}
