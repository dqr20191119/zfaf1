package com.cesgroup.prison.alarm.record.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.common.entity.AffixEntity;

public interface AlarmService extends IBaseCRUDService<AlarmRecordEntity, String> {

	public Page<Map<String, Object>> listAll(AlarmRecordEntity entity, String startTime, String endTime,
			String dprtmntId, String type,PageRequest pageRequest);

	public Map<String, Object> findAlarmRecordById(AlarmRecordEntity entity);

	public void updateAlarmRecord(AlarmRecordEntity entity) throws Exception;

	/**
	* @methodName: addAlertFile
	* @Description: 添加证据信息
	* @param id 报警记录id
	* @param files  证据ids
	* @throws  
	*/
	public void addAlertFile(String id, String files);

	/**
	* @methodName: findAlertFile
	* @Description: 查找证据信息
	* @param id 报警记录id
	* @return List<AffixEntity>
	* @throws  
	*/
	public List<AffixEntity> findAlertFile(String id);

	/**
	* @methodName: glZjByzjIds
	* @Description: 根据证据信息id关联报警证据到附件信息表
	* @param ids
	* @param ywId
	* @param fjfl
	* @return String
	* @throws  
	*/
	String glZjByzjIds(List<String> ids, String ywId, String fjfl);

	/**
	 * @throws Exception 
	* @methodName: queryAlarmLevRecord
	* @Description: 查询当前监狱报警事件发生数（按等级区分）
	* @param cusNumber
	* @throws  
	*/
	Map<String, Object> queryAlarmLevRecord(String cusNumber,String StartTime ,String Now,String DpName) throws Exception;

	public Page<Map<String, Object>> searchRecordList(AlarmRecordEntity entity,PageRequest pageRequest,String startTime,String endTime);

	public Map<String, Object> queryJqAlarmLevRecord(String cusNumber, String startStr, String now, String dprtmntId, PageRequest pageRequest);

	/**
	 *
	 * 查询是否为启动预案
	 * */
	List<EmerRecord> queryQdyaByid(String id);
	/**
	 * 根据j监狱/单位编号、报警器ID，查询报警器所关联的区域对应的系统管理平台部门数据
	 * @param cusNumber
	 * @param alertorId
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Object> queryAlertorAreaMappingDprtmntByCusNumberAndAlertorId(String cusNumber, String alertorId) throws ServiceException;
}
