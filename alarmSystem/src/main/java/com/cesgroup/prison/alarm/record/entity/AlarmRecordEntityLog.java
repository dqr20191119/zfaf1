package com.cesgroup.prison.alarm.record.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**      
* @projectName：alarmSystem   
* @ClassName：AlarmRecordEntity   
* @Description：   报警记录
* @author：Tao.xu   
* @date：2018年1月9日 下午1:02:06   
* @version        
*/
@Entity
@Table(name = "CDS_ALERT_RECORD_DTLS_LOG")
public class AlarmRecordEntityLog extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String alertRecordDtlsId;

	private String sendValues;

	private String errCode;

	private String msg;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ardCrteTime;

	public String getAlertRecordDtlsId() {
		return alertRecordDtlsId;
	}

	public void setAlertRecordDtlsId(String alertRecordDtlsId) {
		this.alertRecordDtlsId = alertRecordDtlsId;
	}

	public Date getArdCrteTime() {
		return ardCrteTime;
	}

	public void setArdCrteTime(Date ardCrteTime) {
		this.ardCrteTime = ardCrteTime;
	}

	public String getSendValues() {
		return sendValues;
	}

	public void setSendValues(String sendValues) {
		this.sendValues = sendValues;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
