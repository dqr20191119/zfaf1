package com.cesgroup.prison.yjct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.YabzEntity;

public interface YabzService extends IBaseCRUDService<YabzEntity, String> {
	
	public YabzEntity getById(String id);

	public Page<YabzEntity> findList(YabzEntity yabzEntity, PageRequest pageRequest);

	public List<YabzEntity> findAllList(YabzEntity yabzEntity);
	
	public YabzEntity saveOrUpdate(YabzEntity yabzEntity);
	
	public void saveOrUpdateGzzInfo(YabzEntity yabzEntity);
	
	public String saveOrUpdateCzffInfo(YabzEntity yabzEntity);

	public String saveOrUpdatePlanAction(YabzEntity yabzEntity) throws Exception;
	
	public void deleteByIds(String ids);

	public void updatePlanStatus(YabzEntity yabzEntity);

	public void copyYaxx(YabzEntity yabzEntity) throws Exception;
	
	public String saveOrUpdatePlanActionRecord(YabzEntity yabzEntity) throws Exception;
}
