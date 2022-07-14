package com.cesgroup.prison.alarm.plan.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.plan.entity.PlanDeviceRltnEntity;

/**      
* @projectName：prison   
* @ClassName：PlanDeviceRltnMapper   
* @Description：  设备关联报警预案
* @author：Tao.xu   
* @date：2017年12月25日 下午6:44:45   
* @version        
*/
public interface PlanDeviceRltnMapper extends BaseDao<PlanDeviceRltnEntity, String> {

	/**
	* @methodName: listAll
	* @Description: 列表查询
	* @param map
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> listAll(Map<String, Object> map);

	/**
	* @methodName: updatePlanDeviceRltnInfo
	* @Description: 更新预案关联设备
	* @param map void
	* @throws  
	*/
	void updatePlanDeviceRltnInfo(Map<String, Object> map);

}
