package com.cesgroup.prison.zfxx.zftg.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zftg.entity.ZfTg;

public interface ZfTgService extends IBaseCRUDService<ZfTg, String> {

	/**
	 * 特管登记
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfTg(String corp, String time, String cjpc);
	
	/**
	 * 备份特管登记数据
	 */
	public void backups();

}
