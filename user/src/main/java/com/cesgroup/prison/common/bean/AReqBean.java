package com.cesgroup.prison.common.bean;

/**
 * cesgroup
 * 统一请求格式
 * @author lihh
 */
public abstract class AReqBean {
	private String reqIp = null;	// 请求地址

	public String getReqIp() {
		return reqIp;
	}

	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}
}
