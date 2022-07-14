package com.cesgroup.prison.zfxx.zfstat.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.zfxx.zfstat.entity.ZfStatZy;

/**
 * Description: 统计暂押访问类
 * @author zhou.jian
 *
 * 2019年2月21日
 */
public interface ZfStatZyService extends IBaseCRUDService<ZfStatZy,String>{

	/**
	 * Description: 2.同步【暂押罪犯】信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZy(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 3.同步【罪犯调动】信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZfdd(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	
	/**
	 * Description: 4.同步【在押罪犯】信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyqk(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 5.同步【性别】罪犯信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyXb(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 6.同步【年龄】罪犯信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyNl(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 7.同步【罪名】罪犯信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyZm(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 8.同步【原判刑期】罪犯信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyYpxq(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 9.同步【剩余刑期】罪犯信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZySyxq(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 10.同步【监狱关押能力】信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyJyGynl(List<Map<String ,Object>> list) throws BusinessLayerException;
	
	/**
	 * Description: 11.同步【各监狱关押人数】罪犯信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyJygyqk(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
	/**
	 * Description: 12.同步【各监狱改造情况】罪犯信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfStatZyJygzqk(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException;
	
}
