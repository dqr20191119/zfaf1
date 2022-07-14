package com.cesgroup.prison.camera.bean;

/**
 * 摄像机状态
 * @author zxh
 *
 */
public class CameraStatusBean {
	
	private String cameraId;
	private String status;
	
	
	public String getCameraId() {
		return cameraId;
	}
	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}