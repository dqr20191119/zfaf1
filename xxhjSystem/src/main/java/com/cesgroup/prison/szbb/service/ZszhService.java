package com.cesgroup.prison.szbb.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.szbb.entity.Zszh;

/**
 * 数字冰雹数据对接 Service
 * 2.战时指挥
 * @author zhou.jian
 * @date 2019-03-01
 */
public interface ZszhService extends IBaseCRUDService<Zszh,String>{

	/**
	 * 1.战时指挥 - 现场警员
	 * @return
	 */
	public void getXcjyList(String jyCode);
	
	/**
	 * 2.战时指挥 - 待援警员
	 * @return
	 */
	public void getDyjyList(String jyCode);
	
	/**
	 * 3.战时指挥 - 待命车辆
	 * @return
	 */
	public void getDmclList(String jyCode);
	
	/**
	 * 4.战时指挥 - 警务装备
	 * @return
	 */
	public void getJwzbList(String jyCode);
	
	/**
	 * 5.战时指挥 - 事件信息
	 * @return
	 */
	public void getSjxx(String jyCode);
	
	/**
	 * 6.战时指挥 - 事件流程
	 * @return
	 */
	public void getSjlcList(String jyCode);
	
	/**
	 * 7.战时指挥 - 当前流程
	 * @return
	 */
	public void getDqlc(String jyCode);
	
	/**
	 * 8.战时指挥 - 涉事部位
	 * @return
	 */
	public void getSsbm(String jyCode);
	
}
