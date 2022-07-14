package com.cesgroup.prison.szbb.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.szbb.entity.Rcgk;


/**
 * 数字冰雹数据对接Service
 * 1.日常管控 
 * @author zhou.jian
 * @date 2019-2-28
 */
public interface RcgkService extends IBaseCRUDService<Rcgk,String>{

	
	/**
	 * 1.今日值班信息接口
	 * @return 结果集
	 */
	public void getJrzbList(String jyCode);
	
	/**
	 * 2.今日概况信息接口
	 * @return 结果集
	 */
	public void getJrgk(String jyCode);
	
	/**
	 * 3.区域管控信息接口
	 * @return 结果集
	 */
	public void getQygkList(String jyCode);
	
	/**
	 * 4.重点罪犯、重控犯信息接口
	 * @return 结果集
	 */
	public void getZdzfList(String jyCode);
	
	/**
	 * 5.大门出入管控信息接口
	 * @return 结果集
	 */
	public void getDmcrList(String jyCode);
	
	/**
	 * 6.人脸识别信息接口
	 * @return 结果集
	 */
	public void getRlsbList(String jyCode);
	
	/**
	 * 7.周界安防信息接口
	 * @return 结果集
	 */
	public void getZjafList(String jyCode);
	
	/**
	 * 8.手机管控信息接口
	 * @return 结果集
	 */
	public void getSjgkList(String jyCode);
	
	/**
	 * 9.狱外押解任务信息接口
	 * @return 结果集
	 */
	public void getYwyjrwList(String jyCode);
	
	/**
	 * 10.狱外押解车辆实时位置信息接口
	 * @return 结果集
	 */
	public void getYwyjclwzList(String jyCode);
	
	
}
