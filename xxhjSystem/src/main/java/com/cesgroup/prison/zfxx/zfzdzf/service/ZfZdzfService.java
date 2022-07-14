package com.cesgroup.prison.zfxx.zfzdzf.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfzdzf.entity.ZfZdzf;

/**
 * Description: 罪犯重点罪犯 数据访问接口
 * @author monkey
 *
 * 2019年3月4日
 */
public interface ZfZdzfService extends IBaseCRUDService<ZfZdzf, String> {
	
	/**
	 * 同步重点罪犯
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfZdzf(String corp, String time, String cjpc);
	
	/**
	 * 备份重点罪犯数据
	 */
	public void backups();
}
