package com.cesgroup.prison.jfsb.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
@Entity
@Table(name = "DVC_DOOR_OPEN")
public class DoorOpenEntity extends StringIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jyid;
	private String openPassword;
	public String getJyid() {
		return jyid;
	}
	public void setJyid(String jyid) {
		this.jyid = jyid;
	}
	public String getOpenPassword() {
		return openPassword;
	}
	public void setOpenPassword(String openPassword) {
		this.openPassword = openPassword;
	}
	
	
}
