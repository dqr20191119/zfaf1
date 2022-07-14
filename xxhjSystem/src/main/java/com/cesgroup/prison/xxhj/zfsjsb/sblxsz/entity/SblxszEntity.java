package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cds_prison_report_type_department")
public class SblxszEntity extends StringIDEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -5319092071869191100L;

    //类型编码
    private String prtdTypeCode ;

    //部门ID
    private String prtdDprtmntId ;

    //是否参与汇总计算 group_key 4.0.1; 1-是; 0-否
    private String prtdIsCalc ;

    //备注
    private String prtdRemark ;

    @Column(updatable = false)
    private Date prtdCrteTime ;

    @Column(updatable = false)
    private String prtdCrteUserId ;

    @Column(updatable = false)
    private String prtdCrteUserName ;

    private Date prtdUpdtTime ;

    private String prtdUpdtUserId ;

    private String prtdUpdtUserName ;

    //是否有效 group_key 4.0.1; 1-是; 0-否
    private String prtdStatus ;

    public String getPrtdTypeCode() {
        return prtdTypeCode;
    }

    public void setPrtdTypeCode(String prtdTypeCode) {
        this.prtdTypeCode = prtdTypeCode;
    }

    public String getPrtdDprtmntId() {
        return prtdDprtmntId;
    }

    public void setPrtdDprtmntId(String prtdDprtmntId) {
        this.prtdDprtmntId = prtdDprtmntId;
    }

    public String getPrtdIsCalc() {
        return prtdIsCalc;
    }

    public void setPrtdIsCalc(String prtdIsCalc) {
        this.prtdIsCalc = prtdIsCalc;
    }

    public String getPrtdRemark() {
        return prtdRemark;
    }

    public void setPrtdRemark(String prtdRemark) {
        this.prtdRemark = prtdRemark;
    }

    public Date getPrtdCrteTime() {
        return prtdCrteTime;
    }

    public void setPrtdCrteTime(Date prtdCrteTime) {
        this.prtdCrteTime = prtdCrteTime;
    }

    public String getPrtdCrteUserId() {
        return prtdCrteUserId;
    }

    public void setPrtdCrteUserId(String prtdCrteUserId) {
        this.prtdCrteUserId = prtdCrteUserId;
    }

    public Date getPrtdUpdtTime() {
        return prtdUpdtTime;
    }

    public void setPrtdUpdtTime(Date prtdUpdtTime) {
        this.prtdUpdtTime = prtdUpdtTime;
    }

    public String getPrtdUpdtUserId() {
        return prtdUpdtUserId;
    }

    public void setPrtdUpdtUserId(String prtdUpdtUserId) {
        this.prtdUpdtUserId = prtdUpdtUserId;
    }

    public String getPrtdStatus() {
        return prtdStatus;
    }

    public void setPrtdStatus(String prtdStatus) {
        this.prtdStatus = prtdStatus;
    }

    public String getPrtdCrteUserName() {
        return prtdCrteUserName;
    }

    public void setPrtdCrteUserName(String prtdCrteUserName) {
        this.prtdCrteUserName = prtdCrteUserName;
    }

    public String getPrtdUpdtUserName() {
        return prtdUpdtUserName;
    }

    public void setPrtdUpdtUserName(String prtdUpdtUserName) {
        this.prtdUpdtUserName = prtdUpdtUserName;
    }
}
