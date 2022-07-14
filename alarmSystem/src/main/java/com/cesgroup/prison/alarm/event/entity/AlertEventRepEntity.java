package com.cesgroup.prison.alarm.event.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
 
/**      
* @projectName：alarmSystem   
* @ClassName：AlertEventEntity   
* @Description：  报警事件报告信息 
* @author：Tao.xu   
* @date：2018年1月19日 上午10:20:10   
* @version        
*/
@Entity
@Table(name = "CDS_ALERT_EVENT_REP")
public class AlertEventRepEntity extends StringIDEntity {
    /**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	private String aevCusNumber;

	private String aevRecordId;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date aevEventTime;

	private String aevEventUserId;

	public String getAevCusNumber() {
		return aevCusNumber;
	}

	public void setAevCusNumber(String aevCusNumber) {
		this.aevCusNumber = aevCusNumber;
	}

	public String getAevRecordId() {
		return aevRecordId;
	}

	public void setAevRecordId(String aevRecordId) {
		this.aevRecordId = aevRecordId;
	}

	public Date getAevEventTime() {
		return aevEventTime;
	}

	public void setAevEventTime(Date aevEventTime) {
		this.aevEventTime = aevEventTime;
	}

	public String getAevEventUserId() {
		return aevEventUserId;
	}

	public void setAevEventUserId(String aevEventUserId) {
		this.aevEventUserId = aevEventUserId;
	}

	
}
