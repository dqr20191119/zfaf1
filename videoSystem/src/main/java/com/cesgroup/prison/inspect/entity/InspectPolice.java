package com.cesgroup.prison.inspect.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 督查通报督查人
 * @author zhengk
 * @date 2018-03-21
 *
 */
@Table(name = "CDS_INSPECT_POLICE_RELATION")
public class InspectPolice extends StringIDEntity{

	private static final long serialVersionUID = 1L;

	private String iprCusNumber;

    private String iprInspectId;

    private String iprPoliceIdnty;

    private String iprPoliceName;
    
    //督察通报类型（0：网络督察通报；1：本地督察通报）
    private String iprTypeIndc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date iprCrteTime;

    private String iprCrteUserId;
    private String iprCrteUserName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date iprUpdtTime;

    private String iprUpdtUserId;
    private String iprUpdtUserName;
    
    public String getIprCrteUserName() {
		return iprCrteUserName;
	}

	public void setIprCrteUserName(String iprCrteUserName) {
		this.iprCrteUserName = iprCrteUserName;
	}

	public String getIprUpdtUserName() {
		return iprUpdtUserName;
	}

	public void setIprUpdtUserName(String iprUpdtUserName) {
		this.iprUpdtUserName = iprUpdtUserName;
	}

    public String getIprInspectId() {
		return iprInspectId;
	}

	public void setIprInspectId(String iprInspectId) {
		this.iprInspectId = iprInspectId;
	}

	public String getIprPoliceIdnty() {
        return iprPoliceIdnty;
    }

    public void setIprPoliceIdnty(String iprPoliceIdnty) {
        this.iprPoliceIdnty = iprPoliceIdnty == null ? null : iprPoliceIdnty.trim();
    }

    public String getIprPoliceName() {
        return iprPoliceName;
    }

    public void setIprPoliceName(String iprPoliceName) {
        this.iprPoliceName = iprPoliceName == null ? null : iprPoliceName.trim();
    }

	public String getIprCusNumber() {
		return iprCusNumber;
	}

	public void setIprCusNumber(String iprCusNumber) {
		this.iprCusNumber = iprCusNumber;
	}

	public String getIprTypeIndc() {
		return iprTypeIndc;
	}

	public void setIprTypeIndc(String iprTypeIndc) {
		this.iprTypeIndc = iprTypeIndc;
	}

	public Date getIprCrteTime() {
        return iprCrteTime;
    }

    public void setIprCrteTime(Date iprCrteTime) {
        this.iprCrteTime = iprCrteTime;
    }

    public String getIprCrteUserId() {
        return iprCrteUserId;
    }

    public void setIprCrteUserId(String iprCrteUserId) {
        this.iprCrteUserId = iprCrteUserId == null ? null : iprCrteUserId.trim();
    }

    public Date getIprUpdtTime() {
        return iprUpdtTime;
    }

    public void setIprUpdtTime(Date iprUpdtTime) {
        this.iprUpdtTime = iprUpdtTime;
    }

    public String getIprUpdtUserId() {
        return iprUpdtUserId;
    }

    public void setIprUpdtUserId(String iprUpdtUserId) {
        this.iprUpdtUserId = iprUpdtUserId == null ? null : iprUpdtUserId.trim();
    }
}