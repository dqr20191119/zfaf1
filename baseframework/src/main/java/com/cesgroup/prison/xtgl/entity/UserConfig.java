package com.cesgroup.prison.xtgl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name="CDS_USER_CONFIG")
public class UserConfig extends StringIDEntity {
	private String ucUserId;
	private String ucMapType;
	private String ucCusNumber;
	public String getUcUserId() {
		return ucUserId;
	}
	public void setUcUserId(String ucUserId) {
		this.ucUserId = ucUserId;
	}
	public String getUcMapType() {
		return ucMapType;
	}
	public void setUcMapType(String ucMapType) {
		this.ucMapType = ucMapType;
	}
	public String getUcCusNumber() {
		return ucCusNumber;
	}
	public void setUcCusNumber(String ucCusNumber) {
		this.ucCusNumber = ucCusNumber;
	}
}
