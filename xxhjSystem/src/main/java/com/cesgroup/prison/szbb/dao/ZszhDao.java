package com.cesgroup.prison.szbb.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.szbb.entity.Zszh;


/**
 * 数字冰雹数据对接Dao
 * 2.战时指挥
 * @author zhou.jian
 * @date 2019-03-01
 */
public interface ZszhDao extends BaseDao<Zszh,String>{

	/**
	 * 1.战时指挥 - 现场警员
	 * @return
	 */
	public List<Map<String,Object>> getXcjyList(Map<String,Object> param);
	
	/**
	 * 2.战时指挥 - 待援警员
	 * @return
	 */
	public List<Map<String,Object>> getDyjyList(Map<String,Object> param);
	
	/**
	 * 3.战时指挥 - 待命车辆
	 * @return
	 */
	public List<Map<String,Object>> getDmclList(Map<String,Object> param);
	
	/**
	 * 4.战时指挥 - 警务装备
	 * @return
	 */
	public List<Map<String,Object>> getJwzbList(Map<String,Object> param);
	
	/**
	 * 5.战时指挥 - 事件信息
	 * @return
	 */
	public Map<String,Object> getSjxx(Map<String,Object> param);
	
	/**
	 * 6.战时指挥 - 事件流程
	 * @return
	 */
	public List<Map<String,Object>> getSjlcList(Map<String,Object> param);
	
	/**
	 * 7.战时指挥 - 当前流程
	 * @return
	 */
	public Map<String,Object> getDqlc(Map<String,Object> param);
	
	/**
	 * 8.战时指挥 - 涉事部位
	 * @return
	 */
	public Map<String,Object> getSsbm(Map<String,Object> param);
	
}
