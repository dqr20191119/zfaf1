package com.cesgroup.prison.wwjg.qzdjwh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wwjg.qzdjwh.entity.QzdjwhEntity;

public interface QzdjwhService extends IBaseCRUDService<QzdjwhEntity, String> {
	

	public QzdjwhEntity getById(String id);

	public Page<QzdjwhEntity> findList(QzdjwhEntity qzdjwhEntity, PageRequest pageRequest);

	public List<QzdjwhEntity> findAllList(QzdjwhEntity qzdjwhEntity);
	
	public void saveOrUpdate(QzdjwhEntity qzdjwhEntity) throws Exception;

	public void deleteByIds(String idList);
	
	public QzdjwhEntity getByCode(String code);

}
