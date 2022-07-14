package com.cesgroup.prison.zfxx.zfzyjwzx.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfzyjwzx.entity.ZfZyjwzx;

public interface ZfZyjwzxService extends IBaseCRUDService<ZfZyjwzx, String> {
	
	/**
	 * 同步暂予监外执行
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfZyjwzx(String corp, String time, String cjpc);
	
	/**
	 * 备份暂予监外执行数据
	 */
	public void backups();
	
}
