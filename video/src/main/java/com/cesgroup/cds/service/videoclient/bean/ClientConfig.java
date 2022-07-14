package com.cesgroup.cds.service.videoclient.bean;

public class ClientConfig {
	private String reqIp;		// 请求IP
	private String clientIp;	// 客户端IP
	private String snapPath;	// 截图保存路径
	private String recordPath;	// 录像保存路径
	private int width;			// 客户端宽度
	private int height;			// 客户端高度
	private int posX;			// 客户端X坐标
	private int posY;			// 客户端Y坐标

	public String getReqIp() {
		return reqIp;
	}
	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getSnapPath() {
		return snapPath;
	}
	public void setSnapPath(String snapPath) {
		this.snapPath = snapPath;
	}
	public String getRecordPath() {
		return recordPath;
	}
	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
}
