package com.cesgroup.prison.deviceMaintain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainRecordEntity;

/**      
* @projectName：alarmSystem   
* @ClassName：DeviceMaintainRecordMapper   
* @Description：   维修记录
* @author：Tao.xu   
* @date：2018年2月28日 上午10:02:27   
* @version        
*/
public interface DeviceMaintainRecordMapper extends BaseDao<DeviceMaintainRecordEntity, String> {

	/**
	* @methodName: listAll
	* @Description: TODO
	* @param map
	* @param pageable
	* @return Page<Map<String,Object>>
	* @throws  
	*/
	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	/**
	* @methodName: deleteById
	* @Description:根据id删记录
	* @param list void    
	* @throws
	*/
	void deleteByIds(List<String> ids);

	/**
	* @methodName: updateRecordInfo
	* @Description: 更新报备记录
	* @param entity void    
	* @throws
	*/
	void updateRecordInfo(Map<String, Object> map);

	List<Map<String, Object>> findDeviceList_sxt(Map<String, Object> map);

	List<Map<String, Object>> findDeviceList_djfj(Map<String, Object> map);

	List<Map<String, Object>> findDeviceList_bjq(Map<String, Object> map);

	List<Map<String, Object>> findDeviceList_mj(Map<String, Object> map);

	List<Map<String, Object>> findDeviceList_gb(Map<String, Object> map);

	List<Map<String, Object>> findDeviceList_djzj(Map<String, Object> map);

	List<Map<String, Object>> findDeviceList_gydw(Map<String, Object> map);
	
	Page<Map<String, Object>> selectYnSbyc(Pageable pageable);
	
}
