package com.cesgroup.prison.common.bean.user.utils;

/**
 * cesgroup
 * 用户等级枚举类
 * @author lihh
 */
public enum EUserLevel {
	/**
	 * 省局用户(province)
	 */
	PROV(1), 
	/**
	 * 监狱用户(prison)
	 */
	PRIS(2), 
	/**
	 * 监区用户(area)
	 */
	AREA(3);

	private int code;
	private EUserLevel(int code){
		this.code = code;
	}

	@Override
	public String toString() {
		return String.valueOf( code );
	}
}
