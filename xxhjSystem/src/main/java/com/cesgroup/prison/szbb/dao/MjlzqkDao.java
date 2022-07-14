package com.cesgroup.prison.szbb.dao;

import java.util.List;
import java.util.Map;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.szbb.entity.Mjlzqk;


public interface MjlzqkDao extends BaseDao<Mjlzqk, String> {
	
	List<Map<String, Object>> selectMjnlgc(Map<String, Object> map);
	
	Map<String, Object> selectMjsl(Map<String, Object> map);
	
	List<Map<String, Object>> selectJqmjsl(Map<String, Object> map);
	
	List<Map<String, Object>> selectJgmjfb(Map<String, Object> map);

	List<Map<String, Object>> selectJqjqb(Map<String, Object> map);
	
}
