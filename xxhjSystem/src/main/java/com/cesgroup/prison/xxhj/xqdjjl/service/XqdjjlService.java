package com.cesgroup.prison.xxhj.xqdjjl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.xqdjjl.entity.XqdjjlEntity;

public interface XqdjjlService extends IBaseCRUDService<XqdjjlEntity, String> {

	public Page<XqdjjlEntity> findList(XqdjjlEntity XqdjjlEntity,PageRequest pageRequest);

	public Page<XqdjjlEntity> searchSwdbPage(XqdjjlEntity XqdjjlEntity,PageRequest pageRequest);
}
