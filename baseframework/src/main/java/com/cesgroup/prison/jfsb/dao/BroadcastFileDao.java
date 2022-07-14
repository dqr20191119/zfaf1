package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;

/**
 * @author lincoln.cheng
 *
 */
public interface BroadcastFileDao extends BaseDao<BroadcastFile, String> {
	/**
	 * 分页查询全部的曲目信息
	 * 
	 * @param map
	 * @param pageable
	 * @return
	 */
	Page<BroadcastFile> listAll(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 根据主键ID查询数据
	 * 
	 * @param id
	 * @return
	 */
	BroadcastFile findById(@Param("id") String id);
	/**
	 * 根据主键ID列表，查询广播媒体库文件列表
	 * @param idList
	 * @return
	 */
	List<BroadcastFile> findByIdList(@Param("idList") List<String> idList);
	/**
	 * 查询最大排序号
	 * 
	 * @return 排序号
	 */
	Integer findMaxOrderByBfdCusNumber(@Param("bfdCusNumber") String bfdCusNumber);
	
	/**
	 * 根据监狱编号、曲目编号查询数据数量
	 * 
	 * @param bfdCusNumber
	 * @param bfdIdnty
	 * @return
	 */
	Integer findCountByBfdCusNumberAndBfdIdnty(@Param("bfdCusNumber") String bfdCusNumber, @Param("bfdIdnty") String bfdIdnty);
	
	/**
	 * 根据监狱编号，查询广播曲目列表
	 * 
	 * @param bfdCusNumber
	 * @return
	 */
	List<BroadcastFile> findByBfdCusNumber(@Param("bfdCusNumber") String bfdCusNumber);
}
