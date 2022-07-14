package com.cesgroup.prison.zfjcj.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CDS_PRISONER_INOUT_RECORD")
public class ZfjcjEntity extends StringIDEntity {

    private String pirCusNumber;

    private String pirApprovalStts;

    private String pirApprovalPolice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pirApprovalTime;

    private String pirApprovalPoliceName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pirLjRq;

    private String pirZfBh;


    public String getPirCusNumber() {
        return pirCusNumber;
    }

    public void setPirCusNumber(String pirCusNumber) {
        this.pirCusNumber = pirCusNumber;
    }

    public String getPirApprovalStts() {
        return pirApprovalStts;
    }

    public void setPirApprovalStts(String pirApprovalStts) {
        this.pirApprovalStts = pirApprovalStts;
    }

    public String getPirApprovalTime() {
        return pirApprovalTime;
    }

    public void setPirApprovalTime(String pirApprovalTime) {
        this.pirApprovalTime = pirApprovalTime;
    }

    public String getPirApprovalPoliceName() {
        return pirApprovalPoliceName;
    }

    public void setPirApprovalPoliceName(String pirApprovalPoliceName) {
        this.pirApprovalPoliceName = pirApprovalPoliceName;
    }

    public String getPirLjRq() {
        return pirLjRq;
    }

    public void setPirLjRq(String pirLjRq) {
        this.pirLjRq = pirLjRq;
    }

    public String getPirZfBh() {
        return pirZfBh;
    }

    public void setPirZfBh(String pirZfBh) {
        this.pirZfBh = pirZfBh;
    }

    public String getPirApprovalPolice() {
        return pirApprovalPolice;
    }

    public void setPirApprovalPolice(String pirApprovalPolice) {
        this.pirApprovalPolice = pirApprovalPolice;
    }
}
