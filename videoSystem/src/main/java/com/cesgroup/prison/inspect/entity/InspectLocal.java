package com.cesgroup.prison.inspect.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 现场督查通报
 * @author zhengk
 * @date 2018-03-23
 *
 */
@Table(name = "CDS_INSPECT_NOTICE_LOCAL")
public class InspectLocal extends StringIDEntity{

	private static final long serialVersionUID = 7179398128049170660L;

    private String inlCusNumber;

    private String inlDutyLogId;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inlInspectTime;

    private String inlSupervisionDevice;

    private String inlPrisonCase;

    private String inlRuleExecute;

    private String inlSuggesition;

    private String inlProblem;

    private String inlDocumentId;

    private String inlApprovalSttsIndc;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inlApprovalTime;

    private String inlApprovalUserId;

    private String inlRemark;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inlCrteTime;

    private String inlCrteUserId;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inlUpdtTime;

    private String inlUpdtUserId;

    private String inlCrteUserName;

    private String inlUpdtUserName;

    private String inlInspectName;
    
    @Transient
    private String iprPoliceNames;//督察人员姓名
    @Transient
    private String iprPoliceIdntys;//督察人员编号
    @Transient
    private String checkPoliceName;//审批领导姓名
    @Transient
    private String checkPoliceIdnty;//审批领导编号
    
    @Transient
    List<InspectLocalRelation> goodList;  //好的方面
    @Transient
    List<InspectLocalRelation> badList;	  //存在的问题

    public List<InspectLocalRelation> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<InspectLocalRelation> goodList) {
		this.goodList = goodList;
	}

	public List<InspectLocalRelation> getBadList() {
		return badList;
	}

	public void setBadList(List<InspectLocalRelation> badList) {
		this.badList = badList;
	}

	public String getIprPoliceNames() {
		return iprPoliceNames;
	}

	public void setIprPoliceNames(String iprPoliceNames) {
		this.iprPoliceNames = iprPoliceNames;
	}

	public String getIprPoliceIdntys() {
		return iprPoliceIdntys;
	}

	public void setIprPoliceIdntys(String iprPoliceIdntys) {
		this.iprPoliceIdntys = iprPoliceIdntys;
	}

	public String getInlInspectName() {
		return inlInspectName;
	}

	public void setInlInspectName(String inlInspectName) {
		this.inlInspectName = inlInspectName;
	}

    public Date getInlInspectTime() {
        return inlInspectTime;
    }

    public void setInlInspectTime(Date inlInspectTime) {
        this.inlInspectTime = inlInspectTime;
    }

    public String getInlSupervisionDevice() {
        return inlSupervisionDevice;
    }

    public void setInlSupervisionDevice(String inlSupervisionDevice) {
        this.inlSupervisionDevice = inlSupervisionDevice == null ? null : inlSupervisionDevice.trim();
    }

    public String getInlPrisonCase() {
        return inlPrisonCase;
    }

    public void setInlPrisonCase(String inlPrisonCase) {
        this.inlPrisonCase = inlPrisonCase == null ? null : inlPrisonCase.trim();
    }

    public String getInlRuleExecute() {
        return inlRuleExecute;
    }

    public void setInlRuleExecute(String inlRuleExecute) {
        this.inlRuleExecute = inlRuleExecute == null ? null : inlRuleExecute.trim();
    }

    public String getInlSuggesition() {
        return inlSuggesition;
    }

    public void setInlSuggesition(String inlSuggesition) {
        this.inlSuggesition = inlSuggesition == null ? null : inlSuggesition.trim();
    }

    public String getInlProblem() {
        return inlProblem;
    }

    public void setInlProblem(String inlProblem) {
        this.inlProblem = inlProblem == null ? null : inlProblem.trim();
    }

	public Date getInlApprovalTime() {
        return inlApprovalTime;
    }

    public void setInlApprovalTime(Date inlApprovalTime) {
        this.inlApprovalTime = inlApprovalTime;
    }

    public String getInlApprovalUserId() {
        return inlApprovalUserId;
    }

    public void setInlApprovalUserId(String inlApprovalUserId) {
        this.inlApprovalUserId = inlApprovalUserId == null ? null : inlApprovalUserId.trim();
    }

    public String getInlRemark() {
        return inlRemark;
    }

    public void setInlRemark(String inlRemark) {
        this.inlRemark = inlRemark == null ? null : inlRemark.trim();
    }

    public Date getInlCrteTime() {
        return inlCrteTime;
    }

    public void setInlCrteTime(Date inlCrteTime) {
        this.inlCrteTime = inlCrteTime;
    }

    public String getInlCrteUserId() {
        return inlCrteUserId;
    }

    public void setInlCrteUserId(String inlCrteUserId) {
        this.inlCrteUserId = inlCrteUserId == null ? null : inlCrteUserId.trim();
    }

    public Date getInlUpdtTime() {
        return inlUpdtTime;
    }

    public void setInlUpdtTime(Date inlUpdtTime) {
        this.inlUpdtTime = inlUpdtTime;
    }

    public String getInlUpdtUserId() {
        return inlUpdtUserId;
    }

    public void setInlUpdtUserId(String inlUpdtUserId) {
        this.inlUpdtUserId = inlUpdtUserId == null ? null : inlUpdtUserId.trim();
    }

    public String getInlCrteUserName() {
        return inlCrteUserName;
    }

    public void setInlCrteUserName(String inlCrteUserName) {
        this.inlCrteUserName = inlCrteUserName == null ? null : inlCrteUserName.trim();
    }

    public String getInlUpdtUserName() {
        return inlUpdtUserName;
    }

    public void setInlUpdtUserName(String inlUpdtUserName) {
        this.inlUpdtUserName = inlUpdtUserName == null ? null : inlUpdtUserName.trim();
    }

	public String getInlDutyLogId() {
		return inlDutyLogId;
	}

	public void setInlDutyLogId(String inlDutyLogId) {
		this.inlDutyLogId = inlDutyLogId;
	}

	public String getInlDocumentId() {
		return inlDocumentId;
	}

	public void setInlDocumentId(String inlDocumentId) {
		this.inlDocumentId = inlDocumentId;
	}

	public String getInlApprovalSttsIndc() {
		return inlApprovalSttsIndc;
	}

	public void setInlApprovalSttsIndc(String inlApprovalSttsIndc) {
		this.inlApprovalSttsIndc = inlApprovalSttsIndc;
	}

	public String getCheckPoliceName() {
		return checkPoliceName;
	}

	public void setCheckPoliceName(String checkPoliceName) {
		this.checkPoliceName = checkPoliceName;
	}

	public String getCheckPoliceIdnty() {
		return checkPoliceIdnty;
	}

	public void setCheckPoliceIdnty(String checkPoliceIdnty) {
		this.checkPoliceIdnty = checkPoliceIdnty;
	}

	public String getInlCusNumber() {
		return inlCusNumber;
	}

	public void setInlCusNumber(String inlCusNumber) {
		this.inlCusNumber = inlCusNumber;
	}
    
}