package com.cesgroup.prison.wwjg.sjlywh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.sjlywh.entity.SjlywhEntity;

public interface SjlywhService extends IBaseCRUDService<SjlywhEntity, String> {
	

	public SjlywhEntity getById(String id);

	public Page<SjlywhEntity> findList(SjlywhEntity sjlywhEntity, PageRequest pageRequest);

	public List<SjlywhEntity> findAllList(SjlywhEntity sjlywhEntity);
	
	public void saveOrUpdate(SjlywhEntity sjlywhEntity) throws Exception;

	public void deleteByIds(String idList);
	
	public SjlywhEntity getByCode(String code);

}
