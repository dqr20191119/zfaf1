package com.cesgroup.prison.common.bean.user.utils;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.util.CesUtil;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.db.service.RedisCache;

/**
 * cesgroup  用户统一管理
 * 用户登录管理（用户登录的缓存信息管理）
 * @author lihh
 *
 */
public class UserLoginManager {

	// 登录日志
	private static final Logger log = LoggerFactory.getLogger(UserLoginManager.class);

	/*
	 * 登录缓存标识
	 */
	public static final String LOGIN_CACHE_USERID_IP = "LOGIN.CACHE.USERID.IP";			// 根据用户ID缓存用户登录IP
	public static final String LOGIN_CACHE_USERNAME_IP = "LOGIN.CACHE.USERNAME.IP";		// 根据用户名缓存用户登录IP
	public static final String LOGIN_CACHE_IP_USERINFO = "LOGIN.CACHE.IP.USERINFO";		// 根据登录IP缓存用户信息
	public static final String LOGIN_CACHE_IP_VERIFYCODE = "LOGIN.CACHE.IP.VERIFYCODE";	// 根据登录IP缓存强制登录校验码


	/**
	 * 登录成功（缓存用户登录数据）
	 * @param loginBean
	 */
	public static synchronized void login (UserBean userBean) {
		try {
			String userId = userBean.getUserId();
			String userName = userBean.getUserName();
			String loginIp = userBean.getLoginIp();

			// 记录登录信息到缓存
			RedisCache.putHash(LOGIN_CACHE_USERID_IP, userId, loginIp);
			RedisCache.putHash(LOGIN_CACHE_USERNAME_IP, userName, loginIp);
			RedisCache.putHash(LOGIN_CACHE_IP_USERINFO, loginIp, JSON.toJSONString(userBean));
		} catch (Exception e) {
			log.error("缓存用户登录数据异常：", e);
		}
	}

	/**
	 * 退出登录
	 * @param userBean
	 * @return
	 */
	public static synchronized boolean logout (UserBean userBean) {
		String userInfo = null;
		try {
			if (userBean != null) {
				RedisCache.deleteHash(LOGIN_CACHE_USERID_IP, userBean.getUserId());
				RedisCache.deleteHash(LOGIN_CACHE_USERNAME_IP, userBean.getUserName());
				RedisCache.deleteHash(LOGIN_CACHE_IP_USERINFO, userBean.getLoginIp());
				userInfo = getUserInfoByLoginIp(userBean.getLoginIp());
			}
		} catch (Exception e) {
			log.error("删除用户登录缓存数据异常：", e);
		}
		return Tools.isEmpty(userInfo);
	}

	/**
	 * 根据用户ID退出登录
	 * @param userId 用户ID
	 * @return
	 */
	public static synchronized boolean logoutByUserId (String userId) {
		return logout(getUserByUserId(userId));
	}

	/**
	 * 根据用户名退出登录
	 * @param userName 用户名
	 * @return
	 */
	public static synchronized boolean logoutByUserName (String userName) {
		return logout(getUserByUserName(userName));
	}

	/**
	 * 根据登录IP退出登录
	 * @param loginIp 登录IP
	 * @return
	 */
	public static synchronized boolean logoutByLoginIp (String loginIp) {
		return logout(getUserByLoginIp(loginIp));
	}



	/**
	 * 根据用户ID获取登录IP
	 * @param userName
	 * @return
	 */
	public static synchronized String getLoginIpByUserId (String userId) {
		return Tools.toStr(RedisCache.getObject(LOGIN_CACHE_USERID_IP, userId), "");
	}


	/**
	 * 根据用户名获取登录IP
	 * @param userName
	 * @return
	 */
	public static synchronized String getLoginIpByUserName (String userName) {
		return Tools.toStr(RedisCache.getObject(LOGIN_CACHE_USERNAME_IP, userName), "");
	}


	/**
	 * 根据用户登录IP获取缓存用户信息
	 * @param loginIp 登录IP
	 * @return
	 */
	public static synchronized String getUserInfoByLoginIp (String loginIp) {
		return Tools.toStr(RedisCache.getObject(LOGIN_CACHE_IP_USERINFO, loginIp));		// 用户信息
	}


	/**
	 * 根据用户ID获取缓存用户信息
	 * @param userId 用户ID
	 * @return
	 */
	public static synchronized UserBean getUserByUserId (String userId) {
		try {
			return getUserByLoginIp(getLoginIpByUserId(userId));
		} catch (Exception e) {
			log.error("根据用户ID获取缓存用户信息异常：" + e.getMessage());
			return null;
		}
	}


	/**
	 * 根据用户名(登录名)获取缓存用户信息
	 * @param userName 用户名/登录名
	 * @return
	 */
	public static synchronized UserBean getUserByUserName (String userName) {
		try {
			return getUserByLoginIp(getLoginIpByUserName(userName));
		} catch (Exception e) {
			log.error("根据用户名(登录名)获取缓存用户信息异常：" + e.getMessage());
			return null;
		}
	}


	/**
	 * 根据用户登录IP获取缓存用户实体对象
	 * @param loginIp 登录IP
	 * @return
	 */
	public static synchronized UserBean getUserByLoginIp (String loginIp) {
		try {
			String userInfo = getUserInfoByLoginIp(loginIp);		// 用户信息
			if (Tools.notEmpty(userInfo)) {
				return JSON.toJavaObject(JSON.parseObject(userInfo), UserBean.class);
			}
		} catch (Exception e) {
			log.error("根据用户登录IP获取缓存用户信息异常：" + e.getMessage());
		}
		return null;
	}


	/**
	 * 获取验证码
	 * @param loginIp 登录IP
	 * @param userKey 登录标识，不传或""则创建一个并返回
	 * @return
	 */
	public static synchronized String getVerifyCode (String loginIp) {
		String verifyCode = "";
		try {
			verifyCode = Tools.toStr(RedisCache.getObject(LOGIN_CACHE_IP_VERIFYCODE, loginIp));
			RedisCache.deleteHash(LOGIN_CACHE_IP_VERIFYCODE, loginIp);
		} catch (Exception e) {
			verifyCode = "";
			log.error("获取或设置强制登录验证码异常：", e);
		}
		return verifyCode;
	}


	/**
	 * 产生一个验证码
	 * @param loginIp 登录IP
	 * @param userKey 登录标识，不传或""则创建一个并返回
	 * @return
	 */
	public static synchronized String createVerifyCode (String loginIp, String userKey) {
		String verifyCode = null;
		try {
			// 不为空则解密验证码
			verifyCode = CesUtil.encrypt(userKey + "|" + Tools.toStr(new Date().getTime()));
			if (Tools.notEmpty(verifyCode)) {
				RedisCache.putHash(LOGIN_CACHE_IP_VERIFYCODE, loginIp, verifyCode);
			}
		} catch (Exception e) {
			verifyCode = null;
			log.error("设置强制登录验证码异常：", e);
		}
		return verifyCode;
	}
}
