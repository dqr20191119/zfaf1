package com.cesgroup.prison.common.bean.login2;

/**
 * cesgroup
 * 登录请求实体类
 * @author lihh
 *
 */
public class LoginReqBean {
	private String mode = null; 		// 登录模式：1.用户密码登录（默认）、2.授权（令牌）登录",
	private String token = null; 		// 登录令牌，mode=2时有效",
	private String userName = null; 	// 登录用户名，mode=1时有效",
	private String password = null; 	// 登录密码，mode=1时有效",
	private String verifyCode = null; 	// 验证码，该值在检测到用户已在其它地方登录时返回，如果验证有效则强制登录"
	private String loginIp = null;		// 登录IP
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}


}
