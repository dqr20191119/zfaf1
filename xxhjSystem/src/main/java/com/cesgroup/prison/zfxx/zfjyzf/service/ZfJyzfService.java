package com.cesgroup.prison.zfxx.zfjyzf.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfjyzf.entity.ZfJyzf;

public interface ZfJyzfService extends IBaseCRUDService<ZfJyzf, String> {
	
	/**
	 * 同步罪犯外出情况-狱外寄押
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfJyzf(String corp, String time, String cjpc);
	
	/**
	 * 备份罪犯外出情况-狱外寄押数据
	 */
	public void backups();
	
}
