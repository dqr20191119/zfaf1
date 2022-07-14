package com.cesgroup.prison.inspect.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "CDS_INSPECT_LOCAL_RELATION")
public class InspectLocalRelation extends StringIDEntity{

	private static final long serialVersionUID = 2054158823338700441L;

	private String inrCusNumber;

    private String inrInspectId;

    private String inrTypeIndc;    //0:表示好的方面；1：表示存在的问题
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inrTime;

    private String inrAddr;

    private String inrDprtmntId;

    private String inrDprtmntName;

    private String inrPeopleTypeIndc;

    private String inrPeopleIdnty;

    private String inrPeopleName;

    private String inrDutyPoliceIdnty;

    private String inrItem;

    private String inrRemark;

    private String inrEvidenceId;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inrCrteTime;

    private String inrCrteUserId;
    private String inrCrteUserName;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inrUpdtTime;

    private String inrUpdtUserId;
    private String inrUpdtUserName;

    public String getInrCrteUserName() {
		return inrCrteUserName;
	}

	public void setInrCrteUserName(String inrCrteUserName) {
		this.inrCrteUserName = inrCrteUserName;
	}

	public String getInrUpdtUserName() {
		return inrUpdtUserName;
	}

	public void setInrUpdtUserName(String inrUpdtUserName) {
		this.inrUpdtUserName = inrUpdtUserName;
	}

	public String getInrInspectId() {
		return inrInspectId;
	}

	public void setInrInspectId(String inrInspectId) {
		this.inrInspectId = inrInspectId;
	}

    public Date getInrTime() {
        return inrTime;
    }

    public void setInrTime(Date inrTime) {
        this.inrTime = inrTime;
    }

    public String getInrAddr() {
        return inrAddr;
    }

    public void setInrAddr(String inrAddr) {
        this.inrAddr = inrAddr == null ? null : inrAddr.trim();
    }

    public String getInrDprtmntName() {
        return inrDprtmntName;
    }

    public void setInrDprtmntName(String inrDprtmntName) {
        this.inrDprtmntName = inrDprtmntName == null ? null : inrDprtmntName.trim();
    }

    public String getInrPeopleIdnty() {
        return inrPeopleIdnty;
    }

    public void setInrPeopleIdnty(String inrPeopleIdnty) {
        this.inrPeopleIdnty = inrPeopleIdnty == null ? null : inrPeopleIdnty.trim();
    }

    public String getInrPeopleName() {
        return inrPeopleName;
    }

    public void setInrPeopleName(String inrPeopleName) {
        this.inrPeopleName = inrPeopleName == null ? null : inrPeopleName.trim();
    }

    public String getInrDutyPoliceIdnty() {
        return inrDutyPoliceIdnty;
    }

    public void setInrDutyPoliceIdnty(String inrDutyPoliceIdnty) {
        this.inrDutyPoliceIdnty = inrDutyPoliceIdnty == null ? null : inrDutyPoliceIdnty.trim();
    }

    public String getInrItem() {
        return inrItem;
    }

    public void setInrItem(String inrItem) {
        this.inrItem = inrItem == null ? null : inrItem.trim();
    }

    public String getInrRemark() {
        return inrRemark;
    }

    public void setInrRemark(String inrRemark) {
        this.inrRemark = inrRemark == null ? null : inrRemark.trim();
    }

    public Date getInrCrteTime() {
        return inrCrteTime;
    }

    public void setInrCrteTime(Date inrCrteTime) {
        this.inrCrteTime = inrCrteTime;
    }

    public String getInrCrteUserId() {
        return inrCrteUserId;
    }

    public void setInrCrteUserId(String inrCrteUserId) {
        this.inrCrteUserId = inrCrteUserId == null ? null : inrCrteUserId.trim();
    }

    public Date getInrUpdtTime() {
        return inrUpdtTime;
    }

    public void setInrUpdtTime(Date inrUpdtTime) {
        this.inrUpdtTime = inrUpdtTime;
    }

    public String getInrUpdtUserId() {
        return inrUpdtUserId;
    }

    public void setInrUpdtUserId(String inrUpdtUserId) {
        this.inrUpdtUserId = inrUpdtUserId == null ? null : inrUpdtUserId.trim();
    }

	public String getInrCusNumber() {
		return inrCusNumber;
	}

	public void setInrCusNumber(String inrCusNumber) {
		this.inrCusNumber = inrCusNumber;
	}

	public String getInrTypeIndc() {
		return inrTypeIndc;
	}

	public void setInrTypeIndc(String inrTypeIndc) {
		this.inrTypeIndc = inrTypeIndc;
	}

	public String getInrDprtmntId() {
		return inrDprtmntId;
	}

	public void setInrDprtmntId(String inrDprtmntId) {
		this.inrDprtmntId = inrDprtmntId;
	}

	public String getInrPeopleTypeIndc() {
		return inrPeopleTypeIndc;
	}

	public void setInrPeopleTypeIndc(String inrPeopleTypeIndc) {
		this.inrPeopleTypeIndc = inrPeopleTypeIndc;
	}

	public String getInrEvidenceId() {
		return inrEvidenceId;
	}

	public void setInrEvidenceId(String inrEvidenceId) {
		this.inrEvidenceId = inrEvidenceId;
	}
    
}