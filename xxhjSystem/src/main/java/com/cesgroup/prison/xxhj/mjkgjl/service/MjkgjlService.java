package com.cesgroup.prison.xxhj.mjkgjl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.mjkgjl.entity.MjkgjlEntity;

public interface MjkgjlService extends IBaseCRUDService<MjkgjlEntity, String> {

	public Page<MjkgjlEntity> findList(MjkgjlEntity mjkgjlEntity,PageRequest pageRequest);

	public Page<MjkgjlEntity> searchSwdbPage(MjkgjlEntity mjkgjlEntity,PageRequest pageRequest);
}
