package com.cesgroup.prison.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.entity.CommonEvidenceEntity;

public interface CommonEvidenceService extends IBaseCRUDService<CommonEvidenceEntity, String> {
	
	public CommonEvidenceEntity getById(String id);

	public Page<CommonEvidenceEntity> findList(CommonEvidenceEntity commonEvidenceEntity, PageRequest pageRequest);

	public List<CommonEvidenceEntity> findAllList(CommonEvidenceEntity commonEvidenceEntity);
	
	public void saveOrUpdate(CommonEvidenceEntity commonEvidenceEntity);
	
	public void deleteByIds(String ids);
}
