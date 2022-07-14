package com.cesgroup.prison.zfxx.ynjcxx.service;

public interface ZfYnjcxxService {

	/**
	 * 同步狱内奖惩信息
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfYnjcxx(String corp, String time, String cjpc);
	
	/**
	 * 备份解回再审数据
	 */
	public void backups();
	
}
