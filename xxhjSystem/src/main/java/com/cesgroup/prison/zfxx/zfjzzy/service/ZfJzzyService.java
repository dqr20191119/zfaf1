package com.cesgroup.prison.zfxx.zfjzzy.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfjzzy.entity.ZfJzzy;

public interface ZfJzzyService extends IBaseCRUDService<ZfJzzy, String >{

	/**
	 * 同步狱外就医信
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfJzzy(String corp, String time, String cjpc);
	
	/**
	 * 备份狱外就医信数据
	 */
	public void backups();
	
}
