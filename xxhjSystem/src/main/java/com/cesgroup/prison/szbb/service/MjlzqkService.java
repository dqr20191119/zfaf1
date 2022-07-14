package com.cesgroup.prison.szbb.service;

import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.szbb.entity.Mjlzqk;

public interface MjlzqkService extends IBaseCRUDService<Mjlzqk, String> {

	/**
	 * 民警年龄构成
	 * @param map
	 * @return
	 */
	Map<String, Object> findMjnlgc(Map<String, Object> map);
	
	/**
	 * 历年民警数量
	 * @param map
	 * @return
	 */
	Map<String, Object> findLnmjsl(Map<String, Object> map);

	/**
	 * 民警学历构成
	 * @param map
	 * @return
	 */
	Map<String, Object> findMjxlgc(Map<String, Object> map);
	
	/**
	 * 监内民警分布
	 * @param map
	 * @return
	 */
	Map<String, Object> findJqmjsl(Map<String, Object> map);
	
	/**
	 * 机关民警分布
	 * @param map
	 * @return
	 */
	Map<String, Object> findJgmjfb(Map<String, Object> map);
	
	/**
	 * 各监区警囚比
	 * @param map
	 * @return
	 */
	Map<String, Object> findJqjqb(Map<String, Object> map);
	
}
