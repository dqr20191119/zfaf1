package com.cesgroup.prison.xxhj.znys.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.znys.entity.ZnysEntity;

public interface ZnysMapper extends BaseDao<ZnysEntity, String> {
	public Page<ZnysEntity> findList(ZnysEntity ZnysEntity, PageRequest pageRequest);

	public Page<ZnysEntity> searchSwdbPage(ZnysEntity ZnysEntity,PageRequest pageRequest);
}