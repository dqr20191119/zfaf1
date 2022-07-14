package com.cesgroup.prison.djwg.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.djwg.entity.DjwgrywhEntity;

public interface DjwgrywhService extends IBaseCRUDService<DjwgrywhEntity, String> {

	public DjwgrywhEntity getById(String id);

	public Page<DjwgrywhEntity> findList(DjwgrywhEntity djwgrywhEntity, PageRequest pageRequest);

	public List<DjwgrywhEntity> findAllList(DjwgrywhEntity djwgrywhEntity); 
	
	public void saveOrUpdate(DjwgrywhEntity djwgrywhEntity) throws Exception;

	public void deleteByIds(String idList);
	
	public Map<String,Object> getDjwg();
	
	
}
