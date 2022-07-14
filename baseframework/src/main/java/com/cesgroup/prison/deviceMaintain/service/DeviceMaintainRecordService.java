package com.cesgroup.prison.deviceMaintain.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainRecordEntity;

/**      
* @projectName：alarmSystem   
* @ClassName：DeviceMaintainRecordService   
* @Description：   维修记录
* @author：Tao.xu   
* @date：2018年2月28日 上午10:06:17   
* @version        
*/
public interface DeviceMaintainRecordService extends IBaseCRUDService<DeviceMaintainRecordEntity, String> {

	Page<Map<String, Object>> listAll(DeviceMaintainRecordEntity entity, Pageable pageable);

	void addRecordInfo(List<Map<String, Object>> devices) throws Exception;

	void updateRecordInfo(DeviceMaintainRecordEntity entity) throws Exception;

	void deleteByIds(List<String> ids);

	DeviceMaintainRecordEntity findById(String id);

	List<Map<String, Object>> findDeviceList(String type, String cusNumber, String aleraId);

	Page<Map<String, Object>> findYnSbyc(PageRequest pageRequest);
}
