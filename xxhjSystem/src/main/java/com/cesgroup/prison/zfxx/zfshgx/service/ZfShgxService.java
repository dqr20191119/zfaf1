package com.cesgroup.prison.zfxx.zfshgx.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfshgx.entity.ZfShgx;

public interface ZfShgxService extends IBaseCRUDService<ZfShgx, String> {
	/**
	 * 同步社会关系
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfShgx(String corp, String time, String cjpc);
	
	/**
	 * 备份社会关系数据
	 */
	public void backups();
	
}
