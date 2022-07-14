package com.cesgroup.prison.emergency.member.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 应急工作组成员表
 */
@Entity
@Table(name = "T_EMER_MEMBER")
public class EmerMember extends StringIDEntity {
    /**
     * 监狱/单位编号
     */
    private String cusNumber;
    /**
     * 民警警号
     */
    private String jobNo;
    /**
     * 成员名称
     */
    private String name;
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
     * 数据来源(0:用户自定义; 1:系统同步)
     */
    private String source;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 排序
     */
    private Long showOrder;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改人
     */
    private String updateUserId;
    /**
     * 修改时间
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

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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