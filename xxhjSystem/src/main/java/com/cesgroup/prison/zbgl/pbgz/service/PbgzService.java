package com.cesgroup.prison.zbgl.pbgz.service;


import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.pbgz.entity.PbgzEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface PbgzService extends IBaseCRUDService<PbgzEntity, String> {
	
	public PbgzEntity getById(String id);

	public Page<PbgzEntity> findList(PbgzEntity pbgzEntity, PageRequest pageRequest);

	public List<PbgzEntity> findAllList(PbgzEntity pbgzEntity);
	
	public void saveOrUpdate(PbgzEntity pbgzEntity);

	public void deleteById(String id);
}
