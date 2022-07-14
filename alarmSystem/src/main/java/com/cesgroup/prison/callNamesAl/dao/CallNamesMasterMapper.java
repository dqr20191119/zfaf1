package com.cesgroup.prison.callNamesAl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.callNamesAl.entity.CallNamesMasterEntity;

public interface CallNamesMasterMapper extends BaseDao<CallNamesMasterEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	List<Map<String, Object>> findAreaDepartment(Map<String, Object> paramMap);

	/**
	* @methodName: findJsAndZfsByLc
	* @Description:  根据楼层查监舍和关押的罪犯数量
	* @param paramMap
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findJsAndZfsByLc(Map<String, Object> paramMap);

	List<String> getPrisonerIndcByJs(Map<String, Object> paramMap);

}
