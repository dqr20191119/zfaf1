package com.cesgroup.prison.zfxx.zfLjtq.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfLjtq.entity.ZfLjtq;

public interface ZfLjtqService extends IBaseCRUDService<ZfLjtq, String> {

	/**
	 * 同步离监探亲
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfLjtq(String corp, String time, String cjpc);
	
	/**
	 * 备份离监探亲数据
	 */
	public void backups();
	
}
