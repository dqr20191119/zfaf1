package com.cesgroup.prison.wwjg.fxdgl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.fxdgl.entity.FxdglEntity;

public interface FxdglService extends IBaseCRUDService<FxdglEntity, String> {
	

	public FxdglEntity getById(String id);

	public Page<FxdglEntity> findList(FxdglEntity FxdglEntity, PageRequest pageRequest);

	public List<FxdglEntity> findAllList(FxdglEntity FxdglEntity);
	
	public void saveOrUpdate(FxdglEntity FxdglEntity) throws Exception;

	public void deleteByIds(String idList);
	
	public FxdglEntity getByCode(String code);

}
