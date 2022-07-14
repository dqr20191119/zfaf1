package com.cesgroup.prison.szbb.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.szbb.entity.Ldgl;

/**
 * 数字冰雹数据对接 Service
 * 4.领导管理驾驶舱
 * @author zhou.jian
 * @date 2019-03-01
 */
public interface LdglService extends IBaseCRUDService<Ldgl,String>{

	/**
	 * 1.领导管理驾驶舱 - 罪犯变化
	 */
	public void getZfbh(String jyCode);
	
	/**
	 * 2.领导管理驾驶舱 - 罪犯年龄对比
	 */
	public void getZfnldb(String jyCode);
	
	/**
	 * 3.领导管理驾驶舱 - 罪犯类型
	 */
	public void getZflx(String jyCode);
	
	/**
	 * 4.领导管理驾驶舱 - 队伍建设
	 */
	public void getDwjs(String jyCode);
	
	/**
	 * 5.领导管理驾驶舱 - 个别谈话
	 */
	public void getGbth(String jyCode);
	
	/**
	 * 6.领导管理驾驶舱 - 亲情电话
	 */
	public void getQqdh(String jyCode);
	
	/**
	 * 7.领导管理驾驶舱 - 刑罚执行
	 */
	public void getXfzx(String jyCode);
	
	/**
	 * 8.领导管理驾驶舱 - 劳动工具
	 */
	public void getLdgj(String jyCode);
	
	/**
	 * 9.领导管理驾驶舱 - 大宗物品
	 */
	public void getDzwp(String jyCode);
	
	/**
	 * 10.领导管理驾驶舱 - 就医统计
	 */
	public void getJytj(String jyCode);
	
	/**
	 * 11.领导管理驾驶舱 - 组织结构
	 */
	public void getZzjg(String jyCode);
	
}
