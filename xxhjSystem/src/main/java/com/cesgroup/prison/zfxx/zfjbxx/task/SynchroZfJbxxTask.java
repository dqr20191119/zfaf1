package com.cesgroup.prison.zfxx.zfjbxx.task;

import java.util.concurrent.Callable;

import com.cesgroup.framework.utils.PropertiesUtil;
import com.cesgroup.prison.zfxx.zfjbxx.service.ZfJbxxService;

/**
 * 同步罪犯基本信息
 * @author lincoln
 *
 */
public class SynchroZfJbxxTask implements Callable<String> {
	/**
	 * 罪犯基本信息服务类
	 */
	private ZfJbxxService zfJbxxService;
	/**
	 * 同步罪犯基本信息
	 */
	private boolean synchroZfJbxx;
	/**
	 * 同步罪犯收押信息
	 */
	private boolean synchroZfSy;
	/**
	 * 同步罪犯离监信息
	 */
	private boolean synchroZfLj;
	
	/**
	 * 构造方法
	 * @param zfJbxxService
	 */
	public SynchroZfJbxxTask(ZfJbxxService zfJbxxService) {
		this.zfJbxxService = zfJbxxService;
		this.synchroZfJbxx = PropertiesUtil.getBooleanByKeyUnchanged("application", "synchro.zfxx.zfJbxxFlag");
		this.synchroZfSy = PropertiesUtil.getBooleanByKeyUnchanged("application", "synchro.zfxx.zfSyFlag");
		this.synchroZfLj = PropertiesUtil.getBooleanByKeyUnchanged("application", "synchro.zfxx.zfLjFlag");
	}

	@Override
	public String call() throws Exception {
		try {
			if(synchroZfJbxx) {
				// 同步基本信息、同案犯信息、照片信息
				this.zfJbxxService.synchoZfJbxxAndZfTafAndZfPhoto();
			}

			if(synchroZfSy) {
				// 同步收押信息
				this.zfJbxxService.synchoZfSy();
			}

			if(synchroZfLj) {
				// 同步离监信息
				this.zfJbxxService.synchoZfLj();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

}
