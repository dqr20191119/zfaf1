package com.cesgroup.prison.common.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.CommonEvidenceEntity;

public interface CommonEvidenceMapper extends BaseDao<CommonEvidenceEntity, String> {

	public CommonEvidenceEntity getById(String id);

	public Page<CommonEvidenceEntity> findList(CommonEvidenceEntity commonEvidenceEntity, PageRequest pageRequest);

	public List<CommonEvidenceEntity> findAllList(CommonEvidenceEntity commonEvidenceEntity); 
}
