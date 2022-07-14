package com.cesgroup.prison.inspect.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 督查通报审核人
 * @author zhengk
 * @date 2018-03-21
 *
 */
@Table(name = "CDS_NOTICE_CHECK_POLICE")
public class InspectCheckPolice extends StringIDEntity{

	private static final long serialVersionUID = 2479074987950106464L;

	private String icpCusNumber;

    private String icpInspectId;

    private String icpInspectTypeIndc;

    private String icpPoliceIdnty;

    private String icpPoliceName;

    private String icpRemark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date icpCrteTime;

    private String icpCrteUserId;
    private String icpCrteUserName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date icpUpdtTime;

    private String icpUpdtUserId;
    private String icpUpdtUserName;
    
    public String getIcpCrteUserName() {
		return icpCrteUserName;
	}

	public void setIcpCrteUserName(String icpCrteUserName) {
		this.icpCrteUserName = icpCrteUserName;
	}

	public String getIcpUpdtUserName() {
		return icpUpdtUserName;
	}

	public void setIcpUpdtUserName(String icpUpdtUserName) {
		this.icpUpdtUserName = icpUpdtUserName;
	}
    
    public String getIcpInspectId() {
		return icpInspectId;
	}

	public void setIcpInspectId(String icpInspectId) {
		this.icpInspectId = icpInspectId;
	}

	public String getIcpCusNumber() {
		return icpCusNumber;
	}

	public void setIcpCusNumber(String icpCusNumber) {
		this.icpCusNumber = icpCusNumber;
	}

	public String getIcpInspectTypeIndc() {
		return icpInspectTypeIndc;
	}

	public void setIcpInspectTypeIndc(String icpInspectTypeIndc) {
		this.icpInspectTypeIndc = icpInspectTypeIndc;
	}

	public String getIcpPoliceIdnty() {
        return icpPoliceIdnty;
    }

    public void setIcpPoliceIdnty(String icpPoliceIdnty) {
        this.icpPoliceIdnty = icpPoliceIdnty == null ? null : icpPoliceIdnty.trim();
    }

    public String getIcpPoliceName() {
        return icpPoliceName;
    }

    public void setIcpPoliceName(String icpPoliceName) {
        this.icpPoliceName = icpPoliceName == null ? null : icpPoliceName.trim();
    }

    public String getIcpRemark() {
        return icpRemark;
    }

    public void setIcpRemark(String icpRemark) {
        this.icpRemark = icpRemark == null ? null : icpRemark.trim();
    }

    public Date getIcpCrteTime() {
        return icpCrteTime;
    }

    public void setIcpCrteTime(Date icpCrteTime) {
        this.icpCrteTime = icpCrteTime;
    }

    public String getIcpCrteUserId() {
        return icpCrteUserId;
    }

    public void setIcpCrteUserId(String icpCrteUserId) {
        this.icpCrteUserId = icpCrteUserId == null ? null : icpCrteUserId.trim();
    }

    public Date getIcpUpdtTime() {
        return icpUpdtTime;
    }

    public void setIcpUpdtTime(Date icpUpdtTime) {
        this.icpUpdtTime = icpUpdtTime;
    }

    public String getIcpUpdtUserId() {
        return icpUpdtUserId;
    }

    public void setIcpUpdtUserId(String icpUpdtUserId) {
        this.icpUpdtUserId = icpUpdtUserId == null ? null : icpUpdtUserId.trim();
    }
}