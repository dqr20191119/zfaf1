package com.cesgroup.prison.zfxx.zfxfbd.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfxfbd.entity.ZfXfbdJx;

public interface ZfXfbdJxService extends IBaseCRUDService<ZfXfbdJx, String> {

	/**
	 * 同步罪犯减刑
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfXfbdJx(String corp, String time, String cjpc);
	
	/**
	 * 备份解回再审数据
	 */
	public void backups();
	
}
