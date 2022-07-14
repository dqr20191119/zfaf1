package com.cesgroup.prison.zfxx.zfsy.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfsy.entity.ZfSy;

/**
 * Description: 收押业务访问接口
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public interface ZfSyService extends IBaseCRUDService<ZfSy, String> {
	
	/**
	 * 同步收押
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfSy(String corp, String time, String cjpc);
	
}
