package com.cesgroup.prison.common.bean.login2;

/**
 * cesgroup
 * 用户退出系统请求实体类
 * @author lihh
 */
public class LogoutReqBean {
	private String userId = null;		//退出系统的用户ID
	private String userName = null; 	// 退出系统的用户名
	private String logoutIp = null;		// 退出系统的请求IP

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogoutIp() {
		return logoutIp;
	}
	public void setLogoutIp(String logoutIp) {
		this.logoutIp = logoutIp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
