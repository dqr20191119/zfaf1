package com.cesgroup.prison.change.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 整改部门
 * @author zhengk
 * @date 2018-03-26
 *
 */
@Table(name = "CDS_CIRCULAR_CHANGED_DPRTMNT")
public class ChangeDepartment   extends StringIDEntity{
  
    private String ccdCusNumber;

    private String ccdChangedId;

    private String ccdDprtmntIdnty;

    private String ccdDprtmntName;

    private String ccdFlowIdnty;

    private String ccdBranchLeaders;

    private String ccdManagerLeaders;

    private String ccdStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ccdCrteTime;

    private String ccdCrteUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ccdUpdtTime;

    private String ccdUpdtUserId;

    private String ccdChangedContent;

    private String ccdBranchLeadersName;

    private String ccdManagerLeadersName;

    public String getCcdDprtmntIdnty() {
        return ccdDprtmntIdnty;
    }

    public void setCcdDprtmntIdnty(String ccdDprtmntIdnty) {
        this.ccdDprtmntIdnty = ccdDprtmntIdnty == null ? null : ccdDprtmntIdnty.trim();
    }

    public String getCcdDprtmntName() {
        return ccdDprtmntName;
    }

    public void setCcdDprtmntName(String ccdDprtmntName) {
        this.ccdDprtmntName = ccdDprtmntName == null ? null : ccdDprtmntName.trim();
    }

    public String getCcdFlowIdnty() {
        return ccdFlowIdnty;
    }

    public void setCcdFlowIdnty(String ccdFlowIdnty) {
        this.ccdFlowIdnty = ccdFlowIdnty == null ? null : ccdFlowIdnty.trim();
    }

    public String getCcdBranchLeaders() {
        return ccdBranchLeaders;
    }

    public void setCcdBranchLeaders(String ccdBranchLeaders) {
        this.ccdBranchLeaders = ccdBranchLeaders == null ? null : ccdBranchLeaders.trim();
    }

    public String getCcdManagerLeaders() {
        return ccdManagerLeaders;
    }

    public void setCcdManagerLeaders(String ccdManagerLeaders) {
        this.ccdManagerLeaders = ccdManagerLeaders == null ? null : ccdManagerLeaders.trim();
    }

    public Date getCcdCrteTime() {
        return ccdCrteTime;
    }

    public void setCcdCrteTime(Date ccdCrteTime) {
        this.ccdCrteTime = ccdCrteTime;
    }

    public String getCcdCrteUserId() {
        return ccdCrteUserId;
    }

    public void setCcdCrteUserId(String ccdCrteUserId) {
        this.ccdCrteUserId = ccdCrteUserId == null ? null : ccdCrteUserId.trim();
    }

    public Date getCcdUpdtTime() {
        return ccdUpdtTime;
    }

    public void setCcdUpdtTime(Date ccdUpdtTime) {
        this.ccdUpdtTime = ccdUpdtTime;
    }

    public String getCcdUpdtUserId() {
        return ccdUpdtUserId;
    }

    public void setCcdUpdtUserId(String ccdUpdtUserId) {
        this.ccdUpdtUserId = ccdUpdtUserId == null ? null : ccdUpdtUserId.trim();
    }

    public String getCcdChangedContent() {
        return ccdChangedContent;
    }

    public void setCcdChangedContent(String ccdChangedContent) {
        this.ccdChangedContent = ccdChangedContent == null ? null : ccdChangedContent.trim();
    }

    public String getCcdBranchLeadersName() {
        return ccdBranchLeadersName;
    }

    public void setCcdBranchLeadersName(String ccdBranchLeadersName) {
        this.ccdBranchLeadersName = ccdBranchLeadersName == null ? null : ccdBranchLeadersName.trim();
    }

    public String getCcdManagerLeadersName() {
        return ccdManagerLeadersName;
    }

    public void setCcdManagerLeadersName(String ccdManagerLeadersName) {
        this.ccdManagerLeadersName = ccdManagerLeadersName == null ? null : ccdManagerLeadersName.trim();
    }

	public String getCcdCusNumber() {
		return ccdCusNumber;
	}

	public void setCcdCusNumber(String ccdCusNumber) {
		this.ccdCusNumber = ccdCusNumber;
	}

	public String getCcdChangedId() {
		return ccdChangedId;
	}

	public void setCcdChangedId(String ccdChangedId) {
		this.ccdChangedId = ccdChangedId;
	}

	public String getCcdStatus() {
		return ccdStatus;
	}

	public void setCcdStatus(String ccdStatus) {
		this.ccdStatus = ccdStatus;
	}
    
}