package com.cesgroup.prison.wwjg.sjfwgl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.sjfwgl.entity.SjfwglEntity;

public interface SjfwglService extends IBaseCRUDService<SjfwglEntity, String> {
	

	public SjfwglEntity getById(String id);

	public Page<SjfwglEntity> findList(SjfwglEntity SjfwglEntity, PageRequest pageRequest);

	public List<SjfwglEntity> findAllList(SjfwglEntity SjfwglEntity);
	
	public void saveOrUpdate(SjfwglEntity SjfwglEntity) throws Exception;

	public void deleteByIds(String idList);

	public SjfwglEntity getByCode(String code);
}
