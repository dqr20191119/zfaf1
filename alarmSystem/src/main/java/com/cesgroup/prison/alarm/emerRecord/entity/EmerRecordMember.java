package com.cesgroup.prison.alarm.emerRecord.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: 01程序
 * @description:
 * @author: Mr.li
 * @create: 2019-11-18 14:42
 */

@Entity
@Table(name = "CDS_EMER_HANDLE_RECORD_MEMBER")
public class EmerRecordMember extends StringIDEntity {

    /**
     * 单位编号
     */
    private String cusNumber;
    /**
     * 应急处置记录ID
     */
    private String recordId;
    /**
     * 应急处置记录的工作组ID
     */
    private String recordGroupId;
    /**
     * 民警警号
     */
    private String memberJobNo;
    /**
     * 成员姓名
     */
    private String memberName;
    /**
     * 呼叫号码
     */
    private String callNo;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 呼叫状态(0:未呼叫; 1:已呼叫.)
     */
    private String callStatus;
    /**
     * 呼叫时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date callTime;
    /**
     * 呼叫结果
     */
    private String callResult;
    /**
     * 应急人员数据来源（0:用户自定义; 1:系统接口同步）
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

    public String getRecordGroupId() {
        return recordGroupId;
    }

    public void setRecordGroupId(String recordGroupId) {
        this.recordGroupId = recordGroupId;
    }

    public String getMemberJobNo() {
        return memberJobNo;
    }

    public void setMemberJobNo(String memberJobNo) {
        this.memberJobNo = memberJobNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public String getCallResult() {
        return callResult;
    }

    public void setCallResult(String callResult) {
        this.callResult = callResult;
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

}