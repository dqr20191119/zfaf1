package com.cesgroup.prison.change.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 整改审批
 * @author zhengk
 * @date 2018-03-26
 *
 */
@Table(name = "CDS_CIRCULAR_CHANGED_APPROVAL")
public class ChangeApproval  extends StringIDEntity{
    private String ccaCusNumber;

    private String ccaFlowIdnty;

    private String ccaChangedIdnty;

    private String ccaDprtmntIdnty;

    private String ccaApprovalType;

    private String ccaPoliceIdnty;

    private String ccaPoliceName;

    private String ccaSuggestion;

    private String ccaContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ccaCrteTime;

    private String ccaCrteUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ccaUpdtTime;

    private String ccaUpdtUserId;

    public String getCcaFlowIdnty() {
        return ccaFlowIdnty;
    }

    public void setCcaFlowIdnty(String ccaFlowIdnty) {
        this.ccaFlowIdnty = ccaFlowIdnty == null ? null : ccaFlowIdnty.trim();
    }

    public String getCcaChangedIdnty() {
        return ccaChangedIdnty;
    }

    public void setCcaChangedIdnty(String ccaChangedIdnty) {
        this.ccaChangedIdnty = ccaChangedIdnty == null ? null : ccaChangedIdnty.trim();
    }

    public String getCcaDprtmntIdnty() {
        return ccaDprtmntIdnty;
    }

    public void setCcaDprtmntIdnty(String ccaDprtmntIdnty) {
        this.ccaDprtmntIdnty = ccaDprtmntIdnty == null ? null : ccaDprtmntIdnty.trim();
    }

    public String getCcaPoliceIdnty() {
        return ccaPoliceIdnty;
    }

    public void setCcaPoliceIdnty(String ccaPoliceIdnty) {
        this.ccaPoliceIdnty = ccaPoliceIdnty == null ? null : ccaPoliceIdnty.trim();
    }

    public String getCcaPoliceName() {
        return ccaPoliceName;
    }

    public void setCcaPoliceName(String ccaPoliceName) {
        this.ccaPoliceName = ccaPoliceName == null ? null : ccaPoliceName.trim();
    }

    public String getCcaContent() {
        return ccaContent;
    }

    public void setCcaContent(String ccaContent) {
        this.ccaContent = ccaContent == null ? null : ccaContent.trim();
    }

    public Date getCcaCrteTime() {
        return ccaCrteTime;
    }

    public void setCcaCrteTime(Date ccaCrteTime) {
        this.ccaCrteTime = ccaCrteTime;
    }

    public String getCcaCrteUserId() {
        return ccaCrteUserId;
    }

    public void setCcaCrteUserId(String ccaCrteUserId) {
        this.ccaCrteUserId = ccaCrteUserId == null ? null : ccaCrteUserId.trim();
    }

    public Date getCcaUpdtTime() {
        return ccaUpdtTime;
    }

    public void setCcaUpdtTime(Date ccaUpdtTime) {
        this.ccaUpdtTime = ccaUpdtTime;
    }

    public String getCcaUpdtUserId() {
        return ccaUpdtUserId;
    }

    public void setCcaUpdtUserId(String ccaUpdtUserId) {
        this.ccaUpdtUserId = ccaUpdtUserId == null ? null : ccaUpdtUserId.trim();
    }

	public String getCcaCusNumber() {
		return ccaCusNumber;
	}

	public void setCcaCusNumber(String ccaCusNumber) {
		this.ccaCusNumber = ccaCusNumber;
	}

	public String getCcaApprovalType() {
		return ccaApprovalType;
	}

	public void setCcaApprovalType(String ccaApprovalType) {
		this.ccaApprovalType = ccaApprovalType;
	}

	public String getCcaSuggestion() {
		return ccaSuggestion;
	}

	public void setCcaSuggestion(String ccaSuggestion) {
		this.ccaSuggestion = ccaSuggestion;
	}
    
}