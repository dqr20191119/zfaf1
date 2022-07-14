package com.cesgroup.prison.common.bean.user.utils;

import java.util.ResourceBundle;

/**
 * cesgroup
 * 项目参数工具类
 * @author lihh
 */
public class UserConfigUtil {
	// 配置文件对象
	private static final ResourceBundle bundle = ResourceBundle.getBundle("user-config");

	/*
	 * 配置文件的KEY常量定义
	 */
	public static final String AUTH_SYSTEM_CODE = "AUTH.SYSTEM.CODE";
	public static final String AUTH_SYSTEM_BASE_ORGCODE = "AUTH.SYSTEM.BASE.ORGCODE";
	public static final String DEF_AIO_USE_ROLE = "DEF.AIO.USE.ROLE";
	public static final String DEF_LOGIN_MODE = "DEF.LOGIN.MODE";
	/**
	 * 获取配置
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return bundle.getString(key);
	}
}
