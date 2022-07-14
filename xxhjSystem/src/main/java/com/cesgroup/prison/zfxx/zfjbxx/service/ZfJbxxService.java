package com.cesgroup.prison.zfxx.zfjbxx.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.zfxx.zfjbxx.entity.ZfJbxx;

/**
 * Description: 罪犯基本信息业务访问接口
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public interface ZfJbxxService extends IBaseCRUDService<ZfJbxx, String> {
	/**
	 * Description: 同步罪犯基本信息任务
	 * @throws BusinessLayerException
	 */
	public void synchroZfJbxxTask() throws BusinessLayerException;
	
	/**
	 * 同步罪犯基本信息、同案犯信息、照片信息
	 * 
	 * @throws BusinessLayerException
	 */
	public void synchoZfJbxxAndZfTafAndZfPhoto() throws BusinessLayerException;
	
	/**
	 * 同步罪犯收押信息
	 * 
	 * @throws BusinessLayerException
	 */
	public void synchoZfSy() throws BusinessLayerException;
	
	/**
	 * 同步罪犯离监信息
	 * 
	 * @throws BusinessLayerException
	 */
	public void synchoZfLj() throws BusinessLayerException;

	/**
	 * 下载罪犯照片
	 * 
	 * @throws BusinessLayerException
	 */
	public void autoDownloadZfPhoto() throws BusinessLayerException;
	
	/**
	 * 下载罪犯照片
	 * 
	 * @param cSzjy 所在监狱
	 * @param cSzjq 所在监区
	 * @param dUrlTime 在监日期
	 * @throws BusinessLayerException
	 */
	public void downloadZfPhoto(String cSzjy, String cSzjq, String dUrlTime) throws BusinessLayerException;
}
