package com.cesgroup.prison.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.entity.CommonEvidenceEntity;
import com.cesgroup.prison.common.entity.CommonEvidenceUseEntity;

public interface CommonEvidenceUseService extends IBaseCRUDService<CommonEvidenceUseEntity, String> {
	
	public CommonEvidenceUseEntity getById(String id);

	public Page<CommonEvidenceUseEntity> findList(CommonEvidenceUseEntity commonEvidenceUseEntity, PageRequest pageRequest);

	public List<CommonEvidenceUseEntity> findAllList(CommonEvidenceUseEntity commonEvidenceUseEntity);
	
	public void saveOrUpdate(CommonEvidenceUseEntity commonEvidenceUseEntity);
	
	public void deleteByIds(String ids);
}
