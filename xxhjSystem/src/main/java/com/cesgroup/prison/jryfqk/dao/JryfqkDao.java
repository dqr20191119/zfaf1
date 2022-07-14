package com.cesgroup.prison.jryfqk.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jryfqk.entity.JryfqkEntity;

public interface JryfqkDao extends BaseDao<JryfqkEntity, String> {
	 
	public List<Map<String,Object>> getJryafq(Map<String,Object> map);
	
	public List<Map<String,Object>> getZfddry(Map<String,Object> map);
	
	/**
	 * 调入
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListDr(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 收押
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListSy(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 调出
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListDc(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 释放
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListSf(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 死亡
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListSw(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 外出就医
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListWcjy(Map<String, Object> map, Pageable pageable);
	
	
	/**
	 * 解回再审
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListJhzs(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 狱外寄押
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListYwjy(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 暂于监外执行
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListZyjwzx(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 离监探亲
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findListLjtq(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 新的所有的罪犯数据明细查询20190922 heqh
	 * @param map
	 * @param pageables
	 * @return
	 */
	public Page<Map<String, Object>> findListAllCommon(Map<String, Object> map, Pageable pageable);

	public Page<Map<String, Object>> findListZaiCe(Map<String, Object> map, Pageable pageable);
	
	public Page<Map<String, Object>> findListZaiYa(Map<String, Object> map, Pageable pageable);
	
}