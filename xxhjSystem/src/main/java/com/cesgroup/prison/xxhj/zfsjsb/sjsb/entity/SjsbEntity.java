package com.cesgroup.prison.xxhj.zfsjsb.sjsb.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "cds_prison_report")
public class SjsbEntity extends StringIDEntity {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -5319092071869191100L;

    //部门ID
    private String cprDprtmntId ;

    //类型编码
    private String cprTypeCode ;

    //汇报数量
    private String cprNumber ;

    //汇报期数YYYYMMDD
    private String cprTime ;

    @Column(updatable = false)
    private Date cprCrteTime ;

    @Column(updatable = false)
    private String cprCrteUserId ;

    @Column(updatable = false)
    private String cprCrteUserName ;

    private Date cprUpdtTime ;

    private String cprUpdtUserId ;

    private String cprUpdtUserName ;

    private String cprRemark;

    private String cprStatus;

    private String cprCusNumber;

    public String getCprDprtmntId() {
        return cprDprtmntId;
    }

    public void setCprDprtmntId(String cprDprtmntId) {
        this.cprDprtmntId = cprDprtmntId;
    }

    public String getCprTypeCode() {
        return cprTypeCode;
    }

    public void setCprTypeCode(String cprTypeCode) {
        this.cprTypeCode = cprTypeCode;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public String getCprTime() {
        return cprTime;
    }

    public void setCprTime(String cprTime) {
        this.cprTime = cprTime;
    }

    public Date getCprCrteTime() {
        return cprCrteTime;
    }

    public void setCprCrteTime(Date cprCrteTime) {
        this.cprCrteTime = cprCrteTime;
    }

    public String getCprCrteUserId() {
        return cprCrteUserId;
    }

    public void setCprCrteUserId(String cprCrteUserId) {
        this.cprCrteUserId = cprCrteUserId;
    }

    public Date getCprUpdtTime() {
        return cprUpdtTime;
    }

    public void setCprUpdtTime(Date cprUpdtTime) {
        this.cprUpdtTime = cprUpdtTime;
    }

    public String getCprUpdtUserId() {
        return cprUpdtUserId;
    }

    public void setCprUpdtUserId(String cprUpdtUserId) {
        this.cprUpdtUserId = cprUpdtUserId;
    }

    public String getCprCrteUserName() {
        return cprCrteUserName;
    }

    public void setCprCrteUserName(String cprCrteUserName) {
        this.cprCrteUserName = cprCrteUserName;
    }

    public String getCprUpdtUserName() {
        return cprUpdtUserName;
    }

    public void setCprUpdtUserName(String cprUpdtUserName) {
        this.cprUpdtUserName = cprUpdtUserName;
    }

    public String getCprRemark() {
        return cprRemark;
    }

    public void setCprRemark(String cprRemark) {
        this.cprRemark = cprRemark;
    }

    public String getCprStatus() {
        return cprStatus;
    }

    public void setCprStatus(String cprStatus) {
        this.cprStatus = cprStatus;
    }

    public String getCprCusNumber() {
        return cprCusNumber;
    }

    public void setCprCusNumber(String cprCusNumber) {
        this.cprCusNumber = cprCusNumber;
    }
}
