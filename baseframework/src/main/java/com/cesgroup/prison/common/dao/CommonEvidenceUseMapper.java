package com.cesgroup.prison.common.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.CommonEvidenceUseEntity;

public interface CommonEvidenceUseMapper extends BaseDao<CommonEvidenceUseEntity, String> {

	public CommonEvidenceUseEntity getById(String id);

	public Page<CommonEvidenceUseEntity> findList(CommonEvidenceUseEntity commonEvidenceUseEntity, PageRequest pageRequest);

	public List<CommonEvidenceUseEntity> findAllList(CommonEvidenceUseEntity commonEvidenceUseEntity); 
}
