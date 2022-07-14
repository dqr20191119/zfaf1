package com.cesgroup.prison.yjct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.GzzglEntity;

public interface GzzglService extends IBaseCRUDService<GzzglEntity, String> {
	
	public GzzglEntity getById(String id);

	public Page<GzzglEntity> findList(GzzglEntity gzzglEntity, PageRequest pageRequest);

	public List<GzzglEntity> findAllList(GzzglEntity gzzglEntity);
	
	public void saveOrUpdate(GzzglEntity gzzglEntity);

	public void deleteByIds(String ids);
}
