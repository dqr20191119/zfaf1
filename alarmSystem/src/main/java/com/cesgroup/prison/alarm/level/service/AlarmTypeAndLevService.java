package com.cesgroup.prison.alarm.level.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.alarm.level.entity.AlarmTypeAndLevEntity;

/**      
* @projectName：prison   
* @ClassName：AlarmTypeAndLevService   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月25日 下午12:15:45   
* @version        
*/
public interface AlarmTypeAndLevService extends IBaseCRUDService<AlarmTypeAndLevEntity, String> {

	/**
	* @methodName: listAll
	* @Description: 分页查询所有
	* @param entity
	* @param pageable
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	public Page<Map<String, Object>> listAll(AlarmTypeAndLevEntity entity, Pageable pageable);

	/**
	* @throws Exception 
	* @methodName: addAlarmTypeAndLevInfo
	* @Description: 添加信息
	* @param entity void    
	*/
	public void addInfo(AlarmTypeAndLevEntity entity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: updateAlarmTypeAndLevInfo
	* @Description:  更新
	* @param entity void    
	*/
	public void updateInfo(AlarmTypeAndLevEntity entity) throws Exception;

	/**
	* @methodName: deleteById
	* @Description: 根据id删记录
	* @param list void    
	* @throws
	*/
	public void deleteByIds(List<String> list);

	/**
	 * @return 
	* @methodName: findById
	* @Description: 通过id查纪录
	* @param id void    
	* @throws
	*/
	public AlarmTypeAndLevEntity findById(String id);

	/**
	* @methodName: findByCusNumberAndType
	* @Description: 根据监狱号和typeid 联合查询
	* @param entity
	* @return Map<String,Object>
	* @throws
	* @author：Tao.xu 
	* @date：2017年12月25日 下午4:27:00      
	*/
	public List<AlarmTypeAndLevEntity> findByCusNumberAndType(AlarmTypeAndLevEntity entity);

}
