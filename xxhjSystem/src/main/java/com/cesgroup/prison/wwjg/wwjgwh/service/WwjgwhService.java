package com.cesgroup.prison.wwjg.wwjgwh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.wwjgwh.entity.WwjgwhEntity;

public interface WwjgwhService extends IBaseCRUDService<WwjgwhEntity, String> {
	

	public WwjgwhEntity getById(String id);

	public Page<WwjgwhEntity> findList(WwjgwhEntity wwjgwhEntity, PageRequest pageRequest);

	public List<WwjgwhEntity> findAllList(WwjgwhEntity wwjgwhEntity);
	
	public void saveOrUpdate(WwjgwhEntity wwjgwhEntity) throws Exception;

	public void deleteByIds(String idList);
	
	public WwjgwhEntity getByCode(String code);

}
