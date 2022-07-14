package com.cesgroup.prison.yjct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.ZjglEntity;

public interface ZjglService extends IBaseCRUDService<ZjglEntity, String> {
	
	public ZjglEntity getById(String id);

	public Page<ZjglEntity> findList(ZjglEntity zjglEntity, PageRequest pageRequest);

	public List<ZjglEntity> findAllList(ZjglEntity zjglEntity);
	
	public void saveOrUpdate(ZjglEntity zjglEntity);

	public void deleteByIds(String ids);
}
