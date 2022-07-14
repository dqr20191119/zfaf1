package com.cesgroup.prison.zbgl.gwgl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.gwgl.entity.GwglEntity;

public interface GwglService extends IBaseCRUDService<GwglEntity, String> {
	
	public GwglEntity getById(String id);

	public Page<GwglEntity> findList(GwglEntity gwglEntity, PageRequest pageRequest);

	public List<GwglEntity> findAllList(GwglEntity gwglEntity);
	
	public void saveOrUpdate(GwglEntity gwglEntity);

	public void deleteById(String id);

}
