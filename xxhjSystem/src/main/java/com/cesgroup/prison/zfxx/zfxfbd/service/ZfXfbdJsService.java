package com.cesgroup.prison.zfxx.zfxfbd.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfxfbd.entity.ZfXfbdJs;

public interface ZfXfbdJsService extends IBaseCRUDService<ZfXfbdJs, String> {

	/**
	 * 同步罪犯假释
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfXfbdJs(String corp, String jyCode, String time, String cjpc);
	
	/**
	 * 备份解回再审数据
	 */
	public void backups();
	
}
