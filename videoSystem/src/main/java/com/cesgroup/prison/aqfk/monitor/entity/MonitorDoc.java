package com.cesgroup.prison.aqfk.monitor.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
*    
* @projectName：prison   
* @ClassName：MonitorDoc   
* @Description：监督单   
* @author：zhengk   
* @date：2017-12-27 17:51   
* @version        
*/
@Entity
@Table(name="CDS_MONITOR_DOC")
public class MonitorDoc  extends StringIDEntity{

    private String mdoCusNumber;

    private String mdoMonitorName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date mdoTime;

    private String mdoAddr;

    private String mdoProblem;

    private String modSttsIndc;

    private String mdoRemark;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date mdoCrteTime;

    private String mdoCrteUserId;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date mdoUpdtTime;

    private String mdoUpdtUserId;
    //推送监狱id
    private String mdoNoticeCusNumber;
    //是否来自省局 0，否 1，是
    private String mdoIsFromProv;
    //查阅状态 0，未查阅 1，已查阅 （针对省局发送的监督单，监狱级用户的查阅状态）
    private String mdoConsultStatus;
    //推送部门id
    private String mdoNoticeDepartment;
    private String mdoNoticeDepartmentName;
    //反馈信息
    private String mdoFeedbackmessage;

    public String getMdoCusNumber() {
        return mdoCusNumber;
    }

    public void setMdoCusNumber(String mdoCusNumber) {
        this.mdoCusNumber = mdoCusNumber == null ? null : mdoCusNumber.trim();
    }

    public String getMdoMonitorName() {
        return mdoMonitorName;
    }

    public void setMdoMonitorName(String mdoMonitorName) {
        this.mdoMonitorName = mdoMonitorName == null ? null : mdoMonitorName.trim();
    }

    public Date getMdoTime() {
        return mdoTime;
    }

    public void setMdoTime(Date mdoTime) {
        this.mdoTime = mdoTime;
    }

    public String getMdoAddr() {
        return mdoAddr;
    }

    public void setMdoAddr(String mdoAddr) {
        this.mdoAddr = mdoAddr == null ? null : mdoAddr.trim();
    }

    public String getMdoProblem() {
        return mdoProblem;
    }

    public void setMdoProblem(String mdoProblem) {
        this.mdoProblem = mdoProblem == null ? null : mdoProblem.trim();
    }

    public String getModSttsIndc() {
        return modSttsIndc;
    }

    public void setModSttsIndc(String modSttsIndc) {
        this.modSttsIndc = modSttsIndc == null ? null : modSttsIndc.trim();
    }

    public String getMdoRemark() {
        return mdoRemark;
    }

    public void setMdoRemark(String mdoRemark) {
        this.mdoRemark = mdoRemark == null ? null : mdoRemark.trim();
    }

    public Date getMdoCrteTime() {
        return mdoCrteTime;
    }

    public void setMdoCrteTime(Date mdoCrteTime) {
        this.mdoCrteTime = mdoCrteTime;
    }

    public String getMdoCrteUserId() {
        return mdoCrteUserId;
    }

    public void setMdoCrteUserId(String mdoCrteUserId) {
        this.mdoCrteUserId = mdoCrteUserId == null ? null : mdoCrteUserId.trim();
    }

    public Date getMdoUpdtTime() {
        return mdoUpdtTime;
    }

    public void setMdoUpdtTime(Date mdoUpdtTime) {
        this.mdoUpdtTime = mdoUpdtTime;
    }

    public String getMdoUpdtUserId() {
        return mdoUpdtUserId;
    }

    public void setMdoUpdtUserId(String mdoUpdtUserId) {
        this.mdoUpdtUserId = mdoUpdtUserId == null ? null : mdoUpdtUserId.trim();
    }

	public String getMdoNoticeCusNumber() {
		return mdoNoticeCusNumber;
	}

	public void setMdoNoticeCusNumber(String mdoNoticeCusNumber) {
		this.mdoNoticeCusNumber = mdoNoticeCusNumber;
	}

	public String getMdoIsFromProv() {
		return mdoIsFromProv;
	}

	public void setMdoIsFromProv(String mdoIsFromProv) {
		this.mdoIsFromProv = mdoIsFromProv;
	}

	public String getMdoConsultStatus() {
		return mdoConsultStatus;
	}

	public void setMdoConsultStatus(String mdoConsultStatus) {
		this.mdoConsultStatus = mdoConsultStatus;
	}

	public String getMdoNoticeDepartment() {
		return mdoNoticeDepartment;
	}

	public void setMdoNoticeDepartment(String mdoNoticeDepartment) {
		this.mdoNoticeDepartment = mdoNoticeDepartment;
	}

    public String getMdoNoticeDepartmentName() {
        return mdoNoticeDepartmentName;
    }

    public void setMdoNoticeDepartmentName(String mdoNoticeDepartmentName) {
        this.mdoNoticeDepartmentName = mdoNoticeDepartmentName;
    }

    public String getMdoFeedbackmessage() {
        return mdoFeedbackmessage;
    }

    public void setMdoFeedbackmessage(String mdoFeedbackmessage) {
        this.mdoFeedbackmessage = mdoFeedbackmessage;
    }
}