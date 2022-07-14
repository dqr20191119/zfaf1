package com.cesgroup.prison.zfxx.zfrzfp.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfrzfp.entity.ZfRzfp;

/**
 * Description: 罪犯老病残 数据访问接口
 * @author monkey
 *
 * 2019年3月3日
 */
public interface ZfRzfpService extends IBaseCRUDService<ZfRzfp, String> {

	/**
	 * 同步认罪服判
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfRzfp(String corp, String time, String cjpc);

	/**
	 * 备份认罪服判数据
	 */
	public void backups();
	
}
