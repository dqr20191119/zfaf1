package com.cesgroup.prison.xxhj.znys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.znys.entity.ZnysEntity;

public interface ZnysService extends IBaseCRUDService<ZnysEntity, String> {

	public Page<ZnysEntity> findList(ZnysEntity ZnysEntity,PageRequest pageRequest);

	public Page<ZnysEntity> searchSwdbPage(ZnysEntity ZnysEntity,PageRequest pageRequest);
}
