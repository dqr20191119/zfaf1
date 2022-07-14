package com.cesgroup.prison.zfxx.jhzs.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.jhzs.entity.ZfJhzs;

public interface ZfJhzsService extends IBaseCRUDService<ZfJhzs, String> {

	/**
	 * 同步解回再审
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfJhzs(String corp, String time, String cjpc);
	
	/**
	 * 备份解回再审数据
	 */
	public void backups();
	
}
