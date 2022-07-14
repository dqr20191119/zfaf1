package com.cesgroup.prison.alarm.plan.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanItemDtlsEntity;

/**      
* @projectName：prison   
* @ClassName：AlarmPlanItemDtlsMapper   
* @Description： 报警预案关联项  
* @author：Tao.xu   
* @date：2017年12月25日 下午6:39:20   
* @version        
*/
public interface AlarmPlanItemDtlsMapper extends BaseDao<AlarmPlanItemDtlsEntity, String> {

	 
	List<Map<String, Object>> listAll(Map<String, Object> map);

	void updateItem(Map<String, Object> map);

	/**
	* @methodName: queryItemsDtlsAndDevice
	* @Description: 查询预案关联项及所关联的设备信息
	* @param map
	* @return Map<String,Object>
	* @throws  
	*/
	List<Map<String, Object>> queryItemsDtlsAndDevice(Map<String, Object> map);

	 
	
}
