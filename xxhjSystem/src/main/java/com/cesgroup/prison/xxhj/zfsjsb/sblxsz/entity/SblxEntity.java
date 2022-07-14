package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cds_prison_report_type")
public class SblxEntity extends StringIDEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -5319092071869191100L;

    //监狱编码
    private String prtCusNumber ;

    //类型编码
    private String  prtTypeCode ;

    //类型名称
    private String  prtTypeName ;

    //备注
    private String  prtRemark ;

    @Column(updatable = false)
    private Date  prtCrteTime ;

    @Column(updatable = false)
    private String  prtCrteUserId ;

    @Column(updatable = false)
    private String prtCrteUserName;

    private Date  prtUpdtTime;

    private String  prtUpdtUserId ;

    private String  prtUpdtUserName ;

    //是否有效 group_key 4.0.1; 1-是; 0-否
    private String  prtStatus ;


    public String getPrtCusNumber() {
        return prtCusNumber;
    }

    public void setPrtCusNumber(String prtCusNumber) {
        this.prtCusNumber = prtCusNumber;
    }

    public String getPrtTypeCode() {
        return prtTypeCode;
    }

    public void setPrtTypeCode(String prtTypeCode) {
        this.prtTypeCode = prtTypeCode;
    }

    public String getPrtTypeName() {
        return prtTypeName;
    }

    public void setPrtTypeName(String prtTypeName) {
        this.prtTypeName = prtTypeName;
    }

    public String getPrtRemark() {
        return prtRemark;
    }

    public void setPrtRemark(String prtRemark) {
        this.prtRemark = prtRemark;
    }

    public Date getPrtCrteTime() {
        return prtCrteTime;
    }

    public void setPrtCrteTime(Date prtCrteTime) {
        this.prtCrteTime = prtCrteTime;
    }

    public String getPrtCrteUserId() {
        return prtCrteUserId;
    }

    public void setPrtCrteUserId(String prtCrteUserId) {
        this.prtCrteUserId = prtCrteUserId;
    }

    public Date getPrtUpdtTime() {
        return prtUpdtTime;
    }

    public void setPrtUpdtTime(Date prtUpdtTime) {
        this.prtUpdtTime = prtUpdtTime;
    }

    public String getPrtUpdtUserId() {
        return prtUpdtUserId;
    }

    public void setPrtUpdtUserId(String prtUpdtUserId) {
        this.prtUpdtUserId = prtUpdtUserId;
    }

    public String getPrtStatus() {
        return prtStatus;
    }

    public void setPrtStatus(String prtStatus) {
        this.prtStatus = prtStatus;
    }

    public String getPrtCrteUserName() {
        return prtCrteUserName;
    }

    public void setPrtCrteUserName(String prtCrteUserName) {
        this.prtCrteUserName = prtCrteUserName;
    }

    public String getPrtUpdtUserName() {
        return prtUpdtUserName;
    }

    public void setPrtUpdtUserName(String prtUpdtUserName) {
        this.prtUpdtUserName = prtUpdtUserName;
    }
}
