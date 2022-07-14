package com.cesgroup.prison.emergency.stepGroup.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 应急处置
 */
@Entity
@Table(name = "T_EMER_STEP_GROUP")
public class EmerStepGroup extends StringIDEntity {
    /**
     * 监狱/单位编号
     */
    private String cusNumber;
    /**
     * 处置步骤编号
     */
    private String stepId;
    /**
     * 应急工作组编号
     */
    private String groupId;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 展示排序
     */
    private Long showOrder;
    /**
     * 更新人
     */
    private String updateUserId;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Override
    @Id
    @Column(length = 32, nullable = false)
    public String getId() {
        return id;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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