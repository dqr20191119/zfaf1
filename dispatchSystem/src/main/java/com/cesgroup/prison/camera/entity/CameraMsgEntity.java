package com.cesgroup.prison.camera.entity;

import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "dvc_camera_base_dtls")
public class CameraMsgEntity extends StringIDEntity {
	
	private String cbdSttsIndc;

	
	public String getCbdSttsIndc() {
		return cbdSttsIndc;
	}

	public void setCbdSttsIndc(String cbdSttsIndc) {
		this.cbdSttsIndc = cbdSttsIndc;
	}
}
