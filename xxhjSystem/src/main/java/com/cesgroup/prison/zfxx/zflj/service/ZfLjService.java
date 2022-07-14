package com.cesgroup.prison.zfxx.zflj.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zflj.entity.ZfLj;

/**
 * Description: 离监业务访问接口
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public interface ZfLjService extends IBaseCRUDService<ZfLj, String> {
	
	/**
	 * 同步离监
	 * @param corp
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfLj(String corp, String time, String cjpc);
	
}
