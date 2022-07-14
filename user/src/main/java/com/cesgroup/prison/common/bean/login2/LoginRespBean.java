package com.cesgroup.prison.common.bean.login2;

import com.cesgroup.prison.common.bean.ARespBean;
import com.cesgroup.prison.common.bean.user.UserBean;

/**
 * cesgroup
 * 用户登录实体类对象
 * @author lihh
 * 
 */
public class LoginRespBean extends ARespBean{
	private String verifyCode = null; 	// 验证码，该值在检测到用户已在其它地方登录时返回，如果验证有效则强制登录"
	private UserBean userBean = null;	// 用户信息

	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
