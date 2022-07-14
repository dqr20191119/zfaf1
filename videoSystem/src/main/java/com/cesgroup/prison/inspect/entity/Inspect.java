package com.cesgroup.prison.inspect.entity;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 网络督查通报
 * @author zhengk
 * @date 2018-01-04
 *
 */
@Table(name = "CDS_INSPECT_NOTICE")
public class Inspect extends StringIDEntity{

	private static final long serialVersionUID = 1L;

	private String inoCusNumber;

    private String inoDutyLogId;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inoInspectTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inoInspectEndTime;

    private String inoDutyOrderId;

    private String inoSupervisionDevice;

    private String inoPrisonCase;

    private String inoRuleExecute;

    private String inoMorningCheck;

    private String inoSuggesition;

    private String inoProblem;

    private String inoDocumentId;

    private String inoApprovalSttsIndc;		//0未提交 ，1已提交-待审核 ， 2不同意  ， 3 同意
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inoApprovalTime;

    private String inoApprovalUserId;

    private String inoRemark;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inoCrteTime;

    private String inoCrteUserId;
    private String inoCrteUserName;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inoUpdtTime;

    private String inoUpdtUserId;
    private String inoUpdtUserName;

    private String inoInspectName;
    
    private String inoNoticeCusNumber;
    //巡查通报文档url
    private String inoInspectDocument;
    //巡查通报期数
    private String inoInspectPhase;
    //巡查通报编校
    private String inoInspectBj;
    
    @Transient
    private String iprPoliceNames;//督察人员姓名（多个）
    @Transient
    private String iprPoliceIdntys;//督察人员编号（多个）
    @Transient
    private String checkPoliceName;//审批领导姓名（单个）
    @Transient
    private String checkPoliceIdnty;//审批领导编号（单个）
    
	public String getInoCrteUserName() {
		return inoCrteUserName;
	}

	public void setInoCrteUserName(String inoCrteUserName) {
		this.inoCrteUserName = inoCrteUserName;
	}

	public String getInoUpdtUserName() {
		return inoUpdtUserName;
	}

	public void setInoUpdtUserName(String inoUpdtUserName) {
		this.inoUpdtUserName = inoUpdtUserName;
	}

	public String getInoNoticeCusNumber() {
		return inoNoticeCusNumber;
	}

	public void setInoNoticeCusNumber(String inoNoticeCusNumber) {
		this.inoNoticeCusNumber = inoNoticeCusNumber;
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

    public Date getInoInspectTime() {
        return inoInspectTime;
    }

    public void setInoInspectTime(Date inoInspectTime) {
        this.inoInspectTime = inoInspectTime;
    }

    public Date getInoInspectEndTime() {
        return inoInspectEndTime;
    }

    public void setInoInspectEndTime(Date inoInspectEndTime) {
        this.inoInspectEndTime = inoInspectEndTime;
    }

    public String getInoSupervisionDevice() {
        return inoSupervisionDevice;
    }

    public void setInoSupervisionDevice(String inoSupervisionDevice) {
        this.inoSupervisionDevice = inoSupervisionDevice == null ? null : inoSupervisionDevice.trim();
    }

    public String getInoPrisonCase() {
        return inoPrisonCase;
    }

    public void setInoPrisonCase(String inoPrisonCase) {
        this.inoPrisonCase = inoPrisonCase == null ? null : inoPrisonCase.trim();
    }

    public String getInoRuleExecute() {
        return inoRuleExecute;
    }

    public void setInoRuleExecute(String inoRuleExecute) {
        this.inoRuleExecute = inoRuleExecute == null ? null : inoRuleExecute.trim();
    }

    public String getInoMorningCheck() {
        return inoMorningCheck;
    }

    public void setInoMorningCheck(String inoMorningCheck) {
        this.inoMorningCheck = inoMorningCheck == null ? null : inoMorningCheck.trim();
    }

    public String getInoSuggesition() {
        return inoSuggesition;
    }

    public void setInoSuggesition(String inoSuggesition) {
        this.inoSuggesition = inoSuggesition == null ? null : inoSuggesition.trim();
    }

    public String getInoProblem() {
        return inoProblem;
    }

    public void setInoProblem(String inoProblem) {
        this.inoProblem = inoProblem == null ? null : inoProblem.trim();
    }

	public Date getInoApprovalTime() {
        return inoApprovalTime;
    }

    public void setInoApprovalTime(Date inoApprovalTime) {
        this.inoApprovalTime = inoApprovalTime;
    }

    public String getInoApprovalUserId() {
        return inoApprovalUserId;
    }

    public void setInoApprovalUserId(String inoApprovalUserId) {
        this.inoApprovalUserId = inoApprovalUserId == null ? null : inoApprovalUserId.trim();
    }

    public String getInoRemark() {
        return inoRemark;
    }

    public void setInoRemark(String inoRemark) {
        this.inoRemark = inoRemark == null ? null : inoRemark.trim();
    }

    public Date getInoCrteTime() {
        return inoCrteTime;
    }

    public void setInoCrteTime(Date inoCrteTime) {
        this.inoCrteTime = inoCrteTime;
    }

    public String getInoCrteUserId() {
        return inoCrteUserId;
    }

    public void setInoCrteUserId(String inoCrteUserId) {
        this.inoCrteUserId = inoCrteUserId == null ? null : inoCrteUserId.trim();
    }

    public Date getInoUpdtTime() {
        return inoUpdtTime;
    }

    public void setInoUpdtTime(Date inoUpdtTime) {
        this.inoUpdtTime = inoUpdtTime;
    }

    public String getInoUpdtUserId() {
        return inoUpdtUserId;
    }

    public void setInoUpdtUserId(String inoUpdtUserId) {
        this.inoUpdtUserId = inoUpdtUserId == null ? null : inoUpdtUserId.trim();
    }

    public String getInoInspectName() {
        return inoInspectName;
    }

    public void setInoInspectName(String inoInspectName) {
        this.inoInspectName = inoInspectName == null ? null : inoInspectName.trim();
    }

	public String getInoCusNumber() {
		return inoCusNumber;
	}

	public void setInoCusNumber(String inoCusNumber) {
		this.inoCusNumber = inoCusNumber== null ? null : inoCusNumber.trim();
	}

	public String getInoDutyLogId() {
		return inoDutyLogId;
	}

	public void setInoDutyLogId(String inoDutyLogId) {
		this.inoDutyLogId = inoDutyLogId== null ? null : inoDutyLogId.trim();
	}

	public String getInoDutyOrderId() {
		return inoDutyOrderId;
	}

	public void setInoDutyOrderId(String inoDutyOrderId) {
		this.inoDutyOrderId = inoDutyOrderId== null ? null : inoDutyOrderId.trim();
	}

	public String getInoDocumentId() {
		return inoDocumentId;
	}

	public void setInoDocumentId(String inoDocumentId) {
		this.inoDocumentId = inoDocumentId== null ? null : inoDocumentId.trim();
	}

	public String getInoApprovalSttsIndc() {
		return inoApprovalSttsIndc;
	}

	public void setInoApprovalSttsIndc(String inoApprovalSttsIndc) {
		this.inoApprovalSttsIndc = inoApprovalSttsIndc== null ? null : inoApprovalSttsIndc.trim();
	}

	public String getInoInspectDocument() {
		return inoInspectDocument;
	}

	public void setInoInspectDocument(String inoInspectDocument) {
		this.inoInspectDocument = inoInspectDocument;
	}

	public String getInoInspectPhase() {
		return inoInspectPhase;
	}

	public void setInoInspectPhase(String inoInspectPhase) {
		this.inoInspectPhase = inoInspectPhase;
	}

	public String getInoInspectBj() {
		return inoInspectBj;
	}

	public void setInoInspectBj(String inoInspectBj) {
		this.inoInspectBj = inoInspectBj;
	} 
}