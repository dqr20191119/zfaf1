package com.cesgroup.prison.zfxx.zfdm.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfdmEntity;

/**
 * Description: 湖南罪犯点名详情
 * 
 */
public interface ZfdmService extends IBaseCRUDService<ZfdmEntity, String> {
	/**
	* @methodName: searchZfdm
	* @Description: 分页列表查询
	* @param map
	* @param pageRequest
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	public Page<Map<String, Object>> searchZfdm(Map<String, Object> map, PageRequest pageRequest);
    
}
