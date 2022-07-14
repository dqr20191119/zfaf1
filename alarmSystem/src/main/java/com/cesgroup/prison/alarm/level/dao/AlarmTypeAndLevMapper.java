package com.cesgroup.prison.alarm.level.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.level.entity.AlarmTypeAndLevEntity;

/**      
* @projectName：prison   
* @ClassName：AlarmTypeAndLevMapper   
* @Description：   报警类型关联等级
* @author：Tao.xu   
* @date：2017年12月25日 下午12:14:37   
* @version        
*/
public interface AlarmTypeAndLevMapper extends BaseDao<AlarmTypeAndLevEntity, String> {

	/**
	* @methodName: listAll
	* @Description: 分页查询
	* @param map
	* @param pageable
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	/**
	* @methodName: updateAlarmTypeAndLevInfo
	* @Description: 更新 
	* @param map void    
	* @throws
	*/
	void updateInfo(Map<String, Object> map);

	/**
	* @methodName: deleteById
	* @Description:根据 List<String> ids删记录
	* @param list void    
	* @throws
	*/
	void deleteByIds(List<String> ids);

}
