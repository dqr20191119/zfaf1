package com.cesgroup.prison.wwjg.fxdjwh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.fxdjwh.entity.FxdjwhEntity;

public interface FxdjwhService extends IBaseCRUDService<FxdjwhEntity, String> {
	

	public FxdjwhEntity getById(String id);

	public Page<FxdjwhEntity> findList(FxdjwhEntity FxdjwhEntity, PageRequest pageRequest);

	public List<FxdjwhEntity> findAllList(FxdjwhEntity FxdjwhEntity);
	
	public void saveOrUpdate(FxdjwhEntity FxdjwhEntity) throws Exception;

	public void deleteByIds(String idList);

	public FxdjwhEntity getByCode(String code);
}
