package com.cesgroup.prison.yjct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.CzffglEntity;

public interface CzffglService extends IBaseCRUDService<CzffglEntity, String> {
	
	public CzffglEntity getById(String id);

	public Page<CzffglEntity> findList(CzffglEntity czffglEntity, PageRequest pageRequest);

	public List<CzffglEntity> findAllList(CzffglEntity czffglEntity);
	
	public void saveOrUpdate(CzffglEntity czffglEntity);

	public void deleteByIds(String ids);
}
