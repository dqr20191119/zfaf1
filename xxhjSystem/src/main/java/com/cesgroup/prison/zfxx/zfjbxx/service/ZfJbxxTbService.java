package com.cesgroup.prison.zfxx.zfjbxx.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfjbxx.entity.ZfJbxx;

/**
 * 同步罪犯基本信息数据
 */
public interface ZfJbxxTbService extends IBaseCRUDService<ZfJbxx, String> {

	/**
	 * 同步罪犯基本信息
	 * @param corp
	 * @param jyId
	 * @param time
	 * @param cjpc
	 */
	public void synchroZfJbxx(String corp, String jyId, String time, String cjpc);
	
	/**
	 * 备份罪犯基本信息数据
	 */
	public void backups();
	
	/**
	 * 全量同步罪犯照片
	 */
	public void synchroZfPhoto();
	
	/**
	 * 增量同步今日新收押罪犯照片
	 * @param corp
	 * @param jyId
	 * @param time
	 * @param cjpc
	 */
	public void synchroIncZfPhoto(String time);
	
}
