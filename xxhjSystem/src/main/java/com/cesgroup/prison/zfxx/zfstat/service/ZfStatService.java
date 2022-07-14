package com.cesgroup.prison.zfxx.zfstat.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.zfxx.zfstat.entity.ZfStat;

/**
 * Description: 统计数据访问接口
 * @author zhou.jian
 *
 * 2019年2月20日
 */
public interface ZfStatService extends IBaseCRUDService<ZfStat,String>{

	/**
	 * Description: 同步罪犯统计信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStat(String jyCode,String jqId,String type,String corps,String jqCorps) throws BusinessLayerException;
	
}
