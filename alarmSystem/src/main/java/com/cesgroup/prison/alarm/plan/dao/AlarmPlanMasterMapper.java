package com.cesgroup.prison.alarm.plan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.exception.PersistenceException;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.plan.entity.AlarmEmerPlanRltn;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanMasterEntity;

/**      
* @projectName：prison   
* @ClassName：AlarmPlanMasterMapper   
* @Description：   报警预案表
* @author：Tao.xu   
* @date：2017年12月25日 下午6:39:41   
* @version        
*/
public interface AlarmPlanMasterMapper extends BaseDao<AlarmPlanMasterEntity, String> {

	/**
	 * 根据主键ID，查询报警预案信息
	 * @param id
	 * @return
	 */
	AlarmPlanMasterEntity findById(@Param("id") String id);

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
	* @methodName: updateAlarmPlanMasterInfo
	* @Description: 更新 
	* @param map void    
	* @throws
	*/
	void updateAlarmPlanMasterInfo(Map<String, Object> map);

	// 1 "门禁"; 2 "摄像机"; 3 "广播"; 4 "大屏"; 5 "对讲";
	public List<Map<String, Object>> listAllForMj(Map<String, Object> map);

	public List<Map<String, Object>> listAllForSx(Map<String, Object> map);

	public List<Map<String, Object>> listAllForGb(Map<String, Object> map);

	public List<Map<String, Object>> listAllForDp(Map<String, Object> map);

	public List<Map<String, Object>> listAllForDj(Map<String, Object> map);

	public List<Map<String, Object>> listAllForBjq(Map<String, Object> map);
}
