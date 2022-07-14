package com.cesgroup.prison.yjct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.FgglEntity;

public interface FgglService extends IBaseCRUDService<FgglEntity, String> {
	
	public FgglEntity getById(String id);

	public Page<FgglEntity> findList(FgglEntity fgglEntity, PageRequest pageRequest);

	public List<FgglEntity> findAllList(FgglEntity fgglEntity);
	
	public void saveOrUpdate(FgglEntity fgglEntity);

	public void deleteByIds(String ids);	
}
