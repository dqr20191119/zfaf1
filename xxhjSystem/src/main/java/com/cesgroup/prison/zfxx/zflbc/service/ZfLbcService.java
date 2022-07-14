package com.cesgroup.prison.zfxx.zflbc.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zflbc.entity.ZfLbc;
/**
 * Description: 罪犯老病残 数据访问接口
 * @author monkey
 *
 * 2019年2月28日
 */

public interface ZfLbcService  extends IBaseCRUDService<ZfLbc,String> {
	/**
	 * 同步老病残
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfLbc(String corp, String time, String cjpc);
	
	/**
	 * 备份老病残数据
	 */
	public void backups();
	
}
