package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.DoorControlEntity;

/**      
* @projectName：prison   
* @ClassName：DoorControlMapper   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月13日 下午5:50:14   
* @version        
*/
public interface DoorControlMapper extends BaseDao<DoorControlEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	List<Map<String, Object>> searchCombData(Map<String, Object> map);
}
