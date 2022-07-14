package com.cesgroup.prison.callNamesAl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.callNamesAl.entity.CallNamesRegisterEntity;

public interface CallNamesRegisterMapper extends BaseDao<CallNamesRegisterEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	/**
	* @methodName: findZfPicInfo
	* @Description: 查询罪犯照片信息
	* @param zfbhs
	* @return List<Map<String,String>>
	* @throws  
	*/
	List<Map<String, String>> findZfPicInfo(List<String> zfbhs);

	/**
	* @methodName: findJsForTree
	* @Description: 区域监舍异步树
	* @param paramMap
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findJsForTree(Map<String, Object> paramMap);

	/**
	* @methodName: findPrisonerByJs
	* @Description:  根据监舍号、楼层号查询罪犯信息
	* @param paramMap
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findPrisonerByJs(Map<String, Object> paramMap);

	/**
	* @methodName: findRegisterPrisonerByJs
	* @Description:  根据监舍号、楼层号查询已经存注册表罪犯信息
	* @param paramMap
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findRegisterPrisonerByJs(Map<String, Object> paramMap);

}
