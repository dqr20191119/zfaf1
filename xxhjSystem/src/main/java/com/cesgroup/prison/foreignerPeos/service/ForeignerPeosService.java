package com.cesgroup.prison.foreignerPeos.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.foreignerPeos.entity.ForeignerPeosEntity;

public interface ForeignerPeosService extends IBaseCRUDService<ForeignerPeosEntity, String> {
	public Integer searchCounts();

	public Page<ForeignerPeosEntity> findList(Map<String, Object> paramMap, PageRequest pageRequest);

}
