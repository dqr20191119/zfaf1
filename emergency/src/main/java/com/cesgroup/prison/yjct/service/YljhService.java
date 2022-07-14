package com.cesgroup.prison.yjct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.YljhEntity;

public interface YljhService extends IBaseCRUDService<YljhEntity, String> {
	
	public YljhEntity getById(YljhEntity yljhEntity);

	public Page<YljhEntity> findList(YljhEntity yljhEntity, PageRequest pageRequest);

	public List<YljhEntity> findAllList(YljhEntity yljhEntity);
	
	public void saveOrUpdate(YljhEntity yljhEntity);

	public void deleteByIds(String ids);

	public void updateStatusById(YljhEntity yljhEntity);
}
