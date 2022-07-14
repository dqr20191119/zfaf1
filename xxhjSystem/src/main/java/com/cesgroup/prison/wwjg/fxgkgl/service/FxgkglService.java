package com.cesgroup.prison.wwjg.fxgkgl.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.fxgkgl.entity.FxgkglEntity;

public interface FxgkglService extends IBaseCRUDService<FxgkglEntity, String> {
	

	public FxgkglEntity getById(String id);

	public Page<FxgkglEntity> findList(FxgkglEntity PgtjglEntity, PageRequest pageRequest);

	public List<FxgkglEntity> findAllList(FxgkglEntity PgtjglEntity);
	
	public void saveOrUpdate(FxgkglEntity FxgkglEntity) throws Exception;

	public void deleteByIds(String idList);

	public FxgkglEntity getByCode(String code);
	
	public FxgkglEntity getByFxcjId(String id);
	
	public Page<Map<String,Object>> findListWdgz(Pageable pageable);
}
