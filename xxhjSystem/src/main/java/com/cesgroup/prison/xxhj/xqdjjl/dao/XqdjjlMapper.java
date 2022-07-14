package com.cesgroup.prison.xxhj.xqdjjl.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.xqdjjl.entity.XqdjjlEntity;

public interface XqdjjlMapper extends BaseDao<XqdjjlEntity, String> {
	public Page<XqdjjlEntity> findList(XqdjjlEntity XqdjjlEntity, PageRequest pageRequest);

	public Page<XqdjjlEntity> searchSwdbPage(XqdjjlEntity XqdjjlEntity, PageRequest pageRequest);
}