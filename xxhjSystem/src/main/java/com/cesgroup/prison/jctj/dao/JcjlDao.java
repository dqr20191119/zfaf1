package com.cesgroup.prison.jctj.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jctj.entity.JcjlEntity;

public interface JcjlDao extends BaseDao<JcjlEntity, String> {
	
	/**
	 * 获取全部进出记录
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getQbjcjl(Map<String, Object> map); 
	
	Map<String, Object> getQbjcjlTotal(Map<String, Object> map);
	
	/**
	 * 获取最新进出记录
	 * @param map
	 * @return
	 */
	public List<Map <String,Object>> getZxjcjl(Map<String, Object> map); 
	
	/**
	 * 获取现有互监组
	 * @param map
	 * @return
	 */
	public List<Map <String,Object>> getHjzh(Map<String, Object> map); 
	
	/**
	 * 获取罪犯
	 * @param map
	 * @return
	 */
	public List<Map <String,Object>> getZf(Map<String, Object> map); 
}