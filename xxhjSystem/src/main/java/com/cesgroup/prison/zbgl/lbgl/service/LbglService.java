package com.cesgroup.prison.zbgl.lbgl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.lbgl.entity.LbglEntity;

public interface LbglService extends IBaseCRUDService<LbglEntity, String> {
	
	public LbglEntity getById(String id);

	public Page<LbglEntity> findList(LbglEntity lbglEntity, PageRequest pageRequest);

	public List<LbglEntity> findAllList(LbglEntity lbglEntity);
	
	public void saveOrUpdate(LbglEntity lbglEntity) throws Exception;

	public void deleteByIds(String idList);
}
