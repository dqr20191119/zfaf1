package com.cesgroup.prison.login2.service;

import com.cesgroup.prison.common.bean.login2.LoginReqBean;
import com.cesgroup.prison.common.bean.login2.LoginRespBean;
import com.cesgroup.prison.common.bean.login2.LogoutReqBean;
import com.cesgroup.prison.common.bean.login2.LogoutRespBean;

/**
 * cesgroup
 * 登录服务接口
 * @author lihh
 *
 */
public interface ILoginService {

	/**
	 * 本地登录
	 * @param reqBean 请求实体类对象
	 * @return
	 */
	public LoginRespBean localLogin (LoginReqBean reqBean);


	/**
	 * 授权登录
	 * @param reqBean 请求实体类对象
	 * @return
	 */
	public LoginRespBean authLogin (LoginReqBean reqBean);


	/**
	 * 用户登出
	 * @param reqBean
	 * @return
	 */
	public LogoutRespBean logout (LogoutReqBean reqBean);
	
	
	public String loginName(String caId);
}
