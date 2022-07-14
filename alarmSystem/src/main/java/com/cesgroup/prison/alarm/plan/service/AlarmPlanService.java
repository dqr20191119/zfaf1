package com.cesgroup.prison.alarm.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.plan.entity.AlarmEmerPlanRltn;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanBroadcastPlan;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanItemDtlsEntity;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanMasterEntity;
import com.cesgroup.prison.alarm.plan.entity.AlertPlanRltnEntity;
import com.cesgroup.prison.alarm.plan.entity.PlanDeviceRltnEntity;

public interface AlarmPlanService extends IBaseCRUDService<AlarmPlanMasterEntity, String> {

	/**
	* @methodName: listAll
	* @Description: 分页查询报警预案
	* @param entity
	* @param pageable
	* @return Page<Map<String,Object>>
	* @throws  
	*/
	public Page<Map<String, Object>> listAllForMaster(AlarmPlanMasterEntity entity, Pageable pageable);

	/**
	* @methodName: listAllForDevice
	* @Description: 列表查询待关联设备信息
	* @param cusNumber 监狱号
	* @param areaId 区域编号
	* @param item 1 "门禁"; 2 "摄像机"; 3 "广播"; 4 "大屏"; 5 "对讲";
	* @param pageable
	* @return Page<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> listAllForDevice(String cusNumber, String areaId, String itemId, String planId);

	/**
	* @methodName: listAllForDeviceByItem
	* @Description: 根据关联项查询关联设备信息
	* @param entity
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> listAllForDeviceByItem(PlanDeviceRltnEntity entity);

	/**
	* @methodName: listAllForAlertor
	* @Description: 列表查询待关联报警器信息
	* @param cusNumber 监狱号
	* @param areaId 区域编号
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> listAllForAlertor(String cusNumber, String areaId);

	/**
	* @methodName: listAllForAlertorByPlan
	* @Description: 根据预案查询关联报警器
	* @param entity
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> listAllForAlertorByPlan(AlertPlanRltnEntity entity);

	/**
	 * @throws Exception 
	* @methodName: addAlarmPlanMasterInfo
	* @Description: 添加预案信息
	* @param entity void
	* @throws
	*/
	public void addMasterInfo(AlarmPlanMasterEntity entity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: addAlertInfo
	* @Description: 添加报警器信息
	* @param entity void
	* @throws
	*/
	public void addAlertInfo(List<Map<String, Object>> alertors) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: addDeviceInfo
	* @Description: 添加设备信息
	* @param devices void
	* @throws  
	*/
	public void addDeviceInfo(List<Map<String, Object>> devices) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: updateAlarmPlanMasterInfo
	* @Description: 更新报警预案
	* @param entity void
	* @throws
	*/
	public void updateMasterInfo(AlarmPlanMasterEntity entity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: updateDeviceInfo
	* @Description: 更新关联设备
	* @param entity void
	* @throws  
	*/
	public void updateDeviceInfo(PlanDeviceRltnEntity entity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: updateAlertInfo
	* @Description: 更新关联报警器
	* @param entity void
	* @throws  
	*/
	public void updateAlertInfo(AlertPlanRltnEntity entity) throws Exception;

	/**
	* @methodName: delAlarmPlanMaster
	* @Description: 删除预案
	* @param entity void
	* @throws
	* @author：Tao.xu 
	* @date：2017年12月26日 上午11:31:27      
	*/
	public void delAlarmPlanMaster(AlarmPlanMasterEntity entity);

	/**
	* @methodName: delAlarmPlanItem
	* @Description: 删除预案关联项
	* @param entity void
	* @throws
	* @author：Tao.xu 
	* @date：2017年12月26日 上午11:31:42      
	*/
	public void delAlarmPlanItem(AlarmPlanItemDtlsEntity entity);

	/**
	* @methodName: delAlertPlanRltn
	* @Description: 删除预案关联报警器
	* @param entity void
	* @throws
	*/
	public void delAlertPlanRltn(AlertPlanRltnEntity entity);

	/**
	* @methodName: delPlanDeviceRltn
	* @Description: 删除预案关联设备
	* @param entity void
	* @throws
	*/
	public void delPlanDeviceRltn(PlanDeviceRltnEntity entity);

	/**
	* @methodName: findMasterById
	* @Description: 根据id查询预案信息，包括关联项和关联设备信息
	* @param id
	* @return AlarmPlanMasterEntity
	* @throws  
	*/
	public AlarmPlanMasterEntity findMasterById(String id);

	public void saveBroadcastPlan(AlarmPlanBroadcastPlan entity);

	public AlarmPlanBroadcastPlan findByPlanId(String bprPlanId);

	/**
	 * 根据报警预案ID，查询报警预案与应急预案关联关系数据
	 * @param alarmPlanId
	 * @return
	 * @throws ServiceException
	 */
	public AlarmEmerPlanRltn queryAlarmEmerPlanRltnByAlarmPlanId(String alarmPlanId) throws ServiceException;

	/**
	 * 保存或更新报警预案与应急预案关联关系数据
	 *
	 * @param entity
	 * @throws ServiceException
	 */
	public void saveOrUpdateAlarmEmerPlanRltn(AlarmEmerPlanRltn entity) throws ServiceException;
}
