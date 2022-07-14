package com.cesgroup.prison.login2.service;

import com.cesgroup.prison.common.bean.login2.LoginReqBean;
import com.cesgroup.prison.common.bean.login2.LoginRespBean;

/**
 * 授权登录服务
 * @author hz208
 *
 */
public interface IAuthLoginService {

	/**
	 * 授权登录
	 * @param reqBean 请求实体类对象
	 * @return
	 */
	public LoginRespBean login (LoginReqBean reqBean);
	
	
	/**
	 * 重新开发的用户登录授权，切换成与大平台相同的SDK
	 * modified by lihh  at 2018-3-14 
	 * 授权登录
	 * @param reqBean 请求实体类对象
	 * @return
	 */
	public LoginRespBean login_new(LoginReqBean reqBean);


	public LoginRespBean loginLocal(LoginReqBean reqBean);
}
