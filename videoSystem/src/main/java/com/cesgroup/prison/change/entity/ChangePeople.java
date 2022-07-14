package com.cesgroup.prison.change.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 整改人员
 * @author zhengk
 * @date 2018-03-26
 *
 */
@Table(name = "CDS_CIRCULAR_CHANGED_PEOPLE")
public class ChangePeople extends StringIDEntity{
    private String ccpCusNumber;

    private String ccpFlowId;

    private String ccpChangedIdnty;

    private String ccpDprtmntIdnty;

    private String ccpPoliceIdnty;

    private String ccpPoliceName;

    private String ccpChangedMatters;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ccpChangedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ccpCrteTime;

    private String ccpCrteUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ccpUpdtTime;

    private String ccpUpdtUserId;


    public String getCcpChangedIdnty() {
        return ccpChangedIdnty;
    }

    public void setCcpChangedIdnty(String ccpChangedIdnty) {
        this.ccpChangedIdnty = ccpChangedIdnty == null ? null : ccpChangedIdnty.trim();
    }

    public String getCcpDprtmntIdnty() {
        return ccpDprtmntIdnty;
    }

    public void setCcpDprtmntIdnty(String ccpDprtmntIdnty) {
        this.ccpDprtmntIdnty = ccpDprtmntIdnty == null ? null : ccpDprtmntIdnty.trim();
    }

    public String getCcpPoliceIdnty() {
        return ccpPoliceIdnty;
    }

    public void setCcpPoliceIdnty(String ccpPoliceIdnty) {
        this.ccpPoliceIdnty = ccpPoliceIdnty == null ? null : ccpPoliceIdnty.trim();
    }

    public String getCcpPoliceName() {
        return ccpPoliceName;
    }

    public void setCcpPoliceName(String ccpPoliceName) {
        this.ccpPoliceName = ccpPoliceName == null ? null : ccpPoliceName.trim();
    }

    public String getCcpChangedMatters() {
        return ccpChangedMatters;
    }

    public void setCcpChangedMatters(String ccpChangedMatters) {
        this.ccpChangedMatters = ccpChangedMatters == null ? null : ccpChangedMatters.trim();
    }

    public Date getCcpChangedTime() {
        return ccpChangedTime;
    }

    public void setCcpChangedTime(Date ccpChangedTime) {
        this.ccpChangedTime = ccpChangedTime;
    }

    public Date getCcpCrteTime() {
        return ccpCrteTime;
    }

    public void setCcpCrteTime(Date ccpCrteTime) {
        this.ccpCrteTime = ccpCrteTime;
    }

    public String getCcpCrteUserId() {
        return ccpCrteUserId;
    }

    public void setCcpCrteUserId(String ccpCrteUserId) {
        this.ccpCrteUserId = ccpCrteUserId == null ? null : ccpCrteUserId.trim();
    }

    public Date getCcpUpdtTime() {
        return ccpUpdtTime;
    }

    public void setCcpUpdtTime(Date ccpUpdtTime) {
        this.ccpUpdtTime = ccpUpdtTime;
    }

    public String getCcpUpdtUserId() {
        return ccpUpdtUserId;
    }

    public void setCcpUpdtUserId(String ccpUpdtUserId) {
        this.ccpUpdtUserId = ccpUpdtUserId == null ? null : ccpUpdtUserId.trim();
    }

	public String getCcpCusNumber() {
		return ccpCusNumber;
	}

	public void setCcpCusNumber(String ccpCusNumber) {
		this.ccpCusNumber = ccpCusNumber;
	}

	public String getCcpFlowId() {
		return ccpFlowId;
	}

	public void setCcpFlowId(String ccpFlowId) {
		this.ccpFlowId = ccpFlowId;
	}
    
}