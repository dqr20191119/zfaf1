package com.cesgroup.prison.alarm.record.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**      
* @projectName：prison   
* @ClassName：AlarmMapper   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月4日 下午2:30:24   
* @version        
*/
public interface AlarmMapper extends BaseDao<AlarmRecordEntity, String> {
	/**
	 * 根据主键ID，查询报警记录实体类
	 * @param id
	 * @return
	 */
	public AlarmRecordEntity findById(@Param("id") String id);

	public Page<Map<String, Object>> listAll(Map<String, Object> map, PageRequest pageRequest);

	public Map<String, Object> findAlarmRecordById(Map<String, Object> map);

	public void updateAlarmRecordInfo(Map<String, Object> map);

	public Map<String, String> queryAlarmLevRecord(Map<String, Object> map);

	public List<Map<String, String>> findCameraByTalkBack(String talkBackId);

	public Page<Map<String, Object>> searchRecordList(Map<String, Object> map, PageRequest pageRequest); 
	
	public Map<String, Object> countBjjl(Map<String, Object> map);


	/**
	 *查询是否为启动预案
	 * */
	List<EmerRecord> queryQdyaByid(@Param("id") String id);

	/**
	 * 根据监狱/部门编号、报警器编号，查询报警器所属区域对应的系统管理平台部门信息
	 *
	 * @param cusNumber
	 * @param alertorId
	 * @return
	 */
	public Map<String, Object> findAlertorAreaMappingDprtmntByCusNumberAndAlertorId(@Param("cusNumber") String cusNumber, @Param("alertorId") String alertorId);
}
