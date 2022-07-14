package com.cesgroup.prison.yjct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.WzglEntity;

public interface WzglService extends IBaseCRUDService<WzglEntity, String> {
	
	public WzglEntity getById(String id);

	public Page<WzglEntity> findList(WzglEntity wzglEntity, PageRequest pageRequest);

	public List<WzglEntity> findAllList(WzglEntity wzglEntity);
	
	public void saveOrUpdate(WzglEntity wzglEntity);

	public void deleteByIds(String ids);
}
