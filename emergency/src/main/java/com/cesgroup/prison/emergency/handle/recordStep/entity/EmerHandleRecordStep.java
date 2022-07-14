package com.cesgroup.prison.emergency.handle.recordStep.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CDS_EMER_HANDLE_RECORD_STEP")
public class EmerHandleRecordStep extends StringIDEntity {
    /**
     * 单位编号
     */
    private String cusNumber;
    /**
     * 应急处置记录ID
     */
    private String recordId;
    /**
     * 处置步骤名称
     */
    private String stepName;
    /**
     * 处置状态(0:未处置; 1:处置完成.)
     */
    private String handleStatus;
    /**
     * 处置时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date handleTime;

    /**
     * 处置内容
     */
    private String handleContent;
    /**
     * 数据状态(0:激活; 1:删除;)
     */
    private String status;
    /**
     * 显示排序
     */
    private Long showOrder;
    /**
     * 最近更新人
     */
    private String updateUserId;
    /**
     * 最近更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 应急处置记录的工作组列表
     */
    @Transient
    private List<EmerHandleRecordGroup> handleGroupList;

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

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
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

    public List<EmerHandleRecordGroup> getHandleGroupList() {
        return handleGroupList;
    }

    public void setHandleGroupList(List<EmerHandleRecordGroup> handleGroupList) {
        this.handleGroupList = handleGroupList;
    }
}