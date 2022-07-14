package com.cesgroup.prison.emergency.handle.recordGroup.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CDS_EMER_HANDLE_RECORD_GROUP")
public class EmerHandleRecordGroup extends StringIDEntity {
    /**
     * 单位编号
     */
    private String cusNumber;
    /**
     * 处置记录ID
     */
    private String recordId;
    /**
     * 应急处置记录的处置步骤ID
     */
    private String recordStepId;
    /**
     * 工作组名称
     */
    private String groupName;
    /**
     * 获取成员方式(0:预定义; 1:即时获取; 2:预定义+即时获取;)
     */
    private String getMemberWay;
    /**
     * 工作组通知内容
     */
    private String groupNotice;
    /**
     * 工作组通知状态(0:未通知; 1:已通知.)
     */
    private String groupNoticeStatus;
    /**
     * 通知时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date groupNoticeTime;
    /**
     * 数据来源（0:用户自定义; 1:系统接口同步）
     */
    private String source;
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
     * 应急处置记录的处置人员列表
     */
    @Transient
    private List<EmerHandleRecordMember> handleMemberList;

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

    public String getRecordStepId() {
        return recordStepId;
    }

    public void setRecordStepId(String recordStepId) {
        this.recordStepId = recordStepId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGetMemberWay() {
        return getMemberWay;
    }

    public void setGetMemberWay(String getMemberWay) {
        this.getMemberWay = getMemberWay;
    }

    public String getGroupNotice() {
        return groupNotice;
    }

    public void setGroupNotice(String groupNotice) {
        this.groupNotice = groupNotice;
    }

    public String getGroupNoticeStatus() {
        return groupNoticeStatus;
    }

    public void setGroupNoticeStatus(String groupNoticeStatus) {
        this.groupNoticeStatus = groupNoticeStatus;
    }

    public Date getGroupNoticeTime() {
        return groupNoticeTime;
    }

    public void setGroupNoticeTime(Date groupNoticeTime) {
        this.groupNoticeTime = groupNoticeTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public List<EmerHandleRecordMember> getHandleMemberList() {
        return handleMemberList;
    }

    public void setHandleMemberList(List<EmerHandleRecordMember> handleMemberList) {
        this.handleMemberList = handleMemberList;
    }
}