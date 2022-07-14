package com.cesgroup.prison.wwjg.pgtjgl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.pgtjgl.entity.PgtjglEntity;

public interface PgtjglService extends IBaseCRUDService<PgtjglEntity, String> {
	

	public PgtjglEntity getById(String id);

	public Page<PgtjglEntity> findList(PgtjglEntity PgtjglEntity, PageRequest pageRequest);

	public List<PgtjglEntity> findAllList(PgtjglEntity PgtjglEntity);
	
	public void saveOrUpdate(PgtjglEntity PgtjglEntity) throws Exception;

	public void deleteByIds(String idList);

	public PgtjglEntity getByCode(String code);
}
