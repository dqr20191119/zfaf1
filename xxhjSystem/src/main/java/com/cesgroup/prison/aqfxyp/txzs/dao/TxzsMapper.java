package com.cesgroup.prison.aqfxyp.txzs.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.aqfxyp.txzs.entity.Txzs;

 
public interface TxzsMapper extends BaseDao <Txzs, String> {
 

	List<Map> getWwjgZf(Map<String, Object> map);
	

	List<Map> getWwjgKf(Map<String, Object> map);

	List<Map> getSjfwKf(Map<String, Object> map);
	
	List<Map> getSjfw(Map<String, Object> map);
	
	List<Map> getfxdKf(Map<String, Object> map);
	
	List<Map> getfxd(Map<String, Object> map);
	
	List<Map> getwgOne(Map<String, Object> map);
	
	
	List<Map> getfxqs(Map<String, Object> map);
	
}
