package com.cesgroup.prison.xxyp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="CDS_PROV_INFORMATION_DAYLY")
public class ProvDayly extends StringIDEntity{
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date pidDate;

	private String pidHour;

	private String pidRecordId;

	private String pidRecordItem;

	private String pidRecordValue;

	public Date getPidDate() {
		return pidDate;
	}

	public void setPidDate(Date pidDate) {
		this.pidDate = pidDate;
	}

	public String getPidHour() {
		return pidHour;
	}

	public void setPidHour(String pidHour) {
		this.pidHour = pidHour;
	}

	public String getPidRecordId() {
		return pidRecordId;
	}

	public void setPidRecordId(String pidRecordId) {
		this.pidRecordId = pidRecordId;
	}

	public String getPidRecordItem() {
		return pidRecordItem;
	}

	public void setPidRecordItem(String pidRecordItem) {
		this.pidRecordItem = pidRecordItem;
	}

	public String getPidRecordValue() {
		return pidRecordValue;
	}

	public void setPidRecordValue(String pidRecordValue) {
		this.pidRecordValue = pidRecordValue;
	}
	
}
