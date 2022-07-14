package com.cesgroup.prison.szbb.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.szbb.entity.Ldgl;

/**
 * 数字冰雹数据对接 Dao
 * 4.领导管理驾驶舱
 * @author zhou.jian
 * @date 2019-03-01
 */
public interface LdglDao extends BaseDao<Ldgl,String>{

	
	/**
	 * 1.1.领导管理驾驶舱 - 罪犯分布
	 * @return
	 */
	public List<Map<String,Object>> getZffbList(Map<String,Object> param);
	
	/**
	 * 1.2.领导管理驾驶舱 - 罪犯变化
	 * @return
	 */
	public List<Map<String,Object>> getZfbhList(Map<String,Object> param);
	
	/**
	 * 2.领导管理驾驶舱 - 犯罪年龄对比
	 * @return
	 */
	public List<Map<String,Object>> getFznldbList(Map<String,Object> param);
	
	/**
	 * 3.1.领导管理驾驶舱 - 犯罪类型
	 * @return
	 */
	public List<Map<String,Object>> getFzlxList(Map<String,Object> param);
	
	/**
	 * 3.2.领导管理驾驶舱 - 刑期分布
	 * @return
	 */
	public List<Map<String,Object>> getXqfbList(Map<String,Object> param);
	
	/**
	 * 4.1.领导管理驾驶舱 - 警力变化率
	 * @return
	 */
	public List<Map<String,Object>> getJlbhlList(Map<String,Object> param);
	
	/**
	 * 4.2.领导管理驾驶舱 - 警力变化趋势
	 * @return
	 */
	public List<Map<String,Object>> getJlbhqsList(Map<String,Object> param);
	
	/**
	 * 5.1.领导管理驾驶舱 - 月普谈
	 * @return
	 */
	public List<Map<String,Object>> getYptList(Map<String,Object> param);
	
	/**
	 * 5.2.领导管理驾驶舱 - 十必谈
	 * @return
	 */
	public List<Map<String,Object>> getSbtList(Map<String,Object> param);
	
	/**
	 * 6.领导管理驾驶舱 - 亲情电话
	 * @return
	 */
	public List<Map<String,Object>> getQqdhList(Map<String,Object> param);
	
	/**
	 * 7.1.领导管理驾驶舱 - 减刑假释
	 * @return
	 */
	public List<Map<String,Object>> getJxjsList(Map<String,Object> param);
	
	/**
	 * 7.2.领导管理驾驶舱 - 申控检
	 * @return
	 */
	public List<Map<String,Object>> getSkjList(Map<String,Object> param);
	
	/**
	 * 8.1.领导管理驾驶舱 - 劳动工具
	 * @return
	 */
	public List<Map<String,Object>> getLdgjList(Map<String,Object> param);
	
	/**
	 * 8.2.领导管理驾驶舱 - 使用情况
	 * @return
	 */
	public List<Map<String,Object>> getSyqkList(Map<String,Object> param);
	
	/**
	 * 9.1.领导管理驾驶舱 - 经费支出
	 * @return
	 */
	public List<Map<String,Object>> getJfzcList(Map<String,Object> param);
	
	/**
	 * 9.2.领导管理驾驶舱 - 饮食结构
	 * @return
	 */
	public List<Map<String,Object>> getYsjgkList(Map<String,Object> param);
	
	/**
	 * 9.3.领导管理驾驶舱 - 采买
	 * @return
	 */
	public List<Map<String,Object>> getCmList(Map<String,Object> param);
	
	/**
	 * 10.1.领导管理驾驶舱 - 监区就医
	 * @return
	 */
	public List<Map<String,Object>> getJqjyList(Map<String,Object> param);
	
	/**
	 * 10.2.领导管理驾驶舱 - 就医趋势
	 * @return
	 */
	public List<Map<String,Object>> getJyqsList(Map<String,Object> param);
	
	/**
	 * 10.3.领导管理驾驶舱 - 慢病分析
	 * @return
	 */
	public List<Map<String,Object>> getMbfxList(Map<String,Object> param);
	
	/**
	 * 11.领导管理驾驶舱 -组织结构
	 * @return
	 */
	public List<Map<String,Object>> getZzjgList(Map<String,Object> param);
	
	
}
