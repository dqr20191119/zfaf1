package com.cesgroup.prison.djwg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.djwg.entity.DjwgzzwhEntity;

public  interface DjwgzzwhService extends IBaseCRUDService<DjwgzzwhEntity, String> {

	public DjwgzzwhEntity getById(String id);

	public Page<DjwgzzwhEntity> findList(DjwgzzwhEntity djwgzzwhEntity, PageRequest pageRequest);

	public List<DjwgzzwhEntity> findAllList(DjwgzzwhEntity djwgzzwhEntity); 

	public DjwgzzwhEntity getByCode(String zzCode);
	
	public void saveOrUpdate(DjwgzzwhEntity djwgzzwhEntity) throws Exception;

	public void deleteByIds(String idList);
}
