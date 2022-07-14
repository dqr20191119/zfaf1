package com.cesgroup.prison.alarm.event.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
 
 
/**      
* @projectName：alarmSystem   
* @ClassName：AlertEvidenceRelationEntity   
* @Description：   报警证据信息
* @author：Tao.xu   
* @date：2018年1月19日 下午1:00:47   
* @version        
*/
@Entity
@Table(name = "CDS_ALERT_EVIDENCE_RELATION")
public class AlertEvidenceRelationEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String aerCusNumber;

	private String aerRecordId;

	private String aerEvidenceId;

	private String aerRemark;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date aerCrteTime;

	private String aerCrteUserId;

	public String getAerCusNumber() {
		return aerCusNumber;
	}

	public void setAerCusNumber(String aerCusNumber) {
		this.aerCusNumber = aerCusNumber;
	}

	public String getAerRecordId() {
		return aerRecordId;
	}

	public void setAerRecordId(String aerRecordId) {
		this.aerRecordId = aerRecordId;
	}

	public String getAerEvidenceId() {
		return aerEvidenceId;
	}

	public void setAerEvidenceId(String aerEvidenceId) {
		this.aerEvidenceId = aerEvidenceId;
	}

	public String getAerRemark() {
		return aerRemark;
	}

	public void setAerRemark(String aerRemark) {
		this.aerRemark = aerRemark;
	}

	public Date getAerCrteTime() {
		return aerCrteTime;
	}

	public void setAerCrteTime(Date aerCrteTime) {
		this.aerCrteTime = aerCrteTime;
	}

	public String getAerCrteUserId() {
		return aerCrteUserId;
	}

	public void setAerCrteUserId(String aerCrteUserId) {
		this.aerCrteUserId = aerCrteUserId;
	}


}
