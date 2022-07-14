package com.cesgroup.framework.cache;
/**
 * cesgroup
 * 刷新缓存接口
 * @author lihh
 *
 */
public interface IRefreshCache {

	/**
	 * 刷新缓存
	 * @return 刷新结果:true/false
	 * @throws Exception
	 */
	public Boolean refresh() throws Exception;
	
	/**
	 * 刷新缓存
	 * @param fileName 文件名
	 * @return 刷新结果:true/false
	 * @throws Exception
	 */
	public Boolean refresh(String fileName) throws Exception;
	
}
