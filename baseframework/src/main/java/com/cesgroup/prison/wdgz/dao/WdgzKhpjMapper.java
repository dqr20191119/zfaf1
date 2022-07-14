package com.cesgroup.prison.wdgz.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wdgz.entity.WdgzKhpjEntity;

/**
 * Description: 风险采集数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface WdgzKhpjMapper extends BaseDao<WdgzKhpjEntity, String> {
 
	public Page<Map<String, Object>> findList(Map<String, Object> map, PageRequest pageRequest);
	
	
	List<Map> getAll(Map<String, Object> map);
 

	List<Map> getAllMX(Map<String, Object> map);
	
	List<Map> getAllpm(Map<String, Object> map);
	
	List<Map> getAllqs(Map<String, Object> map);
	
	
	List<Map> getAllById(Map<String, Object> map);
	
}
