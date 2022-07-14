package com.cesgroup.prison.szbb.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.szbb.entity.Fxpg;


/**
 * 数字冰雹数据对接 Dao
 * 3.安全风险评估 
 * @author zhou.jian
 * @date 2019-03-01
 */
public interface FxpgDao extends BaseDao<Fxpg,String>{

	/**
	 * 1.监狱排名
	 * @return
	 */
	public List<Map<String,Object>> getJypmList(Map<String,Object> param);

	/**
	 * 2.1 已发风险
	 * @return
	 */
	public List<Map<String,Object>> getJffxList(Map<String,Object> param);
	
	/**
	 * 2.2 近期风险
	 * @return
	 */
	public List<Map<String,Object>> getJqfxList(Map<String,Object> param);
	
	/**
	 * 3.频发风险
	 * @return
	 */
	public List<Map<String,Object>> getPffxList(Map<String,Object> param);
	
	
	/**
	 * 4.人地事物情风险等级接口
	 * @return
	 */
	public List<Map<String,Object>> getFxdjList(Map<String,Object> param);
	
	
	/**
	 * 5.全部风险点
	 */
	public List<Map<String,Object>> getAllfxdList(Map<String,Object> param);
	
	
	/**
	 * 6.当前发生风险点
	 */
	public List<Map<String,Object>> getDqfxdList(Map<String,Object> param);
	
	
	/**
	 * 7.当前发生风险详情
	 */
	public List<Map<String,Object>> getDqfsfxList(Map<String,Object> param);
	
	/**
	 * 8.立体化网格风险
	 */
	public List<Map<String,Object>> getWgfxList(Map<String,Object> param);
	
	/**
	 * 9.立体化网格评估
	 */
	public List<Map<String,Object>> getWgpgList(Map<String,Object> param);
	
	
	/**
	 * 10.网格扣分
	 */
	public List<Map<String,Object>> getWgkfList(Map<String,Object> param);
	
	
}
