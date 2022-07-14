package com.cesgroup.prison.change.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 通报整改
 * @author zhengk
 * @date 2018-03-26
 *
 */
@Table(name = "CDS_CIRCULAR_CHANGED")
public class Change extends StringIDEntity{
    private String cchCusNumber;

    private String cchInspectId;

    private String cchChangedContent;

    private String cchStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cchCrteTime;

    private String cchCrteUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cchUpdtTime;

    private String cchUpdtUserId;

    public String getCchInspectId() {
        return cchInspectId;
    }

    public void setCchInspectId(String cchInspectId) {
        this.cchInspectId = cchInspectId == null ? null : cchInspectId.trim();
    }

    public String getCchChangedContent() {
        return cchChangedContent;
    }

    public void setCchChangedContent(String cchChangedContent) {
        this.cchChangedContent = cchChangedContent == null ? null : cchChangedContent.trim();
    }

    public Date getCchCrteTime() {
        return cchCrteTime;
    }

    public void setCchCrteTime(Date cchCrteTime) {
        this.cchCrteTime = cchCrteTime;
    }

    public String getCchCrteUserId() {
        return cchCrteUserId;
    }

    public void setCchCrteUserId(String cchCrteUserId) {
        this.cchCrteUserId = cchCrteUserId == null ? null : cchCrteUserId.trim();
    }

    public Date getCchUpdtTime() {
        return cchUpdtTime;
    }

    public void setCchUpdtTime(Date cchUpdtTime) {
        this.cchUpdtTime = cchUpdtTime;
    }

    public String getCchUpdtUserId() {
        return cchUpdtUserId;
    }

    public void setCchUpdtUserId(String cchUpdtUserId) {
        this.cchUpdtUserId = cchUpdtUserId == null ? null : cchUpdtUserId.trim();
    }

	public String getCchCusNumber() {
		return cchCusNumber;
	}

	public void setCchCusNumber(String cchCusNumber) {
		this.cchCusNumber = cchCusNumber;
	}

	public String getCchStatus() {
		return cchStatus;
	}

	public void setCchStatus(String cchStatus) {
		this.cchStatus = cchStatus;
	}
    
}