package com.cesgroup.prison.zfxx.zfdm.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfdmEntity;

/**   
*    
*    湖南罪犯点名详情
*/
public interface ZfdmMapper extends BaseDao<ZfdmEntity, String> {
	/**
	* @methodName: searchZfdm
	* @Description: 分页列表查询
	* @param map
	* @param pageRequest
	* @return Page<Map<String,String>>    
	* @throws
	*/
	public Page<Map<String, Object>> searchZfdm(Map<String, Object> map, PageRequest pageRequest);




}
