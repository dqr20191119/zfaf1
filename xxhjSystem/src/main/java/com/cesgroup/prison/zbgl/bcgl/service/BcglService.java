package com.cesgroup.prison.zbgl.bcgl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.bcgl.entity.BcglEntity;

public interface BcglService extends IBaseCRUDService<BcglEntity, String> {
	
	public BcglEntity getById(String id);

	public Page<BcglEntity> findList(BcglEntity bcglEntity, PageRequest pageRequest);

	public List<BcglEntity> findAllList(BcglEntity bcglEntity);
	
	public void saveOrUpdate(BcglEntity bcglEntity);

	public void deleteById(String id);
}
