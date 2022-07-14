package com.cesgroup.prison.szbb.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.szbb.entity.Fxpg;

/**
 * 数字冰雹数据对接 Service
 * 3.安全风险评估 
 * @author zhou.jian
 * @date 2019-03-01
 */
public interface FxpgService extends IBaseCRUDService<Fxpg,String>{
	
	/**
	 * 1.监狱排名
	 */
	public void getJypm(String jyCode);
	
	/**
	 * 2.风险清单
	 */
	public void getFxqd(String jyCode);
	
	/**
	 * 3.频发风险
	 */
	public void getPffx(String jyCode);
	
	/**
	 * 4.风险等级
	 */
	public void getFxdj(String jyCode);
	
	/**
	 * 5.全部风险点
	 */
	public void getQbfxd(String jyCode);
	
	/**
	 * 6.当前发生风险点
	 */
	public void getDqfxd(String jyCode);
	
	/**
	 * 7.当前发生风险详情
	 */
	public void getDqfxxq(String jyCode);
	
	/**
	 * 8.网格风险
	 */
	public void getWgfx(String jyCode);
	
	/**
	 * 9.网格风险评估
	 */
	public void getWgfxpg(String jyCode);
	
	/**
	 * 10.网格扣分
	 */
	public void getWgkf(String jyCode);

}
