package com.cesgroup.prison.alarm.plan.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.plan.entity.AlertPlanRltnEntity;

/**      
* @projectName：prison   
* @ClassName：AlertPlanRltnMapper   
* @Description：   预案关联报警器mapper
* @author：Tao.xu   
* @date：2017年12月26日 上午11:36:48   
* @version        
*/
public interface AlertPlanRltnMapper extends BaseDao<AlertPlanRltnEntity, String> {

	/**
	* @methodName: listAll
	* @Description: 列表查询
	* @param map
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> listAllForAlertor(Map<String, Object> map);

	/**
	* @methodName: updateAlertPlanRltnInfo
	* @Description: 更新 
	* @param map void    
	* @throws
	*/
	void updateAlert(Map<String, Object> map);

}
