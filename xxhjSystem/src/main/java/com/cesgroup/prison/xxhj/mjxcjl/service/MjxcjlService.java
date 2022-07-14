package com.cesgroup.prison.xxhj.mjxcjl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.mjxcjl.entity.MjxcjlEntity;

public interface MjxcjlService extends IBaseCRUDService<MjxcjlEntity, String> {

	public Page<MjxcjlEntity> findList(MjxcjlEntity MjxcjlEntity,PageRequest pageRequest);

	public Page<MjxcjlEntity> searchSwdbPage(MjxcjlEntity MjxcjlEntity,PageRequest pageRequest);
}
