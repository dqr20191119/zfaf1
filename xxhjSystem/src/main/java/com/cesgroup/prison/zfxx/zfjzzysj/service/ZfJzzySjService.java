package com.cesgroup.prison.zfxx.zfjzzysj.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfjzzysj.entity.ZfJzzySj;

public interface ZfJzzySjService extends IBaseCRUDService<ZfJzzySj, String> {

	/**
	 * 同步罪犯收监情况-狱外就诊/住院信息任务
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfJzzySj(String corp, String time, String cjpc);
	
	/**
	 * 备份罪犯收监情况-狱外就诊/住院信息任务数据
	 */
	public void backups();
	
}
