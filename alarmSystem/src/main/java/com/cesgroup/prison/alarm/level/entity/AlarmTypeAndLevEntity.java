package com.cesgroup.prison.alarm.level.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：AlarmTypeAndLevEntity   
* @Description：   报警类型关联等级实体
* @author：Tao.xu   
* @date：2017年12月25日 下午12:11:55   
* @version        
*/
@Entity
@Table(name = "CDS_ALARM_TYPE")
public class AlarmTypeAndLevEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;

	private String altCusNumber;

	private String altTypeId;

	private String altLevel;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date altCrteTime;

	private String altCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date altUpdtTime;

	private String altUpdtUserId;

	public String getAltCusNumber() {
		return altCusNumber;
	}

	public void setAltCusNumber(String altCusNumber) {
		this.altCusNumber = altCusNumber;
	}

	public String getAltTypeId() {
		return altTypeId;
	}

	public void setAltTypeId(String altTypeId) {
		this.altTypeId = altTypeId;
	}

	public String getAltLevel() {
		return altLevel;
	}

	public void setAltLevel(String altLevel) {
		this.altLevel = altLevel;
	}

	public Date getAltCrteTime() {
		return altCrteTime;
	}

	public void setAltCrteTime(Date altCrteTime) {
		this.altCrteTime = altCrteTime;
	}

	public String getAltCrteUserId() {
		return altCrteUserId;
	}

	public void setAltCrteUserId(String altCrteUserId) {
		this.altCrteUserId = altCrteUserId;
	}

	public Date getAltUpdtTime() {
		return altUpdtTime;
	}

	public void setAltUpdtTime(Date altUpdtTime) {
		this.altUpdtTime = altUpdtTime;
	}

	public String getAltUpdtUserId() {
		return altUpdtUserId;
	}

	public void setAltUpdtUserId(String altUpdtUserId) {
		this.altUpdtUserId = altUpdtUserId;
	}

}
